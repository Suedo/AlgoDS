package generalworks.parallelism;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class DummyProcess {
    public static int loops = 200;
    private static int idx = 0;
    private static long sleepTimeMs = 1000;


    private static Logger log = LogManager.getLogger(DummyProcess.class);
    private static ExecutorService customPool = Executors.newFixedThreadPool(loops);

    public static void seq() {
        LongSummaryStatistics stats = LongStream.range(0, loops).map(DummyProcess::slowNetworkCall).summaryStatistics();
        System.out.println(stats);
    }

    public static void ps_IO() {
        Instant start = Instant.now();
        LongSummaryStatistics stats = LongStream.range(0, loops).parallel().map(DummyProcess::slowNetworkCall).summaryStatistics();
        log.info("ps@IO completed in :: {}, summaryStats :: {} ", Duration.between(start, Instant.now()).toMillis(), stats);
    }

    public static void ps_CPU() {
        Instant start = Instant.now();
        LongSummaryStatistics stats = LongStream.range(0, loops).parallel().map(DummyProcess::slowCPUCall).summaryStatistics();
        log.info("ps@CPU completed in :: {}, summaryStats :: {} ", Duration.between(start, Instant.now()).toMillis(), stats);
    }

    /**
     * Fastest, with one caveat: if any exception, will have to wait till the end,
     * but imo, assuming Exceptions are rare, the happy path gains should amortize the occasional exceptional slowness :)
     */
    public static void cf_IO() {
        Instant start = Instant.now();

        List<CompletableFuture<Long>> collect = LongStream.range(0, loops).boxed()
                .map(number -> CompletableFuture.supplyAsync(() -> DummyProcess.slowNetworkCall(number), customPool))
                .collect(Collectors.toList());

        LongSummaryStatistics stats = collect.stream().map(CompletableFuture::join).mapToLong(Long::longValue).summaryStatistics();

        log.info("cf@IO completed in :: {}, summaryStats :: {} ", Duration.between(start, Instant.now()).toMillis(), stats);
    }

    public static void cf_CPU() {
        Instant start = Instant.now();

        List<CompletableFuture<Long>> collect = LongStream.range(0, loops).boxed()
                .map(number -> CompletableFuture.supplyAsync(() -> DummyProcess.slowCPUCall(number), customPool))
                .collect(Collectors.toList());

        LongSummaryStatistics stats = collect.stream().map(CompletableFuture::join).mapToLong(Long::longValue).summaryStatistics();

        log.info("cf@CPU completed in :: {}, summaryStats :: {} ", Duration.between(start, Instant.now()).toMillis(), stats);
    }

    /**
     * Streams process things lazily, which means that the pipeline would submit each operation and immediately wait
     * for a result to arrive before starting remaining operations, which could quickly end up with a processing time
     * longer than processing the collection sequentially
     */
    public static void cf_directJoin() {
        Instant start = Instant.now();

        LongSummaryStatistics stats = LongStream.range(0, loops).boxed()
                .map(number -> CompletableFuture.supplyAsync(() -> DummyProcess.slowNetworkCall(number), customPool))
                .map(CompletableFuture::join)
                .mapToLong(Long::longValue)
                .summaryStatistics();

        log.info("cf_directJoin completed in :: {}, summaryStats :: {} ", Duration.between(start, Instant.now()).toMillis(), stats);
    }

    /**
     * Batching of parallelStream observed
     */
    public static void cfps_directJoin() {
        Instant start = Instant.now();
        LongSummaryStatistics stats = LongStream.range(0, loops).boxed()
                .parallel()
                .map(number -> CompletableFuture.supplyAsync(() -> DummyProcess.slowNetworkCall(number), customPool))
                .map(CompletableFuture::join)
                .mapToLong(Long::longValue).summaryStatistics();

        log.info("cfps_directJoin completed in :: {}, summaryStats :: {} ", Duration.between(start, Instant.now()).toMillis(), stats);
    }

    /**
     * Similar to native cf() , with some slight overhead of parallization
     */
    public static void cfps() {
        Instant start = Instant.now();
        List<CompletableFuture<Long>> collect = LongStream.range(0, loops).boxed()
                .parallel()
                .map(number -> CompletableFuture.supplyAsync(() -> DummyProcess.slowNetworkCall(number), customPool))
                .collect(Collectors.toList());

        LongSummaryStatistics stats = collect.stream().map(CompletableFuture::join).mapToLong(Long::longValue).summaryStatistics();

        log.info("cfps completed in :: {}, summaryStats :: {} ", Duration.between(start, Instant.now()).toMillis(), stats);
    }

    public static Long slowNetworkCall(Long i) {
        Instant start = Instant.now();
        log.info(" {} going to sleep. poolsize: {}", i, ForkJoinPool.commonPool().getPoolSize());
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTimeMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(" {} woke up..", i);
        return Duration.between(start, Instant.now()).toMillis();
    }

    public static Long slowCPUCall(Long i) {
        log.info(" slowCPUCall#{}. poolsize: {}", i, ForkJoinPool.commonPool().getPoolSize());
        Instant start = Instant.now();
        String hex = "";
        do {
            String rs = RandomStringUtils.random(50);
            hex = DigestUtils.sha256Hex(rs);
        } while (!hex.startsWith("000"));
        long millis = Duration.between(start, Instant.now()).toMillis();
        log.info("{}: hex = {}", i, hex);
        return millis;
    }

    public static void combine() {
        ExecutorService cpuBoundPool = Executors.newFixedThreadPool(ForkJoinPool.commonPool().getParallelism());
        Instant start = Instant.now();

        // not the best example, as each call is just one iteration
        // ideally, the calls here would encapsulate multiple steps, that would run in the provided pool
        Function<Long, CompletableFuture<Long>> future = number -> CompletableFuture.supplyAsync(() -> slowNetworkCall(number), customPool)
                .thenApplyAsync(val -> slowCPUCall(number), cpuBoundPool)
                .exceptionally(e -> {
                    e.printStackTrace();
                    return 0L;
                });

        List<CompletableFuture<Long>> collect = LongStream.range(0, loops).boxed()
                .map(future)
                .collect(Collectors.toList());

        LongSummaryStatistics stats = collect.stream().map(CompletableFuture::join).mapToLong(Long::longValue).summaryStatistics();

        log.info("combine completed in :: {}, summaryStats :: {} ", Duration.between(start, Instant.now()).toMillis(), stats);

    }

    public static void main(String[] args) {
        System.out.println("CPU Core: " + Runtime.getRuntime().availableProcessors());
        System.out.println("CommonPool Parallelism: " + ForkJoinPool.commonPool().getParallelism());
        System.out.println("CommonPool Common Parallelism: " + ForkJoinPool.getCommonPoolParallelism());

        System.out.println("========== IO BOUND ==========");
        DummyProcess.cf_IO();
        DummyProcess.ps_IO();

        System.out.println("========== CPU BOUND ==========");
        DummyProcess.cf_CPU();
        DummyProcess.ps_CPU();

        System.out.println("========== Combined ==========");
        DummyProcess.combine();

        System.exit(0);
    }
}


// https://4comprehension.com/parallel-collection-processing-1/
// https://github.com/pivovarit/parallel-collectors
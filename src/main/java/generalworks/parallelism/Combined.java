//package generalworks.parallelism;
//
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.time.Duration;
//import java.time.Instant;
//import java.util.LongSummaryStatistics;
//import java.util.concurrent.*;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//import java.util.stream.LongStream;
//
//public class Combined {
//    public static int loops = 200;
//    private static int idx = 0;
//    private static long sleepTimeMs = 1000;
//
//
//    private static Logger log = LogManager.getLogger(DummyProcess.class);
//    private static ExecutorService cpuPool = Executors.newFixedThreadPool(ForkJoinPool.commonPool().getPoolSize());
//    private static ExecutorService ioPool = Executors.newFixedThreadPool(loops);
//
//    public static Function<Long, CompletableFuture<Long>> cpuFunc = val -> CompletableFuture.supplyAsync(() -> cpu(val), cpuPool);
//    public static Function<Long, CompletableFuture<Long>> ioFunc = val -> CompletableFuture.supplyAsync(() -> cpu(val), ioPool);
//
//    public static Long multi(Function<Long, CompletableFuture<Long>> process) {
//        Instant start = Instant.now();
//
//        LongSummaryStatistics stats = LongStream.range(0, loops).boxed()
//                .map(process)
//                .collect(Collectors.toList())
//                .stream()
//                .map(CompletableFuture::join)
//                .mapToLong(Long::longValue)
//                .summaryStatistics();
//        Duration time = Duration.between(start, Instant.now());
//        log.info("multi completed in :: {}, summaryStats :: {} ", time.toMillis(), stats);
//        return time.toMillis();
//
//    }
//
//    private static Long io(Long i) {
//        Instant start = Instant.now();
//        log.info("#io{} connecting..", i);
//        try {
//            TimeUnit.MILLISECONDS.sleep(sleepTimeMs);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info("#io{} completed.", i);
//        return Duration.between(start, Instant.now()).toMillis();
//    }
//
//    public static Long cpu(Long i) {
//        Instant start = Instant.now();
//        log.info("#cpu{} starting..", i);
//        String hex = "";
//        do {
//            String rs = RandomStringUtils.random(50);
//            hex = DigestUtils.sha256Hex(rs);
//        } while (!hex.startsWith("000"));
//        log.info("#cpu{} done.", i);
//        return Duration.between(start, Instant.now()).toMillis();
//    }
//
//    public static void main(String[] args) {
//
//
//        CompletableFuture.supplyAsync(() -> multi(ioFunc)).thenApply(val -> multi(cpuFunc))
//    }
//}

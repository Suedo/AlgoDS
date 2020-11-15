package generalworks;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This code aims to find hash of files recursively under a root directory
 * upto a provided depth. This is a java implementation of the filehash.sh script
 */
public class FileHash {
  
  public static String getHexHash(Path path) {
//    https://www.baeldung.com/java-md5
    MessageDigest md5 = null;
    try {
      md5 = MessageDigest.getInstance("MD5");
      md5.update(Files.readAllBytes(path));
    } catch (Exception e) {
      e.printStackTrace();
    }
    byte[] digest = md5.digest();
    String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
    return String.format("%s,%s", hash, path.toAbsolutePath());
  }
  
  public static Optional<List<String>> diveParallel(String rootDir, int depth) {
//    https://www.baeldung.com/java-list-directory-files
    List<String> op = null;
    try (Stream<Path> stream = Files.walk(Path.of(rootDir), depth)) {
      Instant start = Instant.now();
      op = stream.parallel().filter(path -> !Files.isDirectory(path)) // skip directories
          .map(FileHash::getHexHash).collect(Collectors.toList());
      Instant end = Instant.now();
      System.out.println("Parallel dive completed in (ms): " + Duration.between(start, end).toMillis());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.ofNullable(op);
  }
  
  public static Optional<List<String>> diveSequential(String rootDir, int depth) {
//    https://www.baeldung.com/java-list-directory-files
    List<String> op = null;
    try (Stream<Path> stream = Files.walk(Path.of(rootDir), depth)) {
      Instant start = Instant.now();
      op = stream.filter(path -> !Files.isDirectory(path)) // skip directories
          .map(FileHash::getHexHash).collect(Collectors.toList());
      Instant end = Instant.now();
      System.out.println("Sequential dive completed in (ms): " + Duration.between(start, end).toMillis());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.ofNullable(op);
  }
  
  public static void writeToFile(String opFilePath, Iterable<? extends CharSequence> lines) {
    try {
      Files.write(Path.of(opFilePath), lines);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  
  public static void main(String[] args) {
    
    String dir = "F:\\Pujo18";
    String opFile = "src/main/java/generalworks/op.csv";
    int depth = 5;
//    List<String> strings = FileHash.diveParallel(dir, depth).orElseGet(() -> new ArrayList<String>());
    FileHash.diveSequential(dir, depth);
    FileHash.diveSequential(dir, depth);
    FileHash.diveSequential(dir, depth);
    FileHash.diveParallel(dir, depth);
    FileHash.diveParallel(dir, depth);
    FileHash.diveParallel(dir, depth);

//    writeToFile(opFile, strings);
  }
}

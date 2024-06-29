package generalworks.FileHashing;

import jakarta.xml.bind.DatatypeConverter;
import org.apache.commons.codec.digest.MurmurHash3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This code aims to find hash of files recursively under a root directory
 * upto a provided depth. This is a java implementation of the filehash.sh script
 */
public class FileHash {
  
  public static void deleteFiles() throws IOException {
    String path = "src/main/java/generalworks/delfiles.txt";
    for (String f : Files.readAllLines(Path.of(path))) {
      System.out.println("deleting: " + f);
      Files.delete(Path.of(f));
    }
  }
  
  public static String getMurmer128(Path path) {
    int hash = 0;
    try {
      hash = MurmurHash3.hash32x86(Files.readAllBytes(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return String.valueOf(hash);
  }
  
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
  
  public static List<Path> getAllFilesUnder(String rootDir, int depth) {
    List<Path> paths = new ArrayList<>();
    try {
      paths = Files.walk(Path.of(rootDir), depth)
          .filter(path -> !Files.isDirectory(path)) // skip directories
          .collect(Collectors.toList());  // https://stackoverflow.com/a/64848609/2715083
    } catch (IOException e) {
      e.printStackTrace();
    }
    return paths;
  }
  
  public static List<String> diveParallel(List<Path> paths) {
//    https://www.baeldung.com/java-list-directory-files
    Instant start = Instant.now();
    List<String> op = paths.stream().parallel()
        .map(FileHash::getHexHash)
        .collect(Collectors.toList());
    Instant end = Instant.now();
    System.out.println("Parallel dive completed in (ms): " + Duration.between(start, end).toMillis());
    
    return op;
  }
  
  public static List<String> diveParallelMurmer3(List<Path> paths) {
    //    https://www.baeldung.com/java-list-directory-files
    Instant start = Instant.now();
    List<String> op = paths.stream().parallel()
        .map(FileHash::getMurmer128)
        .collect(Collectors.toList());
    Instant end = Instant.now();
    System.out.println("Parallel Murmer dive completed in (ms): " + Duration.between(start, end).toMillis());
    
    return op;
  }
  
  public static List<String> diveSequential(List<Path> paths) {
 
    Instant start = Instant.now();
    List<String> op = paths.stream().map(FileHash::getHexHash).collect(Collectors.toList());
    Instant end = Instant.now();
    System.out.println("Sequential dive completed in (ms): " + Duration.between(start, end).toMillis());
    
    return op;
  }
  
  public static void writeToFile(String opFilePath, Iterable<? extends CharSequence> lines) {
    try {
      Files.write(Path.of(opFilePath), lines);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  
  public static void main(String[] args) {

//    try {
//      FileHash.deleteFiles();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
    
    String warmup1 = "src/main/java";
    String warmup2 = "Files";
    String warmup3 = "C:\\Users\\Somjit\\Pictures\\wallpapers";
    String dir = "G:\\a6000_New";
    String opFile = "src/main/java/generalworks/op.csv";
    int depth = 8;
  
    List<Path> warmup1Files = getAllFilesUnder(warmup1, depth);
    List<Path> warmup2Files = getAllFilesUnder(warmup2, depth);
    List<Path> warmup3Files = getAllFilesUnder(warmup3, depth);
    List<Path> mainFiles = getAllFilesUnder(dir, depth);
    System.out.println("Main Files Count: " + mainFiles.size());
  
    FileHash.diveSequential(warmup1Files);
    FileHash.diveSequential(warmup1Files);
    
    FileHash.diveParallel(warmup1Files);
    FileHash.diveParallel(warmup1Files);
    
    FileHash.diveParallel(warmup2Files);
    FileHash.diveParallel(warmup2Files);
  
    FileHash.diveParallel(warmup3Files);
    FileHash.diveParallel(warmup3Files);
    
    List<String> strings = FileHash.diveParallel(mainFiles); // 277118 ms
    FileHash.diveParallelMurmer3(mainFiles); // 246748 ms
//    writeToFile(opFile, strings);
  }
}

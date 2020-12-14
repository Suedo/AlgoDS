package generalworks.RegexExtractors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexOnFileContent {
  
  public static List<Path> getAllFilesUnder(String rootDir, int depth, String fileType) {
    List<Path> paths = new ArrayList<>();
    try {
      paths = Files.walk(Path.of(rootDir), depth)
          .filter(path -> !Files.isDirectory(path)) // skip directories
          .filter(path -> path.toString().endsWith(fileType))
          .collect(Collectors.toList());  // https://stackoverflow.com/a/64848609/2715083
    } catch (IOException e) {
      e.printStackTrace();
    }
    return paths;
  }
  
  public static Set<String> extractTokensFromFile(Path filepath, String regex) {
    Pattern p = Pattern.compile(regex);
    try {
      Set<String> tokens = Files.readAllLines(filepath)
          .stream()
          .map(line -> getTokensFromLine(p, line))
          .flatMap(Collection::stream)
          .collect(Collectors.toSet());
      return tokens;
    } catch (IOException e) {
      e.printStackTrace();
      return new HashSet<>();
    }
  }
  
  private static List<String> getTokensFromLine(Pattern p, String line) {
    List<String> matchList = new ArrayList<>();
    Matcher matcher = p.matcher(line);
    while (matcher.find()) {
      matchList.add(matcher.group());
    }
    return matchList;
  }
  
  public static void main(String[] args) {
    // give root path
    String rootDir = "E:\\codeD\\GitHub\\Suedo\\AlgoDS";
    String annotationRegex = "@\\w+(\\(.+\\))?";
    // find all files from under that path
    List<Path> allFilesUnder = getAllFilesUnder(rootDir, 15, "java");
    // loop through each file, passing regex
    Set<String> allAnnotations = allFilesUnder.stream()
        .map(path -> extractTokensFromFile(path, annotationRegex))
        .flatMap(Collection::stream)
        .collect(Collectors.toSet());
    System.out.println("Annotations found: \n");
    allAnnotations.forEach(System.out::println);
  }
  
}

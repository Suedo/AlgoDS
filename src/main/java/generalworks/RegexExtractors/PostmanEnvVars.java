package generalworks.RegexExtractors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PostmanEnvVars {
  
  public static Set<String> extractTokens(String filepath) {
    String regex = "\\{\\{[\\w -_]+\\}\\}";  // {{[\w -_]+}}
    Pattern p = Pattern.compile(regex);
    
    try {
      Set<String> tokens = Files.readAllLines(Path.of(filepath))
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
  
  /**
   * This works with tokens that are not separated by spaces.
   * Valid token format: {{auth_token}}
   * Invalid token format: {{auth token}}
   *
   * @return Set of all found tokens
   */
  public static Set<String> extractTokensNonSpaced(String filepath) {
    try {
      Set<String> tokens = Files.readAllLines(Path.of(filepath))
          .stream()
          .map(line -> Arrays.asList(line.trim().split(" +")))
          .flatMap(Collection::stream)
          .filter(word -> word.startsWith("{{") && word.endsWith("}}"))
          .collect(Collectors.toSet());
      
      return tokens;
      
    } catch (IOException e) {
      e.printStackTrace();
      return new HashSet<>(); // empty
    }
  }
  
  
  public static void main(String[] args) {
    String filepath = "src/main/java/generalworks/RegexExtractors/ip.txt";
    System.out.println(PostmanEnvVars.extractTokens(filepath));
  }
  
}

package generalworks.Ranges;

import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class StreamRange {
  
  static void print(IntStream is) {
    is.forEach(i -> System.out.print(i + " "));
    System.out.println("");
  }
  
  public static void main(String[] args) {
    // ascending order of ints, gap = 1
    print(IntStream.range(1, 10)); // 1 to 9
    print(IntStream.rangeClosed(1,10)); // 1 to 10
  
    IntPredicate greaterThan80 = i -> i >= 80;
    IntUnaryOperator decrementByTwo = i -> i - 2;
    print(IntStream.iterate(90, greaterThan80, decrementByTwo)); // 90 88 86 84 82 80
  }
}

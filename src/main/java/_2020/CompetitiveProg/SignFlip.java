package _2020.CompetitiveProg;

import java.util.Arrays;
import java.util.List;

public class SignFlip {

    String str = "5 + (1 - 2) - (1 - 4) - (5 - 3 + 1)";
    // expected Output: 5 + 1 - 2 - 1 + 4 - 5 + 3 - 1

    boolean insideBrackets = false;
    String lastOuterSymbol = null;
    List<String> numbers = Arrays.asList("1234567890".split(""));


    public void process() {
        for (String c : str.split("")) {
            if (c.equals(" ")) System.out.print(c);
            else if (c.equals("+") || c.equals("-")) {
                if (!insideBrackets) {
                    lastOuterSymbol = c;
                    System.out.print(c);
                } else {
                    // inside bracket
                    // check outer symbol, if "-" , reverse current symbol
                    if (lastOuterSymbol.equals("-")) {
                        // flip the symbol
                        System.out.print(c.equals("+") ? "-" : "+");
                    } else {
                        // last outer symbol was "+", no need to reverse
                        System.out.print(c);
                    }
                }
            } else if (c.equals("(")) {
                insideBrackets = true;
            } else if (c.equals(")")) {
                insideBrackets = false;
                lastOuterSymbol = null;
            } else if (numbers.contains(c)) {
                System.out.print(c);
            }
        }
    }

    public static void main(String[] args) {
        new SignFlip().process();
        // 5 + 1 - 2 - 1 + 4 - 5 + 3 - 1
    }
}

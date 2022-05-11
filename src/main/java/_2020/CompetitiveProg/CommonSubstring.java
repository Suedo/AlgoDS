package _2020.CompetitiveProg;

public class CommonSubstring {
    
    static boolean hasCommonSubString(String a, String b) {
        
        char[] carr1 = a.toCharArray();
        char[] carr2 = b.toCharArray();
        for (int i = 0; i < carr1.length; i++) {
            for (int j = 0; j < carr2.length; j++) {
                if (carr1[i] == carr2[j]) return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println(hasCommonSubString("hello", "o"));
    }
    
}

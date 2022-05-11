package _2020.LeetcodeMonthlyChallenge;

public class AugW1 {
    
    public boolean isPalindrome(String s) {
        String test = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        System.out.println(test);
        char[] chars = test.toCharArray();
        for (int i = 0, j = chars.length - 1; i < chars.length; i++, j--) {
            if (chars[i] != chars[j]) return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        
        String s = "A man, a plan, a canal: Panama";
        System.out.println(new AugW1().isPalindrome(s));
    }
}

package CompetitiveProg;

import java.util.*;

public class CharGroups {

    private String[] ip;
    private ArrayList<String> op;
    private HashMap<String, Integer> charMap;

    Comparator<String> sorter = (String a, String b) -> {
        if (a.charAt(2) == b.charAt(2)) {
            return a.charAt(0) - b.charAt(0);
        }
        return a.charAt(2) - b.charAt(2);
    };

    private void createOP(Map<String, Integer> map) {
        map.forEach((k, v) -> op.add(k + ":" + v));
        System.out.println("before sort: " + op);
        op.sort(sorter);
        System.out.println("after sort: " + op);
    }

    public CharGroups(String ip) {
        this.ip = ip.split("");
        charMap = new HashMap<>();
        op = new ArrayList<>();
    }

    public Map<String, Integer> process() {
        Arrays.sort(this.ip);
        String prev = this.ip[0];
        int count = 1;
        for (int i = 1; i < this.ip.length; i++) {
            if (!this.ip[i].equals(prev)) {
                this.charMap.putIfAbsent(prev, count);
                count = 1; // reset
            } else {
                count++;
            }
            prev = this.ip[i];
        }
        this.charMap.putIfAbsent(prev, count); // to get the last one
        createOP(this.charMap);
        return this.charMap;
    }

    public static void main(String[] args) {
        String ip = "aeeadabdde";
        new CharGroups(ip).process();
    }

    /*
    before sort: [a:3, b:1, d:3, e:3]
    after sort: [b:1, a:3, d:3, e:3]
     */

}

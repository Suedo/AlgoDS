package CompetitiveProg;

public class StringModulo {

    String ip;
    int divider;

    StringModulo(String ip, int div) {
        this.ip = ip;
        this.divider = div;
    }

    public void process() {
        int ipLen = ip.length();
        int remainder = ipLen % divider;
        int distanceCovered = 0;

        for (int i = 0; (i * divider) + remainder < ipLen; i += 1) {
            System.out.println(this.ip.substring(i * divider, (i + 1) * divider));
            distanceCovered += divider;
        }

        System.out.println(this.ip.substring(distanceCovered));
    }

    public static void main(String[] args) {

        new StringModulo("abcdefghij", 3).process();
    }
}

package _2020.CompetitiveProg;


// https://www.hackerrank.com/challenges/new-year-chaos/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays&h_r=next-challenge&h_v=zen
public class CheckQueueBribes {

    // Swap comes from a Bribe
    static void minimumBribes(int[] q) {
        boolean tooChaotic = false;
        int totalBribes = 0;
        int maxAllowedBribes = 2;

        for (int i = 0; i < q.length; i++) {
            int sortedPosition = q[i] - 1;
            int currentPosition = i;
            if (sortedPosition - currentPosition > maxAllowedBribes) {
                System.out.println("Too chaotic");
                return;
            }
            // num of bribes given is number of overtakes done
            for (int j = Math.max(0, sortedPosition - 2); j < i; j++) {
                if (q[j] > q[i]) totalBribes++;
            }
        }
        System.out.println(totalBribes);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 5, 3, 7, 8, 6, 4};
        minimumBribes(arr);
    }
}

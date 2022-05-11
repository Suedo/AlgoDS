package _2020.CompetitiveProg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// courtesy Boosan
// HackerRank @ Masai
public class LebooBuckets {
    
    public static int minTimeToTransferLebooBuckets(int n, int m, int[] bucketWeights, int[] maxCapPerPerson) {
        int result = 0;
        Arrays.sort(bucketWeights);// Sort both the arrays
        Arrays.sort(maxCapPerPerson);
        int i = 0, j = 0;
        while (i < n) {// Iterate Till all buckets are transferred
            while (maxCapPerPerson[j] < bucketWeights[i]) {
                j++;// Reject the weak people.
            }
            int bucketsCarriedInThisTrip = m - j;// No. of strong persons left will be equal to the
            // bucketsCarriedInThisTrip
            i += bucketsCarriedInThisTrip;// Move bucket pointer
            result++;// It is all done in one trip parallel.
            if ((n - i) > 0) {// If there are still buckets left
                result++;// add 1 hour for the Return trip
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        try(BufferedReader r = new BufferedReader(new InputStreamReader(System.in))){
            String[] ip1 = r.readLine().trim().split(" +");
            int n = Integer.parseInt(ip1[0]), m = Integer.parseInt(ip1[1]);
    
            String[] bws = r.readLine().trim().split(" +");
            int[] bucketWeights = new int[bws.length];
            for (int i = 0; i < bws.length; i++) {
                bucketWeights[i] = Integer.parseInt(bws[i]);
            }
            
            String[] caps = r.readLine().split(" +");
            int[] personCapacities = new int[caps.length];
            for (int i = 0; i < caps.length; i++) {
                personCapacities[i] = Integer.parseInt(caps[i]);
            }
    
            int result = minTimeToTransferLebooBuckets(n, m, bucketWeights, personCapacities);
            System.out.println(result);
    
        }catch (IOException e) {
            System.out.println("Error");
        }
    }
}
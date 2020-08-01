package dont.checkin;

import java.util.*;

public class LebooBucketTransferGreedy {

	public static int minTimeToTransferLebooBuckets(int n, int m, int[] bucketWeights, int[] maxCapPerPerson) {
		int result = 0;
		TreeSet<Integer> wts = new TreeSet<>();
		PriorityQueue<Integer> peopleQ = new PriorityQueue<>();
		// Sort both the array, convert them to comfortable sorted- data structure.
		for (int i : bucketWeights) wts.add(i);
		for (int i : maxCapPerPerson) peopleQ.add(i);

		int i = 1;
		while (!wts.isEmpty()) {
			System.out.println("Start of trip! " + i++);
			for (Iterator<Integer> peopleItr = peopleQ.iterator(); peopleItr.hasNext();) {
				int personCapacity = peopleItr.next();
				if (wts.floor(personCapacity) == null) {
					peopleItr.remove();
					continue;
				}
				int w = wts.floor(personCapacity);
				wts.remove(w);
				System.out.println("Person " + personCapacity + " is assigned to  " + w);
			}

			result++;
			if (!wts.isEmpty()) {
				System.out.println("Has more buckets to be transferred, returning back and adding 1 to time");
				result++;
			}
			System.out.println("Trip completion \n");
		}
		return result;
	}

	public static void main(String[] args) {
		int[] bucketWeights = { 29, 79, 203, 212, 221, 275, 279, 343, 358, 372, 482, 502, 504, 594, 632, 642, 674, 679,
				799, 823, 882 };
		int[] maxCapPerPerson = { 29, 56, 87, 114, 120, 120, 480, 979 };
		int res = minTimeToTransferLebooBuckets(bucketWeights.length, maxCapPerPerson.length, bucketWeights,
				maxCapPerPerson);
		System.out.println(res);
	}

}

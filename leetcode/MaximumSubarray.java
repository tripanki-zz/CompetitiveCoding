/**
 * Created by Ankit Tripathi.
 * For further queries/suggestions please mail at ankittripathi0000@gmail.com.
 *
 * @see https://oj.leetcode.com/problems/maximum-subarray/
 */
public class MaximumSubarray {

	public static void main(String[] args) {
		int a[] = {1,0,0,0,55};
		System.out.println("Maximum sum is : " + maxSubArray(a));
	}

	public static int maxSubArray(int[] A) {
		int length = A.length;
		int sum = A[0];
		int auxSum, i;
		
		i = 0;
		
		while ((i < length) && (A[i] <= 0)) {
			if (sum < A[i]) {
				sum = A[i];
			}
			i++;
		}
		
		if (i == length) {
			return sum;
		}
		
		sum = auxSum = A[i++];
		while (i < length) {
			if (A[i] < 0) {
				if (auxSum > sum) {
					sum = auxSum;
				}
			}
			
			auxSum += A[i];
			
			if (auxSum < 0) {
				auxSum = 0;
			}
			
			i++;
		}
		
		if (sum < auxSum) {
			sum = auxSum;
		}
		
        return sum;
    }
}

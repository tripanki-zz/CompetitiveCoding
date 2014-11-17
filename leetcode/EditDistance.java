package oj.leetcode;

/**
 * @author ankit2478
 * @see https://oj.leetcode.com/problems/edit-distance/
 */
public class EditDistance {
	public static void main(String[] args) {
		System.out.println(minDistance("zoologicoarchaeologist", "zoogeologist"));
	}

	public static int minDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
		int[][] mat = new int[len1 + 1][len2 + 1];
		int k;
		
		for (int i = 1; i <= len1; i++) {
			mat[i][0] = i;
		}
		
		for (int i = 1; i <= len2; i++) {
			mat[0][i] = i;
		}
		mat[0][0] = 0;
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (word1.charAt(i-1) == word2.charAt(j-1)) {
					mat[i][j] = mat[i-1][j-1];
				} else {
					k = minimum(mat[i-1][j-1], mat[i-1][j], mat[i][j-1]);
					mat[i][j] = k + 1;
				}
			}
		}
        return mat[len1][len2];
    }

	static int minimum(int a, int b, int c) {
		if (a > b) {
			a = b;
		}
		if (a > c) {
			a = c;
		}

		return a;
	}
}

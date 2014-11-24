/**
 * Created by Ankit Tripathi.
 * For further queries/suggestions please mail at ankittripathi0000@gmail.com.
 *
 * @see https://oj.leetcode.com/problems/interleaving-string/
 */
public class InterleavingStrings {
	public static void main(String[] args) {
		System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
		// "aadbbbaccc"
	}

	public static boolean isInterleave(String s1, String s2, String s3) {
		int len1 = s1.length();
		int len2 = s2.length();
		int len3 = s3.length();

		if (len1 == 0) {
			if (len2 > 0) {
				if (s2.equals(s3)) {
					return true;
				} else {
					return false;
				}
			} else {
				if (len3 == 0) {
					return true;
				} else {
					return false;
				}
			}
		} else if (len2 == 0) {
			if (len1 > 0) {
				if (s1.equals(s3)) {
					return true;
				} else {
					return false;
				}
			} else {
				if (len3 == 0) {
					return true;
				} else {
					return false;
				}
			}
		} else if (len3 == 0) {
			return false;
		}

		if (len1 + len2 != len3) {
			return false;
		}

		boolean[][] mat = new boolean[len1 + 1][len2 + 1];

		for (int i = 0; i <= len1; i++) {
			for (int j = 0; j <= len2; j++) {

				if (i == 0 && j == 0) {
					mat[i][j] = true;
				} else if (i == 0 && s2.charAt(j - 1) == s3.charAt(j - 1)) {
					mat[i][j] = mat[i][j - 1];
				} else if (j == 0 && s1.charAt(i - 1) == s3.charAt(i - 1)) {
					mat[i][j] = mat[i - 1][j];
				} else if ((i-1 >= 0) && s1.charAt(i - 1) == s3.charAt(i + j - 1)
						&& s2.charAt(j - 1) != s3.charAt(i + j - 1)) {
					mat[i][j] = mat[i - 1][j];
				} else if ((j-1 >= 0) && s2.charAt(j - 1) == s3.charAt(i + j - 1)
						&& s1.charAt(i - 1) != s3.charAt(i + j - 1)) {
					mat[i][j] = mat[i][j - 1];
				} else if ((i-1 >= 0) && (j-1 >= 0) && s2.charAt(j - 1) == s3.charAt(i + j - 1)
						&& s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
					mat[i][j] = mat[i][j - 1] || mat[i - 1][j];
				}
			}
		}
		return mat[len1][len2];
	}

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Ankit Tripathi on 25-09-2014.
 * For further queries/suggestions please mail at ankittripathi0000@gmail.com.
 *
 * https://oj.leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {
    private static char[] arr = new char[1001];
    private static int length;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCases = Integer.parseInt(br.readLine());

        while (testCases-- > 0) {
            System.out.println(longestPalindrome(br.readLine()));
        }
    }

    public static String longestPalindrome(String s) {
        int maxCount, count;
        int back, front, even, odd;
        int i, j, startingPoint;

        length = s.length();
        arr = s.toCharArray();
        i = count = maxCount = startingPoint = 0;

        if (length == 1) {
            return s;
        }
        while (i < length - 2) {
            even = odd = 0;

            if (arr[i] == arr[i+1]) {
                back = i - 1;
                front = i + 2;
                even = 2;
                while ((back >= 0) && (front < length)
                        && (s.charAt(back) == s.charAt(front))) {
                    back--;
                    front++;
                    even += 2;
                }

                if (maxCount < even) {
                    maxCount = even;
                    startingPoint = back + 1;
                }
            }

            if (arr[i] == arr[i+2]) {
                back = i - 1;
                front = i + 3;
                odd = 3;
                while ((back >= 0) && (front < length)
                        && (s.charAt(back) == s.charAt(front))) {
                    back--;
                    front++;
                    odd += 2;
                }

                if (maxCount < odd) {
                    maxCount = odd;
                    startingPoint = back + 1;
                }
            }
            i++;
        }

        if ((maxCount == 0) &&(arr[length-2] == arr[length-1])) {
            maxCount = 2;
            startingPoint = length - 2;
        }

        return s.substring(startingPoint, (startingPoint + maxCount));
    }
}

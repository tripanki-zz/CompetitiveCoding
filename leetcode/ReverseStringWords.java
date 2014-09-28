import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Ankit Tripathi on 28-09-2014.
 * For further queries/suggestions please mail at ankittripathi0000@gmail.com.
 *
 * https://oj.leetcode.com/problems/reverse-words-in-a-string/
 */
public class ReverseStringWords {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(reverseWords(br.readLine()));
    }
    public static String reverseWords(String s) {
        String toReturn = "";
        int i, j;
        boolean insideWord = false;
        s = s.trim();
        s = s.replaceAll("\\s+", " ");

        i = j = s.length() - 1;

        while (i >= 0) {
            if (insideWord && s.charAt(i) == ' ') {
                toReturn = toReturn + " " + s.substring(i+1, j+1);
                insideWord = false;
            } else if (insideWord == false) {
                j = i;
                insideWord = true;
            }

            i--;
        }

        toReturn = toReturn + " " + s.substring(0, j+1);
        toReturn = toReturn.trim();
        return toReturn;
    }
}

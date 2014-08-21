import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Ankit Tripathi on 11-08-2014.
 * For further queries/suggestions please mail at ankittripathi0000@gmail.com.
 *	http://www.spoj.com/problems/CMEXPR/
 */
public class CMEXPR {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            input = br.readLine();
            StringBuilder stringBuilder = new StringBuilder(input);
            System.out.println(compress(stringBuilder));
        }
    }

    public static StringBuilder compress(StringBuilder expression) {
        int i = 0;
        char c;
        char preOperator, postOperator;
        boolean removeBracket = false;

        while (i < expression.length()) {
            preOperator = postOperator = '\0';
            while (i < expression.length() && (c = expression.charAt(i)) != '(') {
                i++;
            }

            if (i == expression.length()) {
                return expression;
            }

            if (i != 0) {
                preOperator = expression.charAt(i - 1);
            }

            Stats stats = expressionCompressor(expression, i + 1);

            if (preOperator != '\0') {
                if (preOperator == '+') {
                    removeBracket = true;
                } else if (preOperator == '-' && stats.add == false && stats.subtract == false) {
                    removeBracket = true;
                } else if (preOperator == '*' && stats.add == false && stats.subtract == false) {
                    removeBracket = true;
                } else if (stats.add == false && stats.subtract == false && stats.multiply == false && stats.divide == false) {
                    removeBracket = true;
                } else {
                    removeBracket = false;
                }
            }

            if ((stats.i < (expression.length() - 1))
                    && ((expression.charAt(stats.i + 1) == '+') || (expression.charAt(stats.i + 1) == '-') ||
                    (expression.charAt(stats.i + 1) == '*') || (expression.charAt(stats.i + 1) == '/'))) {
                postOperator = expression.charAt(stats.i + 1);
            }

            if ((preOperator == '\0') || (removeBracket == true)) {
                if (postOperator != '\0') {
                    if (postOperator == '+' || postOperator == '-') {
                        removeBracket = true;
                    } else if (postOperator == '*' && stats.add == false && stats.subtract == false) {
                        removeBracket = true;
                    } else if (postOperator == '/' && stats.add == false && stats.subtract == false) {
                        removeBracket = true;
                    } else if (stats.add == false && stats.subtract == false && stats.multiply == false && stats.divide == false) {
                        removeBracket = true;
                    } else {
                        removeBracket = false;
                    }
                }
            }
            if (((preOperator == '\0') && (postOperator == '\0')) || (removeBracket == true)) {
                expression.deleteCharAt(i);
                expression.deleteCharAt(stats.i - 1);
                i = stats.i - 1;
            } else {
                i = stats.i + 1;
            }
        }

        return expression;
    }
//  (a+(b/j*k+(m-r))-p)
    public static Stats expressionCompressor(StringBuilder expression, int i) {
        Stats stats = new Stats();
        char preOperator, postOperator;
        boolean removeBracket = false;

        while (expression.charAt(i) != ')') {
            switch (expression.charAt(i)) {
                case '+' : stats.add = true;
                    break;
                case '-' : stats.subtract = true;
                    break;
                case '*' : stats.multiply = true;
                    break;
                case '/' : stats.divide = true;
                    break;
                case '(' :
                    preOperator = postOperator = '\0';
                    if ((i != 0)
                            && ((expression.charAt(i - 1) == '+') || (expression.charAt(i - 1) == '-') ||
                                (expression.charAt(i - 1) == '*') || (expression.charAt(i - 1) == '/'))) {
                        preOperator = expression.charAt(i - 1);
                    }

                    Stats currentStat = expressionCompressor(expression, i + 1);

                    if (preOperator != '\0') {
                        if (preOperator == '+') {
                            removeBracket = true;
                        } else if (preOperator == '-' && currentStat.add == false && currentStat.subtract == false) {
                            removeBracket = true;
                        } else if (preOperator == '*' && currentStat.add == false && currentStat.subtract == false) {
                            removeBracket = true;
                        } else if (currentStat.add == false && currentStat.subtract == false && currentStat.multiply == false && currentStat.divide == false) {
                            removeBracket = true;
                        } else {
                            removeBracket = false;
                        }
                    }

                    if ((currentStat.i < (expression.length() - 1))
                            && ((expression.charAt(currentStat.i + 1) == '+') || (expression.charAt(currentStat.i + 1) == '-') ||
                            (expression.charAt(currentStat.i + 1) == '*') || (expression.charAt(currentStat.i + 1) == '/'))) {
                        postOperator = expression.charAt(currentStat.i + 1);
                    }
                    if ((preOperator == '\0') || (removeBracket == true) ) {
                        if (postOperator != '\0') {
                            if (postOperator == '+' || postOperator == '-') {
                                removeBracket = true;
                            } else if (postOperator == '*' && currentStat.add == false && currentStat.subtract == false) {
                                removeBracket = true;
                            } else if (postOperator == '/' && currentStat.add == false && currentStat.subtract == false) {
                                removeBracket = true;
                            } else if (currentStat.add == false && currentStat.subtract == false && currentStat.multiply == false && currentStat.divide == false) {
                                removeBracket = true;
                            } else {
                                removeBracket = false;
                            }
                        }
                    }
                    if (((preOperator == '\0') && (postOperator == '\0')) || (removeBracket == true)) {
                        expression.deleteCharAt(i);
                        expression.deleteCharAt(currentStat.i - 1);

                        stats.add = stats.add ? true : currentStat.add;
                        stats.subtract = stats.subtract ? true : currentStat.subtract;
                        stats.multiply = stats.multiply ? true : currentStat.multiply;
                        stats.divide = stats.divide ? true : currentStat.divide;

                        i = currentStat.i - 2;
                    } else {
                        i = currentStat.i;
                    }
                    break;
            }
            i++;
        }
        stats.i = i;

        return stats;
    }

    static class Stats {
        private int i;
        private boolean add;
        private boolean subtract;
        private boolean multiply;
        private boolean divide;

        public Stats() {
            i = 0;
            add = subtract = multiply = divide = false;
        }
    }

}

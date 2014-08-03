import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
/**
 * http://www.spoj.com/problems/ONP/
 * @author aka_lone_wolf <ankittripathi0000@gmail.com>
 */
public class ONP {

    private static Stack<StringBuilder> stack = new Stack<StringBuilder>();
    private static Stack<Character> operands = new Stack<Character>();
    //private final Character[] operatorPriority = {'+', '-', '*', '\\', '^'};
    private static Map<Character, Integer> operatorPriority = new HashMap<Character, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        Character stackTopOperator = null;
        StringBuilder stackTopString = null;
        char currentCharacter;
        StringBuilder operator1, operator2;
        int testCase = Integer.parseInt(br.readLine());
        int len = 0;
        int i = 0;

        //      Initializing operator priority map.
        operatorPriority.put('(', 0);
        operatorPriority.put('+', 1);
        operatorPriority.put('-', 2);
        operatorPriority.put('*', 3);
        operatorPriority.put('/', 4);
        operatorPriority.put('^', 5);

        while (testCase-- > 0) {
            input = br.readLine();
            stack.clear();
            operands.clear();
            i = 0;
            len = input.length();

            while (i < len) {
                currentCharacter = input.charAt(i);
                if (currentCharacter == ')') {
                    // when bracket is closed.
                    operator2 = stack.pop();
                    while (operands.peek() != '(') {
                        operator1 = stack.pop();
                        operator2 = operator1.append(operator2).append(operands.pop());
                    }
                    stack.push(operator2);
                    operands.pop();
                } else if ((currentCharacter >= 'a') && (currentCharacter <= 'z')){
                    // If c is a variable, then simply push it.
                    stack.push(new StringBuilder(currentCharacter+""));
                } else if (currentCharacter == '(') {      // If c is an operator then check if its priority is push it in the
                        operands.push(currentCharacter);
                } else if (!operands.empty()) {
                    stackTopOperator = operands.peek();
                    operator2 = stack.pop();

                    while ((stackTopOperator != '(') && (isAtHighPriority(stackTopOperator, currentCharacter))) {
                        operator1 = stack.pop();
                        operator2 = operator1.append(operator2).append(stackTopOperator);

                        if (stack.empty()) {
                            break;
                        } else {
                            operands.pop();
                            if (operands.empty()) {
                                stack.push(operator2);
                                break;
                            } else {
                                stackTopOperator = operands.peek();
                            }
                        }
                    }
                    stack.push(operator2);
                    operands.push(currentCharacter);
                } else {
                    operands.push(currentCharacter);
                }
                i++;
            }

            if (stack.size() > 1) {
                operator2 = stack.pop();
                while(!operands.empty()) {
                    operator1 = stack.pop();
                    operator2 = operator1.append(operator2).append(operands.pop());
                }
                stack.push(operator2);
            }

            System.out.println(stack.peek());
        }

    }

    private static boolean isAtHighPriority(Character first, Character second) {
        if (operatorPriority.get(first) > operatorPriority.get(second)) {
            return true;
        } else {
            return false;
        }
    }
}


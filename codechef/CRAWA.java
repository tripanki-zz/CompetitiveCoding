import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by Ankit Tripathi on 22-08-2014.
 * For further queries/suggestions please mail at ankittripathi0000@gmail.com.
 * http://www.codechef.com/problems/CRAWA
 */
public class CRAWA {
    final private static int BUFFER_SIZE = 1 << 17;
    private static byte[] buffer = new byte[BUFFER_SIZE];
    private static DataInputStream in = new DataInputStream(new DataInputStream(System.in));
    private static int currentPointer = 0;
    private static int bytesRead = 0;

    public static void main(String[] args) throws IOException {
        int test, x, y;
        boolean isXEven, isYEven;
        test = getInteger();

        while (test-- > 0) {
            x = getInteger();
            y = getInteger();

            isXEven = ((x & 1) == 0) ? true : false;
            isYEven = ((y & 1) == 0) ? true : false;
            if (x == 0) {
                if (y == 1 || y == -1) {
                    System.out.println("NO");
                    continue;
                } else if (y == 0) {
                    System.out.println("YES");
                    continue;
                }
            }

            if (x >= 0) {       // x is positive
                if (y >= 0) {           // 1st quadrant (+, +)
                    if (!isXEven) {     // X is odd
                        if (y <= (x + 1)) {
                            System.out.println("YES");
                        } else if (isYEven) {
                            System.out.println("YES");
                        } else {
                            System.out.println("NO");
                        }
                    } else {        // X is even
                        if (y <= (x + 1)) {
                            System.out.println("NO");
                        } else if (isYEven) {
                            System.out.println("YES");
                        } else {
                            System.out.println("NO");
                        }
                    }
                } else {                // 4th quadrant (+, -)
                    y = y * -1;
                    if (!isXEven) {     // X is odd
                        if (y < x) {
                            System.out.println("YES");
                        } else if (isYEven) {
                            System.out.println("YES");
                        } else {
                            System.out.println("NO");
                        }
                    } else {        // X is even
                        if (y < x) {
                            System.out.println("NO");
                        } else if (isYEven) {
                            System.out.println("YES");
                        } else {
                            System.out.println("NO");
                        }
                    }
                }
            } else {        // x is negative
                x = x * -1;         // changing x to positive
                if (y >= 0) {           // 2nd quadrant (-, +)
                    if (isXEven) {     // X is even
                        if (y <= x) {
                            System.out.println("YES");
                        } else if (isYEven) {
                            System.out.println("YES");
                        } else {
                            System.out.println("NO");
                        }
                    } else {        // X is odd
                        if (y <= x) {
                            System.out.println("NO");
                        } else if (isYEven) {
                            System.out.println("YES");
                        } else {
                            System.out.println("NO");
                        }
                    }
                } else {                // 3rd quadrant (-, -)
                    y = y * -1;
                    if (y >= 0) {           // 2nd quadrant (-, +)
                        if (isXEven) {     // X is even
                            if (y <= x) {
                                System.out.println("YES");
                            } else if (isYEven) {
                                System.out.println("YES");
                            } else {
                                System.out.println("NO");
                            }
                        } else {        // X is odd
                            if (y <= x) {
                                System.out.println("NO");
                            } else if (isYEven) {
                                System.out.println("YES");
                            } else {
                                System.out.println("NO");
                            }
                        }
                    }
                }
            }

        }
    }

    private static int getInteger() throws IOException {
        int returnValue = 0;
        byte character = read();

        while (character <= ' ') {
            character = read();
        }

        boolean negative = character == '-';

        if (negative) {
            character = read();
        }

        do {
            returnValue = returnValue * 10 + character - '0';
            character = read();
        } while (character > ' ');

        if (negative) {
            return -returnValue;
        }

        return returnValue;
    }

    private static void getInputBytes() throws IOException {
        bytesRead = in.read(buffer, currentPointer = 0, BUFFER_SIZE);

        if (bytesRead == -1) {
            buffer[0] = -1;
        }

    }

    private static byte read() throws IOException {
        if (currentPointer == bytesRead) {
            getInputBytes();
        }

        return buffer[currentPointer++];
    }
}

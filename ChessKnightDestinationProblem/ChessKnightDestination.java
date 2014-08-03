import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Question :  Given a chess board of size N*N, and a knight's initial and final position.
 * We need to calculate the smallest steps that the knight have to move inorder to reach
 * destination iff it is feasible.
 *
 * Created by Ankit Tripathi on 14-07-2014.
 * For further queries/suggestions please mail at ankittripathi0000@gmail.com.
 */
public class ChessKnightDestination {
    private static int[][] chessboard;
    private static int N;
    private static Coordinate start, end;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;

        System.out.println("Enter startX startY endX, endY, N");
        s = br.readLine();

        String[] variables = s.split(" ");

        if (variables.length != 5) {
            return;
        }
        start = new Coordinate(Integer.parseInt(variables[0]), Integer.parseInt(variables[1]));
        end = new Coordinate(Integer.parseInt(variables[2]), Integer.parseInt(variables[3]));

        N = Integer.parseInt(variables[4]);

        chessboard = new int[N][N];
        initChessBoard();

        if (!isValidCoordinates(start) || !isValidCoordinates(end)) {
            return;
        }

        int minHops = minimumHops();

        if (start.equals(end)) {
            System.out.println("Both the coordinates are same, therefor no need to move.");
            return;
        }

        if (minHops == -1) {
            System.out.println("You cannot reach the destination.");
        } else {
            System.out.println("The minimum hops required by knight to reach destination is : " + minHops);
        }
    }

    private static boolean isValidCoordinates(Coordinate point) {
        boolean valid;

        if ( (point.getX() < 0) || (point.getX() >= N) ) {
            valid = false;
        } else if ( (point.getY() < 0) || (point.getY() >= N) ) {
            valid = false;
        } else {
            valid = true;
        }

        return valid;
    }

    private static void initChessBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                chessboard[i][j] = 0;
            }
        }
    }

    //  BFS implementation
    private static int minimumHops() {
        Queue<Coordinate> queue = new LinkedList<Coordinate>();
        Coordinate temp, adjacentPoint;
        int minHops = 0;
        boolean flag = false;
        queue.add(start);
        chessboard[start.getX()][start.getY()] = 0;

        while (!queue.isEmpty()) {
            temp = queue.remove();
            minHops = chessboard[temp.getX()][temp.getY()];

            // For the upper left move
            adjacentPoint = new Coordinate(temp.getX() - 2, temp.getY() - 1);
            if (addToQueue(queue, adjacentPoint, minHops)) {
                flag = true;
                break;
            }

            // For the upper right move
            adjacentPoint = new Coordinate(temp.getX() - 2, temp.getY() + 1);
            if (addToQueue(queue, adjacentPoint, minHops)) {
                flag = true;
                break;
            }

            // For the right upper move
            adjacentPoint = new Coordinate(temp.getX() - 1, temp.getY() + 2);
            if (addToQueue(queue, adjacentPoint, minHops)) {
                flag = true;
                break;
            }

            // For the right down move
            adjacentPoint = new Coordinate(temp.getX() + 1, temp.getY() + 2);
            if (addToQueue(queue, adjacentPoint, minHops)) {
                flag = true;
                break;
            }

            // For the down right move
            adjacentPoint = new Coordinate(temp.getX() + 2, temp.getY() + 1);
            if (addToQueue(queue, adjacentPoint, minHops)) {
                flag = true;
                break;
            }

            // For the down left move
            adjacentPoint = new Coordinate(temp.getX() + 2, temp.getY() - 1);
            if (addToQueue(queue, adjacentPoint, minHops)) {
                flag = true;
                break;
            }

            // For the left down move
            adjacentPoint = new Coordinate(temp.getX() + 1, temp.getY() - 2);
            if (addToQueue(queue, adjacentPoint, minHops)) {
                flag = true;
                break;
            }

            // For the left upper move
            adjacentPoint = new Coordinate(temp.getX() - 1, temp.getY() - 2);
            if (addToQueue(queue, adjacentPoint, minHops)) {
                flag = true;
                break;
            }

        }


        if (flag == true) {
            return minHops + 1;
        } else {
            return  - 1;
        }
    }

    private static boolean addToQueue(Queue<Coordinate> queue, Coordinate adjacentPoint, int minHops) {
        boolean flag = false;

        if (isValidCoordinates(adjacentPoint) && chessboard[adjacentPoint.getX()][adjacentPoint.getY()] == 0) {
            if (adjacentPoint.equals(end)) {
                flag = true;
            } else {
                queue.add(adjacentPoint);
                chessboard[adjacentPoint.getX()][adjacentPoint.getY()] = minHops + 1;
            }
        }

        return flag;
    }
}

/**
 * Created by Ankit Tripathi on 28-07-2014.
 * For further queries/suggestions please mail at ankittripathi0000@gmail.com.
 */
public class Coordinate {
    private int x, y;

    public Coordinate() {
        x = y = 0;
    }

    public Coordinate(int cX, int cY) {
        x = cX;
        y = cY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return (x + ", " + y);
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal;

        if (obj.getClass() != Coordinate.class) {
            equal = false;
        } else {
            Coordinate newCoordinate = (Coordinate) obj;
            if ( (x == newCoordinate.getX()) && (y == newCoordinate.getY()) ) {
                equal = true;
            } else {
                equal = false;
            }
        }

        return equal;
    }
}

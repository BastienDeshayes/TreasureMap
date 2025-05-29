package dataStructure;

import dataObject.Board;

/**
 *
 * @param x horizontal coordinate
 * @param y vertical coordinate
 */
public record Coordinates(int x, int y) implements Comparable<Coordinates> {

    public boolean isInvalid(final Board board) {
        return this.x < 0 || this.y < 0 || this.x >= board.getVerticalLength() || this.y >= board.getVerticalLength();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinates(int x1, int y1))) return false;
        return x == x1 && y == y1;
    }

    @Override
    public int compareTo(Coordinates o) {
        if (this.y == o.y) {
            return Integer.compare(this.x, o.x);
        }
        return Integer.compare(this.y, o.y);
    }
}

package andkantor.f1betting.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.PROPERTY)
public class Point implements Comparable<Point> {

    public static final Point HIT = new Point(5);
    public static final Point NEAR_MISS = new Point(2);
    public static final Point ZERO = new Point(0);

    private int point;

    public Point() {
    }

    public Point(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Point add(Point point) {
        return new Point(this.point + point.point);
    }

    @Override
    public String toString() {
        return "" + point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point1 = (Point) o;

        return point == point1.point;

    }

    @Override
    public int hashCode() {
        return point;
    }

    @Override
    public int compareTo(Point other) {
        return other.point > point
                ? 1
                : other.point == point ? 0 : -1;
    }
}

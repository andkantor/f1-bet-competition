package andkantor.f1betting.model.bet;

public class Point {

    public static final Point HIT = new Point(5);
    public static final Point NEAR_MISS = new Point(2);
    public static final Point ZERO = new Point(0);

    private final int point;

    public Point(int point) {
        this.point = point;
    }

    public Point add(Point point) {
        return new Point(this.point + point.point);
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
}

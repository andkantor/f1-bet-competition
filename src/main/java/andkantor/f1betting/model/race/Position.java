package andkantor.f1betting.model.race;

public class Position {

    private final int position;

    private Position(int position) {
        this.position = position;
    }

    public static Position position(int position) {
        return new Position(position);
    }

    public int difference(Position position) {
        return Math.abs(this.position - position.position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position1 = (Position) o;

        return position == position1.position;

    }

    @Override
    public int hashCode() {
        return position;
    }
}

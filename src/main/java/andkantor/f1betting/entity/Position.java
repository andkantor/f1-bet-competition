package andkantor.f1betting.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.PROPERTY)
public class Position implements Comparable<Position> {

    public static final Position NOT_FINISHED = new Position(-100);

    private static final String NOT_APPLICABLE = "N/A";

    private int position;

    public Position() {
    }

    public Position(int position) {
        this.position = position;
    }

    public static Position createPosition(int position) {
        return new Position(position);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    @Override
    public String toString() {
        return equals(NOT_FINISHED) ? NOT_APPLICABLE : String.valueOf(position);
    }

    @Override
    public int compareTo(Position o) {
        return Integer.compare(position, o.position);
    }
}

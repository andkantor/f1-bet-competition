package andkantor.f1betting.model.race;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.PROPERTY)
public class Position {

    private int position;

    public Position() {
    }

    private Position(int position) {
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
        return String.valueOf(position);
    }
}

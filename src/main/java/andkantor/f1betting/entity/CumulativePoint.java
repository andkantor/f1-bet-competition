package andkantor.f1betting.entity;

import java.math.BigDecimal;

public class CumulativePoint {

    private String username;
    private BigDecimal point;

    public CumulativePoint(String username, BigDecimal point) {
        this.username = username;
        this.point = point;
    }

    public String getUsername() {
        return username;
    }

    public Point getPoint() {
        return new Point(point.intValue());
    }
}

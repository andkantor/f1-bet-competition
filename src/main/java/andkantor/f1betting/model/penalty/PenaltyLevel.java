package andkantor.f1betting.model.penalty;

public enum PenaltyLevel {

    PENALTY_LEVEL_0(-3, -6, -3),
    PENALTY_LEVEL_1(-2, -3, -2),
    PENALTY_LEVEL_2(0, -2, 0),
    PENALTY_LEVEL_ABOVE_2(0, 0, 0);

    public int atPrevious;
    public int onExact;
    public int atNext;

    PenaltyLevel(int atPrevious, int onExact, int atNext) {
        this.atPrevious = atPrevious;
        this.onExact = onExact;
        this.atNext = atNext;
    }

    public static PenaltyLevel penaltyLevel(int level) {
        if (level >= values().length) {
            return PENALTY_LEVEL_ABOVE_2;
        }

        return values()[level];
    }

}

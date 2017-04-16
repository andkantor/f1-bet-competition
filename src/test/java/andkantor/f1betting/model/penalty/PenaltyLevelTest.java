package andkantor.f1betting.model.penalty;

import org.junit.Test;

import static andkantor.f1betting.model.penalty.PenaltyLevel.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PenaltyLevelTest {

    @Test
    public void testPenaltyLevel() {
        assertThat(penaltyLevel(0)).isEqualTo(PENALTY_LEVEL_0);
        assertThat(penaltyLevel(1)).isEqualTo(PENALTY_LEVEL_1);
        assertThat(penaltyLevel(2)).isEqualTo(PENALTY_LEVEL_2);
        assertThat(penaltyLevel(3)).isEqualTo(PENALTY_LEVEL_ABOVE_2);
        assertThat(penaltyLevel(4)).isEqualTo(PENALTY_LEVEL_ABOVE_2);
        assertThat(penaltyLevel(5)).isEqualTo(PENALTY_LEVEL_ABOVE_2);
    }

}
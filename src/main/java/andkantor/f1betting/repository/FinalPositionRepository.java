package andkantor.f1betting.repository;

import andkantor.f1betting.model.race.FinalPosition;
import andkantor.f1betting.model.race.FinalPosition.FinalPositionId;
import andkantor.f1betting.model.race.Race;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FinalPositionRepository extends CrudRepository<FinalPosition, FinalPositionId> {

    List<FinalPosition> findByRace(Race race);

}

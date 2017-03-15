package andkantor.f1betting.repository;

import andkantor.f1betting.entity.Race;
import andkantor.f1betting.entity.RacePoint;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RacePointRepository extends CrudRepository<RacePoint, RacePoint.RacePointId> {

    List<RacePoint> findByRace(Race race);

}

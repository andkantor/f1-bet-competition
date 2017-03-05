package andkantor.f1betting.repository;

import andkantor.f1betting.entity.Driver;
import andkantor.f1betting.entity.FinalPosition;
import andkantor.f1betting.entity.FinalPosition.FinalPositionId;
import andkantor.f1betting.entity.Race;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface FinalPositionRepository extends CrudRepository<FinalPosition, FinalPositionId> {

    List<FinalPosition> findByRace(Race race);

    @Transactional
    void deleteByRace(Race race);

    @Transactional
    void deleteByDriver(Driver driver);
}

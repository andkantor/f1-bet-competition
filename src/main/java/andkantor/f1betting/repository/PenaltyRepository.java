package andkantor.f1betting.repository;

import andkantor.f1betting.entity.Penalty;
import andkantor.f1betting.entity.Race;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface PenaltyRepository extends CrudRepository<Penalty, Penalty.PenaltyId> {

    List<Penalty> findByRace(Race race);

    @Transactional
    void deleteByRace(Race race);
}

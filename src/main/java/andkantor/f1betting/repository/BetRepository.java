package andkantor.f1betting.repository;

import andkantor.f1betting.entity.Bet;
import andkantor.f1betting.entity.FinalPosition;
import andkantor.f1betting.entity.Race;
import andkantor.f1betting.entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface BetRepository extends CrudRepository<Bet, Bet.BetId> {

    List<Bet> findByUserAndRace(User user, Race race);

    @Transactional
    void deleteByUserAndRace(User user, Race race);

}

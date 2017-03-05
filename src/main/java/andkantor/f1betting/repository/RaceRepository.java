package andkantor.f1betting.repository;

import andkantor.f1betting.entity.Race;
import andkantor.f1betting.entity.Season;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RaceRepository extends CrudRepository<Race, Long> {

    List<Race> findBySeason(Season season);

}

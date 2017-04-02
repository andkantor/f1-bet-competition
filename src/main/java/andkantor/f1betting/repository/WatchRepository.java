package andkantor.f1betting.repository;

import andkantor.f1betting.entity.User;
import andkantor.f1betting.entity.Watch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WatchRepository extends CrudRepository<Watch, Watch.WatchId> {

    List<Watch> findByWatcher(User watcher);

}

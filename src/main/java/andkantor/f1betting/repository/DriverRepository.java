package andkantor.f1betting.repository;

import andkantor.f1betting.entity.Driver;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DriverRepository extends CrudRepository<Driver, Long> {

    List<Driver> findByActive(boolean active);

}

package andkantor.f1betting.model.calculator;

import andkantor.f1betting.entity.*;
import andkantor.f1betting.repository.DriverRepository;
import andkantor.f1betting.repository.PenaltyRepository;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class PenaltyProvider {

    private PenaltyRepository penaltyRepository;
    private DriverRepository driverRepository;

    public PenaltyProvider(PenaltyRepository penaltyRepository, DriverRepository driverRepository) {
        this.penaltyRepository = penaltyRepository;
        this.driverRepository = driverRepository;
    }

    public Map<Driver, Map<Position, Point>> getPenaltyMap(Race race) {
        List<Driver> drivers = driverRepository.findByActive(true);
        List<Penalty> penalties = penaltyRepository.findByRace(race);

        return drivers.stream()
                .collect(toMap(
                        driver -> driver,
                        driver -> penalties.stream()
                                .filter(penalty -> penalty.getDriver().equals(driver))
                                .collect(toMap(Penalty::getPosition, Penalty::getPoint))));
    }

}

package andkantor.f1betting.model.race;

import java.time.LocalDateTime;

public class Race {

    private int id;
    private String name;
    private String location;
    private LocalDateTime dateTime;

    public Race(int id, String name, String location, LocalDateTime dateTime) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.dateTime = dateTime;
    }
}

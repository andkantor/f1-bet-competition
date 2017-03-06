package andkantor.f1betting.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "season")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private boolean active;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    public Season() {
        dateCreated = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
}

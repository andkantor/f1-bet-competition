package andkantor.f1betting.model.race;

import javax.persistence.*;

@Entity
@Table(name="season")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

//    private List<Race> races;
//    private List<Racer> racers;
//    private List<Team> teams;


    public Season() {}

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
}

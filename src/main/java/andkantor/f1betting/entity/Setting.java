package andkantor.f1betting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "setting")
public class Setting {

    @Id
    @Column(name="name")
    private String name;

    @Column(name="value")
    private String value;

    public Setting() {
    }

    public Setting(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

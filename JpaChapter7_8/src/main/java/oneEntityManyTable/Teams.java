package oneEntityManyTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Teams {
    @Id
    @Column(name = "TEAM_ID", nullable = false)
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teams{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

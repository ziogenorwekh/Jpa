package useJoinTable;

import javax.persistence.*;

@Entity
public class Locker {
    @Id
    @Column(name = "LOCKER_ID", nullable = false)
    @GeneratedValue
    private Long id;


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Locker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

package jpql;

import javax.persistence.*;

@Entity
public class Member {

    public Member() {

    }
    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }


    @Id
    @Column(name = "MEMBER_ID", nullable = false)
    private String id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

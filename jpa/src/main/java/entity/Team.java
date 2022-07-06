package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
@Entity
public class Team {

    public Team() {

    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;

    // 난 저거에 맵핑이 되어있을 뿐이야
    // 주인은 mappedBy 속성 사용하지 않는다.
    // 주인이 아닌 쪽은 읽기만 가능
    @OneToMany(mappedBy = "team")
    List<Member> members = new ArrayList<>();

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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

}

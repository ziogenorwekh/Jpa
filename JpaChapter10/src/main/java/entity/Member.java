package entity;

import javax.persistence.*;

@Entity
public class Member {

    public Member() {

    }
    public Member(String id, String name,int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }


    @Id
    @Column(name = "MEMBER_ID", nullable = false)
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

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
                ", team=" + team +
                ", age=" + age +
                '}';
    }
}

package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "TEAM_ID",nullable = true)
    private Team team;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

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
        if (this.team != null) {
            this.team.getMembers().remove(this);
        }
        this.team = team;
        this.team.getMembers().add(this);
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
                ", posts=" + posts.size() +
                '}';
    }

}

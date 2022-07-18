package entity;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @Column(name = "POST_ID")
    private String id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", member=" + member +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
        this.member.getPosts().add(this);
    }
}

package useJoinTable;

import javax.persistence.*;

@Entity
public class MemberLocker {
    @Id
//    @GeneratedValue
    @Column(name = "MEMBER_LOCKER_ID")
    private String id;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne
//    @GeneratedValue
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    @Override
    public String toString() {
        return "MemberLocker{" +
                "id=" + id +
                ", member=" + member +
                ", locker=" + locker +
                '}';
    }
}

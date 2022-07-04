package oneEntityManyTable;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Members {
    @Id
    @Column(name = "MEM_ID", nullable = false)
    private String id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID")
    private Teams teams;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "members")
//    @JoinColumn(name = "ORDER_ID")
    private List<Orders> orders = new ArrayList<>();


    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Members{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", teams=" + teams +
                '}';
    }

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

    public Teams getTeams() {
        return teams;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }
}

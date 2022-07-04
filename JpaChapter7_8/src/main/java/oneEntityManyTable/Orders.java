package oneEntityManyTable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEM_ORDERS")
public class Orders  {
    @Id
    @Column(name = "ORDER_ID", nullable = false)
    private String id;

    @Column(name = "ITEM")
    private String name;

    @ManyToOne
    @JoinColumn(name = "MEM_ID")
    private Members members;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Members getMembers() {
        return members;
    }

    public void setMembers(Members members) {
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

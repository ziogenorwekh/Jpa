import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    Long id;

    @Temporal(TemporalType.DATE)
    Date orderDate;

    String status;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (this.user != null) {
            user.getOrdersList().remove(this);
        }
        this.user = user;
        user.getOrdersList().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

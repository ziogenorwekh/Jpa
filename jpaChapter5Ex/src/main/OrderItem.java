import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ITEM_ID")
    Long id;

    int oderPrice;

    int count;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Orders orders;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {

        this.item = item;

        item.getOrderItemList().add(this);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOderPrice() {
        return oderPrice;
    }

    public void setOderPrice(int oderPrice) {
        this.oderPrice = oderPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

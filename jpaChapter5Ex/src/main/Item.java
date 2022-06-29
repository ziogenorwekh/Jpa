import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {

    @Id@GeneratedValue@Column(name = "ITEM_ID")
    Long id;

    int price;

    int stockQuantity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}

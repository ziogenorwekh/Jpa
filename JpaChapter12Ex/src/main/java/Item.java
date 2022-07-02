import javax.persistence.*;

@Entity
// 조인 전략은 엔티티 각각을 모두 테이블로 만들고 자식 테이블이 부모 테이블의
// 기본 키를 받아서 기본 키 + 외래 키로 사용하는 전략이다.
@Inheritance(strategy = InheritanceType.JOINED)
// 부모 클래스에 구분 컬럼을 지정한다. 이 컬럼으로 저장된 자식 테이블을 구분할 수 있다.
@DiscriminatorColumn(name = "DTYPE")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITEM_ID", nullable = false)
    private Long id;

    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

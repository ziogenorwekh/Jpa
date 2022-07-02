import javax.persistence.*;


// 부모 클래스는 테이블과 매핑하지 않고 부모 클래스를 상속받는 자식클래스에게 매핑 정보만 제공하고 싶으면 사용하는 애너테이션
// 밑은 예제
// @MappedSuperclass 는 실제 테이블과 매핑되지 않는다. 단순히 매핑 정보를 상속할 목적으로만 사용된다.
@SuppressWarnings("all")
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

@SuppressWarnings("all")
@Entity
// 부모에게 상속받은 id 속성의 컬럼명을 MEMBER_ID 로 재정의 했다.
@AttributeOverride(name = "id",column = @Column(name = "MEMBER_ID"))
class Member extends BaseEntity {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

@SuppressWarnings("all")
@Entity
class Seller extends BaseEntity {
    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}

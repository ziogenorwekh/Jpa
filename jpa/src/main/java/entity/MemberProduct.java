package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
// 복합 기본 키 매핑 IdClass
// 식별 관계 : 회원상품은 회원과 상품의 기본 키를 받아서 자신의 기본 키로 사용한다.
@IdClass(MemberProductId.class)
public class MemberProduct {
    // 복합 키는 별도의 식별자 클래스로 만들어야 한다.
    // Serializable 을 구현해야 한다.
    // equals 와 hashCode 메서드를 구현해야 한다.
    // 기본 생성자가 있어야 한다.
    // 식별자 클래스는 public 이어야 한다.
    // IdClass 를 사용하는 방법 외에 @EmbeddedId 를 사용하는 방법도 있다.
    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int orderAmount;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}

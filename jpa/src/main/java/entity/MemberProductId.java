package entity;


import java.io.Serializable;

// 회원 상품 식별자 클래스

/***
 * 복합 키는 항상 식별자 클래스를 만들어야 한다.
 * em.find2()를 보면 생성한 식별자 클래스로 엔티티를 조회한다(JpaMain.class).
 */
public class MemberProductId implements Serializable {
    // MemberProduct.member 와 연결
    private String member;
    // MemberProduct.product 와 연결
    private String product;

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

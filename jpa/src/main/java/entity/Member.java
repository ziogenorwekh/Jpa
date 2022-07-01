package entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("All")
@Entity
public class Member {
    // GeneratedValue 에서 GenerationType.AUTO 는 방언에 따라 자동 지정한다.
    // 권장하는 식별자 전략 권장 : Long + 대체키 + 키 생성전략 사용
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    String id;
    // nullable = false 는 Null 값이 있으면 안된다.
    // length = 40 -> Varchar 길이 제한
    @Column(name = "USERNAME", nullable = false, length = 40)
    String name;

    // 날짜 타입 매핑
    @Temporal(TemporalType.DATE)
    Date regDate;

    // Enum 을 쓸거면 무조건 @Enumerated 애너테이션을 사용해야한다.
    // 그리고 현업에서는 무조건 EnumType.STRING 이다.
    @Enumerated(EnumType.STRING)
    MemberType memberType;

//    @Column(name = "TEAM_ID")
//    String teamId;

    // 현업에서는 꼭 LAZY 로딩을 사용하세요.
    // LAZY 는 해당 객체를 사용할 경우에 로딩한다. 지연로딩
    // 가급적이면 지연 로딩을 쓰세요.
    // 페치 조인을 하면 지연 로딩이 발생하지 않는다. 지연 로딩에서 발생하는 문제는 페치 조인을 사용하자.
    // 예시)
//    "select m from Member m join fetch m.team"
    // insertable, updatable 를 false 로 둬서 ReadOnly로 변경할 수 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne(mappedBy = "member")
    private Locker locker;

    // @JoinTable.name : 연결 테이블을 지정한다. 여기서는 MEMBER_PRODUCT 테이블을 선택했다.
    // @JoinTable.joinColumns : 현재 방향인 회원과 매핑할 조인 컬럼 정보를 지정한다.
    // MEMBER_ID 로 지정했다.
    // @JoinTable.inverseJoinColumns : 반대 방향인 상품과 매핑할 조인 컬럼 정보를 지정한다.
    // PRODUCT_ID로 지정했다.
//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT", joinColumns = @JoinColumn(name = "MEMBER_ID"),
//            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
//    private List<Product> products = new ArrayList<>();

//    public List<Product> getProducts() {
//         return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }

    // 대리 키를 사용함으로써 식별 관계에 복합 키를 사용하는 것보다 매핑이 단순해졌다.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    // 다대다 매핑은 실무에서는 잘 사용하지 않으므로(제약 사항이 있기에) OneToMany 로 풀어야 한다.
//    @OneToMany(mappedBy = "member")
//    private List<MemberProduct> memberProducts;

//    public void addProduct(Product product) {
//        products.add(product);
//        product.getMembers().add(this);
//    }

//
//    public List<MemberProduct> getMemberProducts() {
//        return memberProducts;
//    }

//    public void setMemberProducts(List<MemberProduct> memberProducts) {
//        this.memberProducts = memberProducts;
//    }

    public Locker getLocker() {

        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        // 기존 팀과 관계를 제거한다.
        if (this.team != null) {
            this.team.getMembers().remove(this);
        }
        this.team = team;
        // 양방향 관계에서 각각 호출하면 실수할 경우가 있으므로, 하나인 것처럼 사용하는 편이 낫다.
        team.getMembers().add(this);
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
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

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", regDate=" + regDate +
                ", memberType=" + memberType +
                ", team=" + team +
                '}';
    }
}

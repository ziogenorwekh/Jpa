package oneEntityManyTable;

import useJoinTable.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class Chap8Main {

    static Members member;
    static EntityManager em;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lsek8");
        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            save();
            find();
            tx.commit();
        } catch (Exception e) {
            System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
        // 지연 로딩 에러
//        System.out.println("member = " + member);
    }

    public static void save() {
        Members members = new Members();
        members.setId("id");
        members.setName("lsek");

        Teams teams = new Teams();
        teams.setId("team1");
        teams.setName("스프링팀");
        members.setTeams(teams);
        em.persist(teams);
        em.persist(members);

        Orders orders = new Orders();
        orders.setId("감자");
        orders.setName("감자1");
        orders.setMembers(members);
        Orders orders1 = new Orders();
        orders1.setId("쌀");
        orders1.setName("쌀1");
        orders1.setMembers(members);
        em.persist(orders);
        em.persist(orders1);
        // 조회 대상이 영속성 컨텍스트에 이미 있으면 프록시 객체를 사용할 이유가 없으므로, flush 와 clear
        em.flush();
        em.clear();

    }
    public static void find() {

        Members id = em.find(Members.class, "id");
        // teams 는 fetch 가 EAGER 이기에 즉시 로딩
        Teams teams = id.getTeams();
        // orders 는 fetch 가 LAZY 이기에 지연 로딩
        // 컬렉션에는 하나 이상 즉시 로딩하는 것을 권장하지 않는다.
        // 데이터가 많으면 성능 저하를 야기한다.
        List<Orders> orders = id.getOrders();
        System.out.println("orders = " + orders.getClass().getName()); // orders = org.hibernate.collection.internal.PersistentBag
//        System.out.println("id = " + id); 해당 코드는 실제로 팀을 사용하므로, 프록시 초기화
        boolean isLoad = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(orders); // orders 리스트를 실제 사용하지 않았으므로,
        // 초기화 되지 않은 인스턴스는 false 를 반환한다.
        System.out.println("isLoad = " + isLoad); // 지연 로딩
    }
}

package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    static EntityManager em;
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lsek");

        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            save();
            find();
            tx.commit();
        } catch (Exception e) {
            System.out.println("에러 : " + e.getLocalizedMessage());
            System.out.println("!!!");
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    public static void first() {
        Team team = new Team();
        team.setName("TeamA");

        em.persist(team);

        Member member = new Member();
        member.setName("안녕하세요");
        member.setMemberType(MemberType.ADMIN);
        // 실제로 코딩을 하면 member.setTeam(team) 도 하고 team.getMembers().add(member); 두 개 다 쓰는 것을 권장한다.
        // 얘는 null 값이 들어가지 않음.
        member.setTeam(team);
        Member member1 = new Member();
        member1.setName("Hello");
        member1.setMemberType(MemberType.USER);
        member1.setTeam(team);
        // 얘를 해버리면 null 값이 들어감
//            team.getMembers().add(member);
        em.persist(member);
        em.persist(member1);

//            em.flush();
//            em.clear();

        Member findMember = em.find(Member.class, member.getId());
        Team findTeam = findMember.getTeam();
//
//            findTeam.getName();
//
        List<Member> members = findTeam.getMembers();
        for (Member mem : members) {
            System.out.println("member : " + mem);
        }
    }

    public static void save() {
        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품A");
        em.persist(productA);

        Member member1 = new Member();
        member1.setId("member1");
        member1.setName("회원1");
        member1.getProducts().add(productA);
        em.persist(member1);

        System.out.println("Success");
    }

    public static void find() {

        em.flush();
//        em.clear();

        Member member = em.find(Member.class, "member1");
        List<Product> products = member.getProducts();
        for (Product product : products) {
            System.out.println("product.name = " + product.getName());
        }
    }
}

package jpql;

import javax.persistence.*;
import java.util.List;

public class JpqlMain {

    static EntityManager em;
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter10");
        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            save();
            jpqlFind();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void save() {
        Member member = new Member("jpa", "lsek");
        em.persist(member);
        em.flush();
        em.clear();
    }

    private static void jpqlFind() {
        TypedQuery<Member> query = em.createQuery("select m from Member m where m.id = 'jpa'", Member.class);
        List<Member> resultList = query.getResultList();
        for (Member member : resultList) {
            System.out.println("member = " + member);
        }
    }
}

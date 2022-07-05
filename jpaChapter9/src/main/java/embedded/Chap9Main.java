package embedded;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.function.Predicate;

public class Chap9Main implements Serializable {
    static EntityManager em;
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter9");
        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            save();

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
        Member member = new Member();
        member.setId("jpa");
        member.setHomeAddress(new Address("통영","몽돌해수욕장","660-123"));

        member.getFavoriteFoods().add("짬뽕");
        member.getFavoriteFoods().add("짜장면");
        member.getFavoriteFoods().add("탕수육");

        member.getAddresseHistory().add(new Address("서울", "강남", "123-123"));
        member.getAddresseHistory().add(new Address("서울", "강북", "000-000"));

        em.persist(member);
    }
}

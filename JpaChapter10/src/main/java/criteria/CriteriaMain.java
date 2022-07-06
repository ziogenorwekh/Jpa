package criteria;

import jpql.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CriteriaMain {

    static EntityManager em;
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter10");
        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            save();
            criteriaFind();
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
        Member member = new Member("jpa", "lsek2",13);
        em.persist(member);
        em.flush();
        em.clear();
    }

    private static void criteriaFind() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);

        Root<Member> m = query.from(Member.class); // 조회를 시작할 클래스


        CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("name"),"lsek2"));
        List<Member> resultList = em.createQuery(cq).getResultList();
        for (Member member : resultList) {
            System.out.println("member = " + member);
        }
    }
}

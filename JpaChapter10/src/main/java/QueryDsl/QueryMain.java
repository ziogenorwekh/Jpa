package QueryDsl;

import com.mysema.query.jpa.impl.JPAQuery;
import entity.Member;
import entity.QMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class QueryMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter10");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("e = " + e);
        } finally {
            em.close();
            emf.close();
        }

    }

    private static void dsl(EntityManager em) {
        JPAQuery query = new JPAQuery(em);
        QMember qMember = new QMember("m");
        List<Member> members =
                query.from(qMember)
                        .where(qMember.name.eq("회원1"))
                        .orderBy(qMember.name.desc())
                        .list(qMember);
    }
}

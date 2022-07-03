package useJoinTable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Chapter7Main {

    static EntityManager em;

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lsek");
        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            save();
            join();
            tx.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getLocalizedMessage());
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }

    public static void save() {
        Member member = new Member();
        member.setName("멤버");
        em.persist(member);

        Locker locker = new Locker();
        locker.setName("1번 사물함");
        em.persist(locker);

        MemberLocker memberLocker = new MemberLocker();
        memberLocker.setId("Hello");
        memberLocker.setMember(member);
        memberLocker.setLocker(locker);
        em.persist(memberLocker);
    }

    public static void join() {
        MemberLocker memberLocker = em.find(MemberLocker.class, "Hello");
        System.out.println("memberLocker = " + memberLocker);
    }
}

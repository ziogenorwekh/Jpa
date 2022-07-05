package cascade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Chap8MainCascade {

    static EntityManager em;
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter8");
        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            saveCascade();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("e = " + e.getLocalizedMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    public static void saveCascade() {
        Child child = new Child();
        Child child1 = new Child();
        Parent parent = new Parent();
        child.setParent(parent);
        child1.setParent(parent);
        // 여기에 cascade 걸려있으니 add 로 추가해줘야 함
        parent.getChildren().add(child);
        parent.getChildren().add(child1);
        em.persist(parent);
    }

    public static void orphanFunc() {

    }
}

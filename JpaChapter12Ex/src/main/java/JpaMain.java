import org.h2.mvstore.Page;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.awt.print.Pageable;
import java.util.List;

public class JpaMain {
        static EntityManager em;
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lsek");
        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            embeddedKey();
//            save();
//            find();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        em.close();
        emf.close();
    }

    public static void save() {
        Parent parent = new Parent();
        ParentId parentId = new ParentId("myId1", "myId2");
        parent.setId(parentId);
        parent.setName("안녕하세요");
        em.persist(parent);
    }

    public static void find() {

        ParentId parentId = new ParentId("myId1", "myId2");
        Parent parent = em.find(Parent.class, parentId);

        System.out.println("parent = " + parent);
    }

    public static void embeddedKey() {
        ParentId parentId = new ParentId("id1", "id2");
        ParentId parentId1 = new ParentId("id1", "id2");
        boolean equals = parentId.equals(parentId1);
        System.out.println(equals ? "참" : "거짓");
    }
}

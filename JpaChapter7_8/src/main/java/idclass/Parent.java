package idclass;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(ParentId.class)
public class Parent {

    @Id
    private String id1;

    @Id
    private String id2;

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id1='" + id1 + '\'' +
                ", id2='" + id2 + '\'' +
                '}';
    }
}

class ParentId implements Serializable {
    private String id1;
    private String id2;

    public ParentId() {

    }
    public ParentId(String id1, String id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        ParentId parentId = (ParentId) obj;
        if(this.id1.equals(parentId.id1) && this.id2.equals(parentId.id2)){
            return true;
        }
        return false;
    }
}

class Main{
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter7");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Parent parent = new Parent();
            parent.setId1("id1");
            parent.setId2("id2");
            em.persist(parent);
            em.flush();
            em.clear();
            ParentId parentId = new ParentId("id1", "id2");
            Parent parent1 = em.find(Parent.class, parentId);
            System.out.println("parent1 = " + parent1);
        } catch (Exception e) {
            System.out.println("e = " + e);
        } finally {
            em.close();
            emf.close();
        }
    }
}

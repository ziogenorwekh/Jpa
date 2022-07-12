package chapter4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PracticalExample");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            defaultItem(em);
            memberCreate(em);
            memberOrder(em);
            memberOrderFind(em);
            tx.commit();
        } catch (Exception e) {
            System.out.println("e = " + e.getLocalizedMessage());
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void defaultItem(EntityManager em) {
        Item item = new Item();
        item.setId("1");
        item.setName("Apple");
        item.setPrice(1000);
        item.setStockQuantity(5);
        em.persist(item);
        em.flush();
        em.clear();
    }

    private static void memberOrderFind(EntityManager em) {
        Member member = em.find(Member.class, "1");

        OrderItem orderItem = em.find(OrderItem.class, 1L);
//        for (Object o : id) {
//            System.out.println("o = " + o);
//        }
        System.out.println("orderItem = " + orderItem);
    }

    private static void memberOrder(EntityManager em) {
        Member member = em.find(Member.class, "1");
        Order order = new Order();
        order.setId("1");
        order.setMember(member);
        order.setOrderDate(new Date());
        em.persist(order);
        Item item = (Item) em.createQuery("select i from Item i where i.name= :name").
                setParameter("name", "Apple")
                .getSingleResult();
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setItem(item);
        orderItem.setCount(2);
        orderItem.setOderPrice(item.getPrice()* orderItem.getCount());
        em.persist(orderItem);
        em.flush();
        em.clear();
    }

    private static void memberCreate(EntityManager em) {
        Member member = new Member();
        Address address = new Address();
        member.setId("1");
        member.setName("lsek");
        address.setCity("seoul");
        address.setStreet("Gyeongnidan-gil");
        address.setZipcode("111-1111");
        member.setAddress(address);
        em.persist(member);
        em.flush();
        em.clear();
    }
}

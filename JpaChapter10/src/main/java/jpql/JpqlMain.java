package jpql;

import entity.Member;
import entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SuppressWarnings("all")
public class JpqlMain {

    static EntityManager em;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter10");
        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
//            save();
//            jpqlFind();
//            saveMany();
//            findCriteriaObject();
//            findCriteriaTuple();
//            findMemberByTuple();
//            findTeamAndAgeMaxMin();
//            usingFindGroupHaving();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
        }
        finally {
            em.close();
            emf.close();
        }
    }


    private static void usingFindGroupHaving() {
        // new 사용하지 않은 경우에
//        List<Double> resultList = em.createQuery("select AVG(m.age) from Member m " +
//                "group by m.team.name order by m.team.name desc ", Double.class).getResultList();
//        for (Double d : resultList) {
//            System.out.println("d = " + d);
//        }

        // new 사용한 경우에
        List<JoinClass> result = em.createQuery("select new " +
                "jpql.JoinClass(m.team.name,avg(m.age)) from Member m group by m.team.name", JoinClass.class).getResultList();
        for (JoinClass joinClass : result) {
            System.out.println("1. joinClass = " + joinClass);
        }

        // 조인
        List<Object[]> resultList = em.createQuery("select m,t from Member m join m.team t").getResultList();
        for (Object[] objects : resultList) {
            Member member = (Member) objects[0];
            Team team = (Team) objects[1];
            System.out.println("common Join. member = " + member);
        }
        List<Member> list = em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();
        for (Member member : list) {
            System.out.println("fetch Join. member = " + member);
        }
        // 컬렉션 값 연관 경로 탐색

//        List<Object[]> resultList = em.createQuery("select AVG(m.age) from Member m group by m.team.name").getResultList();
//        int average = 0;
//        System.out.println("resultList = " + resultList.size());
//        for (Object[] objects : resultList) {
//            average = (int) objects[0];
//        }
//        System.out.println("average = " + average);
    }

    private static void save() {
        Member member = new Member("jpa", "lsek", 13);
        em.persist(member);
        em.flush();
        em.clear();
    }

    private static void jpqlFind() {
        // 1번째
//        TypedQuery<Member> query = em.createQuery("select m from Member m where m.id = 'lsek'" , Member.class);
        // 2번째
//        TypedQuery<Member> query = em.createQuery("select m from Member m where m.id = :userId", Member.class)
//                .setParameter("userId","jpa");
//        List<Member> resultList = query.getResultList();
        // 3번째
        List<Member> resultList = em.createQuery("select m from Member  m where m.id = :userId", Member.class)
                .setParameter("userId", "jpa").getResultList();
        // 이렇게 만들면 SQL 인젝션 공격을 당할 수 있다.
//        String id = "jpa";
//        TypedQuery<Member> emQuery = em.createQuery("select m from Member m where m.id" +
//                "= '" + id + "'", Member.class);
//        List<Member> resultList1 = emQuery.getResultList();
//        for (Member member : resultList1) {
//            System.out.println("member = " + member);
//        }
        for (Member member : resultList) {
            System.out.println("member = " + member);
        }
    }

}

@SuppressWarnings("all")
class JoinClass {
    public JoinClass(String teamName, Double avg) {
        this.teamName = teamName;
        this.avg = avg;
    }

    String teamName;
    Double avg;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "JoinClass{" +
                "teamName='" + teamName + '\'' +
                ", avg=" + avg.intValue() +
                '}';
    }
}

package criteria;

import entity.Member;
import entity.Team;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

public class CriteriaMain {

    static EntityManager em;
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter10");
        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            saveMany();
//            save();
//            criteriaFind();
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
    private static void saveMany() {
        for (int i = 1; i < 21; i++) {
            Member member = new Member("L" + i, "lsek" + (int)(Math.random() * 20), (int) (Math.random() * 30));
            Team team = new Team();
            int threeForth = (int) (Math.random() * 3);
            if(threeForth == 0) {
                em.persist(member);
                continue;
            }
            if (member.getAge() % 2 == 0) {
                team.setName("one");
            } else {
                team.setName("two");
            }
            member.setTeam(team);
            team.getMembers().add(member);
            em.persist(member);
            em.persist(team);
        }
    }

    private static void findCriteriaObject() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<Member> m = cq.from(Member.class);
        cq.multiselect(m.get("name"), m.get("age")).distinct(false);
        List<Object[]> resultList = em.createQuery(cq).getResultList();
        for (int i = 0; i < resultList.size(); i++) {
            for (int j = 0; j < resultList.get(i).length; j++) {
                Object o = resultList.get(i)[0];
                Object o1 = resultList.get(i)[1];
                System.out.println("o = " + o);
                System.out.println("o1 = " + o1);
            }
        }
    }

    private static void findCriteriaTuple() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<Member> m = cq.from(Member.class);
        cq.multiselect(m.get("name").alias("name"), m.get("age").alias("age"));

        List<Tuple> resultList = em.createQuery(cq).getResultList();
        for (Tuple tuple : resultList) {
            String username = tuple.get("name", String.class);
            Integer age = tuple.get("age", Integer.class);
            System.out.println("age = " + age);
            System.out.println("username = " + username);
        }
    }

    private static void findMemberByTuple() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        Root<Member> m = cq.from(Member.class);

        cq.select(cb.tuple(m.alias("m"), m.get("name").alias("username")));
        List<Tuple> resultList = em.createQuery(cq).getResultList();
        for (Tuple tuple : resultList) {
            Member member = tuple.get("m", Member.class);
            String username = tuple.get("username", String.class);

            System.out.println("member = " + member);
            System.out.println("username = " + username);
        }
    }

    private static void findTeamAndAgeMaxMin() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<Member> m = cq.from(Member.class);

        Expression maxAge = cb.max(m.<Integer>get("age"));
        Expression minAge = cb.min(m.<Integer>get("age"));

        cq.multiselect(m.get("team").get("name").alias("teamName"), maxAge.alias("max"), minAge.alias("min"));
        cq.groupBy(m.get("team").get("name"));

        List<Tuple> resultList = em.createQuery(cq).getResultList();
        for (Tuple tuple : resultList) {
            String teamName = tuple.get("teamName", String.class);
            Integer max = tuple.get("max", Integer.class);
            Integer min = tuple.get("min", Integer.class);
            System.out.println("teamName = " + teamName);
            System.out.println("max = " + max);
            System.out.println("min = " + min);
        }
    }

    private static void findJoin() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        Root<Member> m = cq.from(Member.class);
        Join<Member, Team> t = m.join("team", JoinType.INNER);
        cq.multiselect(m,t).where(cb.equal((Expression<?>) t.get("name").alias("name"),"one"));
    }

}

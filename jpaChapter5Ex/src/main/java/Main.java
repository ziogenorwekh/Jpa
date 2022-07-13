import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter5");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team = new Team();
            team.setId("team1");
            team.setName("teamA");
            em.persist(team);
            Member member = new Member();
            member.setId("1");
            member.setName("123");
            member.setTeam(team);
            em.persist(member);
            System.out.println("member = " + member);
            em.flush();
            em.clear();
            Team team1 = new Team();
            team1.setId("team2");
            team1.setName("teamB");
            em.persist(team1);
            Member member1 = em.find(Member.class, "1");
            member1.setTeam(team1);
            em.flush();
            em.clear();
            Member member2 = em.find(Member.class, "1");
            Team team11 = em.find(Team.class, "team1");
            System.out.println("member2 = " + member2);
            System.out.println("team11 = " + team11);
            tx.commit();
        } catch (Exception e) {
            System.out.println("e = " + e);
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}

package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();


        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("teamA");
            member.setAge(10);
            member.setTeam(team);
            member.setType(MemberType.ADMIN);


            em.persist(member);
            //
            Member member1 = new Member();
            member1.setUsername("team B");
            member1.setAge(10);
            member1.setTeam(team);
            member1.setType(MemberType.ADMIN);


            em.persist(member1);


            em.flush();
            em.clear();

            String query = "select t from Team t ";
            List<Team> singleResult = em.createQuery(query, Team.class)
                    .getResultList();
             singleResult.get(0).getMembers().get(0);


            tx.commit();


            } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();

        }

        emf.close();
    }

   }

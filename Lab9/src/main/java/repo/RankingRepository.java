package repo;

import entity.Ranking;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class RankingRepository implements AbstractRepository<Ranking>{
    private EntityManager entityManager;
    public RankingRepository() {
        entityManager= PersistenceUtil.getInstance().getEntityManagerFactory().createEntityManager();
    }
    @Override
    public void create(Ranking object)
    {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
    }

    @Override
    public Ranking findById(int id) {
        return entityManager.find(Ranking.class,id);
    }

    @Override
    public List<Ranking> findByName(String name) {
        return null;
    }
}

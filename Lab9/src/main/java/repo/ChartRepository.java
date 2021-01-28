package repo;

import entity.Artist;
import entity.Chart;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChartRepository implements AbstractRepository<Chart> {
    EntityManager entityManager;

    public ChartRepository() {
        entityManager= PersistenceUtil.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public void create(Chart object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public Chart findById(int id) {
        return entityManager.find(Chart.class,id);
    }

    @Override
    public List<Chart> findByName(String name) {
        List<Chart> selectedCharts=new ArrayList<Chart>();
        try {
            entityManager.getTransaction().begin();
            selectedCharts = entityManager.createNamedQuery("Chart.findByName").setParameter("name", name).getResultList();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return selectedCharts;
    }
    public Chart getRandomChart()
    {
        Query countQuery=entityManager.createNativeQuery("SELECT COUNT(*) FROM CHARTS");
        long count=((BigDecimal)countQuery.getSingleResult()).longValue();
        Random random=new Random();
        int number=random.nextInt((int)count);
        Query selectQuery=entityManager.createQuery("SELECT chart FROM Chart chart");
        selectQuery.setFirstResult(number);
        selectQuery.setMaxResults(1);
        return (Chart)selectQuery.getSingleResult();

    }
}

package repo;

import entity.Artist;
import util.PersistenceUtil;
import java.util.*;

import javax.persistence.EntityManager;

public class ArtistRepository implements AbstractRepository<Artist>{
    EntityManager entityManager;
    public ArtistRepository() {
        entityManager= PersistenceUtil.getInstance().getEntityManagerFactory().createEntityManager();
    }
    public void create(Artist object)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }
    public Artist findById(int id)
    {
        return entityManager.find(Artist.class,id);
    }
    public List<Artist> findByName(String name)
    {
        List<Artist> selectedArtists=new ArrayList<Artist>();
        try {
            entityManager.getTransaction().begin();
            selectedArtists = entityManager.createNamedQuery("Artist.findByName").setParameter("name", name).getResultList();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return selectedArtists;
    }
}

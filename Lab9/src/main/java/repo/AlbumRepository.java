package repo;

import entity.Album;
import entity.Artist;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


public class AlbumRepository implements AbstractRepository<Album> {
    EntityManager entityManager;

    public AlbumRepository() {
        this.entityManager = PersistenceUtil.getInstance().getEntityManagerFactory().createEntityManager();
    }

    public void create(Album object)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }
    public Album findById(int id)
    {
        return entityManager.find(Album.class,id);
    }
    public List<Album> findByName(String name)
    {
        List<Album> selectedAlbums=new ArrayList<Album>();
        try {
            entityManager.getTransaction().begin();
            selectedAlbums = entityManager.createNamedQuery("Album.findByName").setParameter("name", name).getResultList();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        finally {
            if(entityManager.isOpen())
                entityManager.close();;
        }
        return selectedAlbums;
    }
    public List<Album> findByArtist(Artist artist){
        List<Album> selectedAlbums=new ArrayList<Album>();

        try
        {
            entityManager.getTransaction().begin();
            selectedAlbums=entityManager.createNamedQuery("Album.findByArtist").setParameter("id",artist.getId()).getResultList();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return selectedAlbums;
    }

}

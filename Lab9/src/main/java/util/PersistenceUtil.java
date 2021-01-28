package util;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    private EntityManagerFactory entityManagerFactory;
    private static PersistenceUtil persistenceUtilInstance;
    private PersistenceUtil()
    {
        entityManagerFactory= Persistence.createEntityManagerFactory("MusicAlbumsPU");
    }
    public static PersistenceUtil getInstance()
    {
        if(persistenceUtilInstance==null)
            persistenceUtilInstance=new PersistenceUtil();
        return persistenceUtilInstance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

}

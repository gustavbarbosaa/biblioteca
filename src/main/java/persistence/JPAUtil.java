package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Objects;

public class JPAUtil {
    private final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("biblioteca");
    
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        if (Objects.isNull(entityManager)) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }
}

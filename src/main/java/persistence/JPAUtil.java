package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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

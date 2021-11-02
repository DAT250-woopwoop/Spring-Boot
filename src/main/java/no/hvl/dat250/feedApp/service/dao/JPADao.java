package no.hvl.dat250.feedApp.service.dao;

import javax.persistence.*;
import java.lang.reflect.*;
import java.util.*;

public abstract class JPADao<T, E> implements Dao<T, E> {

    private static final String PERSISTENCE_UNIT_NAME = "FeedApp";

    private static EntityManagerFactory factory;

    public Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public JPADao(){
        this.entityClass = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);

        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = factory.createEntityManager();
    }


    @Override
    public void update(T entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void persist(T entity) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(entity);
            tx.commit();
            entityManager.close();

        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void remove(T entity) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.remove(entity);
            entityManager.flush();
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public T find(E id) {
        entityManager.getTransaction().begin();
        T res = entityManager.find(entityClass, id);
        entityManager.getTransaction().commit();
        return res;
    }

    @Override
    public List<T> getAll() {
        entityManager.getTransaction().begin();
        List<T> res = entityManager
                .createQuery(String.format("Select e from %s e", entityClass.getSimpleName()))
                .getResultList();
        entityManager.getTransaction().commit();
        return res;
    }

    @Override
    public T get(E id) {
        entityManager.getTransaction().begin();
        T res = (T) entityManager.createQuery("Select * from " + entityClass.getSimpleName() + " as e where e.id=" + id).getSingleResult();
        entityManager.getTransaction().commit();
        return res;
    }
}

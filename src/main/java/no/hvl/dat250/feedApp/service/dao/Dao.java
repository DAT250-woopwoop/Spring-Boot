package no.hvl.dat250.feedApp.service.dao;

import java.util.*;

public interface Dao<T, E>{

    void update(T entity);

    void persist(T entity);

    void remove(T entity);

    T find(E id);

    List<T> getAll();

    T get(E id);

}

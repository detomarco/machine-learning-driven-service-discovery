package it.univaq.disim.numismatic.coinservice.business.repository.impl;


import it.univaq.disim.numismatic.coinservice.business.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

abstract class BaseRepositoryImpl<T> implements BaseRepository<T> {

    @Autowired
    protected EntityManager entityManager;

    private final Class<T> clazz;

    BaseRepositoryImpl() {
        Type type = getClass().getGenericSuperclass();

        while (!(type instanceof ParameterizedType) || ((ParameterizedType) type).getRawType() != BaseRepositoryImpl.class) {
            if (type instanceof ParameterizedType) {
                type = ((Class<?>) ((ParameterizedType) type).getRawType()).getGenericSuperclass();
            } else {
                type = ((Class<?>) type).getGenericSuperclass();
            }
        }

        this.clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];

    }

    public List<T> findAll() {
        return entityManager.createQuery("FROM " + clazz.getName(), clazz).getResultList();
    }

}

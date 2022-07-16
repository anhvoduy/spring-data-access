package com.example.race.repositories;

import com.example.race.models.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomRepository implements ICustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Session> customQuerySessions() {
        return entityManager.createNativeQuery("SELECT s.* FROM Sessions s").getResultList();
    }
}

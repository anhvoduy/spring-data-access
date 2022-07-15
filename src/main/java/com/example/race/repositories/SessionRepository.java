package com.example.race.repositories;

import com.example.race.models.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessionRepository {
    @Autowired
    private ISessionRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public Session create(Session session) {
        return repository.saveAndFlush(session);
    }

    public Session update(Session session) {
        return repository.saveAndFlush(session);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Session find(Long id) {
        return repository.getOne(id);
    }

    public List<Session> list() {
        return repository.findAll();
    }

    public List<Session> getSessionsThatHaveName(String name) {
        List<Session> ses = entityManager
                .createNativeQuery("SELECT * FROM Sessions s WHERE UPPER(s.session_name) LIKE UPPER(:name)")
                .setParameter("name", "%" + name + "%").getResultList();
        return ses;
    }

    public List<Session> findBySessionName(String name) {
        return repository.findBySessionNameContains(name);
    }
}

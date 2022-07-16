package com.example.race.repositories;

import com.example.race.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketPriceRepository {
    @Autowired
    private ITicketTypeRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public TicketPrice create(TicketPrice tp) {
        entityManager.persist(tp);
        entityManager.flush();
        return tp;
    }

    public TicketPrice update(TicketPrice tp) {
        tp = entityManager.merge(tp);
        entityManager.flush();
        return tp;
    }

    public void delete(Long id) {
        entityManager.remove(find(id));
        entityManager.flush();
    }

    public TicketPrice find(Long id) {
        return entityManager.find(TicketPrice.class, id);
    }

    public List<TicketPrice> list() {
        return entityManager.createQuery("select t from TicketPrice t").getResultList();
    }
}

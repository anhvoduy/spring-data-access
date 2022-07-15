package com.example.race.repositories;

import com.example.race.models.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITicketTypeRepository extends JpaRepository<TicketType, Long> {
    List<TicketType> findByIncludesWorkshopTrue();

    TicketType findByTicketTypeCode(String id);
}

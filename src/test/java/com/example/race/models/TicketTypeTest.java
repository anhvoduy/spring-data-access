package com.example.race.models;

import com.example.race.repositories.ITicketTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TicketTypeTest {
    @Autowired
    private ITicketTypeRepository repository;

    @Test
    public void testDslQueryIncludeTrue() throws Exception {
        List<TicketType> tickets = repository.findByIncludesWorkshopTrue();
        assertNotNull(tickets);
        assertTrue (tickets.size() > 0);

        assert (tickets.stream().count()) >= 1;
        assert (tickets.get(0).getIncludesWorkshop()) == true;
    }
}

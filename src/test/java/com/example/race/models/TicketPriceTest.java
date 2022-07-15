package com.example.race.models;

import com.example.race.repositories.ITicketPriceRepository;
import com.example.race.repositories.ITicketTypeRepository;
import com.example.race.repositories.PricingCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TicketPriceTest {
    @Autowired
    private ITicketPriceRepository tpRepository;

    @Autowired
    private PricingCategoryRepository pcRepository;

    @Autowired
    private ITicketTypeRepository ttRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testFindAll() throws Exception {
        List<TicketPrice> tickets = tpRepository.findAll();
        assertNotNull(tickets);
        assertTrue(tickets.stream().count() > 0);
    }

    @Test
    public void testFind() throws Exception {
        TicketPrice ticket = tpRepository.getById(1L);
        assertNotNull(ticket);
        assertEquals(1, ticket.getTicketPriceId());
    }

    @Test
    @Transactional
    public void testSaveAndGetAndDelete() throws Exception {
        TicketPrice tp = new TicketPrice();
        tp.setBasePrice(BigDecimal.valueOf(200, 2));

        tp.setPricingCategory(pcRepository.find("E"));

        tp.setTicketType(ttRepository.findByTicketTypeCode("C"));

        tp = tpRepository.saveAndFlush(tp);

        // clear the persistence context so we don't return the previously cached location object
        // this is a test only thing and normally doesn't need to be done in prod code
        entityManager.clear();

        TicketPrice otherTp = tpRepository.getById(tp.getTicketPriceId());
        assertEquals(BigDecimal.valueOf(200, 2).floatValue(), otherTp.getBasePrice().floatValue());

        tpRepository.deleteById(otherTp.getTicketPriceId());
    }

    @Test
    public void testQueryAnnotation() throws Exception {
        List<TicketPrice> tickets = tpRepository.findTicketByBasePrice(BigDecimal.valueOf(500));
        assertTrue(tickets.stream().count()>0);
        assertEquals((float)500, tickets.get(0).getBasePrice().floatValue());
    }

    @Test
    public void testQueryAnnotationWithWorkshop() throws Exception {
        List<TicketPrice> tickets = tpRepository.findTicketUnderBasePriceWithWorkshops(BigDecimal.valueOf(1000));
        assertTrue(tickets.stream().count()>0);

        assertTrue(tickets.get(0).getBasePrice().floatValue() < 1000);
        assertTrue(tickets.get(0).getTicketType().getIncludesWorkshop().equals(true));
    }

    @Test
    public void testQueryAnnotationByRelationship() throws Exception {
        List<TicketPrice> tickets = tpRepository.queryTicketRelationship(BigDecimal.valueOf(1000));
        assertTrue(tickets.stream().count()>0);

        assertTrue(tickets.get(0).getBasePrice().floatValue() < 1000);
        assertTrue(tickets.get(0).getTicketType().getIncludesWorkshop().equals(true));
    }

    @Test
    public void testQueryAnnotationByCategoryCode() throws Exception {
        List<TicketPrice> tickets = tpRepository.queryTicketWithCategoryCode("E");
        assertTrue(tickets.stream().count()>0);
        assertEquals("E", tickets.get(0).getPricingCategory().getPricingCategoryCode());
    }

    @Test
    public void namedFindTicketsByPricingCategoryName() throws Exception {
        List<TicketPrice> tickets = tpRepository.namedFindTicketsByPricingCategoryName("Regular");
        assertTrue(tickets.stream().count()>0);
        assertEquals("Regular", tickets.get(0).getPricingCategory().getPricingCategoryName());
    }

    @Test
    public void nativeFindTicketsByCategoryWithWorkshop() throws Exception {
        List<TicketPrice> tickets = tpRepository.nativeFindTicketsByCategoryWithWorkshop("Regular");
        assertTrue(tickets.stream().count()>0);
        assertEquals("Regular", tickets.get(0).getPricingCategory().getPricingCategoryName());
    }
}

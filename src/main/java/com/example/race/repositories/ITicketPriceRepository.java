package com.example.race.repositories;

import com.example.race.models.TicketPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ITicketPriceRepository extends JpaRepository<TicketPrice, Long> {
    @Query(value = "select tp from TicketPrice tp where tp.basePrice = ?1")
    List<TicketPrice> findTicketByBasePrice(BigDecimal basePrice);

    @Query(value = "select tp from TicketPrice tp where tp.basePrice < ?1 and tp.ticketType.includesWorkshop = true")
    List<TicketPrice> findTicketUnderBasePriceWithWorkshops(BigDecimal basePrice);

    @Query(value = "select tp from TicketPrice tp where tp.basePrice < :price and tp.ticketType.includesWorkshop = true")
    List<TicketPrice> queryTicketRelationship(@Param("price") BigDecimal basePrice);

    @Query(value = "select * from ticket_prices where pricing_category_code = ?1", nativeQuery = true)
    List<TicketPrice> queryTicketWithCategoryCode(String priceCategoryCode);

    List<TicketPrice> namedFindTicketsByPricingCategoryName(@Param("name") String name);

    List<TicketPrice> nativeFindTicketsByCategoryWithWorkshop(@Param("name") String name);
}

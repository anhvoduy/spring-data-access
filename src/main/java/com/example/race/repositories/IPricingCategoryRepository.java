package com.example.race.repositories;

import com.example.race.models.PricingCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPricingCategoryRepository extends JpaRepository<PricingCategory, Long> {
}

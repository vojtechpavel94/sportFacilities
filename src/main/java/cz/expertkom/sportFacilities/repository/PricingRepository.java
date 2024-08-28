package cz.expertkom.sportFacilities.repository;

import cz.expertkom.sportFacilities.model.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Integer> {
    Optional<Pricing> findByFacilityId(Integer facilityId);
}
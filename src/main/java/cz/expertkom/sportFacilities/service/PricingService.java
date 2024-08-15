package cz.expertkom.sportFacilities.service;

import cz.expertkom.sportFacilities.model.Facility;
import cz.expertkom.sportFacilities.model.Pricing;
import cz.expertkom.sportFacilities.repository.PricingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingService {
    private final PricingRepository pricingRepository;

    public PricingService(PricingRepository pricingRepository) {
        this.pricingRepository = pricingRepository;
    }

     //get cenu za sportoviště
     public Pricing getPrice(Integer facilityId) {
         return pricingRepository.findById(facilityId).orElse(null);
     }
}

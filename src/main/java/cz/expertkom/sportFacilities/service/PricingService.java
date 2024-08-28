package cz.expertkom.sportFacilities.service;
import cz.expertkom.sportFacilities.model.Pricing;
import cz.expertkom.sportFacilities.repository.FacilityRepository;
import cz.expertkom.sportFacilities.repository.PricingRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class PricingService {
    private final PricingRepository pricingRepository;

    private final FacilityRepository facilityRepository;

    public PricingService(PricingRepository pricingRepository, FacilityRepository facilityRepository) {
        this.pricingRepository = pricingRepository;
        this.facilityRepository = facilityRepository;
    }

     public Pricing getPrice(Integer id) {
         return pricingRepository.findById(id).orElse(null);
     }

    //get cenu za sportoviště
    /*public Pricing getPriceById(Integer facilityId) {
        return pricingRepository.findById(facilityId).orElse(null);
    }*/

    public Pricing getPriceById(Integer facilityId) {
        return pricingRepository.findByFacilityId(facilityId).orElse(null);
    }
/*
    public void updatePricing(int id, Pricing pricing) {
        pricing.setPricingId(id); // zachováme původní ID
        pricing.setPrice(pricing.getPrice());
        pricing.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        pricingRepository.save(pricing);
    }*/
public Pricing updatePricing(int id, Pricing pricing) {
    pricing.setPricingId(id); // // původní ID dáme znovu do PricingId
    pricing.setPrice(pricing.getPrice());
    pricing.setFacilityId(pricing.getFacilityId());
    pricing.setUser_type(pricing.getUser_type());
    pricing.setReservation_type(pricing.getReservation_type());
    pricing.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    return pricingRepository.save(pricing);
}
}

package cz.expertkom.sportFacilities.controllers;

import cz.expertkom.sportFacilities.dto.FacilityDto;
import cz.expertkom.sportFacilities.dto.FacilityRegisterDto;
import cz.expertkom.sportFacilities.dto.UserDto;
import cz.expertkom.sportFacilities.model.Facility;
import cz.expertkom.sportFacilities.model.Pricing;
import cz.expertkom.sportFacilities.model.UserRole;
import cz.expertkom.sportFacilities.service.FacilityService;
import cz.expertkom.sportFacilities.service.PricingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //provolávání jen z PostMana
@RequestMapping("/facilities") //možná lepší mít /api/v1/users
@Slf4j
public class FacilityController {
    private final FacilityService facilityService;

    private final PricingService pricingService;

    @Autowired
    public FacilityController (FacilityService facilityService, PricingService pricingService) {
        this.facilityService = facilityService;
        this.pricingService = pricingService;
    }

    @PostMapping
    public Facility registerFacility(@RequestBody FacilityRegisterDto facilityRegisterDto){
        return facilityService.registerFacility(facilityRegisterDto);
    }

    @GetMapping
    public List<FacilityDto> getAllFacilities() {
        log.info("#FC&gu01: getAllFacilities called");
        return facilityService.getAll();
    }
    //získej cenu sportoviště na základě id
    @GetMapping("/{facilityId}/price")
    public Pricing getPrice(@PathVariable Integer facilityId) {
        log.info("#FC&gp01: getPrice called with facilityId: {}", facilityId);
        return pricingService.getPrice(facilityId);
    }
}

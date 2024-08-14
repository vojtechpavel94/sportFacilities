package cz.expertkom.sportFacilities.controllers;

import cz.expertkom.sportFacilities.dto.FacilityDto;
import cz.expertkom.sportFacilities.service.FacilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //provolávání jen z PostMana
@RequestMapping("/facilities") //možná lepší mít /api/v1/users
@Slf4j
public class FacilityController {
    private final FacilityService facilityService;

    @Autowired
    public FacilityController (FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @GetMapping
    public List<FacilityDto> getAllFacilities() {
        log.info("#UC&gu01: getAllUsers called");
        return facilityService.getAll();
    }
}

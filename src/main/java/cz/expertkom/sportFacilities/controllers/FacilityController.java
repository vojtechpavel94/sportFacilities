package cz.expertkom.sportFacilities.controllers;

import cz.expertkom.sportFacilities.dto.FacilityDto;
import cz.expertkom.sportFacilities.service.FacilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //provolávání jen z PostMana
@RequestMapping("/api/v1/facilities") //možná lepší mít /api/v1/users
@Slf4j
public class FacilityController {
    @Autowired
    private FacilityService facilityService;

    @GetMapping
    public List<FacilityDto> getAllFacilities() {
        log.info("#FC&gu01: getAllFacilities called");
        return facilityService.getAllFacilities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityDto> getFacilityById(@PathVariable int id) {
        FacilityDto FacilityDto = facilityService.getFacilityById(id);
        return new ResponseEntity<>(FacilityDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FacilityDto> createFacility(@RequestBody FacilityDto FacilityDto) {
        FacilityDto createdFacility = facilityService.createFacility(FacilityDto);
        return new ResponseEntity<>(createdFacility, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacilityDto> updateFacility(@PathVariable int id, @RequestBody FacilityDto FacilityDto) {
        FacilityDto updatedFacility = facilityService.updateFacility(id, FacilityDto);
        return new ResponseEntity<>(updatedFacility, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable int id) {
        facilityService.deleteFacility(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

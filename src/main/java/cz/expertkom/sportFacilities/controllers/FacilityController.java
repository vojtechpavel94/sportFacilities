package cz.expertkom.sportFacilities.controllers;

import cz.expertkom.sportFacilities.dto.FacilityDto;
import cz.expertkom.sportFacilities.service.FacilityService;
import cz.expertkom.sportFacilities.service.WeatherService;
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
    private final WeatherService weatherService;
    private final FacilityService facilityService;

    //use Autowired or create constructor in classic way
    public FacilityController(WeatherService weatherService, FacilityService facilityService) {
        this.weatherService = weatherService;
        this.facilityService = facilityService;
    }

    @GetMapping
    public List<FacilityDto> getAllFacilities() {
        log.info("#FC&gu01: getAllFacilities called");
        return facilityService.getAllFacilities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityDto> getFacilityById(@PathVariable int id) {
        FacilityDto facilityDto = facilityService.getFacilityById(id);
        return new ResponseEntity<>(facilityDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FacilityDto> createFacility(@RequestBody FacilityDto facilityDto) {
        FacilityDto createdFacility = facilityService.createFacility(facilityDto);
        return new ResponseEntity<>(createdFacility, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacilityDto> updateFacility(@PathVariable int id, @RequestBody FacilityDto facilityDto) {
        FacilityDto updatedFacility = facilityService.updateFacility(id, facilityDto);
        return new ResponseEntity<>(updatedFacility, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable int id) {
        facilityService.deleteFacility(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Weather
    /*
    @GetMapping("/{id}/weather")
    public String getFacilityWeather(@PathVariable int id) {
        log.info("#FC&gfw01: getFacilityWeather called");
        // Get facility
        FacilityDto facility = facilityService.getFacilityById(id);
        // Get weather data using the facility's latitude and longitude
        return weatherService.getWeather(facility.getLatitude(), facility.getLongitude());
    }*/

    @GetMapping("/{id}/weather")
    public ResponseEntity<String> getFacilityWeather(
            @PathVariable int id,
            @RequestParam String dt) {
        log.info("#FC&gfw01: getFacilityWeather called with date= {}", dt);
        //get facility
        FacilityDto facility = facilityService.getFacilityById(id);
        //latitude, longitude, date
        String weatherData = weatherService.getWeather(facility.getLatitude(), facility.getLongitude(), dt);
        return ResponseEntity.ok(weatherData);
    }
}

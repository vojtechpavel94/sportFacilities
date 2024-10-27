package cz.expertkom.sportFacilities.controllers;

import cz.expertkom.sportFacilities.model.Pricing;
import cz.expertkom.sportFacilities.service.FacilityService;
import cz.expertkom.sportFacilities.service.PricingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //provolávání jen z PostMana
@RequestMapping("/api/v1/pricing")
@Slf4j
public class PricingController {
    @Autowired
    private FacilityService facilityService;

    private PricingService pricingService;

    @Autowired
    public PricingController (FacilityService facilityService, PricingService pricingService) {
        this.facilityService = facilityService;
        this.pricingService = pricingService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pricing> getPrice(@PathVariable int id) {
        Pricing pricing = pricingService.getPrice(id);
        return new ResponseEntity<>(pricing, HttpStatus.OK);
    }

    @GetMapping("/{facilityId}/price")
    public ResponseEntity<Pricing> getPriceById(@PathVariable Integer facilityId) {
        log.info("#PC&gpi01: getPrice called with facilityId: {}", facilityId);
        Pricing pricing = pricingService.getPriceById(facilityId);
        //když nenajde, tak vrátí error 404
        return new ResponseEntity<>(pricing, pricing != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    //Nedělám přes DTO, což zřejmě vede k tomu, že dokážu updatnout pouze cenu, kdyby to udělal přes PricingDto, dokázal bych na setnout více sloupců
    //Pokud nechci nic vracet, stačí mi Void a vrátím si jen HttpStatus.
    /*@PutMapping("/{id}")
    public ResponseEntity<Void> updateFacility(@PathVariable int id, @RequestBody Pricing pricing) {
        pricingService.updatePricing(id, pricing);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    //test s vrácením hodnot
    @PutMapping("/{id}")
    public ResponseEntity<Pricing> updateFacility(@PathVariable int id, @RequestBody Pricing pricing) {
        return new ResponseEntity<>(pricingService.updatePricing(id, pricing), HttpStatus.OK);
    }
}

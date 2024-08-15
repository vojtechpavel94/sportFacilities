package cz.expertkom.sportFacilities.service;

import cz.expertkom.sportFacilities.dto.FacilityDto;
import cz.expertkom.sportFacilities.dto.FacilityRegisterDto;
import cz.expertkom.sportFacilities.model.Facility;
import cz.expertkom.sportFacilities.repository.FacilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class FacilityService {
    private final FacilityRepository facilityRepository;
    //private final FacilityService facilityService;
    private final FacilityMapper facilityMapper;

    public FacilityService (FacilityRepository facilityRepository, /*FacilityService facilityService, */FacilityMapper facilityMapper) {
        this.facilityRepository = facilityRepository;
        //this.facilityService = facilityService;
        this.facilityMapper = facilityMapper;
    }

    public List<FacilityDto> getAll() {
        log.info("#FS&ga01: getAll called");
        //return list;
        return facilityRepository.findAll().stream().map(facilityMapper::toDto).toList();
    }

    public Facility registerFacility (FacilityRegisterDto facilityRegisterDto){
        log.info("#FS&ru01: registerFacility called, facilityRegisterDto={}", facilityRegisterDto);
        Facility facility = new Facility();

        facility.setName(facilityRegisterDto.getName());
        facility.setCity(facilityRegisterDto.getCity());
        //acility.setPricing(facilityRegisterDto.getPricing());

        return facilityRepository.save(facility);
    }

}

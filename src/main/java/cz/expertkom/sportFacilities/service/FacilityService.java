package cz.expertkom.sportFacilities.service;

import cz.expertkom.sportFacilities.dto.FacilityDto;
import cz.expertkom.sportFacilities.dto.FacilityRegisterDto;
import cz.expertkom.sportFacilities.exception.ResourceNotFoundException;
import cz.expertkom.sportFacilities.model.Facility;
import cz.expertkom.sportFacilities.repository.FacilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FacilityService {
    @Autowired
    private FacilityRepository facilityRepository;
    //private final FacilityService facilityService;
    @Autowired
    private FacilityMapper facilityMapper;


    public List<FacilityDto> getAllFacilities() {
        return facilityRepository.findAll().stream()
                .map(facilityMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FacilityDto getFacilityById(int id) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facility not found with id " + id));
        return facilityMapper.toDTO(facility);
    }

    public FacilityDto createFacility(FacilityDto FacilityDto) {
        Facility facility = facilityMapper.toEntity(FacilityDto);
        Facility savedFacility = facilityRepository.save(facility);
        return facilityMapper.toDTO(savedFacility);
    }

    public FacilityDto updateFacility(int id, FacilityDto FacilityDto) {
        Facility facility = facilityMapper.toEntity(FacilityDto);
        facility.setFacilityId(id); // původní ID dáme znovu do FacilityId
        facility.setLastEdited(new Timestamp(System.currentTimeMillis()));
        Facility updatedFacility = facilityRepository.save(facility);
        return facilityMapper.toDTO(updatedFacility);
    }

    public void deleteFacility(int id) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facility not found with id " + id));
        facilityRepository.delete(facility);
    }

    /*public FacilityService (FacilityRepository facilityRepository, FacilityMapper facilityMapper) {
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
    }*/
}

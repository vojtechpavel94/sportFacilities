package cz.expertkom.sportFacilities.service;

import cz.expertkom.sportFacilities.dto.FacilityDto;
import cz.expertkom.sportFacilities.model.Facility;
import cz.expertkom.sportFacilities.repository.PricingRepository;
import cz.expertkom.sportFacilities.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacilityMapper {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PricingRepository pricingRepository;

    public FacilityDto toDTO(Facility facility) {
        FacilityDto dto = new FacilityDto();
        dto.setFacilityId(facility.getFacilityId());
        dto.setStreet(facility.getStreet());
        dto.setCity(facility.getCity());
        dto.setZipCode(facility.getZipCode());
        dto.setCapacity(facility.getCapacity());
        dto.setType(facility.getType());
        dto.setName(facility.getName());
        dto.setPricingId(facility.getPricing() != null ? facility.getPricing().getPricingId() : 0);
        dto.setManagerId(facility.getManager() != null ? facility.getManager().getUserId() : 0);
        dto.setIndoor(facility.getIndoor());
        dto.setLighting(facility.getLighting());
        dto.setLongitude(facility.getLongitude());
        dto.setLatitude(facility.getLatitude());
        dto.setPhotos(facility.getPhotos());
        dto.setRating(facility.getRating());
        dto.setDescription(facility.getDescription());
        dto.setOpeningHour(facility.getOpeningHour());
        dto.setClosingHour(facility.getClosingHour());
        dto.setAvailability(facility.getAvailability());
        dto.setLastEditedBy(facility.getLastEditedBy());
        return dto;
    }

    public Facility toEntity(FacilityDto dto) {
        Facility facility = new Facility();
        facility.setStreet(dto.getStreet());
        facility.setCity(dto.getCity());
        facility.setZipCode(dto.getZipCode());
        facility.setCapacity(dto.getCapacity());
        facility.setType(dto.getType());
        facility.setName(dto.getName());
        facility.setPricing(pricingRepository.findById(dto.getPricingId()).orElse(null));
        facility.setManager(userRepository.findById(dto.getManagerId()).orElse(null));
        facility.setIndoor(dto.getIndoor());
        facility.setLighting(dto.getLighting());
        facility.setLongitude(dto.getLongitude());
        facility.setLatitude(dto.getLatitude());
        facility.setPhotos(dto.getPhotos());
        facility.setRating(dto.getRating());
        facility.setDescription(dto.getDescription());
        facility.setOpeningHour(dto.getOpeningHour());
        facility.setClosingHour(dto.getClosingHour());
        facility.setAvailability(dto.getAvailability());
        facility.setLastEditedBy(dto.getLastEditedBy());
        return facility;
    }


    /*public FacilityDto toDto(Facility facility) {
        FacilityDto facilityDto = new FacilityDto();
        facilityDto.setName(facility.getName());
        facilityDto.setCity(facility.getCity());
        //facilityDto.setPricing(facility.getPricing());

        return facilityDto;
    }*/
}

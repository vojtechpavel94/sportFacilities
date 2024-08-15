package cz.expertkom.sportFacilities.service;

import cz.expertkom.sportFacilities.dto.FacilityDto;
import cz.expertkom.sportFacilities.model.Facility;
import org.springframework.stereotype.Component;

@Component
public class FacilityMapper {
    public FacilityDto toDto(Facility facility) {
        FacilityDto facilityDto = new FacilityDto();
        facilityDto.setName(facility.getName());
        facilityDto.setCity(facility.getCity());
        //facilityDto.setPricing(facility.getPricing());

        return facilityDto;
    }
}

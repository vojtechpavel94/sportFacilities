package cz.expertkom.sportFacilities.dto;

import lombok.Data;

@Data
public class FacilityRegisterDto {
    private String name;
    private int pricing;
    private String city;
    //nejspíš zatím nevyužiju
    //private int managerId;
}

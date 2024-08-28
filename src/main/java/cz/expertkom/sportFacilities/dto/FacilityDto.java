package cz.expertkom.sportFacilities.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;

@Data
public class FacilityDto {
    private int facilityId;
    private String street;
    private String city;
    private String zipCode;
    private int capacity;
    private String type;
    private String name;
    private int pricingId; // FK
    private int managerId; // FK
    private Boolean indoor;
    private Boolean lighting;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String photos;
    private BigDecimal rating;
    private String description;
    private Time openingHour;
    private Time closingHour;
    private Boolean availability;
    private int lastEditedBy;

    /*
    private String name;
    private int pricing;
    private String city;*/
}

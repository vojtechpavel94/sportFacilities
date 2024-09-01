package cz.expertkom.sportFacilities.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Time;

@Entity
@Table(name = "facilities")
@Data
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int facilityId;

    private String street;
    private String city;
    private String zipCode;
    private Integer capacity;
    private String type;
    private String name;

    @ManyToOne
    @JoinColumn(name = "pricing_id")
    private Pricing pricing;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    private Timestamp createdAt;
    private Timestamp lastEdited;
    private Integer lastEditedBy;
    private Boolean indoor;
    private Boolean lighting;
    private double longitude;
    private double latitude;
    private String photos;
    private BigDecimal rating;
    private String description;
    private Time openingHour;
    private Time closingHour;
    private Boolean availability;

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int facilityId;
    @NotNull
    private String name;
    //@NotNull
    //private int pricing;
    @NotNull
    private String city;*/
}

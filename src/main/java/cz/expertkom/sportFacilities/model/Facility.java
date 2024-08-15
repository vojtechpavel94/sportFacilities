package cz.expertkom.sportFacilities.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "facilities")
@Data
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int facilityId;
    @NotNull
    private String name;
    //@NotNull
    //private int pricing;
    @NotNull
    private String city;
/*
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pricingId", referencedColumnName = "pricingId")
    @NotNull // Use @NotNull for object references
    private Pricing pricingId;*/

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "managerId", referencedColumnName = "managerId")
    @NotNull // Use @NotNull for object references
    private Facility managerId;*/
}

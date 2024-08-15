package cz.expertkom.sportFacilities.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table (name = "pricing")
@Data
public class Pricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pricingId;

    @NotNull
    private int price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "facilityId", referencedColumnName = "facilityId")
    @NotNull // Use @NotNull for object references
    private Facility facilityId;
}

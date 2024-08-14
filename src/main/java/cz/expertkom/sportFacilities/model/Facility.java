package cz.expertkom.sportFacilities.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "facilities")
@Data
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int facilityId;
    @NotBlank
    private String name;
    private int pricing;
    private String city;

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "managerId", referencedColumnName = "managerId")
    @NotNull // Use @NotNull for object references
    private Facility managerId;*/
}

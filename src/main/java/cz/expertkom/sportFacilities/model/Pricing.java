package cz.expertkom.sportFacilities.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table (name = "pricing")
@Data
public class Pricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pricingId;

    /*@Column(nullable = false)
    private Boolean isWeekend;  // true = víkend, false = pracovní den*/

    /*@Column(nullable = false)
    private Boolean isInternal;  // true = interní osoba, false = externí osoba*/

    /*@Column(nullable = false)
    private Boolean isFullDay;  // true = celodenní pronájem, false = hodinový pronájem*/

    @Column(nullable = false)
    private BigDecimal price;  // Cena za pronájem

    //@Column(length = 255)
    //private String description;  // Popis cenové položky

    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @Column(nullable = false)
    private String user_type;

    @Column(nullable = false)
    private String reservation_type;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @Column(nullable = false/*, updatable = false*/)
    private Integer facilityId;
}

package cz.expertkom.sportFacilities.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
@Table (name="users")
@Data
    public class User {
//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private String email;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    @NotNull // Use @NotNull for object references
    private UserRole roleId;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String username;

    @Column(nullable = false)
    @NotBlank
    private String password;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;

    /*@Column(length = 20)
    //@NotBlank
    private String phone;

    @Column(length = 100)
    private String street;

    @Column(length = 100)
    private String city;

    @Column(name = "zip_code", length = 10)
    private String zipCode;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String surname;

    @Column(name = "last_edited")
    private LocalDateTime lastEdited;

    @Column(name = "last_edited_by")
    private Long lastEditedBy;*/

    //private Integer role;
}
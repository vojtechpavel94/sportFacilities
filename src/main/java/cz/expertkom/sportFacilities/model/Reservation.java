package cz.expertkom.sportFacilities.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

    @Entity
    @Table(name="reservations")
    @Data
    public class Reservation {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int reservationId;

        @ManyToOne
        @JoinColumn(name = "user_id")
        @NotNull
        private User userId;

        @ManyToOne
        @JoinColumn(name = "facility_id")
        @NotNull
        private Facility facilityId;

        @DateTimeFormat
        private Time startTime;

        private Time endTime;

        @DateTimeFormat(pattern = "dd-MM-yyyy")
        private Date createdAt;

        @Column
        private String status;

        @Column
        private Date startDate;

        @Column
        private String paymentMethod;

        @Column
        private String contact;
}

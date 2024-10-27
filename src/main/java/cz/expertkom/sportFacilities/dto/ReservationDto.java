package cz.expertkom.sportFacilities.dto;

import lombok.Data;
import java.sql.Date;
import java.sql.Time;

@Data
public class ReservationDto {
    private int userId;
    private int facilityId;
    private Time startTime;
    private Time endTime;
    private String status;
    private Date startDate;
    private String paymentMethod;
    //private String contact;
    private String email;
}

package cz.expertkom.sportFacilities.service;

import cz.expertkom.sportFacilities.dto.ReservationDto;
import cz.expertkom.sportFacilities.exception.ResourceNotFoundException;
import cz.expertkom.sportFacilities.model.Facility;
import cz.expertkom.sportFacilities.model.Reservation;
import cz.expertkom.sportFacilities.model.User;
import cz.expertkom.sportFacilities.repository.FacilityRepository;
import cz.expertkom.sportFacilities.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    private final UserRepository userRepository;
    private final FacilityRepository facilityRepository;

    public ReservationDto toDto(Reservation reservation) {
        ReservationDto dto = new ReservationDto();
        dto.setStartTime(reservation.getStartTime());
        dto.setEndTime(reservation.getEndTime());
        dto.setUserId(reservation.getUserId().getUserId());
        dto.setFacilityId(reservation.getFacilityId().getFacilityId());
        dto.setStatus(reservation.getStatus());
        dto.setStartDate(new java.sql.Date(reservation.getStartDate().getTime()));
        dto.setPaymentMethod(reservation.getPaymentMethod());
        dto.setContact(reservation.getContact());
        return dto;
    }

    @Autowired
    public ReservationMapper(UserRepository userRepository, FacilityRepository facilityRepository) {
        this.userRepository = userRepository;
        this.facilityRepository = facilityRepository;
    }
    public Reservation toEntity(ReservationDto dto) {
        Reservation reservation = new Reservation();
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        reservation.setUserId(user);
        Facility facility = facilityRepository.findById(dto.getFacilityId())
                .orElseThrow(() -> new ResourceNotFoundException("Facility not found"));
        reservation.setFacilityId(facility);
        reservation.setStartTime(dto.getStartTime());
        reservation.setEndTime(dto.getEndTime());
        reservation.setStatus(dto.getStatus());
        reservation.setStartDate(dto.getStartDate());
        reservation.setPaymentMethod(dto.getPaymentMethod());
        reservation.setContact(dto.getContact());
        return reservation;
    }
}
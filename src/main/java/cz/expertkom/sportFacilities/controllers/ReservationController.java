package cz.expertkom.sportFacilities.controllers;

import cz.expertkom.sportFacilities.dto.FacilityDto;
import cz.expertkom.sportFacilities.dto.ReservationDto;
import cz.expertkom.sportFacilities.exception.ResourceNotFoundException;
import cz.expertkom.sportFacilities.model.Facility;
import cz.expertkom.sportFacilities.model.Pricing;
import cz.expertkom.sportFacilities.model.Reservation;
import cz.expertkom.sportFacilities.model.User;
import cz.expertkom.sportFacilities.repository.ReservationRepository;
import cz.expertkom.sportFacilities.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
@Slf4j
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
        ReservationDto createdReservation = reservationService.createReservation(reservationDto);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ReservationDto> getAllReservations() {
        return (reservationService.getAllReservations());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable User userId) {
        log.info("#PC&gpi01: getReservation called with userId: {}", userId);
        ReservationDto reservationDto = reservationService.getReservationByUserId(userId);
        return new ResponseEntity<>(reservationDto, reservationDto != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer reservationId) {
        reservationService.deleteReservation(reservationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Integer reservationId, @RequestBody Reservation reservation) {
        return new ResponseEntity<>(reservationService.updateReservation(reservationId, reservation), HttpStatus.OK);
    }

    //vrátit rezervace pro uživatele, případně pak omožnost editovat rezervaci - DONE
    //update - DONE
    //delete - DONE
}

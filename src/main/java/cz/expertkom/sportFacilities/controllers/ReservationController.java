package cz.expertkom.sportFacilities.controllers;

import cz.expertkom.sportFacilities.dto.ReservationDto;
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

    //vrátit rezervace pro uživatele, případně pak omožnost editovat rezervaci
    //update
    //delete
}

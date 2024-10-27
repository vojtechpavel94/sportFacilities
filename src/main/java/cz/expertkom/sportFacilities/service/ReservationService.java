package cz.expertkom.sportFacilities.service;

import cz.expertkom.sportFacilities.dto.ReservationDto;
import cz.expertkom.sportFacilities.exception.ResourceNotFoundException;
import cz.expertkom.sportFacilities.model.Reservation;
import cz.expertkom.sportFacilities.model.User;
import cz.expertkom.sportFacilities.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;
    @Autowired
    public ReservationService(ReservationMapper reservationMapper, ReservationRepository reservationRepository) {
        this.reservationMapper = reservationMapper;
        this.reservationRepository = reservationRepository;
    }

    public ReservationDto createReservation(ReservationDto reservationDto) {
        Reservation reservation = reservationMapper.toEntity(reservationDto);
        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(savedReservation);
    }

    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(reservationMapper::toDto).toList();
    }

    public ReservationDto getReservationByUserId(User userId) {
        Reservation reservation = reservationRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        //bylo potřeba to prohnat přes DTO, abych omezil informace, které se mi vrátí.
        return reservationMapper.toDto(reservation);
    }

    public void deleteReservation(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + reservationId));
        reservationRepository.delete(reservation);
    }

    public ReservationDto updateReservation(Integer id, Reservation reservation) {
        //Načtu rezervaci podle id
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));
        //Nastavit pouze pole, která chci aktualizovat
        existingReservation.setStatus(reservation.getStatus());
        reservationRepository.save(existingReservation);
        return reservationMapper.toDto(existingReservation);
    }
}

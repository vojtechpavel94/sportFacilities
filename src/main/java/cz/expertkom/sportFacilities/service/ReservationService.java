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

    //pokud nedám list a v db existuje více rezervací u jednoho uživatele, tak mi to dropne
    /*public List<ReservationDto> getReservationByUserId(User userId) {
        List<Reservation> reservation = reservationRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        //bylo potřeba to prohnat přes DTO, abych omezil informace, které se mi vrátí.
        return reservationMapper.toDto(reservation);
    }*/

    public List<ReservationDto> getReservationByUserId(User userId) {
        List<Reservation> reservations = reservationRepository.findByUserId(userId);
        if (reservations.isEmpty()) {
            throw new ResourceNotFoundException("No reservations found for user with id " + userId);
        }
        return reservationMapper.toDtoList(reservations);
    }

    public void deleteReservation(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + reservationId));
        reservationRepository.delete(reservation);
    }

    public ReservationDto updateReservation(Integer id, Reservation reservation) {
        //načtu rezervaci podle id
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));
        //nastavit pouze pole, která chci aktualizovat
        existingReservation.setStatus(reservation.getStatus());
        reservationRepository.save(existingReservation);
        return reservationMapper.toDto(existingReservation);
    }
}

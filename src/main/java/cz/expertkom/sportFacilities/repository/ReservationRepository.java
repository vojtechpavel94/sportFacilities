package cz.expertkom.sportFacilities.repository;

import cz.expertkom.sportFacilities.model.Reservation;
import cz.expertkom.sportFacilities.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findByUserId(User userId);
}


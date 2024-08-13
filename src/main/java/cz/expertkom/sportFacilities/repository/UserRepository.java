package cz.expertkom.sportFacilities.repository;

import cz.expertkom.sportFacilities.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Integer> {
}

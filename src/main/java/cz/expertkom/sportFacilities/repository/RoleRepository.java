package cz.expertkom.sportFacilities.repository;

import cz.expertkom.sportFacilities.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <UserRole, Integer> {
}

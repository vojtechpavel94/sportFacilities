package cz.expertkom.sportFacilities.service;

import cz.expertkom.sportFacilities.model.UserRole;
import cz.expertkom.sportFacilities.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    private final RoleRepository roleRepository;

    public UserRoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<UserRole> findAll() {
        return roleRepository.findAll();
    }

    public UserRole getById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }
}

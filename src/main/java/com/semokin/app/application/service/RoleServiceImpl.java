package com.semokin.app.application.service;

import com.semokin.app.application.contract.RoleService;
import com.semokin.app.domain.model.Role;
import com.semokin.app.infrastructure.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Transactional
    public Role getOrSave(Role.Privilege role) {
        Optional<Role> optionalRole = roleRepository.findByRole(role);
        // role available return it
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }

        // role not available create new
        Role currentRole = Role.builder()
                .role(role)
                .build();

        return roleRepository.saveAndFlush(currentRole);
    }
}

package com.semokin.app.application.contract;

import com.semokin.app.domain.model.Role;

public interface RoleService {
    Role getOrSave(Role.Privilege role);
}

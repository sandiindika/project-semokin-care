package com.semokin.app.application.contract;

import com.semokin.app.adapter.dto.request.CustomerCreateRequest;
import com.semokin.app.adapter.dto.request.CustomerUpdateRequest;
import com.semokin.app.adapter.dto.request.StaffCreateRequest;
import com.semokin.app.adapter.dto.request.StaffUpdateRequest;
import com.semokin.app.adapter.dto.response.CustomerResponse;
import com.semokin.app.adapter.dto.response.StaffResponse;
import com.semokin.app.domain.model.User;

import java.util.List;

public interface StaffService {
    StaffResponse createStaff(User user, StaffCreateRequest request);
    StaffResponse getStaffById(User user,String id); // todo: GET /staff/{id}
    void deleteStaffById(User user,String id); // todo: DEL /staff/{id}
    StaffResponse updateStaff(User user, StaffUpdateRequest request); // todo: PATCH /staff/{id}
    List<StaffResponse> getAllStaff(); // todo: GET /staff/

}

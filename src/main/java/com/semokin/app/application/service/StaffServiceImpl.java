package com.semokin.app.application.service;

import com.semokin.app.adapter.dto.request.CustomerUpdateRequest;
import com.semokin.app.adapter.dto.request.StaffCreateRequest;
import com.semokin.app.adapter.dto.request.StaffUpdateRequest;
import com.semokin.app.adapter.dto.response.StaffResponse;
import com.semokin.app.application.contract.StaffService;
import com.semokin.app.application.contract.ValidateService;
import com.semokin.app.domain.exception.ResourceNoContentException;
import com.semokin.app.domain.model.Staff;
import com.semokin.app.domain.model.User;
import com.semokin.app.infrastructure.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private  final StaffRepository staffRepository;
    private final ValidateService validateService;
    @Override
    @Transactional
    public StaffResponse createStaff(User user, StaffCreateRequest request) {
        validateService.validate(request);
        Staff staff = new Staff();
        // Implement business logic here to create a staff response from the given request
        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setPhoneNumber(request.getPhoneNumber());
        staff.setUser(user);
        staffRepository.save(staff);

        return createResponse(staff);
    }

    @Override
    @Transactional(readOnly = true)
    public StaffResponse getStaffById(User user, String id) {
        Staff staff = staffRepository.findFirstByUserAndId(user, id)
                .orElseThrow(()-> new ResourceNoContentException("Could not find staff"));
        return createResponse(staff);
    }

    @Override
    @Transactional
    public void deleteStaffById(User user, String id) {
        Staff staff = staffRepository.findFirstByUserAndId(user, id)
                .orElseThrow(()-> new ResourceNoContentException("Could not find staff"));
        staffRepository.delete(staff);
    }

    @Override
    @Transactional
    public StaffResponse updateStaff(User user, StaffUpdateRequest request) {
        validateService.validate(request);
        Staff staff = staffRepository.findFirstByUserAndId(user, request.getId())
                .orElseThrow(()-> new ResourceNoContentException("Could not find staff"));

        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setPhoneNumber(request.getPhoneNumber());
        staff.setDateOfBirth(request.getDateOfBirth());
        staff.setAddress(request.getAddress());
        staff.setSalary(request.getSalary());
        staffRepository.save(staff);

        return createResponse(staff);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StaffResponse> getAllStaff() {
        return List.of();
    }

    private StaffResponse createResponse(Staff staff) {
        // Implement business logic here to create a staff response from the given staff object
        return StaffResponse.builder()
                .id(staff.getId())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .phoneNumber(staff.getPhoneNumber())
                .dateOfBirth(staff.getDateOfBirth())
                .address(staff.getAddress())
                .salary(staff.getSalary())
                .build();
    }
}

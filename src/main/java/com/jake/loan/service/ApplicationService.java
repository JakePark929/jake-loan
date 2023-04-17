package com.jake.loan.service;

import com.jake.loan.dto.ApplicationDTO;

public interface ApplicationService {
    ApplicationDTO.Response create(ApplicationDTO.Request request);
    ApplicationDTO.Response get(Long applicationId);
}

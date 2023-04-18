package com.jake.loan.service;

import com.jake.loan.dto.ApplicationDTO;

public interface ApplicationService {
    ApplicationDTO.Response get(Long applicationId);
    ApplicationDTO.Response create(ApplicationDTO.Request request);
    ApplicationDTO.Response update(Long applicationId, ApplicationDTO.Request request);
    void delete(Long applicationId);

    Boolean acceptTerms(Long applicationId, ApplicationDTO.AcceptTerms request);
}

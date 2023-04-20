package com.jake.loan.service;

import com.jake.loan.dto.RepaymentDTO;

public interface RepaymentService {
    RepaymentDTO.Response create(Long applicationId, RepaymentDTO.Request request);
}

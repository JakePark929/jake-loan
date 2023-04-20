package com.jake.loan.service;

import com.jake.loan.dto.RepaymentDTO;

import java.util.List;

public interface RepaymentService {
    List<RepaymentDTO.ListResponse> get(Long applicationId);
    RepaymentDTO.Response create(Long applicationId, RepaymentDTO.Request request);
    RepaymentDTO.UpdateResponse update(Long repaymentId, RepaymentDTO.Request request);
}

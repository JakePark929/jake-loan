package com.jake.loan.service;

import com.jake.loan.dto.BalanceDTO;

public interface BalanceService {
    BalanceDTO.Response get(Long applicationId);
    BalanceDTO.Response create(Long applicationId, BalanceDTO.Request request);
    BalanceDTO.Response update(Long applicationId, BalanceDTO.UpdateRequest request);
    BalanceDTO.Response repaymentUpdate(Long applicationId, BalanceDTO.RepaymentRequest request);
    void delete(Long applicationId);
}

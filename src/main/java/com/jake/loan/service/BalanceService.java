package com.jake.loan.service;

import com.jake.loan.dto.BalanceDTO;

public interface BalanceService {
    BalanceDTO.Response create(Long applicationId, BalanceDTO.Request request);
}
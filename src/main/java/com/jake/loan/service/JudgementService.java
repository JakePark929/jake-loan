package com.jake.loan.service;

import com.jake.loan.dto.JudgementDTO;

public interface JudgementService {
    JudgementDTO.Response create(JudgementDTO.Request request);
}

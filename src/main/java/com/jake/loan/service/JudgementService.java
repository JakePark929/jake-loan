package com.jake.loan.service;

import com.jake.loan.dto.ApplicationDTO;
import com.jake.loan.dto.JudgementDTO;

public interface JudgementService {
    JudgementDTO.Response get(Long judgementId);
    JudgementDTO.Response getJudgementOfApplication(Long applicationId);
    JudgementDTO.Response create(JudgementDTO.Request request);
    JudgementDTO.Response update(Long judgementId, JudgementDTO.Request request);
    void delete(Long judgementId);
    ApplicationDTO.GrantAmount grant(Long judgementId);
}

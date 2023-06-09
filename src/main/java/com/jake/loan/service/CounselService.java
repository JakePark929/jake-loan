package com.jake.loan.service;

import com.jake.loan.dto.CounselDTO;

public interface CounselService {
    CounselDTO.Response get(Long counselId);
    CounselDTO.Response create(CounselDTO.Request request);
    CounselDTO.Response update(Long counselId, CounselDTO.Request request);
    void delete(Long counselId);
}

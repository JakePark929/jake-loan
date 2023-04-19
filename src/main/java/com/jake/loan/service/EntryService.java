package com.jake.loan.service;

import com.jake.loan.dto.EntryDTO;

public interface EntryService {
    EntryDTO.Response create(Long applicationId, EntryDTO.Request request);
}

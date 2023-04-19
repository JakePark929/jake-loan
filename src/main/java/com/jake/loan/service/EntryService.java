package com.jake.loan.service;

import com.jake.loan.dto.EntryDTO;

public interface EntryService {
    EntryDTO.Response get(Long applicationId);
    EntryDTO.Response create(Long applicationId, EntryDTO.Request request);
    EntryDTO.UpdateResponse update(Long entryId, EntryDTO.Request request);
}

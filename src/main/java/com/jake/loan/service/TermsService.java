package com.jake.loan.service;

import com.jake.loan.dto.TermsDTO;

import java.util.List;

public interface TermsService {
    List<TermsDTO.Response> getAll();
    TermsDTO.Response create(Object request);
}


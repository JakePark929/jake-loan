package com.jake.loan.service;

import com.jake.loan.domain.Terms;
import com.jake.loan.dto.TermsDTO;
import com.jake.loan.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class TermsServiceImpl implements TermsService {
    private final TermsRepository termsRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TermsDTO.Response> getAll() {
        List<Terms> termsList = termsRepository.findAll();

        return termsList.stream().map(t -> modelMapper.map(t, TermsDTO.Response.class)).collect(Collectors.toList());
    }

    @Override
    public TermsDTO.Response create(Object request) {
        Terms terms = modelMapper.map(request, Terms.class);
        Terms created = termsRepository.save(terms);
        return modelMapper.map(created, TermsDTO.Response.class);
    }
}

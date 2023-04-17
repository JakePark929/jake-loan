package com.jake.loan.service;

import com.jake.loan.domain.Counsel;
import com.jake.loan.dto.CounselDTO;
import com.jake.loan.repository.CounselRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CounselServiceImpl implements CounselService {
    private final CounselRepository counselRepository;
    private final ModelMapper modelMapper;

    @Override
    public CounselDTO.Response create(CounselDTO.Request request) {
        Counsel counsel = modelMapper.map(request, Counsel.class);
        counsel.setAppliedAt(LocalDateTime.now());

        Counsel created = counselRepository.save(counsel);

        return modelMapper.map(created, CounselDTO.Response.class);
    }
}

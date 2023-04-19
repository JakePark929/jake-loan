package com.jake.loan.service;

import com.jake.loan.domain.Application;
import com.jake.loan.domain.Entry;
import com.jake.loan.dto.BalanceDTO;
import com.jake.loan.dto.EntryDTO;
import com.jake.loan.exception.BaseException;
import com.jake.loan.exception.ResultType;
import com.jake.loan.repository.ApplicationRepository;
import com.jake.loan.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EntryServiceImpl implements EntryService {
    private final BalanceService balanceService;
    private final EntryRepository entryRepository;
    private final ApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;

    @Override
    public EntryDTO.Response get(Long applicationId) {
        Optional<Entry> entry = entryRepository.findByApplicationId(applicationId);
        if (entry.isPresent()) {
            return modelMapper.map(entry, EntryDTO.Response.class);
        } else {
            return null;
        }
    }

    @Override
    public EntryDTO.Response create(Long applicationId, EntryDTO.Request request) {
        // 계약 체결 여부 검증
        if (!isContractedApplication(applicationId)) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }

        Entry entry = modelMapper.map(request, Entry.class);
        entry.setApplicationId(applicationId);

        entryRepository.save(entry);

        // 대출 잔고 관리
        balanceService.create(applicationId,
                BalanceDTO.Request.builder()
                        .entryAmount(request.getEntryAmount())
                        .build());

        return modelMapper.map(entry, EntryDTO.Response.class);
    }

    private boolean isContractedApplication(Long applicationId) {
        // findByApplicationIdAndContractedNotNull 로도 처리할 수 있음
        Optional<Application> existed = applicationRepository.findById(applicationId);
        if (existed.isEmpty()) {
            return false;
        }

        return existed.get().getContractedAt() != null;
    }
}

package com.jake.loan.service;

import com.jake.loan.constant.RepaymentType;
import com.jake.loan.domain.Application;
import com.jake.loan.domain.Entry;
import com.jake.loan.domain.Repayment;
import com.jake.loan.dto.BalanceDTO;
import com.jake.loan.dto.RepaymentDTO;
import com.jake.loan.exception.BaseException;
import com.jake.loan.exception.ResultType;
import com.jake.loan.repository.ApplicationRepository;
import com.jake.loan.repository.EntryRepository;
import com.jake.loan.repository.RepaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RepaymentServiceImpl implements RepaymentService {
    private final RepaymentRepository repaymentRepository;
    private final ApplicationRepository applicationRepository;
    private final EntryRepository entryRepository;
    private final BalanceService balanceService;
    private final ModelMapper modelMapper;

    @Override
    public RepaymentDTO.Response create(Long applicationId, RepaymentDTO.Request request) {
        // validation
        // 1. 계약을 완료한 신청정보
        // 2. 집행이 되어 있어야 함
        if (!isRepayableApplication(applicationId)) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }

        Repayment repayment = modelMapper.map(request, Repayment.class);
        repayment.setApplicationId(applicationId);

        repaymentRepository.save(repayment);

        // 잔고
        // balance : 500 -> 100 = 400
        BalanceDTO.Response updatedBalance = balanceService.repaymentUpdate(applicationId,
                BalanceDTO.RepaymentRequest.builder()
                        .repaymentAmount(request.getRepaymentAmount())
                        .type(RepaymentType.REMOVE)
                        .build()
        );

        RepaymentDTO.Response response = modelMapper.map(repayment, RepaymentDTO.Response.class);
        response.setBalance(updatedBalance.getBalance());

        return response;
    }

    private boolean isRepayableApplication(Long applicationId) {
        Optional<Application> existApplication = applicationRepository.findById(applicationId);
        if (existApplication.isEmpty()) {
            return false;
        }
        if (existApplication.get().getContractedAt() == null) {
            return false;
        }

        Optional<Entry> existedEntry = entryRepository.findByApplicationId(applicationId);
        return existedEntry.isPresent();
    }
}

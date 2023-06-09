package com.jake.loan.service;

import com.jake.loan.domain.Application;
import com.jake.loan.domain.Judgement;
import com.jake.loan.dto.ApplicationDTO;
import com.jake.loan.dto.JudgementDTO;
import com.jake.loan.repository.ApplicationRepository;
import com.jake.loan.repository.JudgementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JudgementServiceImplTest {
    @InjectMocks
    private JudgementServiceImpl judgementService;

    @Mock
    private JudgementRepository judgementRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseOfExistJudgementEntity_When_RequestExistJudgementId() {
        Judgement entity = Judgement.builder()
                .judgementId(1L)
                .build();

        when(judgementRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));

        JudgementDTO.Response actual = judgementService.get(1L);

        assertThat(actual.getJudgementId()).isSameAs(1L);
    }

    @Test
    void Should_ReturnResponseOfExistJudgementEntity_When_RequestExistApplicationId() {
        Application applicationEntity = Application.builder()
                .applicationId(1L)
                .build();

        Judgement judgementEntity = Judgement.builder()
                .judgementId(1L)
                .build();

        when(applicationRepository.findById(1L)).thenReturn(Optional.ofNullable(applicationEntity));
        when(judgementRepository.findByApplicationId(1L)).thenReturn(Optional.ofNullable(judgementEntity));

        JudgementDTO.Response actual = judgementService.getJudgementOfApplication(1L);

        assertThat(actual.getJudgementId()).isSameAs(1L);
    }

    @Test
    void Should_ReturnResponseOfSetJudgementEntity_When_RequestNewJudgement() {
        Judgement judgement = Judgement.builder()
                .applicationId(1L)
                .name("Member Kim")
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();

        JudgementDTO.Request request = JudgementDTO.Request.builder()
                .applicationId(1L)
                .name("Member Kim")
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();

        // application find
        when(applicationRepository.findById(1L)).thenReturn(Optional.ofNullable(Application.builder().build()));

        // judgement save
        when(judgementRepository.save(any(Judgement.class))).thenReturn(judgement);

        JudgementDTO.Response actual = judgementService.create(request);

        assertThat(actual.getName()).isSameAs(judgement.getName());
        assertThat(actual.getApplicationId()).isSameAs(judgement.getApplicationId());
        assertThat(actual.getApprovalAmount()).isSameAs(judgement.getApprovalAmount());
    }

    @Test
    void Should_ReturnUpdatedResponseOfExistJudgementEntity_When_RequestUpdateExistJudgementInfo() {
        Judgement entity = Judgement.builder()
                .judgementId(1L)
                .name("Member Kim")
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();

        JudgementDTO.Request request = JudgementDTO.Request.builder()
                .name("Member Lee")
                .approvalAmount(BigDecimal.valueOf(10000000))
                .build();

        when(judgementRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        when(judgementRepository.save(any(Judgement.class))).thenReturn(entity);

        JudgementDTO.Response actual = judgementService.update(1L, request);

        assertThat(actual.getJudgementId()).isSameAs(1L);
        assertThat(actual.getName()).isSameAs(request.getName());
        assertThat(actual.getApprovalAmount()).isSameAs(request.getApprovalAmount());
    }

    @Test
    void Should_DeletedJudgementEntity_When_RequestDeleteExistJudgementInfo() {
        Judgement entity = Judgement.builder()
                .judgementId(1L)
                .build();

        when(judgementRepository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        when(judgementRepository.save(any(Judgement.class))).thenReturn(entity);

        judgementService.delete(1L);

        assertThat(entity.getIsDeleted()).isTrue();
    }

    @Test
    void Should_ReturnUpdateResponseOfExistApplicationEntity_When_RequestGrantApprovalAmountOfJudgementInfo() {
        Judgement judgementEntity = Judgement.builder()
                .name("Member Kim")
                .applicationId(1L)
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();

        Application applicationEntity = Application.builder()
                .applicationId(1L)
                .approvalAmount(BigDecimal.valueOf(5000000))
                .build();

        when(judgementRepository.findById(1L)).thenReturn(Optional.ofNullable(judgementEntity));
        when(applicationRepository.findById(1L)).thenReturn(Optional.ofNullable(applicationEntity));
        when(applicationRepository.save(any(Application.class))).thenReturn(applicationEntity);

        ApplicationDTO.GrantAmount actual = judgementService.grant(1L);

        assertThat(actual.getApplicationId()).isSameAs(1L);
        assertThat(actual.getApprovalAmount()).isSameAs(judgementEntity.getApprovalAmount());
    }
}
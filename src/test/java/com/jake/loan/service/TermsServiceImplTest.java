package com.jake.loan.service;

import com.jake.loan.domain.Terms;
import com.jake.loan.dto.TermsDTO;
import com.jake.loan.repository.TermsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TermsServiceImplTest {
    @InjectMocks
    TermsServiceImpl termsService;

    @Mock
    private TermsRepository termsRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnAllResponseOfExistTermsEntities_When_RequestTermsList() {
        Terms entityA = Terms.builder()
                .name("대출 이용약관 1")
                .termsDetailUrl("https://abc-storage.acc/asdasdasdq")
                .build();

        Terms entityB = Terms.builder()
                .name("대출 이용약관 2")
                .termsDetailUrl("https://abc-storage.acc/assdfqewasdq")
                .build();

        List<Terms> list = new ArrayList<>(Arrays.asList(entityA, entityB));

        when(termsRepository.findAll()).thenReturn(Arrays.asList(entityA, entityB));

        List<TermsDTO.Response> actual = termsService.getAll();

        assertThat(actual.size()).isSameAs(list.size());
    }

    @Test
    void Should_ReturnResponseOfNewTermsEntity_When_RequestTerms() {
        Terms entity = Terms.builder()
                .name("대출 이용 약관")
                .termsDetailUrl("https://abc-storage.acc/djlksadisladsa")
                .build();

        TermsDTO.Request request = TermsDTO.Request.builder()
                .name("대출 이용 약관")
                .termsDetailUrl("https://abc-storage.acc/djlksadisladsa")
                .build();

        when(termsRepository.save(any(Terms.class))).thenReturn(entity);

        TermsDTO.Response actual = termsService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
        assertThat(actual.getTermsDetailUrl()).isSameAs(entity.getTermsDetailUrl());

        termsService.create(request);
    }
}
package com.jake.loan.controller;

import com.jake.loan.dto.ResponseDTO;
import com.jake.loan.dto.TermsDTO;
import com.jake.loan.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/terms")
@RestController
public class TermsController extends AbstractController {
    private final TermsService termsService;

    @GetMapping
    public ResponseDTO<List<TermsDTO.Response>> getAll() {
        return ok(termsService.getAll());
    }

    @PostMapping
    public ResponseDTO<TermsDTO.Response> create(@RequestBody TermsDTO.Request request) {
        return ok(termsService.create(request));
    }
}

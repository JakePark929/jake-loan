package com.jake.loan.controller;

import com.jake.loan.dto.JudgementDTO;
import com.jake.loan.dto.ResponseDTO;
import com.jake.loan.service.JudgementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/judgements")
@RestController
public class JudgementController extends AbstractController {
    private final JudgementService judgementService;

    @PostMapping
    public ResponseDTO<JudgementDTO.Response> create(@RequestBody JudgementDTO.Request request) {
        return ok(judgementService.create(request));
    }
}

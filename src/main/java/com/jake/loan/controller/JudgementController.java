package com.jake.loan.controller;

import com.jake.loan.dto.JudgementDTO;
import com.jake.loan.dto.ResponseDTO;
import com.jake.loan.service.JudgementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/judgements")
@RestController
public class JudgementController extends AbstractController {
    private final JudgementService judgementService;

    @GetMapping("/{judgementId}")
    public ResponseDTO<JudgementDTO.Response> get(@PathVariable Long judgementId) {
        return ok(judgementService.get(judgementId));
    }

    @GetMapping("/applications/{applicationId}")
    public ResponseDTO<JudgementDTO.Response> getJudgementOfApplication(@PathVariable Long applicationId) {
        return ok(judgementService.getJudgementOfApplication(applicationId));
    }

    @PostMapping
    public ResponseDTO<JudgementDTO.Response> create(@RequestBody JudgementDTO.Request request) {
        return ok(judgementService.create(request));
    }
}

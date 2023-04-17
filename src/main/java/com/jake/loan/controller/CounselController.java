package com.jake.loan.controller;

import com.jake.loan.dto.CounselDTO;
import com.jake.loan.dto.ResponseDTO;
import com.jake.loan.service.CounselService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/counsels")
@RestController
public class CounselController extends AbstractController {
    private final CounselService counselService;

    @GetMapping("/{counselId}")
    public ResponseDTO<CounselDTO.Response> get(@PathVariable Long counselId) {
        return ok(counselService.get(counselId));
    }

    @PostMapping
    public ResponseDTO<CounselDTO.Response> create(@RequestBody CounselDTO.Request request) {
        return ok(counselService.create(request));
    }

    @PutMapping("/{counselId}")
    public ResponseDTO<CounselDTO.Response> update(@PathVariable Long counselId, @RequestBody CounselDTO.Request request) {
        return ok(counselService.update(counselId, request));
    }
}

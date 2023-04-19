package com.jake.loan.controller;

import com.jake.loan.dto.EntryDTO;
import com.jake.loan.dto.ResponseDTO;
import com.jake.loan.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/internal/applications")
@RestController
public class InternalController extends AbstractController {
    private final EntryService entryService;

    @PostMapping("/{applicationId}/entries")
    public ResponseDTO<EntryDTO.Response> create(@PathVariable Long applicationId, @RequestBody EntryDTO.Request request) {
        return ok(entryService.create(applicationId, request));
    }
}

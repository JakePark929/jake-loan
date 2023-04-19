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

    @GetMapping("{applicationId}/entries")
    public ResponseDTO<EntryDTO.Response> get(@PathVariable Long applicationId) {
        return ok(entryService.get(applicationId));
    }

    @PostMapping("/{applicationId}/entries")
    public ResponseDTO<EntryDTO.Response> create(@PathVariable Long applicationId, @RequestBody EntryDTO.Request request) {
        return ok(entryService.create(applicationId, request));
    }

    @PutMapping("/entries/{entryId}")
    public ResponseDTO<EntryDTO.UpdateResponse> update(@PathVariable Long entryId, @RequestBody EntryDTO.Request request) {
        return ok(entryService.update(entryId, request));
    }

    @DeleteMapping("/entries/{entryId}")
    public ResponseDTO<Void> delete(@PathVariable Long entryId) {
        entryService.delete(entryId);
        return ok();
    }
}

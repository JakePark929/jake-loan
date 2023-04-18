package com.jake.loan.controller;

import com.jake.loan.dto.ApplicationDTO;
import com.jake.loan.dto.ResponseDTO;
import com.jake.loan.service.ApplicationService;
import com.jake.loan.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/applications")
@RestController
public class ApplicationController extends AbstractController {
    private final ApplicationService applicationService;
    private final FileStorageService fileStorageService;

    @GetMapping("/{applicationId}")
    public ResponseDTO<ApplicationDTO.Response> get(@PathVariable Long applicationId) {
        return ok(applicationService.get(applicationId));
    }

    @PostMapping
    public ResponseDTO<ApplicationDTO.Response> create(@RequestBody ApplicationDTO.Request request) {
        return ok(applicationService.create(request));
    }

    @PutMapping("/{applicationId}")
    public ResponseDTO<ApplicationDTO.Response> update(@PathVariable Long applicationId, @RequestBody ApplicationDTO.Request request) {
        return ok(applicationService.update(applicationId, request));
    }

    @DeleteMapping("/{applicationId}")
    public ResponseDTO<Void> delete(@PathVariable Long applicationId) {
        applicationService.delete(applicationId);

        return ok();
    }

    @PostMapping("/{applicationId}/terms")
    public ResponseDTO<Boolean> acceptTerms(@PathVariable Long applicationId, @RequestBody ApplicationDTO.AcceptTerms request) {
        return ok(applicationService.acceptTerms(applicationId, request));
    }

    @PostMapping("/files")
    public ResponseDTO<Void> upload(MultipartFile file) {
        fileStorageService.save(file);
        return ok();
    }
}

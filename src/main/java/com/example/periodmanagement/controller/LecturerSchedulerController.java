package com.example.periodmanagement.controller;

import com.example.periodmanagement.dto.ApiMessageDto;
import com.example.periodmanagement.dto.PageResponseDto;
import com.example.periodmanagement.dto.lecturerScheduler.LecturerSchedulerDto;
import com.example.periodmanagement.form.lecturerSchduler.CreateLecturerSchedulerForm;
import com.example.periodmanagement.form.lecturerSchduler.UpdateLecturerSchedulerForm;
import com.example.periodmanagement.model.criteria.LecturerSchedulerCriteria;
import com.example.periodmanagement.service.LecturerSchedulerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/lecturer-scheduler")
@Tag(name = "Lecturer Scheduler Controller")
public class LecturerSchedulerController {

    LecturerSchedulerService lecturerSchedulerService;

    // create
    @PostMapping(path = "/create")
    @PreAuthorize("hasAuthority('LEC_SDL_CRE')")
    public ResponseEntity<ApiMessageDto<LecturerSchedulerDto>> create(@RequestBody @Valid CreateLecturerSchedulerForm form) {
        LecturerSchedulerDto lecturerSchedulerDto = lecturerSchedulerService.createLecturerScheduler(form);

        ApiMessageDto<LecturerSchedulerDto> response = ApiMessageDto.<LecturerSchedulerDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Create lecturer scheduler successfully")
                .data(lecturerSchedulerDto)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // get paging and filtering
    @GetMapping(path = "/get/{id}")
    @PreAuthorize("hasAuthority('LEC_SDL_GET')")
    public ApiMessageDto<LecturerSchedulerDto> getById(@PathVariable("id") Long id) {
        LecturerSchedulerDto lecturerSchedulerDto = lecturerSchedulerService.getLecturerScheduler(id);

        return ApiMessageDto.<LecturerSchedulerDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get lecturer scheduler successfully")
                .data(lecturerSchedulerDto)
                .build();
    }

    // get paging and filtering
    @GetMapping(path = "/list")
    @PreAuthorize("hasAuthority('LEC_SDL_GET')")
    public ApiMessageDto<PageResponseDto<LecturerSchedulerDto>> list(
            @ModelAttribute LecturerSchedulerCriteria lecturerSchedulerCriteria,
            Pageable pageable
    ) {
        PageResponseDto<LecturerSchedulerDto> pageResponse = lecturerSchedulerService.getPageLecturerScheduler(lecturerSchedulerCriteria, pageable);

        return ApiMessageDto.<PageResponseDto<LecturerSchedulerDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get list lecturer scheduler successfully")
                .data(pageResponse)
                .build();
    }

    // update
    @PutMapping(path = "/update")
    @PreAuthorize("hasAuthority('LEC_SDL_UPD')")
    public ResponseEntity<ApiMessageDto<LecturerSchedulerDto>> update(@RequestBody @Valid UpdateLecturerSchedulerForm form) {
        LecturerSchedulerDto lecturerSchedulerDto = lecturerSchedulerService.updateLecturerScheduler(form);

        ApiMessageDto<LecturerSchedulerDto> response = ApiMessageDto.<LecturerSchedulerDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Update lecturer scheduler successfully")
                .data(lecturerSchedulerDto)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // delete
    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('LEC_SDL_DEL')")
    public ResponseEntity<ApiMessageDto<Void>> delete(@PathVariable Long id) {
        lecturerSchedulerService.deleteLecturerScheduler(id);

        ApiMessageDto<Void> response = ApiMessageDto.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Delete lecturer scheduler successfully")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}

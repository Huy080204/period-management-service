package com.example.periodmanagement.controller;

import com.example.periodmanagement.dto.ApiMessageDto;
import com.example.periodmanagement.dto.PageResponseDto;
import com.example.periodmanagement.dto.period.PeriodDto;
import com.example.periodmanagement.form.period.CreatePeriodForm;
import com.example.periodmanagement.form.period.UpdatePeriodForm;
import com.example.periodmanagement.model.criteria.PeriodCriteria;
import com.example.periodmanagement.service.PeriodService;
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
@RequestMapping(path = "/api/period")
@Tag(name = "Period Controller")
public class PeriodController {

    PeriodService periodService;

    // create
    @PostMapping(path = "/create")
    @PreAuthorize("hasAuthority('PER_CRE')")
    public ResponseEntity<ApiMessageDto<PeriodDto>> create(@RequestBody @Valid CreatePeriodForm form) {
        PeriodDto periodDto = periodService.createPeriod(form);

        ApiMessageDto<PeriodDto> response = ApiMessageDto.<PeriodDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Create period successfully")
                .data(periodDto)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // get by id
    @GetMapping(path = "/get/{id}")
    @PreAuthorize("hasAuthority('PER_GET')")
    public ApiMessageDto<PeriodDto> getById(@PathVariable Long id) {
        PeriodDto pageResponse = periodService.getPeriod(id);

        return ApiMessageDto.<PeriodDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get period successfully")
                .data(pageResponse)
                .build();
    }

    // get paging and filtering
    @GetMapping(path = "/list")
    @PreAuthorize("hasAuthority('PER_GET')")
    public ApiMessageDto<PageResponseDto<PeriodDto>> list(
            @ModelAttribute PeriodCriteria periodCriteria,
            Pageable pageable
    ) {
        PageResponseDto<PeriodDto> pageResponse = periodService.getPagePeriods(periodCriteria, pageable);

        return ApiMessageDto.<PageResponseDto<PeriodDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get periods successfully")
                .data(pageResponse)
                .build();
    }

    // update
    @PutMapping(path = "/update")
    @PreAuthorize("hasAuthority('PER_UPD')")
    public ResponseEntity<ApiMessageDto<PeriodDto>> update(@RequestBody @Valid UpdatePeriodForm form) {
        PeriodDto periodDto = periodService.updatePeriod(form);

        ApiMessageDto<PeriodDto> response = ApiMessageDto.<PeriodDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Update period successfully")
                .data(periodDto)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // delete
    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('PER_DEL')")
    public ResponseEntity<ApiMessageDto<Void>> delete(@PathVariable Long id) {
        periodService.deletePeriod(id);

        ApiMessageDto<Void> response = ApiMessageDto.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Delete period successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

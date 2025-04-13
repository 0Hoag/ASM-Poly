package com.example.ASM.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.SpecificationType.SpecificationTypeRequest;
import com.example.ASM.dto.request.SpecificationType.SpecificationTypeUpdateRequest;
import com.example.ASM.dto.response.product.SpecificationTypeResponse;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.SpecificationTypeMapper;
import com.example.ASM.repository.SpecificationTypeRepository;
import com.example.ASM.service.build.SpecificationTypeBuilder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpecificationTypeService {
    SpecificationTypeMapper mapper;
    SpecificationTypeRepository repo;
    SpecificationTypeBuilder builder;

    public boolean Create(SpecificationTypeRequest request) {
        builder.processRequest(request);

        try {
            repo.save(mapper.toSpecificationType(request));
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }

        return true;
    }

    public SpecificationTypeResponse Detail(int id) {
        var spec = repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.SPECIFICATION_TYPE_NOT_EXISTED));
        return mapper.toSpecificationTypeResponse(spec);
    }

    public List<SpecificationTypeResponse> List() {
        return repo.findAll().stream().map(mapper::toSpecificationTypeResponse).collect(Collectors.toList());
    }

    public PageResponse<SpecificationTypeResponse> Get(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = repo.findAll(pageable);

        var data = pageData.getContent().stream()
                .map(mapper::toSpecificationTypeResponse)
                .collect(Collectors.toList());

        return PageResponse.<SpecificationTypeResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .data(data)
                .build();
    }

    public SpecificationTypeResponse Update(int id, SpecificationTypeUpdateRequest request) {
        var spec = repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.SPECIFICATION_TYPE_NOT_EXISTED));
        builder.processUpdateRequest(request);
        mapper.updateSpecificationType(spec, request);
        return mapper.toSpecificationTypeResponse(repo.save(spec));
    }

    public void Delete(int id) {
        if (!repo.existsById(id)) {
            throw new AppException(ErrorCode.SPECIFICATION_TYPE_NOT_EXISTED);
        }

        repo.deleteById(id);
    }
}

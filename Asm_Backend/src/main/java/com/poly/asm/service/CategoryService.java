package com.poly.asm.service;

import com.poly.asm.dto.PageResponse;
import com.poly.asm.dto.request.Category.CategoryRequest;
import com.poly.asm.dto.request.Category.CategoryUpdateRequest;
import com.poly.asm.dto.response.CategoryResponse;
import com.poly.asm.exception.AppException;
import com.poly.asm.exception.ErrorCode;
import com.poly.asm.mapper.CategoryMapper;
import com.poly.asm.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryMapper mapper;
    CategoryRepository repo;

    public Boolean CreateCategory(CategoryRequest request) {
        if (request.getCategoryName().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        var existed = repo.existsByCategoryName(request.getCategoryName());
        if (existed) {
            throw new AppException(ErrorCode.CATEGORIES_NAME_EXISTED);
        }

        if (request.getParentCategory() != 0) {
            repo.findById(request.getParentCategory())
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORIES_NOT_EXISTED));
        }

        try {
            repo.save(mapper.toCategory(request));
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }

        return true;
    }

    public CategoryResponse Detail(int id) {
        var cate = repo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORIES_NOT_EXISTED));
        return mapper.toCategoryResponse(cate);
    }

    public List<CategoryResponse> List() {
        return repo.findAll().stream()
                .map(mapper::toCategoryResponse)
                .collect(Collectors.toList());
    }


    public PageResponse<CategoryResponse> Get(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = repo.findAll(pageable);

        var data = pageData.getContent().stream()
                .map(category -> {
                    var cate = repo.findById(category.getId())
                            .orElseThrow(() -> new AppException(ErrorCode.CATEGORIES_NOT_EXISTED));
                    return mapper.toCategoryResponse(cate);
                }).collect(Collectors.toList());

        return PageResponse.<CategoryResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .data(data)
                .build();
    }

    public CategoryResponse Update(int id, CategoryUpdateRequest request) {
        var cate = repo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORIES_NOT_EXISTED));

        mapper.updateCategory(cate, request);
        return mapper.toCategoryResponse(repo.save(cate));
    }

    public void Delete(int id) {
        if (!repo.existsById(id)) {
            throw new AppException(ErrorCode.CATEGORIES_NOT_EXISTED);
        }

        repo.deleteById(id);
    }
}

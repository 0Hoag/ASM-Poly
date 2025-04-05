package com.example.ASM.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.OrderStatus.OrderStatusRequest;
import com.example.ASM.dto.response.order.OrderStatusResponse;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.OrderStatusMapper;
import com.example.ASM.repository.OrderStatusRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderStatusService {
    OrderStatusRepository repo;
    OrderStatusMapper mapper;

    public Boolean Create(OrderStatusRequest request) {
        if (request.getStatusName().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_INPUT);
        }

        try {
            repo.save(mapper.toOrderStatus(request));
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
        return true;
    }

    public OrderStatusResponse Detail(int id) {
        var status = repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDERS_STATUS_NOT_EXISTED));
        return mapper.toOrderStatusResponse(status);
    }

    public List<OrderStatusResponse> List() {
        return repo.findAll().stream().map(mapper::toOrderStatusResponse).collect(Collectors.toList());
    }

    public PageResponse<OrderStatusResponse> Get(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = repo.findAll(pageable);

        var data = pageData.getContent().stream()
                .map(mapper::toOrderStatusResponse)
                .collect(Collectors.toList());

        return PageResponse.<OrderStatusResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .data(data)
                .build();
    }

    public Boolean Update(int id, OrderStatusRequest request) {
        var status = repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDERS_STATUS_NOT_EXISTED));

        status.setStatusName(request.getStatusName());
        repo.save(status);
        return true;
    }

    public void Delete(int id) {
        if (!repo.existsById(id)) {
            throw new AppException(ErrorCode.ORDERS_STATUS_NOT_EXISTED);
        }
        repo.deleteById(id);
    }
}

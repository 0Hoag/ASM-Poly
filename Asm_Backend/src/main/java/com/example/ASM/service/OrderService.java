package com.example.ASM.service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.Order.OrderRequest;
import com.example.ASM.dto.request.Order.OrderUpdateRequest;
import com.example.ASM.dto.response.order.OrderResponse;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.OrderMapper;
import com.example.ASM.repository.OrderRepository;
import com.example.ASM.service.build.OrderBuilder;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderService {
    OrderRepository repo;
    OrderMapper mapper;
    OrderBuilder builder;
    OrderDetailService orderDetailService;

    @Transactional
    public Boolean Create(OrderRequest request) {
        builder.processRequest(request);

        try {

            // Save order first to get the ID
            var savedOrder = repo.save(builder.buildOrder(request));

            if (request.getOrderDetails() != null && !request.getOrderDetails().isEmpty()) {
                // Use OrderDetailService to create each order detail
                request.getOrderDetails().forEach(detailRequest -> {
                    detailRequest.setOrderId(savedOrder.getId());
                    orderDetailService.Create(detailRequest);
                });
            }

            return true;
        } catch (DataIntegrityViolationException e) {
            log.error("Error creating order", e);
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
    }

    public OrderResponse Detail(int id) {
        var order = repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDERS_NOT_EXISTED));
        return mapper.toOrderResponse(order);
    }

    public List<OrderResponse> List() {
        return repo.findAll().stream().map(mapper::toOrderResponse).collect(Collectors.toList());
    }

    public PageResponse<OrderResponse> Get(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = repo.findAll(pageable);

        var data = pageData.getContent().stream().map(mapper::toOrderResponse).collect(Collectors.toList());

        return PageResponse.<OrderResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .data(data)
                .build();
    }

    public OrderResponse Update(int id, OrderUpdateRequest request) {
        var order = repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDERS_NOT_EXISTED));

        builder.processUpdateRequest(request);

        mapper.update(order, request);
        return mapper.toOrderResponse(repo.save(order));
    }

    public void Delete(int id) {
        if (!repo.existsById(id)) {
            throw new AppException(ErrorCode.ORDERS_NOT_EXISTED);
        }

        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
    }

    public PageResponse<OrderResponse> getOrdersByUserId(int userId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = repo.findByUserId(userId, pageable);

        var data = pageData.getContent().stream().map(mapper::toOrderResponse).collect(Collectors.toList());
        return PageResponse.<OrderResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .data(data)
                .build();
    }
}

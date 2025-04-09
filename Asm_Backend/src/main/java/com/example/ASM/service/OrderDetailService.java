package com.example.ASM.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.OrderDetail.OrderDetailRequest;
import com.example.ASM.dto.request.OrderDetail.OrderDetailUpdateRequest;
import com.example.ASM.dto.response.order.OrderDetailResponse;
import com.example.ASM.entity.Order;
import com.example.ASM.entity.OrderDetail;
import com.example.ASM.entity.Product;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.OrderDetailMapper;
import com.example.ASM.repository.OrderDetailRepository;
import com.example.ASM.repository.OrderRepository;
import com.example.ASM.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailRepository repo;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDetailMapper mapper;

    @Transactional
    public Boolean Create(OrderDetailRequest request) {
        Order order = orderRepository
                .findById(request.getOrderId())
                .orElseThrow(() -> new AppException(ErrorCode.ORDERS_NOT_EXISTED));

        Product product = productRepository
                .findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));

        Optional<OrderDetail> existingDetail =
                repo.findByOrderIdAndProductId(request.getOrderId(), request.getProductId());
        if (existingDetail.isPresent()) {
            OrderDetail orderDetail = existingDetail.get();
            orderDetail.setQuantity(orderDetail.getQuantity() + request.getQuantity());
            repo.save(orderDetail);
            return true;
        }

        OrderDetail newOrderDetail = new OrderDetail();
        newOrderDetail.setOrder(order);
        newOrderDetail.setProduct(product);
        newOrderDetail.setQuantity(request.getQuantity());
        newOrderDetail.setCurrentPrice(product.getPrice());

        repo.save(newOrderDetail);

        return true;
    }

    public OrderDetailResponse Detail(int id) {
        OrderDetail orderDetail =
                repo.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_FOUND));
        return mapper.toOrderDetailResponse(orderDetail);
    }

    public PageResponse<OrderDetailResponse> Get(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = repo.findAll(pageable);

        var data = pageData.getContent().stream()
                .map(mapper::toOrderDetailResponse)
                .collect(Collectors.toList());

        return PageResponse.<OrderDetailResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .data(data)
                .build();
    }

    public List<OrderDetailResponse> List() {
        List<OrderDetail> orderDetails = repo.findAll();
        return orderDetails.stream().map(mapper::toOrderDetailResponse).collect(Collectors.toList());
    }

    public List<OrderDetailResponse> GetByOrderId(int orderId) {
        List<OrderDetail> orderDetails = repo.findByOrderId(orderId);
        return orderDetails.stream().map(mapper::toOrderDetailResponse).collect(Collectors.toList());
    }

    @Transactional
    public OrderDetailResponse UpdateQuantity(int orderDetailId, OrderDetailUpdateRequest request) {
        OrderDetail orderDetail =
                repo.findById(orderDetailId).orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_FOUND));

        if (request.getQuantity() <= 0) {
            throw new AppException(ErrorCode.INVALID_QUANTITY);
        }

        orderDetail.setQuantity(request.getQuantity());
        repo.save(orderDetail);
        return mapper.toOrderDetailResponse(orderDetail);
    }

    @Transactional
    public void Delete(int orderDetailId) {
        if (!repo.existsById(orderDetailId)) {
            throw new AppException(ErrorCode.ORDER_DETAIL_NOT_FOUND);
        }
        repo.deleteById(orderDetailId);
    }
}

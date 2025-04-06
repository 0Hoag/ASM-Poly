package com.example.ASM.service;

import com.example.ASM.dto.request.OrderDetail.OrderDetailRequest;
import com.example.ASM.dto.request.OrderDetail.OrderDetailUpdateRequest;
import com.example.ASM.dto.response.OrderDetailResponse;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDetailMapper orderDetailMapper;

    /**
     * Lấy danh sách OrderDetail theo OrderId
     */
    public List<OrderDetailResponse> getByOrderId(int orderId) {
        return orderDetailRepository.findByOrderId(orderId)
                .stream()
                .map(orderDetailMapper::toOrderDetailResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lấy toàn bộ OrderDetail
     */
    public List<OrderDetailResponse> getAllOrderDetails() {
        return orderDetailRepository.findAll()
                .stream()
                .map(orderDetailMapper::toOrderDetailResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lấy OrderDetail theo ID
     */
    public OrderDetailResponse getOrderDetailById(int id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_FOUND));
        return orderDetailMapper.toOrderDetailResponse(orderDetail);
    }

    /**
     * Thêm OrderDetail vào đơn hàng: nếu đã có -> cộng dồn số lượng, nếu chưa có -> thêm mới
     */
    @Transactional
    public OrderDetailResponse createOrderDetail(int orderId, OrderDetailRequest request) {
        // Kiểm tra đơn hàng
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));

        // Kiểm tra sản phẩm
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        // Kiểm tra OrderDetail đã tồn tại chưa
        Optional<OrderDetail> existingDetail = orderDetailRepository
                .findByOrderIdAndProductId(orderId, request.getProductId());

        if (existingDetail.isPresent()) {
            // Nếu đã tồn tại -> cộng dồn số lượng
            OrderDetail orderDetail = existingDetail.get();
            orderDetail.setQuantity(orderDetail.getQuantity() + request.getQuantity());
            orderDetailRepository.save(orderDetail);
            return orderDetailMapper.toOrderDetailResponse(orderDetail);
        }

        // Nếu chưa có -> tạo mới
        OrderDetail newOrderDetail = new OrderDetail();
        newOrderDetail.setOrder(order);
        newOrderDetail.setProduct(product);
        newOrderDetail.setQuantity(request.getQuantity());
        newOrderDetail.setCurrentPrice(product.getPrice());

        orderDetailRepository.save(newOrderDetail);
        return orderDetailMapper.toOrderDetailResponse(newOrderDetail);
    }

    /**
     * Cập nhật số lượng của OrderDetail
     */
    @Transactional
    public OrderDetailResponse updateOrderDetail(int orderDetailId, OrderDetailUpdateRequest request) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_FOUND));

        if (request.getQuantity() <= 0) {
            throw new AppException(ErrorCode.INVALID_QUANTITY);
        }

        orderDetail.setQuantity(request.getQuantity());
        orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toOrderDetailResponse(orderDetail);
    }

    /**
     * Xoá OrderDetail theo ID
     */
    @Transactional
    public void deleteOrderDetail(int orderDetailId) {
        if (!orderDetailRepository.existsById(orderDetailId)) {
            throw new AppException(ErrorCode.ORDER_DETAIL_NOT_FOUND);
        }
        orderDetailRepository.deleteById(orderDetailId);
    }
}

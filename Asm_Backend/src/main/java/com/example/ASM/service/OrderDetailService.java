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
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        return orderDetails.stream()
                .map(orderDetailMapper::toOrderDetailResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lấy toàn bộ OrderDetail
     */
    public List<OrderDetailResponse> getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        return orderDetails.stream()
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
     * Thêm OrderDetail vào một đơn hàng (Nếu đã có, cộng dồn số lượng)
     */
    @Transactional
    public OrderDetailResponse createOrderDetail(int orderId, OrderDetailRequest request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        // Kiểm tra xem sản phẩm đã có trong OrderDetail chưa
        Optional<OrderDetail> existingDetail = orderDetailRepository.findByOrderIdAndProductId(orderId, request.getProductId());
        if (existingDetail.isPresent()) {
            // Nếu tồn tại, cộng dồn số lượng
            OrderDetail orderDetail = existingDetail.get();
            orderDetail.setQuantity(orderDetail.getQuantity() + request.getQuantity());
            orderDetailRepository.save(orderDetail);
            return orderDetailMapper.toOrderDetailResponse(orderDetail);
        }

        // Nếu chưa có, tạo OrderDetail mới
        OrderDetail newOrderDetail = new OrderDetail();
        newOrderDetail.setOrder(order);
        newOrderDetail.setProduct(product);
        newOrderDetail.setQuantity(request.getQuantity());
        newOrderDetail.setCurrentPrice(product.getPrice());

        orderDetailRepository.save(newOrderDetail);
        return orderDetailMapper.toOrderDetailResponse(newOrderDetail);
    }

    /**
     * Cập nhật OrderDetail (Chỉ cho phép cập nhật số lượng)
     */
    @Transactional
    public OrderDetailResponse updateOrderDetail(int orderDetailId, OrderDetailUpdateRequest request) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_FOUND));

        // Đảm bảo số lượng mới hợp lệ
        if (request.getQuantity() <= 0) {
            throw new AppException(ErrorCode.INVALID_QUANTITY);
        }

        orderDetail.setQuantity(request.getQuantity());
        orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toOrderDetailResponse(orderDetail);
    }

    /**
     * Xóa OrderDetail theo ID
     */
    @Transactional
    public void deleteOrderDetail(int orderDetailId) {
        if (!orderDetailRepository.existsById(orderDetailId)) {
            throw new AppException(ErrorCode.ORDER_DETAIL_NOT_FOUND);
        }
        orderDetailRepository.deleteById(orderDetailId);
    }
}

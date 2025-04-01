package com.example.ASM.service;

import com.example.ASM.dto.request.OrderDetail.OrderDetailRequest;
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
     * Thêm OrderDetail vào một đơn hàng
     */
    @Transactional
    public OrderDetailResponse addOrderDetail(int orderId, OrderDetailRequest request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(request.getQuantity());
        orderDetail.setCurrentPrice(product.getPrice()); // Lưu giá tại thời điểm đặt hàng

        orderDetailRepository.save(orderDetail);

        return orderDetailMapper.toOrderDetailResponse(orderDetail);
    }

    /**
     * Cập nhật OrderDetail (số lượng, giá hiện tại nếu cần)
     */
    @Transactional
    public OrderDetailResponse updateOrderDetail(int orderDetailId, OrderDetailRequest request) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_FOUND));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        orderDetail.setProduct(product);
        orderDetail.setQuantity(request.getQuantity());
        orderDetail.setCurrentPrice(product.getPrice());

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

package com.example.ASM.service;

import com.example.ASM.dto.PageResponse;
import com.example.ASM.dto.request.Order.OrderRequest;
import com.example.ASM.dto.request.Order.OrderUpdateRequest;
import com.example.ASM.dto.response.OrderResponse;
import com.example.ASM.entity.Order;
import com.example.ASM.entity.OrderStatus;
import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.mapper.OrderMapper;
import com.example.ASM.repository.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    UserRepository userRepository;
    OrderDetailService orderDetailService;
    OrderStatusRepository orderStatusRepository;
    AddressRepository addressRepository;

    // Lấy đơn hàng theo ID
    public OrderResponse getOrderById(int id) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
        var response = orderMapper.toOrderResponse(order);
        response.setOrderDetails(orderDetailService.getByOrderId(id));
        return response;
    }

    // Lấy tất cả đơn hàng
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    // Lấy danh sách đơn hàng theo phân trang
    public PageResponse<OrderResponse> getOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        var pageData = orderRepository.findAll(pageable);

        var data = pageData.getContent().stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());

        return PageResponse.<OrderResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .data(data)
                .build();
    }

    // Tạo đơn hàng mới
    public OrderResponse createOrder(OrderRequest request) {
        // Lấy người dùng từ request
        var user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // Lấy địa chỉ từ request
        var address = addressRepository.findById(request.getAddressId())
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_EXISTED));

        // Lấy trạng thái đơn hàng từ request
        var orderStatus = orderStatusRepository.findById(request.getOrderStatusId())
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_STATUS_NOT_EXISTED));

        // Tạo đối tượng Order mới
        var order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setOrderStatus(orderStatus);
        order.setTotalAmount(request.getTotalAmount());
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        // Lưu đơn hàng vào cơ sở dữ liệu
        var savedOrder = orderRepository.save(order);

        // Chuyển đổi sang đối tượng OrderResponse và trả về
        return orderMapper.toOrderResponse(savedOrder);
    }



    // Cập nhật đơn hàng
    public OrderResponse updateOrder(int id, OrderUpdateRequest request) {
        // Lấy đơn hàng hiện tại từ cơ sở dữ liệu
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));

        // Lấy địa chỉ mới từ request
        var address = addressRepository.findById(request.getAddressId())
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_EXISTED));

        // Lấy trạng thái đơn hàng mới từ request
        var orderStatus = orderStatusRepository.findById(request.getOrderStatusId())
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_STATUS_NOT_EXISTED));

        // Cập nhật các thông tin của đơn hàng
        order.setAddress(address);
        order.setOrderStatus(orderStatus);
        order.setTotalAmount(request.getTotalAmount());

        // Lưu lại đơn hàng đã cập nhật
        var updatedOrder = orderRepository.save(order);

        // Chuyển đổi thành OrderResponse và trả về
        return orderMapper.toOrderResponse(updatedOrder);
    }



    // Cập nhật trạng thái đơn hàng
    public void updateOrderStatus(int id, int statusId) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));

        var orderStatus = orderStatusRepository.findById(statusId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_STATUS_NOT_EXISTED));  // sửa tên ErrorCode nếu cần

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }

    // Xóa đơn hàng
    public void deleteOrder(int id) {
        if (!orderRepository.existsById(id)) {
            throw new AppException(ErrorCode.ORDER_NOT_EXISTED);
        }
        orderRepository.deleteById(id);
    }
}

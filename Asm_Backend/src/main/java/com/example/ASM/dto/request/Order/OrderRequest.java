package com.example.ASM.dto.request.Order;

import com.example.ASM.dto.request.OrderDetail.OrderDetailRequest;
import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private int userId;               // ID của người dùng
    private int addressId;            // ID của địa chỉ giao hàng
    private int orderStatusId;        // Thêm trạng thái đơn hàng
    private double totalAmount;       // Thêm tổng số tiền của đơn hàng
    private List<OrderDetailRequest> orderDetails;  // Danh sách chi tiết đơn hàng
}

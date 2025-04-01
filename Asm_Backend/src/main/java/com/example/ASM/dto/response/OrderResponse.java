package com.example.ASM.dto.response;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderResponse {
    private int id;                   // ID của đơn hàng
    private Timestamp createdAt;      // Thời gian tạo đơn hàng
    private double totalAmount;       // Tổng số tiền của đơn hàng
    private String status;            // Trạng thái đơn hàng
    private List<OrderDetailResponse> orderDetails;  // Danh sách chi tiết đơn hàng
}

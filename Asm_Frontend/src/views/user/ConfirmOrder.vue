<template>
  <div class="container mt-5">
    <div class="card order-confirmation-card p-4">
      <div class="card-body">
        <h1 class="text-success text-center">Xác nhận đơn hàng</h1>

        <div class="progress mt-4">
          <div class="progress-bar bg-success" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">Processing Order</div>
        </div>
        <hr />

        <h5>Thông tin đặt hàng</h5>
        <div class="d-flex justify-content-between align-items-center">
          <div>
            <p><strong>Họ tên:</strong> Trần Duy Nin</p>
            <p><strong>Địa chỉ:</strong> 123 Song Hành, Quận 12, TP.HCM</p>
            <p><strong>Số điện thoại:</strong> 0901983123</p>
          </div>
          <router-link :to="{ name: 'address' }" class="btn btn-outline-primary"> <i class="bi bi-pencil me-2"></i>Chỉnh sửa </router-link>
        </div>

        <hr />
        <h5>Thông tin đơn hàng</h5>
        <div v-for="(item, index) in selectedItems" :key="index" class="product-item">
          <div class="d-flex align-items-center">
            <img :src="item.images[0]?.url || '../../assets/img/default.jpg'" alt="Product Image" />
            <div class="ms-3">
              <h6>{{ item.productName }}</h6>
              <small>Số lượng: {{ item.quantity }}, Màu: {{ item.color || "N/A" }}</small>
            </div>
          </div>
          <span>
            {{
              (item.saleStatus ? item.priceSale : item.price).toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
              })
            }}
          </span>
        </div>

        <hr />
        <h5>Order Summary</h5>
        <ul class="list-group">
          <li class="list-group-item d-flex justify-content-between align-items-center">
            Thành tiền
            <span>{{ totalAmount.toLocaleString("vi-VN", { style: "currency", currency: "VND" }) }}</span>
          </li>
          <li class="list-group-item d-flex justify-content-between align-items-center">
            Phí vận chuyển
            <span>0 VNĐ</span>
          </li>
          <li class="list-group-item d-flex justify-content-between align-items-center">
            <strong>Tổng tiền</strong>
            <span>
              <strong>
                {{
                  (totalAmount + 0).toLocaleString("vi-VN", {
                    style: "currency",
                    currency: "VND",
                  })
                }}
              </strong>
            </span>
          </li>
        </ul>

        <div class="mt-4 text-center">
          <router-link :to="{ name: 'cart' }" class="btn btn-danger mx-2">Hủy</router-link>
          <button class="btn btn-success" @click="placeOrder">Đặt hàng</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Modal Thông Báo -->
  <div v-if="showSuccessModal" class="custom-modal">
    <div class="custom-modal-content text-center">
      <img src="../../assets/img/success.png" alt="Success Icon" />
      <h1 class="mt-3 text-success">Đặt hàng thành công!</h1>
      <button class="btn btn-secondary mt-3" @click="redirectToHome">Đóng</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
const showSuccessModal = ref(false); // Modal hiển thị khi true
const selectedItems = ref([]);
const totalAmount = ref(0);

onMounted(() => {
  const items = JSON.parse(localStorage.getItem("selectedCartItems")) || [];
  console.log("Dữ liệu từ localStorage:", items); // Kiểm tra dữ liệu
  selectedItems.value = items;

  // Tính tổng tiền của các sản phẩm
  totalAmount.value = items.reduce((acc, item) => {
    const price = item.saleStatus ? item.priceSale : item.price; // Sử dụng giá khuyến mãi nếu có
    return acc + price * item.quantity;
  }, 0);
});

const redirectToHome = () => {
  window.location.href = "/"; // Điều hướng về trang Home
};

const placeOrder = async () => {
  try {
    const userId = localStorage.getItem("userId");
    if (!userId) {
      alert("Bạn cần đăng nhập trước khi đặt hàng.");
      return;
    }

    if (selectedItems.value.length === 0) {
      alert("Giỏ hàng trống.");
      return;
    }

    const addressId = 1;
    const orderStatusId = 1;

    const orderData = {
      totalAmount: totalAmount.value,
      address: addressId,
      user: parseInt(userId),
      orderStatus: orderStatusId,
      orderDetails: selectedItems.value.map((item) => ({
        orderId: 0,
        productId: item.id,
        quantity: item.quantity,
        currentPrice: item.saleStatus ? item.priceSale : item.price,
      })),
    };

    const response = await axios.post("/asm/api/v1/orders/", orderData);

    if (response.status === 200 || response.status === 201) {
      // ✅ Xoá từng sản phẩm đã đặt khỏi giỏ hàng
      for (const item of selectedItems.value) {
        try {
          await axios.delete(`/asm/api/v1/cart-detail/${item.cartDetailId}`);
        } catch (deleteErr) {
          console.error(`Không thể xoá item ID ${item.cartDetailId}`, deleteErr);
        }
      }

      // ✅ Xoá localStorage và hiển thị modal
      localStorage.removeItem("selectedCartItems");
      showSuccessModal.value = true;
    } else {
      alert("Đặt hàng thất bại.");
    }
  } catch (error) {
    console.error("Lỗi khi đặt hàng:", error.response?.data || error.message);
    alert("Đặt hàng thất bại. Vui lòng thử lại!");
  }
};
</script>

<style scoped>
body {
  background-color: #f8f9fa;
}

.order-confirmation-card {
  max-width: 700px;
  margin: 50px auto;
  border-radius: 15px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.product-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 0;
}

.product-item img {
  width: 80px;
  height: 80px;
  object-fit: cover;
}
.custom-modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 9999;
}

.custom-modal-content {
  background-color: #fff;
  padding: 40px;
  border-radius: 15px;
  max-width: 400px;
  width: 100%;
}
</style>

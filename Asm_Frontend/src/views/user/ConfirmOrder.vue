<template>
  <div class="container mt-5">
    <div class="card order-confirmation-card p-4">
      <div class="card-body">
        <h1 class="text-success text-center">Xác nhận đơn hàng</h1>

        <div class="progress mt-4">
          <div
            class="progress-bar bg-success"
            role="progressbar"
            style="width: 75%"
            aria-valuenow="75"
            aria-valuemin="0"
            aria-valuemax="100"
          >
            Processing Order
          </div>
        </div>
        <hr />
        <h5>Thông tin đặt hàng</h5>
        <div class="d-flex justify-content-between align-items-center">
          <div>
            <p><strong>Họ tên:</strong> Trần Duy Nin</p>
            <p><strong>Địa chỉ:</strong> 123 Song Hành, Quận 12, TP.HCM</p>
            <p><strong>Số điện thoại:</strong> 0901983123</p>
          </div>
          <router-link
            :to="{ name: 'address' }"
            class="btn btn-outline-primary"
          >
            <i class="bi bi-pencil me-2"></i>Chỉnh sửa
          </router-link>
        </div>

        <hr />
        <h5>Thông tin đơn hàng</h5>
        <div class="product-item">
          <div class="d-flex align-items-center">
            <img src="../../assets/img/ip15.jpg" alt="Product Image" />
            <div class="ms-3">
              <h6>Iphone 15 Pro Max</h6>
              <small>Số lượng: 1, Màu: Đen</small>
            </div>
          </div>
          <span>25.000.000 VNĐ</span>
        </div>
        <div class="product-item">
          <div class="d-flex align-items-center">
            <img src="../../assets/img/ip15hong.jpg" alt="Product Image" />
            <div class="ms-3">
              <h6>Iphone 15 Pro Max</h6>
              <small>Số lượng: 1, Màu: Hồng</small>
            </div>
          </div>
          <span>24.000.000 VNĐ</span>
        </div>
        <hr />
        <h5>Order Summary</h5>
        <ul class="list-group">
          <li
            class="list-group-item d-flex justify-content-between align-items-center"
          >
            Thành tiền <span>49.000.000 VNĐ</span>
          </li>
          <li
            class="list-group-item d-flex justify-content-between align-items-center"
          >
            Phí vận chuyển <span>100.000 VNĐ</span>
          </li>
          <li
            class="list-group-item d-flex justify-content-between align-items-center"
          >
            <strong>Tổng tiền</strong>
            <span><strong>49.100.000 VNĐ</strong></span>
          </li>
        </ul>
        <div class="mt-4 text-center">
          <router-link :to="{ name: 'cart' }" class="btn btn-danger mx-2"
            >Hủy</router-link
          >
          <button class="btn btn-success" @click="showModal">Đặt hàng</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Start modal đặt hàng thành công -->
  <div>
    <!-- Modal Bootstrap -->
    <div
      ref="modalRef"
      class="modal fade"
      tabindex="-1"
      aria-labelledby="successModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <!-- <div class="modal-header">
            <h5 class="modal-title text-success" id="successModalLabel">
              Đặt hàng thành công!
            </h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div> -->
          <div class="modal-body">
            <div class="container text-center mt-5">
              <img src="../../assets/img/success.png" alt="Success Icon" />
              <h1 class="mt-3 text-success">Đặt hàng thành công!</h1>
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Đóng
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- End modal đặt hàng -->
</template>

<script setup>
import { ref, onMounted } from "vue";
import { Modal } from "bootstrap";

// Biến tham chiếu đến modal
const modalRef = ref(null);
let modalInstance = null;

// Khi component được mount, khởi tạo Bootstrap Modal
onMounted(() => {
  if (modalRef.value) {
    modalInstance = new Modal(modalRef.value);
  }
});

// Hiển thị modal khi đặt hàng thành công
const showModal = () => {
  modalInstance?.show();
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
</style>

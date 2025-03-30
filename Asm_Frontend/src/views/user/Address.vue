<template>
  <div class="bg-white rounded shadow-sm p-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h4 class="mb-0">Địa chỉ của tôi</h4>
      <button
        class="btn btn-primary"
        data-bs-toggle="modal"
        data-bs-target="#editCustomerInfoModal"
      >
        <i class="bi bi-plus-lg me-1"></i> Thêm địa chỉ mới
      </button>
    </div>
    <hr />

    <!-- Address Card 1 -->
    <div class="card mb-3 border">
      <div class="card-body">
        <div class="d-flex justify-content-between align-items-start mb-2">
          <h5 class="card-title mb-0">Nguyễn Văn A</h5>
          <div>
            <span class="badge bg-primary me-1">Mặc định</span>
          </div>
        </div>
        <p class="card-text mb-1">
          <i class="bi bi-telephone me-2"></i>0912 345 678
        </p>
        <p class="card-text mb-3">
          <i class="bi bi-geo-alt me-2"></i>123 Đường Lê Lợi, Phường Bến Nghé,
          Quận 1, TP. Hồ Chí Minh
        </p>
        <div class="d-flex gap-2">
          <button
            class="btn btn-outline-primary btn-sm"
            data-bs-toggle="modal"
            data-bs-target="#editCustomerInfoModal"
          >
            Chỉnh sửa
          </button>
          <button class="btn btn-outline-danger btn-sm">Xóa</button>
        </div>
      </div>
    </div>

    <!-- Address Card 2 -->
    <div class="card mb-3 border">
      <div class="card-body">
        <div class="d-flex justify-content-between align-items-start mb-2">
          <h5 class="card-title mb-0">Nguyễn Văn A</h5>
        </div>
        <p class="card-text mb-1">
          <i class="bi bi-telephone me-2"></i>0987 654 321
        </p>
        <p class="card-text mb-3">
          <i class="bi bi-geo-alt me-2"></i>456 Đường Nguyễn Huệ, Phường Bến
          Thành, Quận 1, TP. Hồ Chí Minh
        </p>
        <div class="d-flex gap-2">
          <button class="btn btn-outline-secondary btn-sm">
            Đặt làm mặc định
          </button>
          <button
            class="btn btn-outline-primary btn-sm"
            data-bs-toggle="modal"
            data-bs-target="#editCustomerInfoModal"
          >
            Chỉnh sửa
          </button>
          <button class="btn btn-outline-danger btn-sm">Xóa</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Start modal thêm địa chỉ -->

  <!-- Edit Customer Info Modal -->
  <div
    class="modal fade"
    id="editCustomerInfoModal"
    tabindex="-1"
    aria-labelledby="editCustomerInfoModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="editCustomerInfoModalLabel">
            Chỉnh sửa thông tin giao hàng
          </h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <form id="customer-info-form">
            <div class="mb-3">
              <label for="customerName" class="form-label"
                >Tên người nhận:</label
              >
              <input
                type="text"
                class="form-control"
                id="customerName"
                placeholder="Cô gái mộng mơ hay khóc nhè"
              />
            </div>
            <div class="mb-3">
              <label for="shippingAddress" class="form-label">Địa chỉ:</label>
              <input
                type="text"
                class="form-control"
                id="shippingAddress"
                placeholder="123 Tô Đức Thắng, Tp HCM"
              />
            </div>
            <div class="mb-3">
              <label for="paymentMethod" class="form-label"
                >Số điện thoại:</label
              >
              <input
                type="text"
                class="form-control"
                id="paymentMethod"
                placeholder="+84 xxx xxx xxx"
              />
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Đóng
          </button>
          <button
            type="button"
            class="btn btn-primary"
            onclick="saveCustomerInfo()"
          >
            Lưu
          </button>
        </div>
      </div>
    </div>
  </div>

  <!-- End modal thêm địa chỉ -->
</template>

<script setup></script>

<style lang="scss" scoped></style>

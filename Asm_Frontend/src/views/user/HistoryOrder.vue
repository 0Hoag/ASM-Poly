<template>
  <div class="bg-white rounded shadow-sm p-4">
    <h4 class="mb-4">Đơn hàng của tôi</h4>

    <!-- Order Tabs -->
    <ul class="nav nav-tabs mb-4">
      <li class="nav-item">
        <a class="nav-link active" href="#">Tất cả</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Chờ xác nhận</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Đang giao</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Đã giao</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Đã hủy</a>
      </li>
    </ul>

    <!-- Search Bar -->
    <div class="mb-4">
      <div class="input-group">
        <input
          type="text"
          class="form-control"
          placeholder="Tìm kiếm theo mã đơn hàng hoặc tên sản phẩm"
        />
        <button class="btn btn-primary" type="button">
          <i class="bi bi-search"></i>
        </button>
      </div>
    </div>

    <!-- Order 1 -->
    <div class="card mb-3 border">
      <div
        class="card-header bg-white d-flex justify-content-between align-items-center"
      >
        <div>
          <span class="fw-bold">Mã đơn hàng: #123456</span>
          <span class="ms-3 text-muted small">Ngày đặt: 15/10/2023</span>
        </div>
        <span class="badge bg-success">Đã giao</span>
      </div>
      <div class="card-body">
        <div class="row mb-3">
          <div class="col-md-2">
            <img
              src="../../assets/img/Samsung Galaxy S21 Ultra.png"
              class="img-fluid rounded"
              alt="Product"
            />
          </div>
          <div class="col-md-7">
            <h6 class="mb-1">Samsung Galaxy S21 Ultra</h6>
            <p class="text-muted mb-1 small">Phân loại: Đen, 128Gb</p>
            <p class="mb-0 small">x1</p>
          </div>
          <div class="col-md-3 text-md-end">
            <p class="text-danger mb-0">299.000₫</p>
          </div>
        </div>
        <div class="row mb-3">
          <div class="col-md-2">
            <img
              src="../../assets/img/Apple Watch Series 7 GPS 45MM Midnight.png"
              class="img-fluid rounded"
              alt="Product"
            />
          </div>
          <div class="col-md-7">
            <h6 class="mb-1">Apple Watch Series 7 GPS 45MM Midnight</h6>
            <p class="text-muted mb-1 small">Phân loại: Xanh đậm</p>
            <p class="mb-0 small">x1</p>
          </div>
          <div class="col-md-3 text-md-end">
            <p class="text-danger mb-0">499.000₫</p>
          </div>
        </div>
        <hr />
        <div class="d-flex justify-content-between align-items-center">
          <div>
            <span class="text-muted">Tổng tiền:</span>
            <span class="text-danger fw-bold ms-2">798.000₫</span>
          </div>
          <div class="d-flex gap-2">
            <button class="btn btn-outline-primary btn-sm">Mua lại</button>
            <button class="btn btn-outline-secondary btn-sm">
              Xem chi tiết
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Order 2 -->
    <div class="card mb-3 border">
      <div
        class="card-header bg-white d-flex justify-content-between align-items-center"
      >
        <div>
          <span class="fw-bold">Mã đơn hàng: #123457</span>
          <span class="ms-3 text-muted small">Ngày đặt: 10/10/2023</span>
        </div>
        <span class="badge bg-warning text-dark">Đang giao</span>
      </div>
      <div class="card-body">
        <div class="row mb-3">
          <div class="col-md-2">
            <img
              src="../../assets/img/Samsung Galaxy S21 Ultra.png"
              class="img-fluid rounded"
              alt="Product"
            />
          </div>
          <div class="col-md-7">
            <h6 class="mb-1">Samsung Galaxy S211 Ultra</h6>
            <p class="text-muted mb-1 small">Phân loại: trắng, 128GB128GB</p>
            <p class="mb-0 small">x1</p>
          </div>
          <div class="col-md-3 text-md-end">
            <p class="text-danger mb-0">799.000₫</p>
          </div>
        </div>
        <hr />
        <div class="d-flex justify-content-between align-items-center">
          <div>
            <span class="text-muted">Tổng tiền:</span>
            <span class="text-danger fw-bold ms-2">799.000₫</span>
          </div>
          <div class="d-flex gap-2">
            <button class="btn btn-outline-secondary btn-sm">
              Xem chi tiết
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Order 3 -->
    <div class="card mb-3 border">
      <div
        class="card-header bg-white d-flex justify-content-between align-items-center"
      >
        <div>
          <span class="fw-bold">Mã đơn hàng: #123458</span>
          <span class="ms-3 text-muted small">Ngày đặt: 05/10/2023</span>
        </div>
        <span class="badge bg-secondary">Đã hủy</span>
      </div>
      <div class="card-body">
        <div class="row mb-3">
          <div class="col-md-2">
            <img
              src="../../assets/img/Apple Watch Series 7 GPS 45MM Midnight.png"
              class="img-fluid rounded"
              alt="Product"
            />
          </div>
          <div class="col-md-7">
            <h6 class="mb-1">Apple Watch Series 66</h6>
            <p class="text-muted mb-1 small">Phân loại: Đen</p>
            <p class="mb-0 small">x1</p>
          </div>
          <div class="col-md-3 text-md-end">
            <p class="text-danger mb-0">599.000₫</p>
          </div>
        </div>
        <hr />
        <div class="d-flex justify-content-between align-items-center">
          <div>
            <span class="text-muted">Tổng tiền:</span>
            <span class="text-danger fw-bold ms-2">599.000₫</span>
          </div>
          <div class="d-flex gap-2">
            <button class="btn btn-outline-primary btn-sm">Mua lại</button>
            <button class="btn btn-outline-secondary btn-sm">
              Xem chi tiết
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Pagination -->
    <nav aria-label="Page navigation">
      <ul class="pagination justify-content-center mt-4">
        <li class="page-item disabled">
          <a class="page-link" href="#" tabindex="-1" aria-disabled="true"
            >Trước</a
          >
        </li>
        <li class="page-item active"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
          <a class="page-link" href="#">Sau</a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>

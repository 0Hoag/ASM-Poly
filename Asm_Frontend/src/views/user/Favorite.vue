<template>
  <div class="bg-white rounded shadow-sm p-4">
    <h4 class="mb-4">Sản phẩm ưu thích</h4>
    <hr />

    <div class="row g-4">
      <!-- Product Card 1 -->
      <div class="col-md-6 col-lg-4">
        <div class="card h-100 border">
          <div class="position-relative">
            <img
              src="../../assets/img/Samsung Galaxy S21 Ultra.png"
              class="card-img-top"
              alt="Product"
            />
            <button
              class="btn btn-sm position-absolute top-0 end-0 m-2 bg-white rounded-circle p-1"
            >
              <i class="bi bi-heart-fill text-danger"></i>
            </button>
          </div>
          <div class="card-body">
            <h5 class="card-title">Samsung Galaxy S21 Ultra</h5>
            <div class="d-flex align-items-center mb-2">
              <span class="text-danger fw-bold me-2">299.000₫</span>
              <span class="text-decoration-line-through text-muted small"
                >399.000₫</span
              >
              <span class="badge bg-danger ms-auto">-25%</span>
            </div>
            <div class="d-grid">
              <button class="btn btn-primary">Thêm vào giỏ hàng</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>

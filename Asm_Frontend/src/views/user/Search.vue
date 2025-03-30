<template>
  <div class="container my-5">
    <h1 class="mb-4 text-center">Kết Quả tìm kiếm</h1>
    <!-- Product listing -->
    <div class="col-md-12">
      <div class="d-flex justify-content-between align-items-center mb-4">
        <p class="mb-0">Hiển thị 1-12 trong số 36 sản phẩm</p>
        <div class="d-flex align-items-center">
          <label class="me-2">Sắp xếp theo:</label>
          <select class="form-select" style="width: auto">
            <option selected>Mặc định</option>
            <option value="1">Giá: Thấp đến Cao</option>
            <option value="2">Giá: Cao đến Thấp</option>
            <option value="3">Tên: A-Z</option>
            <option value="4">Tên: Z-A</option>
          </select>
        </div>
      </div>

      <div class="row row-cols-1 row-cols-md-3 g-4">
        <!-- Product Card 1 -->
        <div class="col">
          <div class="card h-100 product-card">
            <img
              src="../../assets/img/ip14.png"
              class="card-img-top"
              alt="iPhone 14 Pro"
            />
            <div class="card-body">
              <h5 class="card-title">iPhone 14 Pro</h5>
              <p class="card-text price-sale">27.990.000₫</p>
              <p class="card-text">
                <small class="text-muted"><del>29.990.000₫</del> -6%</small>
              </p>
            </div>
            <div class="card-footer bg-transparent border-top-0">
              <a href="#" class="btn btn-primary w-100">Xem chi tiết</a>
            </div>
          </div>
        </div>

        <!-- Product Card 2 -->
        <div class="col">
          <div class="card h-100 product-card">
            <img
              src="../../assets/img/image5.png"
              class="card-img-top"
              alt="Samsung Galaxy S23"
            />
            <div class="card-body">
              <h5 class="card-title">Samsung Galaxy S23</h5>
              <p class="card-text price-sale">21.990.000₫</p>
              <p class="card-text">
                <small class="text-muted"><del>23.990.000₫</del> -8%</small>
              </p>
            </div>
            <div class="card-footer bg-transparent border-top-0">
              <a href="#" class="btn btn-primary w-100">Xem chi tiết</a>
            </div>
          </div>
        </div>

        <!-- Product Card 3 -->
        <div class="col">
          <div class="card h-100 product-card">
            <img
              src="../../assets/img/image6.png"
              class="card-img-top"
              alt="Xiaomi 13"
            />
            <div class="card-body">
              <h5 class="card-title">Xiaomi 13</h5>
              <p class="card-text price-sale">18.990.000₫</p>
              <p class="card-text">
                <small class="text-muted"><del>20.990.000₫</del> -9%</small>
              </p>
            </div>
            <div class="card-footer bg-transparent border-top-0">
              <a href="#" class="btn btn-primary w-100">Xem chi tiết</a>
            </div>
          </div>
        </div>

        <!-- Product Card 4 -->
        <div class="col">
          <div class="card h-100 product-card">
            <img
              src="../../assets/img/image7.png"
              class="card-img-top"
              alt="OPPO Find X5 Pro"
            />
            <div class="card-body">
              <h5 class="card-title">OPPO Find X5 Pro</h5>
              <p class="card-text price-sale">19.990.000₫</p>
              <p class="card-text">
                <small class="text-muted"><del>22.990.000₫</del> -13%</small>
              </p>
            </div>
            <div class="card-footer bg-transparent border-top-0">
              <a href="#" class="btn btn-primary w-100">Xem chi tiết</a>
            </div>
          </div>
        </div>
        <!-- Product Card 4 -->
        <div class="col">
          <div class="card h-100 product-card">
            <img
              src="../../assets/img/image7.png"
              class="card-img-top"
              alt="OPPO Find X5 Pro"
            />
            <div class="card-body">
              <h5 class="card-title">OPPO Find X5 Pro</h5>
              <p class="card-text price-sale">19.990.000₫</p>
              <p class="card-text">
                <small class="text-muted"><del>22.990.000₫</del> -13%</small>
              </p>
            </div>
            <div class="card-footer bg-transparent border-top-0">
              <a href="#" class="btn btn-primary w-100">Xem chi tiết</a>
            </div>
          </div>
        </div>
        <!-- Product Card 4 -->
        <div class="col">
          <div class="card h-100 product-card">
            <img
              src="../../assets/img/image7.png"
              class="card-img-top"
              alt="OPPO Find X5 Pro"
            />
            <div class="card-body">
              <h5 class="card-title">OPPO Find X5 Pro</h5>
              <p class="card-text price-sale">19.990.000₫</p>
              <p class="card-text">
                <small class="text-muted"><del>22.990.000₫</del> -13%</small>
              </p>
            </div>
            <div class="card-footer bg-transparent border-top-0">
              <a href="#" class="btn btn-primary w-100">Xem chi tiết</a>
            </div>
          </div>
        </div>

        <!-- Thêm các Product Card khác tương tự ở đây -->
      </div>

      <!-- Pagination -->
      <nav aria-label="Page navigation" class="mt-4">
        <ul class="pagination justify-content-center">
          <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1" aria-disabled="true"
              >Trước</a
            >
          </li>
          <li class="page-item active">
            <a class="page-link" href="#">1</a>
          </li>
          <li class="page-item"><a class="page-link" href="#">2</a></li>
          <li class="page-item"><a class="page-link" href="#">3</a></li>
          <li class="page-item">
            <a class="page-link" href="#">Sau</a>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>

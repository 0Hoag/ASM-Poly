<template>
  <div class="container my-5">
    <h1 class="mb-4">Điện thoại di động</h1>

    <div class="row">
      <!-- Sidebar with filters -->
      <div class="col-md-3 mb-4">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Bộ lọc</h5>
            <form>
              <div class="mb-3">
                <label class="form-label">Hãng sản xuất</label>
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    value=""
                    id="apple"
                  />
                  <label class="form-check-label" for="apple"> Apple </label>
                </div>
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    value=""
                    id="samsung"
                  />
                  <label class="form-check-label" for="samsung">
                    Samsung
                  </label>
                </div>
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    value=""
                    id="xiaomi"
                  />
                  <label class="form-check-label" for="xiaomi"> Xiaomi </label>
                </div>
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    value=""
                    id="oppo"
                  />
                  <label class="form-check-label" for="oppo"> OPPO </label>
                </div>
              </div>
              <div class="mb-3">
                <label class="form-label">Giá</label>
                <select class="form-select">
                  <option selected>Tất cả giá</option>
                  <option value="1">Dưới 5 triệu</option>
                  <option value="2">5 - 10 triệu</option>
                  <option value="3">10 - 20 triệu</option>
                  <option value="4">Trên 20 triệu</option>
                </select>
              </div>
              <button type="submit" class="btn btn-primary w-100">
                Áp dụng
              </button>
            </form>
          </div>
        </div>
      </div>

      <!-- Product listing -->
      <div class="col-md-9">
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
  </div>
</template>

<script setup></script>

<style scoped>
body {
  font-family: "Poppins", sans-serif;
}
.navbar {
  padding: 12px 0;
}
.navbar-nav .nav-link {
  font-size: 16px;
  font-weight: 600;
  color: #333 !important;
  padding: 10px 15px;
  transition: all 0.3s ease-in-out;
}
.navbar-nav .nav-link:hover {
  color: #fff !important;
  background: rgba(0, 0, 0, 0.2);
  border-radius: 5px;
}
.header-icons a {
  font-size: 18px;
  margin-left: 15px;
  position: relative;
  color: #333;
  text-decoration: none;
  transition: color 0.3s;
}
.header-icons a:hover {
  color: #ff9900;
}
.header-icons .badge {
  font-size: 12px;
  top: -5px;
  right: -8px;
}
.search-box input {
  border-radius: 30px;
}
.search-box button {
  border-radius: 30px;
  padding: 5px 15px;
}
#bannerCarousel {
  max-width: 100%;
  height: 400px;
  overflow: hidden;
}
.brand-logo {
  max-width: 100px;
  height: 50px;
  object-fit: contain;
  margin: auto;
}

/* Card sản phẩm */
.product-card {
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  border-radius: 10px;
  overflow: hidden;
  max-width: 300px; /* Thu nhỏ chiều rộng card */
  padding: 10px;
  margin: auto;
}

.product-card:hover {
  transform: scale(1.05);
  box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.15);
}

/* Hình ảnh sản phẩm */
.product-image {
  position: relative;
  overflow: hidden;
  border-radius: 10px;
}

.product-image img {
  width: 100%;
  height: auto;
  transition: transform 0.3s ease;
}

.product-card:hover .product-image img {
  transform: scale(1.1);
}

/* Badge giảm giá */
.sale-badge {
  position: absolute;
  top: 10px;
  left: 10px;
  background: red;
  color: white;
  font-size: 14px;
  font-weight: bold;
  padding: 5px 10px;
  border-radius: 5px;
}

/* Giá gạch ngang */
.text-decoration-line-through {
  font-size: 14px;
}

/* Nút CTA */
.btn-danger {
  border-radius: 5px;
  transition: background 0.3s ease;
}

.btn-danger:hover {
  background: #c40000;
}
.product-image img {
  width: 100%; /* Đảm bảo ảnh không bị méo */
  height: 180px; /* Giới hạn chiều cao */
  object-fit: contain; /* Giữ tỷ lệ ảnh, không bị méo */
}
.sale-section {
  background: linear-gradient(to right, #ffd700, #ffc107); /* Gradient vàng */
  padding: 40px 0;
}
</style>

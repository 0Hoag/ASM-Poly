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
        <div class="card product-card">
          <img
            src="../../assets/img/ip15hong.jpg"
            class="card-img-top product-image"
            alt="Sản phẩm 1"
          />
          <div class="card-body text-center">
            <h5 class="product-title">Iphone 15</h5>
            <p class="product-price">20,000,000₫</p>
            <router-link
              to="/category/product"
              class="btn btn-add-to-cart btn-success w-100"
            >
              Xem sản phẩm
            </router-link>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="card product-card">
          <img
            src="../../assets/img/dt2.png"
            class="card-img-top product-image"
            alt="Sản phẩm 2"
          />
          <div class="card-body text-center">
            <h5 class="product-title">Iphone 15</h5>
            <p class="product-price">20,000,000₫</p>
            <router-link
              to="/category/product"
              class="btn btn-add-to-cart btn-success w-100"
            >
              Xem sản phẩm
            </router-link>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="card product-card">
          <img
            src="../../assets/img/dt3.png"
            class="card-img-top product-image"
            alt="Sản phẩm 3"
          />
          <div class="card-body text-center">
            <h5 class="product-title">Iphone 15</h5>
            <p class="product-price">20,000,000₫</p>
            <router-link
              to="/category/product"
              class="btn btn-add-to-cart btn-success w-100"
            >
              Xem sản phẩm
            </router-link>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="card product-card">
          <img
            src="../../assets/img/dt4.jpg"
            class="card-img-top product-image"
            alt="Sản phẩm 4"
          />
          <div class="card-body text-center">
            <h5 class="product-title">Iphone 15</h5>
            <p class="product-price">20,000,000₫</p>
            <router-link
              to="/category/product"
              class="btn btn-add-to-cart btn-success w-100"
            >
              Xem sản phẩm
            </router-link>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="card product-card">
          <img
            src="../../assets/img/dt5.webp"
            class="card-img-top product-image"
            alt="Sản phẩm 5"
          />
          <div class="card-body text-center">
            <h5 class="product-title">Iphone 15</h5>
            <p class="product-price">20,000,000₫</p>
            <router-link
              to="/category/product"
              class="btn btn-add-to-cart btn-success w-100"
            >
              Xem sản phẩm
            </router-link>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="card product-card">
          <img
            src="../../assets/img/ip15hong.jpg"
            class="card-img-top product-image"
            alt="Sản phẩm 1"
          />
          <div class="card-body text-center">
            <h5 class="product-title">Iphone 15</h5>
            <p class="product-price">20,000,000₫</p>
            <router-link
              to="/category/product"
              class="btn btn-add-to-cart btn-success w-100"
            >
              Xem sản phẩm
            </router-link>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="card product-card">
          <img
            src="../../assets/img/dt2.png"
            class="card-img-top product-image"
            alt="Sản phẩm 2"
          />
          <div class="card-body text-center">
            <h5 class="product-title">Iphone 15</h5>
            <p class="product-price">20,000,000₫</p>
            <router-link
              to="/category/product"
              class="btn btn-add-to-cart btn-success w-100"
            >
              Xem sản phẩm
            </router-link>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="card product-card">
          <img
            src="../../assets/img/dt3.png"
            class="card-img-top product-image"
            alt="Sản phẩm 3"
          />
          <div class="card-body text-center">
            <h5 class="product-title">Iphone 15</h5>
            <p class="product-price">20,000,000₫</p>
            <router-link
              to="/category/product"
              class="btn btn-add-to-cart btn-success w-100"
            >
              Xem sản phẩm
            </router-link>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="card product-card">
          <img
            src="../../assets/img/dt4.jpg"
            class="card-img-top product-image"
            alt="Sản phẩm 4"
          />
          <div class="card-body text-center">
            <h5 class="product-title">Iphone 15</h5>
            <p class="product-price">20,000,000₫</p>
            <router-link
              to="/category/product"
              class="btn btn-add-to-cart btn-success w-100"
            >
              Xem sản phẩm
            </router-link>
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

<style lang="scss" scoped>
</style>
<style>
.pagination .page-item .page-link {
    color: #333; /* Màu chữ */
    background-color: #f8f9fa; /* Màu nền */
    border: 1px solid #ddd; /* Viền nhẹ */
    padding: 10px 15px;
    margin: 0 5px;
    border-radius: 8px; /* Bo góc */
    transition: all 0.3s ease-in-out;
}

.pagination .page-item.active .page-link {
    background-color: #02571a; /* Màu xanh chủ đạo */
    color: white;
    border-color: #00ff22;
    font-weight: bold;
}

.pagination .page-item .page-link:hover {
    background-color: #ae2004; /* Hiệu ứng hover */
    color: white;
    border-color: #ab1a01;
}

.pagination .page-item.disabled .page-link {
    color: #ccc;
    background-color: #eee;
    border-color: #ddd;
    cursor: not-allowed;
}
</style>

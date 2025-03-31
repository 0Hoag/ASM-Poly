<template>
  <!-- SP nổi bật -->
  <div class="container my-5">
    <h2
      class="text-center mb-4"
      style="
        font-size: 2rem;
        font-weight: bold;
        color: #ff4500;
        text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
      "
    >
      <i class="fa-solid fa-phone" style="color: #d81515"></i> SẢN PHẨM NỔI BẬT
    </h2>
    <div class="row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4">
      <div class="col">
        <div class="card product-card">
          <img
            src="../../assets/img/ip15xanh.jpeg"
            class="card-img-top product-image"
            alt="Sản phẩm 1"
          />
          <div class="card-body text-center">
            <h5 class="product-title">iPhone 15 Pro Max</h5>
            <p class="product-price">35,000,000₫</p>
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
            src="../../assets/img/ip15xanh.jpeg"
            class="card-img-top product-image"
            alt="Sản phẩm 2"
          />
          <div class="card-body text-center">
            <h5 class="product-title">Samsung Galaxy S24</h5>
            <p class="product-price">30,000,000₫</p>
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
            src="../../assets/img/ip15xanh.jpeg"
            class="card-img-top product-image"
            alt="Sản phẩm 3"
          />
          <div class="card-body text-center">
            <h5 class="product-title">Xiaomi 13T Pro</h5>
            <p class="product-price">15,000,000₫</p>
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
            src="../../assets/img/ip15xanh.jpeg"
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
    </div>
  </div>
  <!-- san pham moi ra mat -->
  <div class="container my-5">
    <h2
      class="text-center mb-4"
      style="
        font-size: 2rem;
        font-weight: bold;
        color: #ff4500;
        text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
      "
    >
      <i class="fa-solid fa-phone" style="color: #f51e0f"></i> SẢN PHẨM MỚI RA
      MẮT
    </h2>
    <div class="row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4">
      <div class="col">
        <div class="card product-card">
          <img
            src="../../assets/img/ip15hong.jpg"
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
            src="../../assets/img/ip15hong.jpg"
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
            src="../../assets/img/ip15hong.jpg"
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
            src="../../assets/img/ip15hong.jpg"
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
    </div>
  </div>
</template>
<script setup>
import { Swiper, SwiperSlide } from "swiper/vue";
</script>
<style>
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
/* san pham noi bat */
#bannerCarousel .carousel-inner img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.product-slider {
  display: flex;
  overflow: hidden;
  white-space: nowrap;
  position: relative;
  width: 100%;
}

.product-slider .slider-wrapper {
  display: flex;
  animation: slide 10s linear infinite;
}

.product-card {
  flex: 0 0 25%; /* Hiển thị 4 sản phẩm cùng lúc */
  padding: 10px;
  transition: transform 0.3s;
}

@keyframes slide {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(-100%);
  }
}

/* Khi hover, dừng chuyển động */
.product-slider:hover .slider-wrapper {
  animation-play-state: paused;
}
.sale-section {
  background: linear-gradient(to right, #ffd700, #ffc107); /* Gradient vàng */
  padding: 40px 0;
}
.hot-sale-bar {
  margin: 10px;
  background: linear-gradient(to right, #ff0000, #ff8800);
  color: #fff;
  font-size: 20px;
  font-weight: bold;
  text-align: center;
  text-transform: uppercase;
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
</style>

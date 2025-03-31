<template>
  <!-- banner -->
  <div id="bannerCarousel" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img
          src="../../assets/img/banner1.png"
          class="d-block w-100"
          alt="Quảng cáo 1"
        />
      </div>
      <div class="carousel-item">
        <img
          src="../../assets/img/banner2.png"
          class="d-block w-100"
          alt="Quảng cáo 2"
        />
      </div>
      <div class="carousel-item">
        <img
          src="../../assets/img/banner3.jpg"
          class="d-block w-100"
          alt="Quảng cáo 3"
        />
      </div>
    </div>
    <button
      class="carousel-control-prev"
      type="button"
      data-bs-target="#bannerCarousel"
      data-bs-slide="prev"
    >
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    </button>
    <button
      class="carousel-control-next"
      type="button"
      data-bs-target="#bannerCarousel"
      data-bs-slide="next"
    >
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
    </button>
  </div>
</template>

<script setup></script>

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

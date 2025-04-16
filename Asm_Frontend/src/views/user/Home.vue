<template>
  <!-- banner -->
<div id="bannerCarousel" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="..//..//assets/img/banner1.png" class="d-block w-100" alt="Quảng cáo 1">
        </div>
        <div class="carousel-item">
          <img src="..//..//assets/img/banner2.png" class="d-block w-100" alt="Quảng cáo 2">
        </div>
        <div class="carousel-item">
          <img src="..//..//assets/img/banner3.png" class="d-block w-100" alt="Quảng cáo 3">
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#bannerCarousel" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#bannerCarousel" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
    </button>
</div>
<!-- sale -->
  <div class="hot-sale-bar">
    <div class="marquee">
        🔥 SIÊU SALE HÔM NAY - GIẢM GIÁ LÊN ĐẾN 70% - NHANH TAY SĂN DEAL HOT 🔥 
        🚀 FREESHIP TOÀN QUỐC 🚀 
        🎁 TẶNG QUÀ HẤP DẪN KHI MUA HÀNG 🎁
    </div>
</div>
<!-- Item Sale -->
<div class="sale-section py-4">
    <div class="container">
        <h2 class="text-center mb-4" style="font-size: 2rem; font-weight: bold; color: #ff4500; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);">
            🔥 SALE SỐC - GIẢM GIÁ LỚN 🔥
        </h2>  
        <div id="saleCarousel" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-inner">
    <!-- Slide 1 -->
    <div class="carousel-item active">
      <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
        <div class="col" v-for="product in products.slice(4, 8)" :key="product.id">
          <div class="card product-card border-0 shadow-sm text-center h-100">
            <div class="product-image position-relative">
              <img
                :src="product.images.length ? product.images[0].url : '/default.jpg'"
                class="card-img-top"
                style="height: 200px; object-fit: cover"
                :alt="product.productName"
              />
              <span
                v-if="product.originalPrice && product.salePrice"
                class="sale-badge position-absolute top-0 start-0 bg-danger text-white px-2 py-1"
              >
                -{{ Math.round(100 - (product.salePrice / product.originalPrice) * 100) }}%
              </span>
            </div>
            <div class="card-body">
              <h5 class="card-title">{{ product.productName }}</h5>
              <p
                v-if="product.originalPrice"
                class="text-muted text-decoration-line-through mb-1"
              >
                {{ formatPrice(product.originalPrice) }}
              </p>
              <p class="text-danger fw-bold fs-5">
                {{ formatPrice(product.salePrice) }}
              </p>
              <router-link
              :to="`/category/product/${product.id}`"
              class="btn btn-danger w-50 fw-bold"
              >
                Mua ngay
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Nút điều hướng -->
  <button class="carousel-control-prev" type="button" data-bs-target="#saleCarousel" data-bs-slide="prev">
    <span class="carousel-control-prev-icon btn-danger" aria-hidden="true"></span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#saleCarousel" data-bs-slide="next">
    <span class="carousel-control-next-icon btn-danger" aria-hidden="true"></span>
  </button>
</div>

    </div>
</div>
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
      
      <div class="col" v-for="(product, index) in products.slice(5, 9)"      >
      <div class="card product-card">
        <img
          :src="product.images.length ? product.images[0].url : '/default.jpg'"
          class="card-img-top product-image" style="width: 100%; height: 100%;"
          :alt="product.productName"
        />
        <div class="card-body text-center">
          <h5 class="product-title">{{ product.productName }}</h5>
          <p class="product-price">{{ formatPrice(product.salePrice) }}</p>
          <router-link             
          :to="`/category/product/${product.id}`" 
          class="btn btn-add-to-cart btn-success w-100">
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
      <i class="fa-solid fa-phone" style="color: #f51e0f"></i> SẢN PHẨM MỚI RA MẮT
    </h2>
    <div class="row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4">
      
      <div class="col" v-for="(product, index) in products.slice(1, 5)"      >
      <div class="card product-card">
        <img
          :src="product.images.length ? product.images[0].url : '/default.jpg'"
          class="card-img-top product-image" style="width: 100%; height: 100%;"
          :alt="product.productName"
        />
        <div class="card-body text-center">
          <h5 class="product-title">{{ product.productName }}</h5>
          <p class="product-price">{{ formatPrice(product.salePrice) }}</p>
          <router-link
          :to="`/category/product/${product.id}`"
          class="btn btn-add-to-cart btn-success w-100"
          >
            Xem sản phẩm
          </router-link>
        </div>
      </div>
    </div>
    </div>
    
  </div>
<!-- Danh Sách sản phẩm -->
<div class="container my-5">
  <h2
    class="text-center mb-4" style="font-size: 2rem; font-weight: bold; color: #ff4500; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);">
    <i class="fa-solid fa-phone" style="color: #f51e0f"></i> ĐIỆN THOẠI CHÍNH HÃNG GIÁ TỐT </h2>
  <div class="row row-cols-1 row-cols-md-3 row-cols-lg-5 g-4">
    
    <div class="col" v-for="product in products" :key="product.id">
      <div class="card product-card">
        <img
          :src="product.images.length ? product.images[0].url : '/default.jpg'"
          class="card-img-top product-image" style="width: 100%; height: 100%;"
          :alt="product.productName"
        />
        <div class="card-body text-center">
          <h5 class="product-title">{{ product.productName }}</h5>
          <p class="product-price">{{ formatPrice(product.salePrice) }}</p>
          <router-link
            :to="`/category/product/${product.id}`"
            class="btn btn-add-to-cart btn-success w-100"
          >
            Xem sản phẩm
          </router-link>
        </div>
      </div>
    </div>

  </div>
</div>
 <!-- end -->

</template>
<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const goToDetail = (id) => {
  router.push({ name: 'ProductDetail', params: { id } })
}
import { ref, onMounted } from 'vue'
import axios from 'axios'

const products = ref([])

const fetchProducts = async () => {
  try {
    const response = await axios.get('/api/v1/product/Get')
    console.log('Dữ liệu từ API:', response.data)
    products.value = response.data.result.data
  } catch (error) {
    console.error('Lỗi khi lấy dữ liệu sản phẩm:', error)
  }
}

onMounted(() => {
  fetchProducts()
})
const formatPrice = (price) => {
  if (!price && price !== 0) return '';
  return new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
  }).format(price);
};
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
  color: #ffffff;
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
  max-width: 260px; /* Thu nhỏ chiều rộng card */
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
  border-radius: 10px;
  transition: background 0.3s ease;
}
.carousel-control-prev,
.carousel-control-next {
    margin-left: -80px;
}

.carousel-control-prev-icon,
.carousel-control-next-icon {
    filter: invert(1); /* Đổi màu icon thành trắng nếu cần */
    margin-right: -80px;
}
.product-image img {
  width: 100%; /* Đảm bảo ảnh không bị méo */
  height: 180px; /* Giới hạn chiều cao */
  object-fit: contain; /* Giữ tỷ lệ ảnh, không bị méo */
}
.marquee {
  white-space: nowrap;
  overflow: hidden;
  animation: scroll 10s linear infinite;
}

@keyframes scroll {
  0%   { transform: translateX(100%); }
  100% { transform: translateX(-100%); }
}
</style>

<template>
  <div class="container my-5">
    <h1 class="mb-4">Điện thoại di động</h1>
    <div class="container my-4">
      <div class="row row-cols-2 row-cols-md-4 row-cols-lg-6 g-3">
        <!-- Apple -->
        <div class="col">
          <a href="#" class="text-decoration-none">
            <div class="card text-center p-3 brand-card">
              <img src="..//..//assets/img/apple.png" class="brand-logo" alt="Apple" />
            </div>
          </a>
        </div>
        <!-- Samsung -->
        <div class="col">
          <a href="#" class="text-decoration-none">
            <div class="card text-center p-3 brand-card">
              <img src="..//..//assets/img/samsung.png" class="brand-logo" alt="Samsung" />
            </div>
          </a>
        </div>
        <!-- Xiaomi -->
        <div class="col">
          <a href="#" class="text-decoration-none">
            <div class="card text-center p-3 brand-card">
              <img src="..//..//assets/img/xiaomi.png" class="brand-logo" alt="Xiaomi" />
            </div>
          </a>
        </div>
        <!-- Oppo -->
        <div class="col">
          <a href="#" class="text-decoration-none">
            <div class="card text-center p-3 brand-card">
              <img src="..//..//assets/img/oppo.png" class="brand-logo" alt="Oppo" />
            </div>
          </a>
        </div>
        <!-- Realme -->
        <div class="col">
          <a href="#" class="text-decoration-none">
            <div class="card text-center p-3 brand-card">
              <img src="..//..//assets/img/realme.png" class="brand-logo" alt="Realme" />
            </div>
          </a>
        </div>
        <!-- Vivo -->
        <div class="col">
          <a href="#" class="text-decoration-none">
            <div class="card text-center p-3 brand-card">
              <img src="..//..//assets/img/vivo.png" class="brand-logo" alt="Vivo" />
            </div>
          </a>
        </div>
      </div>
    </div>
    <div class="row">
      <!-- Sidebar with filters -->
      <div class="col-md-3 mb-4">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Bộ lọc</h5>
            <form @submit.prevent="filterByPrice">
              <div class="mb-3">
                <label class="form-label fw-semibold">Lọc theo giá</label>
                <div class="row g-2">
                  <div class="col">
                    <input type="number" class="form-control" v-model="priceMin" placeholder="Giá từ" min="0" />
                  </div>
                  <div class="col">
                    <input type="number" class="form-control" v-model="priceMax" placeholder="Giá đến" min="0" />
                  </div>
                </div>
              </div>
              <button type="submit" class="btn btn-primary w-100">Áp dụng</button>
            </form>
          </div>
        </div>
      </div>

      <!-- Product listing -->
      <div class="col-md-9">
        <div class="d-flex justify-content-between align-items-center mb-4">
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

        <div class="row row-cols-1 row-cols-md-4 g-4">
          <!-- Product Card 1 -->
          <div class="col" v-for="product in products" :key="product.id">
            <div class="card product-card">
              <img :src="product.images.length ? product.images[0].url : '/default.jpg'" class="card-img-top product-image" style="width: 100%; height: 100%" :alt="product.productName" />
              <div class="card-body text-center">
                <h5 class="product-title">{{ product.productName }}</h5>
                <p class="product-price">{{ formatPrice(product.salePrice) }}</p>
                <router-link :to="`/category/product/${product.id}`" class="btn btn-add-to-cart btn-success w-100"> Xem sản phẩm </router-link>
              </div>
            </div>
          </div>
        </div>

        <!-- Pagination -->
        <nav aria-label="Page navigation" class="mt-4">
          <ul class="pagination justify-content-center">
            <li class="page-item disabled">
              <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Trước</a>
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

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
const products = ref([]);
const priceMin = ref(null);
const priceMax = ref(null);
const fetchProducts = async () => {
  try {
    const response = await axios.get("/asm/api/v1/product/List");
    console.log("Dữ liệu từ API:", response.data);
    products.value = response.data.result;
  } catch (error) {
    console.error("Lỗi khi lấy dữ liệu sản phẩm:", error);
  }
};
const filterByPrice = () => {
  if (priceMin.value && priceMax.value) {
    products.value = products.value.filter((product) => product.price >= priceMin.value && product.price <= priceMax.value);
  } else if (priceMin.value) {
    products.value = products.value.filter((product) => product.price >= priceMin.value);
  } else if (priceMax.value) {
    products.value = products.value.filter((product) => product.price <= priceMax.value);
  } else {
    fetchProducts(); // Nếu không có giá, tải lại tất cả sản phẩm
  }
};
onMounted(() => {
  fetchProducts();
});
const formatPrice = (price) => {
  if (!price && price !== 0) return "";
  return new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  }).format(price);
};
</script>

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

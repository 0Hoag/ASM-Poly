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
        <div class="col" v-for="product in paginatedProducts" :key="product.id">
          <div class="card product-card">
            <img :src="product.images[0].url" class="card-img-top product-image" alt="Sản phẩm 1" />
            <div class="card-body text-center">
              <h5 class="product-title">{{ product.productName }}</h5>
              <p class="product-price">{{ product.priceSale ? product.priceSale : product.price }}</p>
              <router-link :to="'/category/product/' + product.id" class="btn btn-add-to-cart btn-success w-100"> Xem sản phẩm </router-link>
            </div>
          </div>
        </div>

        <!-- Thêm các Product Card khác tương tự ở đây -->
      </div>
      <h3 v-if="listProduct.length === 0">Không có kết quả tìm thấy</h3>
      <!-- Pagination -->

      <nav aria-label="Page navigation" class="mt-4" v-if="listProduct.length > 0">
        <ul class="pagination justify-content-center">
          <li class="page-item">
            <button @click="currentPage = 1" class="page-link">
              <span aria-hidden="true">&laquo;</span>
            </button>
          </li>
          <li class="page-item">
            <button @click="prevPage" :disabled="currentPage === 1" class="page-link">
              <span aria-hidden="true">&#8826;</span>
            </button>
          </li>
          <li class="page-item active">
            <a class="page-link">{{ currentPage }} / {{ totalPage }}</a>
          </li>
          <li class="page-item">
            <button @click="nextPage" :disabled="currentPage === totalPage" class="page-link">&#8827;</button>
          </li>
          <li class="page-item">
            <button @click="currentPage = totalPage" class="page-link">
              <span aria-hidden="true">&raquo;</span>
            </button>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { computed, onBeforeMount, ref } from "vue";
import { useRoute } from "vue-router";
// khai bao bien
const route = useRoute();
const listProduct = ref([]);
const currentPage = ref(1);
const limit = ref(9);
const keyword = ref(route.query.keyword || "");

// computed
const paginatedProducts = computed(() => {
  const startIndex = (currentPage.value - 1) * limit.value;
  const endIndex = startIndex + limit.value;
  return listProduct.value.slice(startIndex, endIndex);
});

const totalPage = computed(() => {
  return Math.ceil(listProduct.value.length / limit.value);
});

// methods
const getAllProducts = async () => {
  try {
    const response = await axios("http://localhost:8080/asm/api/v1/product/List"); // Thay thế bằng URL API thực tế
    listProduct.value = response.data.result.filter((item) => item.productName.includes(keyword.value)); // Lọc sản phẩm không bị xóa

    console.log(listProduct.value);
  } catch (error) {
    console.error("Error fetching products:", error.message);
  }
};

const prevPage = () => {
  if (currentPage.value > 1) {
    currentPage.value--;
  }
};

const nextPage = () => {
  if (currentPage.value < totalPage.value) {
    currentPage.value++;
    console.log(currentPage.value);
  }
};

// mouted
onBeforeMount(async () => {
  await getAllProducts();
});
</script>

<style lang="scss" scoped></style>
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

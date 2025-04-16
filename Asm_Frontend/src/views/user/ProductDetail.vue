<template>
  <div class="container my-5" v-if="product">
    <div class="row">
      <div class="col-md-6">
        <!-- Carousel ảnh sản phẩm -->
        <div id="productCarousel" class="carousel slide" data-bs-ride="carousel">
          <div class="carousel-inner">
            <div
              v-for="(img, index) in product.images"
              :key="img.id"
              :class="['carousel-item', { active: index === 0 }]"
            >
              <div class="fixed-image-frame mx-auto d-flex justify-content-center align-items-center">
                <img :src="img.url" class="fixed-product-img" alt="Ảnh sản phẩm" />
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="col-md-6">
        <h3>{{ product.productName }}</h3>
        <p class="text-muted">{{ product.description }}</p>
        <h4 class="text-danger">
          {{ product.salePrice.toLocaleString() }}₫
          <small class="text-decoration-line-through text-secondary ms-2">
            {{ product.price.toLocaleString() }}₫
          </small>
        </h4>

        <div class="mt-5">
          <button @click="addToCart(product)" class="btn btn-outline-primary w-25 mx-2">
            <i class="bi bi-cart-fill"></i> Thêm vào giỏ
          </button>
          <button class="btn btn-danger w-25 mx-2">Mua ngay</button>
          <button class="btn btn-warning w-25 d-block m-2">
            <i class="bi bi-heart"></i> Yêu thích
          </button>
        </div>
      </div>
    </div>

    <!-- Thông số kỹ thuật và sản phẩm liên quan -->
    <!-- ... -->
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const router = useRouter()
const product = ref(null)
const products = ref([])

// Hàm lấy thông tin sản phẩm
const fetchProduct = async () => {
  try {
    const response = await axios.get(`/api/v1/product/${route.params.id}`)
    product.value = response.data.result
  } catch (error) {
    console.error('Lỗi khi lấy dữ liệu sản phẩm:', error)
  }
}

// Hàm lấy danh sách sản phẩm liên quan
const fetchProducts = async () => {
  try {
    const response = await axios.get('/api/v1/product/Get')
    console.log('Dữ liệu từ API:', response.data)
    products.value = response.data.result.data
  } catch (error) {
    console.error('Lỗi khi lấy dữ liệu sản phẩm:', error)
  }
}

// Hàm định dạng giá
const formatPrice = (price) => {
  if (!price && price !== 0) return ''
  return new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
  }).format(price)
}

// Hàm thêm sản phẩm vào giỏ hàng
const addToCart = async (product) => {
  try {
    let cartId = localStorage.getItem('cartId');
    console.log('cardid',cartId);
    const userIdRaw = localStorage.getItem('userId');

    const userId = userIdRaw && userIdRaw !== 'undefined' && userIdRaw !== 'null'
      ? Number(userIdRaw)
      : null;

    if (!userId) {
      alert('⚠️ Vui lòng đăng nhập trước khi thêm sản phẩm vào giỏ hàng.');
      return;
    }

    // Nếu chưa có cartId thì lấy hoặc tạo mới
    if (!cartId) {
      try {
        const cartRes = await axios.get(`/api/v1/cart/${userId}`);
        const userCart = cartRes.data.result;

        if (userCart && userCart.id) {
          cartId = userCart.id;
          localStorage.setItem('cartId', cartId);
        } else {
          const newCartRes = await axios.post(`/api/v1/cart`, { userId });
          const newCart = newCartRes.data.result;

          if (!newCart || !newCart.id) {
            throw new Error('Không thể tạo giỏ hàng.');
          }

          cartId = newCart.id;
          localStorage.setItem('cartId', cartId);
        }
      } catch (err) {
        console.error('❌ Lỗi lấy/tạo giỏ hàng:', err.response?.data || err);
        alert('Không thể lấy hoặc tạo giỏ hàng. Vui lòng thử lại sau.');
        return;
      }
    }

    const payload = {
      cart: Number(cartId),
      product: Number(product.id),
      quantity: 1,
    };

    const response = await axios.post(`/api/v1/cart-detail/`, payload);
    alert('✅ Thêm vào giỏ hàng thành công!');
  } catch (error) {
    console.error('❌ Lỗi khi thêm vào giỏ hàng:', error.response?.data || error);
    alert(`Thêm vào giỏ thất bại: ${error.response?.data?.message || 'Lỗi không xác định'}`);
  }
};

// Gọi các hàm khi component được mount
onMounted(() => {
  fetchProduct()
  fetchProducts()
})
</script>

<style scoped>
.thumbnail-img {
  width: 40px;
  height: 40px;
  object-fit: cover;
}

.carousel-inner {
  display: flex;
  overflow: hidden;
}
.carousel-item {
  display: flex;
  justify-content: space-around;
  flex: 0 0 100%;
}
.product-card {
  width: 200px;
  text-align: center;
}
.fixed-image-frame {
  width: 400px;
  height: 400px;
  background-color: #fff;
  border: 2px solid #e0e0e0;
  border-radius: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  overflow: hidden;
}

.fixed-product-img {
  max-width: 100%;
  max-height: 100%;
  object-fit: contain;
}
</style>
<template>
  <div class="container my-5" v-if="product">
    <div class="row">
      <div class="col-md-6">
        <!-- Carousel ảnh sản phẩm -->
        <div id="productCarousel" class="carousel slide" data-bs-ride="carousel">
          <div class="carousel-inner">
            <div v-for="(img, index) in product.images" :key="img.id" :class="['carousel-item', { active: index === 0 }]">
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
          {{ formatPrice(product.salePrice) }}
          <small class="text-decoration-line-through text-secondary ms-2">
            {{ formatPrice(product.price) }}
          </small>
        </h4>

        <div class="mt-5">
          <button @click="addToCart(product)" class="btn btn-outline-primary w-25 mx-2"><i class="bi bi-cart-fill"></i> Thêm vào giỏ</button>
          <button class="btn btn-danger w-25 mx-2">Mua ngay</button>
          <button class="btn btn-warning w-25 d-block m-2"><i class="bi bi-heart"></i> Yêu thích</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";

const route = useRoute();
const product = ref(null);
const products = ref([]);

// Lấy sản phẩm theo ID từ route
const fetchProduct = async () => {
  try {
    const res = await axios.get(`/asm/api/v1/product/${route.params.id}`);
    product.value = res.data.result;
  } catch (err) {
    console.error("Lỗi khi lấy dữ liệu sản phẩm:", err);
  }
};

// Lấy danh sách tất cả sản phẩm
const fetchProducts = async () => {
  try {
    const res = await axios.get("/asm/api/v1/product/Get");
    console.log("Dữ liệu từ API:", res.data);
    products.value = res.data.result.data;
  } catch (err) {
    console.error("Lỗi khi lấy danh sách sản phẩm:", err);
  }
};

// Định dạng giá tiền
const formatPrice = (price) => {
  if (!price && price !== 0) return "";
  return new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  }).format(price);
};

// Thêm sản phẩm vào giỏ hàng
const addToCart = async (product) => {
  try {
    let cartId = localStorage.getItem("cartId");
    const userIdRaw = localStorage.getItem("userId");
    const userId = userIdRaw && userIdRaw !== "undefined" && userIdRaw !== "null" ? Number(userIdRaw) : null;

    if (!userId) {
      alert("⚠️ Vui lòng đăng nhập trước khi thêm sản phẩm vào giỏ hàng.");
      return;
    }

    console.log("🧑 userIdRaw:", userIdRaw);
    console.log("🧾 cartId:", cartId);
    console.log("✅ userId:", userId);

    // Nếu không có cartId thì tạo giỏ hàng mới
    if (!cartId) {
      console.log("🛠 CartId không tồn tại, tiến hành tạo giỏ hàng mới...");
      try {
        const createCartPayload = { user: userId };
        const newCartRes = await axios.post(`/asm/api/v1/cart/`, createCartPayload);
        const isCreated = newCartRes.data.result;

        if (isCreated) {
          // Gọi lại để lấy thông tin giỏ hàng (bao gồm ID)
          const cartDetailRes = await axios.get(`/asm/api/v1/cart/${userId}`);
          const cartData = cartDetailRes.data.result;

          if (!cartData || !cartData.id) {
            throw new Error("Không lấy được thông tin giỏ hàng sau khi tạo.");
          }

          cartId = cartData.id;
          localStorage.setItem("cartId", String(cartId));
          console.log("🆕 Giỏ hàng mới đã tạo và lấy lại:", cartId);
        } else {
          throw new Error("Tạo giỏ hàng thất bại.");
        }
        localStorage.setItem("cartId", String(cartId));
        console.log("🆕 Giỏ hàng mới đã tạo:", cartId);
      } catch (err) {
        console.error("❌ Lỗi khi tạo giỏ hàng mới:", err.response?.data || err);
        alert("Không thể tạo giỏ hàng. Vui lòng thử lại sau.");
        return;
      }
    }

    // Gửi request thêm sản phẩm vào giỏ hàng
    const cartDetailPayload = {
      cart: Number(cartId),
      product: Number(product.id),
      quantity: 1,
    };

    const res = await axios.post(`/asm/api/v1/cart-detail/`, cartDetailPayload);
    alert("✅ Sản phẩm đã được thêm vào giỏ hàng!");
    console.log("📦 Thêm sản phẩm thành công:", res.data);
  } catch (error) {
    console.error("❌ Lỗi khi thêm vào giỏ hàng:", error.response?.data || error);
    alert(`❌ Thêm vào giỏ thất bại: ${error.response?.data?.message || "Lỗi không xác định"}`);
  }
};

onMounted(() => {
  fetchProduct();
  fetchProducts();
});
</script>

<style scoped>
.carousel-inner {
  display: flex;
  overflow: hidden;
}

.carousel-item {
  display: flex;
  justify-content: center;
  flex: 0 0 100%;
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

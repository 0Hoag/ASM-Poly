<template>
  <div class="container py-5">
    <div class="row justify-content-center">
      <div class="col-md-6 col-lg-5">
        <div class="bg-white rounded shadow-sm p-4 p-md-5">
          <h2 class="text-center mb-4">Đăng nhập</h2>

          <form @submit.prevent="handleLogin">
            <div class="mb-3">
              <label for="email" class="form-label">Email hoặc số điện thoại</label>
              <input type="text" class="form-control py-2" id="email" v-model="email" placeholder="Nhập email hoặc số điện thoại" />
            </div>

            <div class="mb-3">
              <label for="password" class="form-label">Mật khẩu</label>
              <div class="input-group">
                <input :type="passwordVisible ? 'text' : 'password'" class="form-control py-2" id="password" v-model="password" placeholder="Nhập mật khẩu" />
                <button class="btn btn-outline-secondary" type="button" @click="togglePasswordVisibility">
                  <i :class="passwordVisible ? 'bi bi-eye-slash' : 'bi bi-eye'"></i>
                </button>
              </div>
            </div>

            <p v-if="error" class="text-danger text-center">{{ error }}</p>

            <div class="d-flex justify-content-between mb-4">
              <div class="form-check">
                <input class="form-check-input" type="checkbox" id="rememberMe" v-model="rememberMe" />
                <label class="form-check-label" for="rememberMe"> Ghi nhớ đăng nhập </label>
              </div>
              <router-link to="/re-password" class="text-decoration-none">Quên mật khẩu?</router-link>
            </div>

            <div class="d-grid mb-4">
              <button type="submit" class="btn btn-primary py-2">Đăng nhập</button>
            </div>

            <div class="text-center mb-4">
              <p class="text-muted">Hoặc đăng nhập với</p>
              <div class="d-flex justify-content-center gap-3">
                <a href="#" class="btn btn-outline-primary"> <i class="bi bi-facebook me-2"></i>Facebook </a>
                <a href="#" class="btn btn-outline-danger"> <i class="bi bi-google me-2"></i>Google </a>
              </div>
            </div>

            <div class="text-center">
              <p class="mb-0">
                Bạn chưa có tài khoản?
                <router-link to="/register" class="text-decoration-none">Đăng ký ngay</router-link>
              </p>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onBeforeMount, ref } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

const cartAll = ref([]);
const email = ref("");
const password = ref("");
const error = ref("");
const rememberMe = ref(false);
const passwordVisible = ref(false);
const router = useRouter();

const togglePasswordVisibility = () => {
  passwordVisible.value = !passwordVisible.value;
};

const handleLogin = async () => {
  try {
    const payload = {
      email: email.value,
      password: password.value,
    };

    console.log("Dữ liệu gửi đi:", payload);

    const response = await axios.post("/asm/api/v1/auth/login", payload);

    console.log("Phản hồi từ API:", response.data);

    if (response.data.code === 1000 && response.data.result) {
      const user = response.data.result;
      const token = btoa(`${payload.email}:${payload.password}`);
      localStorage.setItem("authToken", `Basic ${token}`);
      localStorage.setItem("role", response.data.result.role);
      // Lưu userId và thông tin người dùng vào localStorage
      localStorage.setItem("userId", String(user.id)); // Lưu userId dưới dạng chuỗi
      localStorage.setItem("user", JSON.stringify(user)); // Lưu toàn bộ thông tin người dùng
      // Kiểm tra và lưu cartId nếu có
      const cartDetail = cartAll.value.find((cart) => cart.userId === user.id);

      console.log("Chi tiết giỏ hàng:", cartDetail);
      if (cartDetail) {
        localStorage.setItem("cartId", String(cartDetail.id)); // Lưu cartId dưới dạng chuỗi
        console.log("Cart ID đã được lưu:", cartDetail.id);
      } else {
        console.log("Không có giỏ hàng, cần tạo mới sau.");
      }

      // Chuyển hướng về trang chủ
      router.push("/").then(() => window.location.reload());
    } else {
      // Xử lý lỗi từ API
      error.value = response.data.result?.message || "Đã có lỗi xảy ra!";
      console.error("Thông báo lỗi:", response.data.result?.message);
    }
  } catch (err) {
    console.error("Lỗi xảy ra:", err);
    if (err.response) {
      error.value = err.response.data.message || "Lỗi từ API!";
    } else {
      error.value = "Lỗi không xác định!";
    }
  }
};

const getAllCart = async () => {
  try {
    const response = await axios.get(`/asm/api/v1/cart/List`);
    cartAll.value = response.data.result;
  } catch (error) {
    console.error("Lỗi khi lấy giỏ hàng:", error);
  }
};
onBeforeMount(async () => {
  await getAllCart();
});
</script>

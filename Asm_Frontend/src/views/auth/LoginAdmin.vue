<template>
  <div class="container py-5">
    <div class="row justify-content-center">
      <div class="col-md-6 col-lg-5">
        <div class="bg-white rounded shadow-sm p-4 p-md-5">
          <h2 class="text-center mb-4">Đăng nhập</h2>

          <div>
            <div class="mb-3">
              <label for="email" class="form-label">Email </label>
              <input type="text" class="form-control py-2" id="email" placeholder="Nhập email " v-model="form.email" />
            </div>

            <div class="mb-3">
              <label for="password" class="form-label">Mật khẩu</label>
              <div class="input-group">
                <input type="password" class="form-control py-2" id="password" placeholder="Nhập mật khẩu" v-model="form.password" />
                <button class="btn btn-outline-secondary" type="button">
                  <i class="bi bi-eye"></i>
                </button>
              </div>
            </div>

            <div class="d-flex justify-content-between mb-4">
              <div class="form-check">
                <input class="form-check-input" type="checkbox" id="rememberMe" />
                <label class="form-check-label" for="rememberMe"> Ghi nhớ đăng nhập </label>
              </div>
            </div>

            <div class="d-grid mb-4">
              <button type="button" @click="login" class="btn btn-primary py-2">Đăng nhập</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { onBeforeMount, ref } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();
const cartAll = ref([]);
const form = ref({
  email: "",
  password: "",
});
const login = async () => {
  try {
    const { email, password } = form.value;
    if (!email || !password) return alert("Vui lòng nhập đăng nhập");
    const resp = await axios.post("http://localhost:8080/asm/api/v1/auth/login", { email, password });
    if (resp.data.result) {
      // mã hóa thành base64
      const user = resp.data.result;
      const token = btoa(`${email}:${password}`);
      localStorage.setItem("authToken", `Basic ${token}`);
      localStorage.setItem("role", resp.data.result.role);
      localStorage.setItem("userId", String(user.id)); // Lưu userId dưới dạng chuỗi
      localStorage.setItem("user", JSON.stringify(user)); // Lưu toàn bộ thông tin người dùng
      const cartDetail = cartAll.value.find((cart) => cart.userId === user.id);
      console.log("Chi tiết giỏ hàng:", cartDetail);
      if (cartDetail) {
        localStorage.setItem("cartId", String(cartDetail.id)); // Lưu cartId dưới dạng chuỗi
        console.log("Cart ID đã được lưu:", cartDetail.id);
      } else {
        console.log("Không có giỏ hàng, cần tạo mới sau.");
      }
      // Reload trang để axios nhận token từ main.js
      if (resp.data.result.role == false) {
        alert("Vui lòng đăng nhập với quyền admin");
        return;
      }
      router.replace("/admin/dashboard");
      return;
    }
  } catch (error) {
    console.log(error.message);
    alert("Sai tài khoản hoặc mật khẩu");
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

<style lang="scss" scoped></style>

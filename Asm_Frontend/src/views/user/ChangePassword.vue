<template>
  <div class="bg-white rounded shadow-sm p-4">
    <h4 class="mb-4">Đổi mật khẩu</h4>
    <hr />

    <div class="row">
      <div class="col-md-8">
        <form @submit.prevent="changePassword">
          <!-- Mật khẩu hiện tại -->
          <div class="mb-3">
            <label for="currentPassword" class="form-label fw-medium">Mật khẩu hiện tại</label>
            <div class="input-group">
              <input :type="showCurrent ? 'text' : 'password'" id="currentPassword" class="form-control py-2" v-model="currentPassword" required />
              <button class="btn btn-outline-secondary" type="button" @click="showCurrent = !showCurrent">
                <i :class="showCurrent ? 'bi bi-eye-slash' : 'bi bi-eye'"></i>
              </button>
            </div>
          </div>

          <!-- Mật khẩu mới -->
          <div class="mb-3">
            <label for="newPassword" class="form-label fw-medium">Mật khẩu mới</label>
            <div class="input-group">
              <input :type="showNew ? 'text' : 'password'" id="newPassword" class="form-control py-2" v-model="newPass" required />
              <button class="btn btn-outline-secondary" type="button" @click="showNew = !showNew">
                <i :class="showNew ? 'bi bi-eye-slash' : 'bi bi-eye'"></i>
              </button>
            </div>
            <div class="form-text">Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường và số.</div>
          </div>

          <!-- Xác nhận mật khẩu -->
          <div class="mb-4">
            <label for="confirmPassword" class="form-label fw-medium">Xác nhận mật khẩu mới</label>
            <div class="input-group">
              <input :type="showConfirm ? 'text' : 'password'" id="confirmPassword" class="form-control py-2" v-model="confirmPassword" required />
              <button class="btn btn-outline-secondary" type="button" @click="showConfirm = !showConfirm">
                <i :class="showConfirm ? 'bi bi-eye-slash' : 'bi bi-eye'"></i>
              </button>
            </div>
          </div>

          <!-- Nút thao tác -->
          <div class="d-flex justify-content-end gap-2 mt-4">
            <button type="button" class="btn btn-outline-secondary" @click="resetForm">Hủy</button>
            <button type="submit" class="btn btn-primary">Cập nhật mật khẩu</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

const router = useRouter();

const currentPassword = ref("");
const newPass = ref("");
const confirmPassword = ref("");

const showCurrent = ref(false);
const showNew = ref(false);
const showConfirm = ref(false);

// Reset form
const resetForm = () => {
  currentPassword.value = "";
  newPass.value = "";
  confirmPassword.value = "";
  showCurrent.value = false;
  showNew.value = false;
  showConfirm.value = false;
};

// Đổi mật khẩu
const changePassword = async () => {
  // Kiểm tra hợp lệ
  if (newPass.value !== confirmPassword.value) {
    alert("Mật khẩu mới và xác nhận mật khẩu không khớp!");
    return;
  }

  if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/.test(newPass.value)) {
    alert("Mật khẩu mới phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường và số.");
    return;
  }

  try {
    const userId = JSON.parse(localStorage.getItem("userId"));
    if (!userId) {
      alert("Bạn cần đăng nhập để thay đổi mật khẩu!");
      return;
    }

    const payload = {
      password: currentPassword.value,
      newPass: newPass.value,
      confirmPassword: confirmPassword.value,
    };

    const response = await axios.post(`/asm/api/v1/user/changePass/${userId}`, payload);

    if (response.data.code === 1000) {
      alert("Mật khẩu đã được thay đổi thành công! Vui lòng đăng nhập lại.");
      localStorage.clear();
      router.push("/login");
    } else {
      alert(`Đổi mật khẩu thất bại: ${response.data.message}`);
    }
  } catch (error) {
    console.error("Lỗi khi thay đổi mật khẩu:", error.response?.data || error.message);
    if (error.response?.data?.message) {
      alert(`Lỗi: ${error.response.data.message}`);
    } else {
      alert("Có lỗi xảy ra khi thay đổi mật khẩu!");
    }
  }
};
</script>

<style scoped>
/* Tùy chỉnh thêm nếu cần */
</style>

<template>
  <div class="bg-white rounded-3 shadow-sm p-4">
    <h4 class="mb-4">Thông tin cá nhân</h4>
    <hr />

    <div class="row">
      <form>
        <div class="mb-4">
          <label for="fullName" class="form-label fw-medium">Họ và tên</label>
          <input type="text" class="form-control w-100 mw-75 py-2" id="fullName" placeholder="Nguyễn Văn A" v-model="user.fullName" />
        </div>

        <div class="mb-4">
          <label for="email" class="form-label fw-medium">Email</label>
          <input type="email" class="form-control w-100 mw-75 py-2" id="email" placeholder="email@example.com" v-model="user.email" />
        </div>

        <div class="mb-4">
          <label for="phone" class="form-label fw-medium">Số điện thoại</label>
          <input type="tel" class="form-control w-100 mw-75 py-2" id="phone" placeholder="+84 xxx xxx xxx" v-model="user.phoneNumber" />
        </div>

        <div class="d-flex justify-content-end gap-3 mt-4">
          <button type="button" class="btn btn-outline-secondary">Hủy</button>
          <button @click="updateUser(user)" type="submit" class="btn btn-primary">Lưu thay đổi</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
// biến để chứa dữ liệu
const user = ref({
  fullName: "",
  email: "",
});
onMounted(async () => {
  const storedUser = JSON.parse(localStorage.getItem("user"));
  const userId = storedUser?.id;

  if (userId) {
    try {
      const response = await axios.get(`/asm/api/v1/profile/${userId}`);
      user.value = response.data.result;
    } catch (error) {
      console.error("Có lỗi xảy ra khi lấy dữ liệu người dùng:", error);
    }
  }
});
//update user
const updateUser = async () => {
  const userId = JSON.parse(localStorage.getItem("user")).id;
  try {
    const response = await axios.put(`/asm/api/v1/profile/${userId}`, user.value);
    if (response.status === 200) {
      alert("Cập nhật thông tin thành công!");
    } else {
      alert("Cập nhật thông tin thất bại!");
    }
  } catch (error) {
    console.error("Có lỗi xảy ra khi cập nhật thông tin người dùng:", error);
  }
};
</script>

<style lang="scss" scoped></style>

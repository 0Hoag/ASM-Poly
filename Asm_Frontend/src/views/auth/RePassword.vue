<template>
  <div class="container py-5">
    <div class="row justify-content-center">
      <div class="col-md-6 col-lg-5">
        <div class="text-center mb-4">
          <img src="./img/logo.png" class="img-fluid" alt="" />
        </div>

        <div class="bg-white rounded shadow-sm p-4 p-md-5">
          <h2 class="text-center mb-3">Quên mật khẩu</h2>

          <form>
            <div class="mb-4">
              <label for="emailOrPhone" class="form-label"
                >Email hoặc số điện thoại</label
              >
              <input
                type="text"
                class="form-control py-2"
                id="emailOrPhone"
                placeholder="Nhập email hoặc số điện thoại"
              />
            </div>

            <div class="d-grid mb-3">
              <button type="button" class="btn btn-primary py-2">
                Tiếp tục
              </button>
            </div>

            <div class="text-center">
              <router-link to="/login" class="text-decoration-none"
                >Quay lại đăng nhập</router-link
              >
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import router from "../../routes";

import axios from 'axios';

const changePassword = async (userId, oldPassword, newPassword, confirmPassword) => {
  try {
    const response = await axios.post(`/api/v1/user/changePass/${userId}`, {
      oldPassword: oldPassword,
      newPassword: newPassword,
      confirmPassword: confirmPassword,
    });

    if (response.data.code === 1000) {
      alert('Mật khẩu đã được thay đổi thành công!');
    } else {
      alert('Đổi mật khẩu thất bại!');
    }
  } catch (error) {
    console.error('Lỗi khi thay đổi mật khẩu:', error.response?.data || error.message);
    alert('Có lỗi xảy ra khi thay đổi mật khẩu!');
  }
};

</script>

<style lang="scss" scoped></style>

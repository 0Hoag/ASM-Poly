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
</script>

<style lang="scss" scoped></style>

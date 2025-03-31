<template>
  <div class="container py-5">
    <div class="row justify-content-center">
      <div class="col-md-6 col-lg-5">
        <div class="bg-white rounded shadow-sm p-4 p-md-5">
          <h2 class="text-center mb-4">Đăng nhập</h2>

          <form>
            <div class="mb-3">
              <label for="email" class="form-label"
                >Email hoặc số điện thoại</label
              >
              <input
                type="text"
                class="form-control py-2"
                id="email"
                placeholder="Nhập email hoặc số điện thoại"
              />
            </div>

            <div class="mb-3">
              <label for="password" class="form-label">Mật khẩu</label>
              <div class="input-group">
                <input
                  type="password"
                  class="form-control py-2"
                  id="password"
                  placeholder="Nhập mật khẩu"
                />
                <button class="btn btn-outline-secondary" type="button">
                  <i class="bi bi-eye"></i>
                </button>
              </div>
            </div>

            <div class="d-flex justify-content-between mb-4">
              <div class="form-check">
                <input
                  class="form-check-input"
                  type="checkbox"
                  id="rememberMe"
                />
                <label class="form-check-label" for="rememberMe">
                  Ghi nhớ đăng nhập
                </label>
              </div>
              <router-link to="/re-password" class="text-decoration-none"
                >Quên mật khẩu?</router-link
              >
            </div>

            <div class="d-grid mb-4">
              <button type="submit" class="btn btn-primary py-2">
                Đăng nhập
              </button>
            </div>

            <div class="text-center mb-4">
              <p class="text-muted">Hoặc đăng nhập với</p>
              <div class="d-flex justify-content-center gap-3">
                <a href="#" class="btn btn-outline-primary">
                  <i class="bi bi-facebook me-2"></i>Facebook
                </a>
                <a href="#" class="btn btn-outline-danger">
                  <i class="bi bi-google me-2"></i>Google
                </a>
              </div>
            </div>

            <div class="text-center">
              <p class="mb-0">
                Bạn chưa có tài khoản?
                <router-link to="/register" class="text-decoration-none"
                  >Đăng ký ngay</router-link
                >
              </p>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>

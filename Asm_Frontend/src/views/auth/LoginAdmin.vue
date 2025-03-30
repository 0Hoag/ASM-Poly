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
            </div>

            <div class="d-grid mb-4">
              <button type="submit" class="btn btn-primary py-2">
                Đăng nhập
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>

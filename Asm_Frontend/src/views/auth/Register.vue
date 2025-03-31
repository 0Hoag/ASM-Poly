<template>
  <div class="container py-5">
    <div class="row justify-content-center">
      <div class="col-md-8 col-lg-6">
        <div class="bg-white rounded shadow-sm p-4 p-md-5">
          <h2 class="text-center mb-4">Đăng ký tài khoản</h2>

          <form>
            <div class="mb-3">
              <label for="firstName" class="form-label">Họ và tên</label>
              <input
                type="text"
                class="form-control py-2"
                id="firstName"
                placeholder="Nhập họ và tên"
              />
            </div>

            <div class="mb-3">
              <label for="email" class="form-label">Email</label>
              <input
                type="email"
                class="form-control py-2"
                id="email"
                placeholder="Nhập email"
              />
            </div>

            <div class="mb-3">
              <label for="phone" class="form-label">Số điện thoại</label>
              <input
                type="tel"
                class="form-control py-2"
                id="phone"
                placeholder="Nhập số điện thoại"
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
              <div class="form-text">
                Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường và
                số.
              </div>
            </div>

            <div class="mb-4">
              <label for="confirmPassword" class="form-label"
                >Xác nhận mật khẩu</label
              >
              <div class="input-group">
                <input
                  type="password"
                  class="form-control py-2"
                  id="confirmPassword"
                  placeholder="Nhập lại mật khẩu"
                />
                <button class="btn btn-outline-secondary" type="button">
                  <i class="bi bi-eye"></i>
                </button>
              </div>
            </div>

            <div class="mb-4">
              <div class="form-check">
                <input
                  class="form-check-input"
                  type="checkbox"
                  id="agreeTerms"
                />
                <label class="form-check-label" for="agreeTerms">
                  Tôi đồng ý với
                  <a href="#" class="text-decoration-none"
                    >Điều khoản dịch vụ</a
                  >
                  và
                  <a href="#" class="text-decoration-none"
                    >Chính sách bảo mật</a
                  >
                </label>
              </div>
            </div>

            <div class="d-grid mb-4">
              <button type="submit" class="btn btn-primary py-2">
                Đăng ký
              </button>
            </div>

            <div class="text-center mb-4">
              <p class="text-muted">Hoặc đăng ký với</p>
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
                Bạn đã có tài khoản?
                <router-link to="/login" class="text-decoration-none"
                  >Đăng nhập</router-link
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

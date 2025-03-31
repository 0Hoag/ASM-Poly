<template>
  <div class="bg-white rounded shadow-sm p-4">
    <h4 class="mb-4">Đổi mật khẩu</h4>
    <hr />

    <div class="row">
      <div class="col-md-8">
        <form>
          <div class="mb-3">
            <label for="currentPassword" class="form-label fw-medium"
              >Mật khẩu hiện tại</label
            >
            <div class="input-group">
              <input
                type="password"
                class="form-control py-2"
                id="currentPassword"
              />
              <button class="btn btn-outline-secondary" type="button">
                <i class="bi bi-eye"></i>
              </button>
            </div>
          </div>

          <div class="mb-3">
            <label for="newPassword" class="form-label fw-medium"
              >Mật khẩu mới</label
            >
            <div class="input-group">
              <input
                type="password"
                class="form-control py-2"
                id="newPassword"
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
            <label for="confirmPassword" class="form-label fw-medium"
              >Xác nhận mật khẩu mới</label
            >
            <div class="input-group">
              <input
                type="password"
                class="form-control py-2"
                id="confirmPassword"
              />
              <button class="btn btn-outline-secondary" type="button">
                <i class="bi bi-eye"></i>
              </button>
            </div>
          </div>

          <div class="d-flex justify-content-end gap-2 mt-4">
            <button type="button" class="btn btn-outline-secondary">Hủy</button>
            <button type="submit" class="btn btn-primary">
              Cập nhật mật khẩu
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>

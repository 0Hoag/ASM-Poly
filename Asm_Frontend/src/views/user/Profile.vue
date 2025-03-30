<template>
  <div class="bg-white rounded-3 shadow-sm p-4">
    <h4 class="mb-4">Thông tin cá nhân</h4>
    <hr />

    <div class="row">
      <form>
        <div class="mb-4">
          <label for="fullName" class="form-label fw-medium">Họ và tên</label>
          <input
            type="text"
            class="form-control w-100 mw-75 py-2"
            id="fullName"
            placeholder="Nguyễn Văn A"
          />
        </div>

        <div class="mb-4">
          <label class="form-label fw-medium">Giới tính</label>
          <div class="d-flex gap-4 align-items-center">
            <div class="form-check">
              <input
                class="form-check-input"
                type="radio"
                name="gender"
                id="male"
                checked
              />
              <label class="form-check-label" for="male">Nam</label>
            </div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="radio"
                name="gender"
                id="female"
              />
              <label class="form-check-label" for="female">Nữ</label>
            </div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="radio"
                name="gender"
                id="other"
              />
              <label class="form-check-label" for="other">Khác</label>
            </div>
          </div>
        </div>

        <div class="mb-4">
          <label for="email" class="form-label fw-medium">Email</label>
          <input
            type="email"
            class="form-control w-100 mw-75 py-2"
            id="email"
            placeholder="email@example.com"
          />
        </div>

        <div class="mb-4">
          <label for="phone" class="form-label fw-medium">Số điện thoại</label>
          <input
            type="tel"
            class="form-control w-100 mw-75 py-2"
            id="phone"
            placeholder="+84 xxx xxx xxx"
          />
        </div>

        <div class="d-flex justify-content-end gap-3 mt-4">
          <button type="button" class="btn btn-outline-secondary">Hủy</button>
          <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>

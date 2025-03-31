<template>
  <div
    class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse position-fixed top-0 bottom-0 pt-5 pb-2"
  >
    <div class="position-sticky pt-3">
      <h4 class="fw-bold text-primary text-center">Admin panel</h4>
      <ul class="nav flex-column">
        <li class="nav-item">
          <router-link class="nav-link" :to="{ name: 'dashboard' }">
            <i class="bi bi-speedometer2"></i> Tổng quan
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{ name: 'user-management' }">
            <i class="bi bi-people"></i> Quản lý người dùng
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{ name: 'product-management' }">
            <i class="bi bi-box"></i> Quản lý sản phẩm
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{ name: 'order-management' }">
            <i class="bi bi-cart"></i> Quản lý đơn hàng
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{ name: 'category-management' }">
            <i class="bi bi-cart"></i> Quản lý danh mục
          </router-link>
        </li>
        <li class="nav-item">
          <router-link
            class="nav-link"
            :to="{ name: 'product-type-management' }"
          >
            <i class="bi bi-cart"></i> Quản lý loại hàng
          </router-link>
        </li>

        <!-- <li class="nav-item">
          <router-link
            class="nav-link active"
            :to="{ name: 'order-status-management' }"
          >
            <i class="bi bi-check2-circle"></i> Trạng thái đơn hàng
          </router-link>
        </li> -->
        <!-- <li class="nav-item">
          <router-link class="nav-link active":to="{name: 'user-management'}">
            <i class="bi bi-plus-circle"></i>Tạo Đơn Hàng
          </router-link>
        </li> -->
      </ul>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>

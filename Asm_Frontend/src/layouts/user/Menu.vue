<template>
  <div class="bg-white rounded-3 shadow-sm border p-3">
    <div class="list-group list-group-flush">
      <router-link
        :to="{ name: 'profile' }"
        class="list-group-item list-group-item-action rounded-3"
        aria-current="true"
      >
        Hồ sơ
      </router-link>
      <router-link
        :to="{ name: 'change-password' }"
        class="list-group-item list-group-item-action rounded-3"
        >Đổi mật khẩu</router-link
      >
      <router-link
        :to="{ name: 'address' }"
        class="list-group-item list-group-item-action rounded-3"
        >Địa chỉ</router-link
      >
      <router-link
        :to="{ name: 'favorite' }"
        class="list-group-item list-group-item-action rounded-3"
        >Sản phẩm ưu thích</router-link
      >
      <router-link
        :to="{ name: 'history-order' }"
        class="list-group-item list-group-item-action rounded-3"
        >Đơn hàng</router-link
      >
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>

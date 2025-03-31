<template>
  <div class="container py-5">
    <div class="row g-4">
      <Menu class="col-md-3"></Menu>
      <!-- Hiển thị ra các trang con -->
      <div class="col-md-9">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script setup>
import Menu from "../../layouts/user/Menu.vue";
import Footer from "../../layouts/user/Footer.vue";
import Header from "../../layouts/user/Header.vue";
</script>

<style lang="scss" scoped></style>

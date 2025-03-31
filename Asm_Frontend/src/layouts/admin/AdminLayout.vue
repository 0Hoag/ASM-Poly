<template>
  <Header> </Header>

  <div class="mt-5">
    <div class="row">
      <Menu></Menu>
    </div>
    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 py-4 overflow-auto">
      <!-- Chèn nội từ  dây -->
      <router-view></router-view>
      <!-- Kết thúc chèn nội dung -->
    </main>
  </div>
</template>

<script setup>
import Header from "./Header.vue";
import Menu from "./Menu.vue";
</script>

<style lang="scss" scoped></style>

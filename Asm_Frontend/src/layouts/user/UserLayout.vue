<template>
  <div class="d-flex flex-column min-vh-100">
    <Header></Header>
    <main class="flex-grow-1">
      <router-view />
    </main>
    <Footer></Footer>
  </div>
</template>

<script setup>
import Footer from "./Footer.vue";
import Header from "./Header.vue";
</script>

<style lang="scss" scoped></style>

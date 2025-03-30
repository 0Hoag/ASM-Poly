<template>
  <header class="fixed-top" th:fragment="header-admin">
    <nav class="navbar navbar-expand-lg navbar-light bg-dark text-white">
      <div class="container-fluid">
        <div class="left d-flex">
          <span>Admin panel</span>
          <a href="/admin" class="mx-3 nav-link text-white"
            ><i class="bi bi-speedometer"></i> Dashboard</a
          >
          <a href="/" class="mx-3 nav-link text-white"
            ><i class="bi bi-house-door-fill"></i> Trang chủ</a
          >
        </div>
        <div class="user-info">
          Xin chào, Admin <a href="/account/logout">Đăng xuất</a>
        </div>
      </div>
    </nav>
  </header>
</template>

<script setup></script>

<style lang="scss" scoped></style>

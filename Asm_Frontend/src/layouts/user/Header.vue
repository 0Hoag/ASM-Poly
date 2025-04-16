<template>
  <header class="bg-light shadow-sm">
    <div class="container py-3 d-flex align-items-center justify-content-between">
      <router-link to="/" class="text-dark fw-bold fs-4 text-decoration-none">
        <img src="../../assets/img/logotext.PNG" alt="Logo" width="150px" />
      </router-link>

      <form class="d-flex search-box w-50" action="/search">
        <input
          class="form-control me-2"
          type="search"
          placeholder="Tìm kiếm điện thoại..."
          aria-label="Search"
          name="keyword"
        />
        <button class="btn btn-warning" type="submit">
          <i class="bi bi-search"></i>
        </button>
      </form>

      <div class="header-icons d-flex align-items-center">
        <router-link :to="{ name: 'cart' }" class="position-relative">
          <i class="bi bi-cart2"></i>
          <span v-if="cartQuantity > 0" class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
            {{ cartQuantity }}
          </span>
        </router-link>

        <!-- Hiển thị Đăng nhập nếu chưa đăng nhập -->
        <template v-if="!user">
          <router-link :to="{ name: 'login' }" class="d-flex align-items-center">
            <i class="bi bi-person"></i> Đăng nhập
          </router-link>
        </template>

        <!-- Hiển thị Hồ sơ cá nhân nếu đã đăng nhập -->
        <template v-if="user">
          <router-link :to="{ name: 'account' }" class="d-flex align-items-center">
            <i class="bi bi-person-circle"></i>Xin chào! <span style="color: red;"> {{ user.fullName }}</span>
          </router-link>
          <!-- Nút đăng xuất -->
          <button @click="handleLogout" class="btn btn-danger ms-3">
            Đăng xuất
          </button>
        </template>
      </div>
    </div>

    <!-- Menu điều hướng -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-warning">
      <div class="container">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link :to="{ name: 'category' }" class="nav-link">ĐIỆN THOẠI</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{ name: 'category' }" class="nav-link">PHỤ KIỆN</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

// Lấy thông tin người dùng từ localStorage
const user = ref(null);
const router = useRouter();
const cartQuantity = ref(0);

// Hàm xử lý đăng xuất
const handleLogout = () => {
  // Xóa thông tin người dùng và token khỏi localStorage
  localStorage.removeItem('userId');
  localStorage.removeItem('authToken');
  localStorage.removeItem('cartId');
  localStorage.removeItem('cartQuantity');
  localStorage.removeItem('user');
  localStorage.removeItem('role');
  
  // Cập nhật lại trạng thái người dùng
  user.value = null;

  // Điều hướng về trang đăng nhập
  router.push('/login');
};

// Kiểm tra trạng thái đăng nhập khi component được tải
onMounted(() => {
  const storedUser = JSON.parse(localStorage.getItem('user'));
  if (storedUser) {
    user.value = storedUser;  // Cập nhật trạng thái người dùng nếu đã đăng nhập
  }

  // Kiểm tra giỏ hàng và số lượng sản phẩm trong giỏ hàng
  const cart = JSON.parse(localStorage.getItem('cart')) || [];
  cartQuantity.value = cart.reduce((sum, item) => sum + item.quantity, 0);
});
</script>
<style scoped>
body {
  font-family: "Poppins", sans-serif;
}
.navbar {
  padding: 12px 0;
}
.navbar-nav .nav-link {
  font-size: 16px;
  font-weight: 600;
  color: #333 !important;
  padding: 10px 15px;
  transition: all 0.3s ease-in-out;
}
.navbar-nav .nav-link:hover {
  color: #fff !important;
  background: rgba(0, 0, 0, 0.2);
  border-radius: 5px;
}
.header-icons a {
  font-size: 18px;
  margin-left: 15px;
  position: relative;
  color: #333;
  text-decoration: none;
  transition: color 0.3s;
}
.header-icons a:hover {
  color: #ff9900;
}
.header-icons .badge {
  font-size: 12px;
  top: -5px;
  right: -8px;
}
.search-box input {
  border-radius: 30px;
}
.search-box button {
  border-radius: 30px;
  padding: 5px 15px;
}
</style>

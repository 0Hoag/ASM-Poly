import { createRouter, createWebHistory } from "vue-router";

import userRouter from "./userRouter";
import adminRouter from "./adminRouter";
import authRouter from "./authRouter";

const routes = [...userRouter, ...adminRouter, ...authRouter];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem("authToken");
  const isAuthenticated = !!token;

  const role = localStorage.getItem("role");
  console.log(role);

  if (to.meta.requiresAuth && !isAuthenticated) {
    if (to.path !== "/admin/login") {
      // Chỉ chuyển hướng nếu không phải đang ở trang login
      return next("/admin/login");
    }
  }

  // Nếu URL bắt đầu bằng /admin và không có quyền admin (role = false)
  if (to.path.startsWith("/admin") && role !== "true") {
    // Kiểm tra xem role có phải là true không
    if (to.path !== "/admin/login") {
      // Không chuyển hướng nếu đã ở trang login
      return next("/admin/login");
    }
  }
  next();
});
export default router;

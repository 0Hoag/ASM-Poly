import UserLayout from "../layouts/user/UserLayout.vue";
import Login from "../views/auth/Login.vue";
import LoginAdmin from "../views/auth/LoginAdmin.vue";
import Register from "../views/auth/Register.vue";
import RePassword from "../views/auth/RePassword.vue";
const authRouter = [
  {
    path: "/user",
    name: "user",
    component: UserLayout,
    children: [
      {
        path: "/login",
        name: "login",
        component: Login,
      },
      {
        path: "/register",
        name: "register",
        component: Register,
      },
    ],
  },
  {
    path: "/re-password",
    name: "re-password",
    component: RePassword,
  },
  {
    path: "/admin/login",
    name: "admin-login",
    component: LoginAdmin,
  },
];

export default authRouter;

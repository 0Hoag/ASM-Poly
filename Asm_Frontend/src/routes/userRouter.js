const userRouter = [
  {
    path: "/",
    component: () => import("../layouts/user/UserLayout.vue"),
    children: [
      {
        path: "",
        component: () => import("../views/user/Home.vue"),
      },
      {
        path: "search",
        name: "search",
        component: () => import("../views/user/Search.vue"),
      },
      {
        path: "category",
        name: "category",
        component: () => import("../views/user/Category.vue"),
      },
      {
        path: "category/product/:id",
        name: "product",
        component: () => import("../views/user/ProductDetail.vue"),
      },
      {
        path: "cart",
        name: "cart",
        component: () => import("../views/user/Cart.vue"),
      },
      {
        path: "cart/confirm-order",
        name: "confirm-order",
        component: () => import("../views/user/ConfirmOrder.vue"),
      },
      {
        path: "account",
        name: "account",
        component: () => import("../views/user/Account.vue"),
        redirect: "/account/profile",
        children: [
          {
            path: "profile",
            name: "profile",
            component: () => import("../views/user/Profile.vue"),
          },
          {
            path: "address",
            name: "address",
            component: () => import("../views/user/Address.vue"),
          },
          {
            path: "change-password",
            name: "change-password",
            component: () => import("../views/user/ChangePassword.vue"),
          },
          {
            path: "favorite",
            name: "favorite",
            component: () => import("../views/user/Favorite.vue"),
          },
          {
            path: "history-order",
            name: "history-order",
            component: () => import("../views/user/HistoryOrder.vue"),
          },
        ],
      },
    ],
  },
];

export default userRouter;

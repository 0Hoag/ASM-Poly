import { createRouter, createWebHistory } from "vue-router";

import userRouter from "./userRouter";
import adminRouter from "./adminRouter";
import authRouter from "./authRouter";

const routes = [...userRouter, ...adminRouter, ...authRouter];

const router = createRouter({
  history: createWebHistory(),
  routes,
});
export default router;

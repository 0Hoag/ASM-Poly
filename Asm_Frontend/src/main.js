import { createApp } from "vue";
import "./style.css";
import App from "./App.vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";
import "bootstrap-icons/font/bootstrap-icons.css";
import axios from "axios";
import router from "./routes";
const app = createApp(App);
// 👉 Cấu hình baseURL (nếu cần)
axios.defaults.baseURL = "http://localhost:8080";

// 👉 Lấy token từ localStorage
const token = localStorage.getItem("authToken");

// 👉 Gắn token vào header nếu có
if (token) {
  axios.defaults.headers.common["Authorization"] = token;
}
app.config.globalProperties.$axios = axios;
app.use(router);
app.mount("#app");
export function getUserId() {
  const stored = localStorage.getItem("user");
  if (!stored) return null;
  try {
    const user = JSON.parse(stored);
    return user?.id || null;
  } catch (e) {
    return null;
  }
}

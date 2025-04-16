<template>
  <div class="container py-4">
    <h2 class="mb-4">Lịch sử đơn hàng</h2>

    <!-- Tabs lọc trạng thái -->
    <div class="btn-group mb-3">
      <button class="btn" :class="{ 'btn-primary': activeTab === 'all', 'btn-outline-primary': activeTab !== 'all' }" @click="setTab('all')">Tất cả</button>
      <button class="btn" :class="{ 'btn-primary': activeTab === 'pending', 'btn-outline-primary': activeTab !== 'pending' }" @click="setTab('pending')">Chờ xác nhận</button>
      <button class="btn" :class="{ 'btn-primary': activeTab === 'shipping', 'btn-outline-primary': activeTab !== 'shipping' }" @click="setTab('shipping')">Đang giao</button>
      <button class="btn" :class="{ 'btn-primary': activeTab === 'delivered', 'btn-outline-primary': activeTab !== 'delivered' }" @click="setTab('delivered')">Đã giao</button>
      <button class="btn" :class="{ 'btn-primary': activeTab === 'cancelled', 'btn-outline-primary': activeTab !== 'cancelled' }" @click="setTab('cancelled')">Đã hủy</button>
    </div>

    <!-- Tìm kiếm -->
    <div class="input-group mb-4">
      <input type="text" class="form-control" placeholder="Tìm theo mã đơn hoặc tên sản phẩm..." v-model="searchQuery" @input="searchOrders" />
    </div>

    <!-- Danh sách đơn hàng -->
    <div v-if="filteredOrders.length === 0" class="text-center text-muted">Không có đơn hàng nào.</div>
    <div v-else>
      <div v-for="order in filteredOrders" :key="order.id" class="border rounded p-3 mb-3">
        <div><strong>Mã đơn:</strong> #{{ order.id }}</div>
        <div><strong>Ngày đặt:</strong> {{ new Date(order.createdAt).toLocaleString() }}</div>
        <div><strong>Tổng tiền:</strong> {{ formatCurrency(order.totalAmount) }}</div>
        <div>
          <strong>Sản phẩm:</strong>
          <ul>
            <li v-for="item in order.orderDetails" :key="item">{{ item }}</li>
          </ul>
        </div>
        <div><strong>Trạng thái:</strong> {{ order.orderStatus }}</div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      orders: [],
      activeTab: "all",
      searchQuery: "",
    };
  },
  computed: {
    filteredOrders() {
      return this.orders.filter((order) => {
        const matchTab = this.activeTab === "all" || this.normalize(order.orderStatus) === this.activeTab;
        const matchSearch = order.id.toString().includes(this.searchQuery.toLowerCase()) || order.orderDetails.join(" ").toLowerCase().includes(this.searchQuery.toLowerCase());
        return matchTab && matchSearch;
      });
    },
  },
  methods: {
    setTab(tab) {
      this.activeTab = tab;
    },
    searchOrders() {
      // Reactive via v-model
    },
    normalize(status) {
      const map = {
        "Ðang chờ xử lí": "pending",
        "Đang chờ xử lí": "pending",
        "Đang giao": "shipping",
        "Đã giao": "delivered",
        "Đã hủy": "cancelled",
      };
      return map[status] || "other";
    },
    formatCurrency(value) {
      return Number(value).toLocaleString("vi-VN") + " đ";
    },
  },
  mounted() {
    const user = JSON.parse(localStorage.getItem("user")); // hoặc thay bằng Vuex nếu bạn dùng Vuex
    if (!user || !user.id) {
      console.error("Không tìm thấy thông tin người dùng");
      return;
    }

    axios.get("/asm/api/v1/orders/List").then((res) => {
      this.orders = res.data.result.filter((order) => order.user === user.id);
    });
  },
};
</script>

<style scoped>
.btn-group .btn {
  margin-right: 8px;
}
</style>

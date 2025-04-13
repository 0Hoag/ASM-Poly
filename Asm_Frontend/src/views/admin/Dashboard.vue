<template>
  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">Tổng Quan Hệ Thống</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
      <div class="btn-group me-2">
        <button type="button" class="btn btn-sm btn-outline-secondary"><i class="bi bi-file-earmark-excel"></i> Xuất báo cáo</button>
        <button type="button" class="btn btn-sm btn-outline-secondary"><i class="bi bi-printer"></i> In báo cáo</button>
      </div>
      <button type="button" class="btn btn-sm btn-outline-primary dropdown-toggle"><i class="bi bi-calendar3"></i> Tuần này</button>
    </div>
  </div>

  <!-- Dashboard Stats -->
  <div class="row">
    <div class="col-md-6 col-lg-3 mb-4">
      <div class="card card-dashboard border-0 shadow-sm">
        <div class="card-body">
          <div class="d-flex align-items-center">
            <div class="stat-icon bg-primary bg-opacity-10 text-primary me-3">
              <i class="bi bi-people-fill fs-4"></i>
            </div>
            <div>
              <h6 class="card-title mb-0">Tổng người dùng</h6>
              <h2 class="mt-2 mb-0">{{ listCustomer.length }}</h2>
              <!-- <p class="text-success mb-0">
                <i class="bi bi-arrow-up"></i> 12.5%
                <small class="text-muted">so với tháng trước</small>
              </p> -->
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-6 col-lg-3 mb-4">
      <div class="card card-dashboard border-0 shadow-sm">
        <div class="card-body">
          <div class="d-flex align-items-center">
            <div class="stat-icon bg-success bg-opacity-10 text-success me-3">
              <i class="bi bi-cart-fill fs-4"></i>
            </div>
            <div>
              <h6 class="card-title mb-0">Đơn hàng mới</h6>
              <h2 class="mt-2 mb-0">{{ listOrder.length }}</h2>
              <!-- <p class="text-success mb-0">
                <i class="bi bi-arrow-up"></i> 8.2%
                <small class="text-muted">so với tháng trước</small>
              </p> -->
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-6 col-lg-3 mb-4">
      <div class="card card-dashboard border-0 shadow-sm">
        <div class="card-body">
          <div class="d-flex align-items-center">
            <div class="stat-icon bg-warning bg-opacity-10 text-warning me-3">
              <i class="bi bi-currency-dollar fs-4"></i>
            </div>
            <div>
              <h6 class="card-title mb-0">Doanh thu</h6>
              <h2 class="mt-2 mb-0">{{ (totalRevenue / 1_000_000).toFixed(1) }} triệu</h2>
              <!-- <p class="text-success mb-0">
                <i class="bi bi-arrow-up"></i> 5.7%
                <small class="text-muted">so với tháng trước</small>
              </p> -->
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-6 col-lg-3 mb-4">
      <div class="card card-dashboard border-0 shadow-sm">
        <div class="card-body">
          <div class="d-flex align-items-center">
            <div class="stat-icon bg-info bg-opacity-10 text-info me-3">
              <i class="bi bi-box-seam-fill fs-4"></i>
            </div>
            <div>
              <h6 class="card-title mb-0">Sản phẩm</h6>
              <h2 class="mt-2 mb-0">{{ listProduct.length }}</h2>
              <!-- <p class="text-success mb-0">
                <i class="bi bi-arrow-up"></i> 3.2%
                <small class="text-muted">so với tháng trước</small>
              </p> -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <!-- Recent Orders -->
    <div class="col-md-8 mb-4">
      <div class="card border-0 shadow-sm h-100">
        <div class="card-header bg-white">
          <div class="d-flex justify-content-between align-items-center">
            <h5 class="mb-0">Đơn hàng gần đây</h5>
            <router-link :to="{ name: 'order-management' }" class="btn btn-sm btn-primary">Xem tất cả</router-link>
          </div>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-hover">
              <thead>
                <tr>
                  <th>Mã đơn</th>
                  <th>Khách hàng</th>
                  <th>Trạng thái</th>
                  <th>Tổng tiền</th>
                  <th>Ngày đặt</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="order in listOrder" :key="order.id">
                  <td>{{ order.id }}</td>
                  <td>{{ order.fullName }}</td>
                  <td>
                    <span class="badge" :class="getStatusClass(order.orderStatus)">{{ order.orderStatus }}</span>
                  </td>
                  <td>{{ order.totalAmount.toLocaleString("vi", { style: "currency", currency: "VND" }) }}</td>
                  <td>{{ new Date(order.createdAt).toLocaleDateString("vi-VN") }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- Top Products -->
    <div class="col-md-4 mb-4">
      <div class="card border-0 shadow-sm h-100">
        <div class="card-header bg-white">
          <div class="d-flex justify-content-between align-items-center">
            <h5 class="mb-0">Sản phẩm bán chạy</h5>
            <router-link :to="{ name: 'product-management' }" class="btn btn-sm btn-primary">Xem tất cả</router-link>
          </div>
        </div>
        <div class="card-body">
          <ul class="list-group list-group-flush">
            <li class="list-group-item px-0 d-flex justify-content-between align-items-center" v-for="product in listProduct.filter((product) => product.soldQuantity > 0)" :key="product.id">
              <div class="d-flex align-items-center">
                <div class="bg-light rounded" style="width: 40px; height: 40px">
                  <img :src="product.images[0].url" alt="img" width="40" height="40" />
                </div>
                <div class="ms-3">
                  <h6 class="mb-0">{{ product.productName }}</h6>
                  <small class="text-muted">{{ product.category }}</small>
                </div>
              </div>
              <span class="badge bg-primary rounded-pill">{{ product.soldQuantity }}</span>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { computed, onBeforeMount, ref } from "vue";

// init
const listOrder = ref([]);
const listCustomer = ref([]);
const listProduct = ref([]);
// methods
const getAllCustomer = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/user/List");
    listCustomer.value = Array.from(resp.data.result).filter((user) => user.role === false);
  } catch (error) {
    console.log(error.message);
  }
};
const getAllOrder = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/orders/List");
    listOrder.value = resp.data.result
      .reverse()
      .slice(0, 5)
      .map((item) => {
        const user = listCustomer.value.find((customer) => customer.id == item.user);

        return {
          ...item,
          fullName: user ? user.fullName : "",
        };
      });
  } catch (error) {
    console.log(error.message);
  }
};

const getAllProducts = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/product/List");
    listProduct.value = resp.data.result;
  } catch (error) {
    console.log(error.message);
  }
};

const getStatusClass = (status) => {
  switch (status) {
    case "Chờ xác nhận":
      return "bg-secondary";
    case "Đang giao":
      return "bg-warning";
    case "Đã giao":
      return "bg-success";
    case "Đã hủy":
      return "bg-danger";
    case "Đã xác nhận":
      return "bg-info";
    default:
      return "bg-light";
  }
};
// computed
const totalRevenue = computed(() => {
  return listOrder.value.reduce((total, order) => total + order.totalAmount, 0);
});
// mouted
onBeforeMount(async () => {
  await getAllCustomer();
  await getAllOrder();
  await getAllProducts();
});
</script>

<style lang="scss" scoped></style>

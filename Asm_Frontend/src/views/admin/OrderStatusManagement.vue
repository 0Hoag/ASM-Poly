<template>
  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">Trạng Thái Đơn Hàng</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
      <div class="btn-group me-2">
        <button type="button" class="btn btn-sm btn-outline-secondary"><i class="bi bi-file-earmark-excel"></i> Xuất báo cáo</button>
        <button type="button" class="btn btn-sm btn-outline-secondary"><i class="bi bi-printer"></i> In báo cáo</button>
      </div>
      <button type="button" class="btn btn-sm btn-outline-primary dropdown-toggle"><i class="bi bi-calendar3"></i> Tuần này</button>
    </div>
  </div>

  <!-- Status Overview Cards -->
  <div class="row mb-4">
    <div class="col-md-4 col-lg-2 mb-3">
      <div class="card status-card h-100 border-0 shadow-sm">
        <div class="card-body text-center">
          <div class="status-icon bg-secondary bg-opacity-10 text-secondary mx-auto mb-2">
            <i class="bi bi-hourglass-split"></i>
          </div>
          <h5 class="card-title">Chờ xác nhận</h5>
          <h3 class="mt-1 mb-0">12</h3>
          <p class="text-muted small">đơn hàng</p>
        </div>
      </div>
    </div>
    <div class="col-md-4 col-lg-2 mb-3">
      <div class="card status-card h-100 border-0 shadow-sm">
        <div class="card-body text-center">
          <div class="status-icon bg-info bg-opacity-10 text-info mx-auto mb-2">
            <i class="bi bi-check-circle"></i>
          </div>
          <h5 class="card-title">Đã xác nhận</h5>
          <h3 class="mt-1 mb-0">18</h3>
          <p class="text-muted small">đơn hàng</p>
        </div>
      </div>
    </div>
    <div class="col-md-4 col-lg-2 mb-3">
      <div class="card status-card h-100 border-0 shadow-sm">
        <div class="card-body text-center">
          <div class="status-icon bg-primary bg-opacity-10 text-primary mx-auto mb-2">
            <i class="bi bi-box-seam"></i>
          </div>
          <h5 class="card-title">Đang chuẩn bị</h5>
          <h3 class="mt-1 mb-0">24</h3>
          <p class="text-muted small">đơn hàng</p>
        </div>
      </div>
    </div>
    <div class="col-md-4 col-lg-2 mb-3">
      <div class="card status-card h-100 border-0 shadow-sm">
        <div class="card-body text-center">
          <div class="status-icon bg-warning bg-opacity-10 text-warning mx-auto mb-2">
            <i class="bi bi-truck"></i>
          </div>
          <h5 class="card-title">Đang giao</h5>
          <h3 class="mt-1 mb-0">36</h3>
          <p class="text-muted small">đơn hàng</p>
        </div>
      </div>
    </div>
    <div class="col-md-4 col-lg-2 mb-3">
      <div class="card status-card h-100 border-0 shadow-sm">
        <div class="card-body text-center">
          <div class="status-icon bg-success bg-opacity-10 text-success mx-auto mb-2">
            <i class="bi bi-check2-all"></i>
          </div>
          <h5 class="card-title">Hoàn thành</h5>
          <h3 class="mt-1 mb-0">128</h3>
          <p class="text-muted small">đơn hàng</p>
        </div>
      </div>
    </div>
    <div class="col-md-4 col-lg-2 mb-3">
      <div class="card status-card h-100 border-0 shadow-sm">
        <div class="card-body text-center">
          <div class="status-icon bg-danger bg-opacity-10 text-danger mx-auto mb-2">
            <i class="bi bi-x-circle"></i>
          </div>
          <h5 class="card-title">Đã hủy</h5>
          <h3 class="mt-1 mb-0">8</h3>
          <p class="text-muted small">đơn hàng</p>
        </div>
      </div>
    </div>
  </div>

  <!-- Order Status Management -->
  <div class="row">
    <div class="col-md-6 mb-4">
      <div class="card-header bg-white">
        <h5 class="mb-0">Trạng thái đơn hàng</h5>
      </div>
      <div class="card border-0 shadow-sm h-100">
        <div class="card-header bg-white"></div>
        <div class="card-body">
          <table class="table table-hover">
            <thead>
              <tr>
                <th>Mã đơn</th>
                <th>Khách hàng</th>
                <th>Trạng thái</th>
                <th>Cập nhật</th>
                <th>Thao tác</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="order in listOrder" :key="order.id">
                <td>{{ order.id }}</td>
                <td>{{ order.fullName }}</td>
                <td>
                  <div class="dropdown">
                    <button class="btn btn-sm dropdown-toggle badge bg-secondary" type="button" data-bs-toggle="dropdown">{{ order.orderStatus }}</button>
                    <ul class="dropdown-menu">
                      <li>
                        <button class="dropdown-item" onclick="changeStatus(this, 'Chờ xác nhận', 'bg-secondary')">Chờ xác nhận</button>
                      </li>
                      <li>
                        <button class="dropdown-item" onclick="changeStatus(this, 'Đã xác nhận', 'bg-info')">Đã xác nhận</button>
                      </li>
                      <li>
                        <button class="dropdown-item" onclick="changeStatus(this, 'Đang chuẩn bị', 'bg-primary')">Đang chuẩn bị</button>
                      </li>
                      <li>
                        <button class="dropdown-item" onclick="changeStatus(this, 'Đang giao', 'bg-warning')">Đang giao</button>
                      </li>
                      <li>
                        <button class="dropdown-item" onclick="changeStatus(this, 'Hoàn thành', 'bg-success')">Hoàn thành</button>
                      </li>
                      <li>
                        <button class="dropdown-item" onclick="changeStatus(this, 'Đã hủy', 'bg-danger')">Đã hủy</button>
                      </li>
                    </ul>
                  </div>
                </td>
                <td>30/03/2023</td>
                <td>
                  <div class="dropdown">
                    <button class="btn btn-sm btn-outline-primary dropdown-toggle" type="button" data-bs-toggle="dropdown">Cập nhật</button>
                    <ul class="dropdown-menu">
                      <li>
                        <a class="dropdown-item" href="#"><i class="bi bi-check-circle"></i> Xác nhận</a>
                      </li>
                      <li>
                        <a class="dropdown-item" href="#"><i class="bi bi-x-circle"></i> Hủy</a>
                      </li>
                    </ul>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <div class="col-md-6 mb-4">
      <div class="card border-0 shadow-sm h-100">
        <div class="card-header bg-white">
          <h5 class="mb-0">Lịch sử cập nhật trạng thái</h5>
        </div>
        <div class="card-body">
          <div class="timeline">
            <div class="timeline-item">
              <div class="timeline-badge bg-success">
                <i class="bi bi-check-circle"></i>
              </div>
              <div class="timeline-content">
                <div class="d-flex justify-content-between">
                  <h6 class="mb-1">Đơn hàng #ORD-0025 đã hoàn thành</h6>
                  <small class="text-muted">1 giờ trước</small>
                </div>
                <p class="mb-0">Cập nhật bởi: Admin</p>
              </div>
            </div>
            <div class="timeline-item">
              <div class="timeline-badge bg-warning">
                <i class="bi bi-truck"></i>
              </div>
              <div class="timeline-content">
                <div class="d-flex justify-content-between">
                  <h6 class="mb-1">Đơn hàng #ORD-0027 đang giao</h6>
                  <small class="text-muted">2 giờ trước</small>
                </div>
                <p class="mb-0">Cập nhật bởi: Admin</p>
              </div>
            </div>
            <div class="timeline-item">
              <div class="timeline-badge bg-primary">
                <i class="bi bi-box-seam"></i>
              </div>
              <div class="timeline-content">
                <div class="d-flex justify-content-between">
                  <h6 class="mb-1">Đơn hàng #ORD-0028 đang chuẩn bị</h6>
                  <small class="text-muted">3 giờ trước</small>
                </div>
                <p class="mb-0">Cập nhật bởi: Admin</p>
              </div>
            </div>
            <div class="timeline-item">
              <div class="timeline-badge bg-info">
                <i class="bi bi-check-circle"></i>
              </div>
              <div class="timeline-content">
                <div class="d-flex justify-content-between">
                  <h6 class="mb-1">Đơn hàng #ORD-0029 đã xác nhận</h6>
                  <small class="text-muted">4 giờ trước</small>
                </div>
                <p class="mb-0">Cập nhật bởi: Admin</p>
              </div>
            </div>
            <div class="timeline-item">
              <div class="timeline-badge bg-danger">
                <i class="bi bi-x-circle"></i>
              </div>
              <div class="timeline-content">
                <div class="d-flex justify-content-between">
                  <h6 class="mb-1">Đơn hàng #ORD-0022 đã hủy</h6>
                  <small class="text-muted">1 ngày trước</small>
                </div>
                <p class="mb-0">Cập nhật bởi: Admin</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { onBeforeMount, ref } from "vue";

// khai báo biến
// methods
const listOrderStatus = ref([]);
const listOrder = ref([]);
const listCustomer = ref([]);
// get
const getAllCustomer = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/user/List");
    listCustomer.value = Array.from(resp.data.result).filter((user) => user.role === false);
  } catch (error) {
    console.log(error.message);
  }
};
const getAllOrderStatus = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/order-status/List");
    listOrderStatus.value = resp.data.result;
    console.log("order status", listOrderStatus.value);
  } catch (error) {
    console.log(error.message);
  }
};
const getAllOrder = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/orders/List");
    listOrder.value = resp.data.result.map((item) => {
      const user = listCustomer.value.find((customer) => customer.id == item.user);

      return {
        ...item,
        fullName: user ? user.fullName : "",
      };
    });
    totalPage.value = resp.data.result.totalPages;
  } catch (error) {
    console.log(error.message);
  }
};
// create

// update

// delete

// pagination

// search

// computed
// watch
// mounted
onBeforeMount(async () => {
  await getAllOrderStatus();
  await getAllCustomer();
  await getAllOrder();
});
</script>
<style scoped>
.status-icon {
  width: 32px;
  height: 32px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  margin: 0 auto;
}
.timeline-item {
  position: relative;
  padding-left: 45px;
  margin-bottom: 20px;
}
.timeline-item:before {
  content: "";
  position: absolute;
  left: 22px;
  top: 0;
  height: 100%;
  width: 2px;
  background-color: #dee2e6;
}
.timeline-item:last-child:before {
  height: 50%;
}
.timeline-item:first-child:before {
  top: 50%;
  height: 50%;
}
.timeline-badge {
  position: absolute;
  left: 10px;
  top: 0;
  width: 25px;
  height: 25px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  z-index: 1;
}
.timeline-content {
  padding: 15px;
  border-radius: 5px;
  background-color: #f8f9fa;
  border-left: 3px solid #0d6efd;
}

.status-card h5.card-title {
  font-size: 0.9rem;
  margin-bottom: 0.25rem;
}

.status-card h3 {
  font-size: 1.5rem;
  margin: 0.25rem 0;
}

.status-card p.small {
  font-size: 0.7rem;
  margin-bottom: 0;
}

.status-card .status-icon {
  margin-bottom: 0.5rem !important;
}

.status-card .status-icon i {
  font-size: 1rem;
}
</style>

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
  <div class="row mb-4 d-flex flex-wrap justify-content-center">
    <div class="col-md-4 col-lg-2 mb-3" v-for="status in listOrderStatus" :key="status.id">
      <div class="card status-card h-100 border-0 shadow-sm">
        <div class="card-body text-center">
          <div class="status-icon bg-opacity-10 mx-auto mb-2" :class="[getStatusClass(status.statusName), getStatusTextClass(status.statusName)]">
            <i class="bi" :class="getStatusIcon(status.statusName)"></i>
          </div>
          <h5 class="card-title">{{ status.statusName }}</h5>
          <h3 class="mt-1 mb-0">{{ status.orders.length }}</h3>
          <p class="text-muted small">đơn hàng</p>
        </div>
      </div>
    </div>
  </div>

  <!-- Order Status Management -->
  <div class="row">
    <div class="col-md-12 mb-4">
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
                <th>Thời gian đặt</th>
                <th>Thao tác</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="order in listOrder" :key="order.id">
                <td>{{ order.id }}</td>
                <td>{{ order.fullName }}</td>
                <td>
                  <div class="dropdown">
                    <button class="btn btn-sm dropdown-toggle badge bg-secondary" :class="getStatusClass(order.tempStatus)" type="button" data-bs-toggle="dropdown">{{ order.tempStatus }}</button>
                    <ul class="dropdown-menu">
                      <li>
                        <button class="dropdown-item" v-for="status in listOrderStatus" :key="status.id" @click="order.tempStatus = status.statusName">
                          {{ status.statusName }}
                        </button>
                      </li>
                    </ul>
                  </div>
                </td>
                <td>{{ new Date(order.createdAt).toLocaleString("vi-VN") }}</td>
                <td>
                  <div class="dropdown" data-bs-auto-close="outside">
                    <button class="btn btn-sm btn-outline-primary dropdown-toggle" type="button" data-bs-toggle="dropdown">Cập nhật</button>
                    <ul class="dropdown-menu">
                      <li>
                        <button class="dropdown-item" @click="updateOrderStatus(order)"><i class="bi bi-check-circle"></i> Xác nhận</button>
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
    <!-- pagination -->
    <div class="d-flex justify-content-between align-items-center mt-3">
      <div>
        <span>Hiển thị 1-5 của 25 mục</span>
        <select v-model="limit" @change="changePerPage" class="form-select form-select-sm d-inline-block ms-2" style="width: auto">
          <option :value="val" v-for="val in limits" :key="val">{{ val }}</option>
        </select>
      </div>
      <nav aria-label="Page navigation">
        <ul class="pagination">
          <li class="page-item">
            <button @click="currentPage = 1" class="page-link">
              <span aria-hidden="true">&laquo;</span>
            </button>
          </li>
          <li class="page-item">
            <button @click="prevPage" :disabled="currentPage === 1" class="page-link">
              <span aria-hidden="true">&#8826;</span>
            </button>
          </li>
          <li class="page-item active">
            <a class="page-link">{{ currentPage }} / {{ totalPage }}</a>
          </li>
          <li class="page-item">
            <button @click="nextPage" :disabled="currentPage === totalPage" class="page-link">&#8827;</button>
          </li>
          <li class="page-item">
            <button @click="currentPage = totalPage" class="page-link">
              <span aria-hidden="true">&raquo;</span>
            </button>
          </li>
        </ul>
      </nav>
    </div>
    <!-- <div class="col-md-6 mb-4">
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
    </div> -->
  </div>
</template>

<script setup>
import axios from "axios";
import { computed, onBeforeMount, ref, watch } from "vue";

// khai báo biến
// methods
const listOrderStatus = ref([]);
const listOrder = ref([]);
const listCustomer = ref([]);
const limits = ref([5, 10, 15, 25]);
const limit = ref(5);
const currentPage = ref(1);
const totalPage = ref(null);
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
    const resp = await axios.get("http://localhost:8080/asm/api/v1/orders/Get", {
      params: {
        size: limit.value,
        page: currentPage.value,
      },
    });
    listOrder.value = resp.data.result.data.map((item) => {
      const user = listCustomer.value.find((customer) => customer.id == item.user);

      return {
        ...item,
        fullName: user ? user.fullName : "",
        tempStatus: item.orderStatus,
      };
    });
    totalPage.value = resp.data.result.totalPages;
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
const getStatusIcon = (statusName) => {
  switch (statusName) {
    case "Chờ xác nhận":
      return "bi-hourglass-split";
    case "Đã xác nhận":
      return "bi-check-circle";
    case "Đang giao":
      return "bi-truck";
    case "Đã giao":
      return "bi-check2-all";
    case "Đã hủy":
      return "bi-x-circle";
    default:
      return "bi-question-circle";
  }
};

const getStatusTextClass = (statusName) => {
  switch (statusName) {
    case "Chờ xác nhận":
      return "text-secondary";
    case "Đã xác nhận":
      return "text-info";
    case "Đang chuẩn bị":
      return "text-primary";
    case "Đang giao":
      return "text-warning";
    case "Đã giao":
    case "Hoàn thành":
      return "text-success";
    case "Đã hủy":
      return "text-danger";
    default:
      return "text-muted";
  }
};

// create

// update
const updateOrderStatus = async (order) => {
  try {
    const orderStatus = listOrderStatus.value.find((item) => item.statusName === order.tempStatus);
    if (!orderStatus) throw new Error("Order status not found");
    const updateOrder = {
      totalAmount: order.totalAmount,
      address: order.address,
      orderStatus: orderStatus.id,
      user: order.user,
    };
    console.log("updateOrder", updateOrder);
    const resp = await axios.put("http://localhost:8080/asm/api/v1/orders/" + order.id, updateOrder);
    if (resp.data.result) {
      alert("Cap nhat thanh cong!");
      await getAllOrder();
      await getAllOrderStatus();
    }
  } catch (error) {
    console.log(error.message);
  }
};
// delete

// pagination
const prevPage = () => {
  if (currentPage.value > 1) {
    currentPage.value--;
  }
};
const nextPage = () => {
  if (currentPage.value < totalPage.value) {
    currentPage.value++;
    console.log(currentPage.value);
  }
};

const changePerPage = () => {
  const maxPage = totalPage.value; // Tính số trang hợp lệ mới
  if (currentPage.value > maxPage) {
    currentPage.value = maxPage;
  }
};
// search

// computed
const orderStatisticByStatus = computed(() => {
  const status = {};
});
// watch
watch([currentPage, limit], () => {
  getAllOrder();
});
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

<template>
  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">Quản Lý Đơn Hàng</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
      <div class="btn-group me-2">
        <button type="button" class="btn btn-sm btn-outline-secondary"><i class="bi bi-file-earmark-excel"></i> Xuất Excel</button>
        <button type="button" class="btn btn-sm btn-outline-secondary"><i class="bi bi-file-earmark-pdf"></i> Xuất PDF</button>
      </div>
    </div>
  </div>

  <div class="card mb-4">
    <div class="card-header bg-primary text-white"><i class="bi bi-cart me-1"></i> Danh sách đơn hàng</div>
    <div class="card-body">
      <div class="row mb-3">
        <div class="col-md-6 d-flex align-items-center">
          <router-link :to="{ name: 'create-order' }" class="btn btn-success me-2"> <i class="bi bi-plus-circle"></i> Tạo đơn hàng </router-link>
          <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="bulkActionDropdown" data-bs-toggle="dropdown" aria-expanded="false">Thao tác hàng loạt</button>
            <ul class="dropdown-menu" aria-labelledby="bulkActionDropdown">
              <li>
                <a class="dropdown-item" href="#"><i class="bi bi-trash"></i> Xóa đã chọn</a>
              </li>
              <li>
                <a class="dropdown-item" href="#"><i class="bi bi-check-circle"></i> Xác nhận đã chọn</a>
              </li>
              <li>
                <a class="dropdown-item" href="#"><i class="bi bi-x-circle"></i> Hủy đã chọn</a>
              </li>
            </ul>
          </div>
          <div class="ms-2">
            <router-link class="btn btn-primary" :to="{ name: 'order-status-management' }"> <i class="bi bi-check2-circle"></i> Trạng thái đơn hàng </router-link>
          </div>
        </div>
        <div class="col-md-6">
          <div class="input-group search-container ms-auto">
            <input type="text" class="form-control" placeholder="Tìm kiếm đơn hàng ..." aria-label="Tìm kiếm" v-model="keyword" @keyup.enter="searchOrderById" />
            <button class="btn btn-primary" type="button" @click="searchOrderById">
              <i class="bi bi-search"></i>
            </button>
          </div>
        </div>
      </div>

      <div class="row mb-3">
        <div class="col-md-12">
          <div class="btn-group" role="group" aria-label="Order filters">
            <button type="button" class="btn btn-outline-primary">Tất cả</button>
            <button type="button" class="btn btn-outline-primary">Chờ xác nhận</button>
            <button type="button" class="btn btn-outline-primary">Đã xác nhận</button>
            <button type="button" class="btn btn-outline-primary">Hoàn thành</button>
            <button type="button" class="btn btn-outline-primary">Đã hủy</button>
          </div>
        </div>
      </div>

      <div class="table-responsive">
        <table class="table table-striped table-hover table-bordered">
          <thead class="table-light">
            <tr>
              <th width="40">
                <div class="form-check">
                  <input class="form-check-input" type="checkbox" id="selectAll" />
                  <label class="form-check-label" for="selectAll"></label>
                </div>
              </th>
              <th>Mã đơn <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Khách hàng <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Ngày đặt <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Tổng tiền <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Trạng thái <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th width="150">Thao tác</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="order in listOrder" :key="order.id">
              <td>
                <div class="form-check">
                  <input class="form-check-input" type="checkbox" />
                </div>
              </td>
              <td>{{ order.id }}</td>
              <td>{{ order.fullName }}</td>
              <td>{{ new Date(order.createdAt).toLocaleString("vi-VN") }}</td>
              <td>{{ order.totalAmount.toLocaleString("vi", { style: "currency", currency: "VND" }) }}</td>

              <td>
                <span class="badge" :class="getStatusClass(order.orderStatus)">{{ order.orderStatus }}</span>
              </td>
              <td class="action-buttons">
                <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#viewOrderModal">
                  <i class="bi bi-eye"></i>
                </button>
                <router-link :to="{ name: 'edit-order', params: { idOrder: order.id } }" class="btn btn-warning btn-sm">
                  <i class="bi bi-pencil"></i>
                </router-link>
                <button class="btn btn-danger btn-sm" @click="deleteOrder(order.id)">
                  <i class="bi bi-trash"></i>
                </button>
              </td>
            </tr>
            <tr v-if="!listOrder.length">
              <td colspan="7" class="text-center">Khong co du lieu</td>
            </tr>
          </tbody>
        </table>
      </div>

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
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { onBeforeMount, ref, watch } from "vue";

// khai báo biến
const listOrder = ref([]);
const listCustomer = ref([]);
const limits = ref([5, 10, 15, 25]);
const limit = ref(5);
const currentPage = ref(1);
const totalPage = ref(null);
const keyword = ref("");
const listOriginalOrder = ref([]); // Lưu danh sách gốc để sử dụng trong tìm kiếm
// methods

// get
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
      };
    });
    totalPage.value = resp.data.result.totalPages;
    console.log(listOrder.value);
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
// create

// update

// delete
const deleteOrder = async (id) => {
  try {
    const order = listOrder.value.find((item) => item.id === id);
    if (order.orderStatus !== "Đã hủy") {
      alert("Đơn hàng không thể xóa!");
      return;
    }
    if (confirm("Bạn có chắc chắn muốn xóa đơn hàng này không?")) {
      const resp = await axios.delete(`http://localhost:8080/asm/api/v1/orders/${id}`);

      alert("Xóa thành công");
      await getAllOrder();
    }
  } catch (error) {
    console.log(error.message);
  }
};
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

const searchOrderById = () => {
  if (!keyword.value.trim()) {
    listOrder.value = [...listOriginalOrder.value]; // Giữ nguyên danh sách gốc nếu không nhập gì
  } else {
    listOrder.value = listOriginalOrder.value.filter((item) => item.id == keyword.value);
  }
  currentPage.value = 1;
};
// computed
// watch
watch([currentPage, limit], () => {
  getAllOrder();
});
// mounted
onBeforeMount(async () => {
  await getAllCustomer();
  await getAllOrder();
  listOriginalOrder.value = [...listOrder.value]; // Lưu danh sách gốc để sử dụng trong tìm kiếm
});
</script>

<style lang="scss" scoped></style>

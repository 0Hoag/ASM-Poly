<template>
  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">Quản Lý Người Dùng</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
      <div class="btn-group me-2">
        <button type="button" class="btn btn-sm btn-outline-secondary"><i class="bi bi-file-earmark-excel"></i> Xuất Excel</button>
        <button type="button" class="btn btn-sm btn-outline-secondary"><i class="bi bi-file-earmark-pdf"></i> Xuất PDF</button>
      </div>
    </div>
  </div>

  <div class="card mb-4">
    <div class="card-header bg-primary text-white"><i class="bi bi-people me-1"></i> Danh sách người dùng</div>
    <div class="card-body">
      <div class="row mb-3">
        <div class="col-md-6 d-flex align-items-center">
          <button type="button" class="btn btn-success me-2" data-bs-toggle="modal" data-bs-target="#addUserModal"><i class="bi bi-plus-circle"></i> Thêm người dùng</button>
          <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="bulkActionDropdown" data-bs-toggle="dropdown" aria-expanded="false">Thao tác hàng loạt</button>
            <ul class="dropdown-menu" aria-labelledby="bulkActionDropdown">
              <li>
                <a class="dropdown-item" href="#"><i class="bi bi-trash"></i> Xóa đã chọn</a>
              </li>
              <li>
                <a class="dropdown-item" href="#"><i class="bi bi-check-circle"></i> Kích hoạt đã chọn</a>
              </li>
              <li>
                <a class="dropdown-item" href="#"><i class="bi bi-x-circle"></i> Vô hiệu hóa đã chọn</a>
              </li>
            </ul>
          </div>
        </div>
        <div class="col-md-6">
          <div class="input-group search-container ms-auto">
            <input type="text" class="form-control" placeholder="Tìm kiếm người dùng..." aria-label="Tìm kiếm" v-model="keyword" @keydown.enter="search" />
            <button class="btn btn-primary" type="button" @click="search">
              <i class="bi bi-search"></i>
            </button>
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
              <th>No.<i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Họ tên <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Email <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Số điện thoại <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Vai trò <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th width="150">Thao tác</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(u, index) in listUser" :key="u.id">
              <td>
                <div class="form-check">
                  <input class="form-check-input" type="checkbox" />
                </div>
              </td>
              <td>{{ index + 1 + limit * (currentPage - 1) }}</td>
              <td>{{ u.fullName }}</td>
              <td>{{ u.email }}</td>
              <td>{{ u.phoneNumber }}</td>
              <td>{{ u.role ? "Quản trị viên" : "User" }}</td>
              <td class="action-buttons">
                <!-- <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#viewUserModal">
                  <i class="bi bi-eye"></i>
                </button> -->
                <button class="btn btn-warning btn-sm" data-bs-toggle="modal" @click="openEditModal(u)" data-bs-target="#editUserModal">
                  <i class="bi bi-pencil"></i>
                </button>
                <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteUserModal">
                  <i class="bi bi-trash"></i>
                </button>
              </td>
            </tr>
            <tr v-if="!listUser.length">
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

  <!-- View User Modal -->
  <!-- <div class="modal fade" id="viewUserModal" tabindex="-1" aria-labelledby="viewUserModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header bg-primary text-white">
          <h5 class="modal-title" id="viewUserModalLabel">Thông tin người dùng</h5>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-md-6">
              <div class="mb-3">
                <label class="form-label fw-bold">ID:</label>
                <p>1</p>
              </div>
              <div class="mb-3">
                <label class="form-label fw-bold">Họ tên:</label>
                <p>Nguyễn Văn A</p>
              </div>
              <div class="mb-3">
                <label class="form-label fw-bold">Email:</label>
                <p>nguyenvana@example.com</p>
              </div>
              <div class="mb-3">
                <label class="form-label fw-bold">Số điện thoại:</label>
                <p>0901234567</p>
              </div>
            </div>
            <div class="col-md-6">
              <div class="mb-3">
                <label class="form-label fw-bold">Vai trò:</label>
                <p>Quản trị viên</p>
              </div>
              <div class="mb-3">
                <label class="form-label fw-bold">Trạng thái:</label>
                <p><span class="badge bg-success">Hoạt động</span></p>
              </div>
              <div class="mb-3">
                <label class="form-label fw-bold">Ngày tạo:</label>
                <p>01/01/2023</p>
              </div>
              <div class="mb-3">
                <label class="form-label fw-bold">Đăng nhập gần nhất:</label>
                <p>25/03/2023 10:30</p>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
        </div>
      </div>
    </div>
  </div> -->

  <!-- Add User Modal -->
  <div class="modal fade" id="addUserModal" tabindex="-1" aria-labelledby="addUserModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header bg-success text-white">
          <h5 class="modal-title" id="addUserModalLabel">Thêm người dùng mới</h5>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="row">
              <div class="col-md-6">
                <div class="mb-3">
                  <label for="userName" class="form-label">Họ tên <span class="text-danger">*</span></label>
                  <input type="text" class="form-control" id="userName" required v-model="formData.fullName" />
                </div>
                <div class="mb-3">
                  <label for="userEmail" class="form-label">Email <span class="text-danger">*</span></label>
                  <input type="email" class="form-control" id="userEmail" required v-model="formData.email" />
                </div>
                <div class="mb-3">
                  <label for="userPhone" class="form-label">Số điện thoại</label>
                  <input type="tel" class="form-control" id="userPhone" v-model="formData.phoneNumber" />
                </div>
              </div>
              <div class="col-md-6">
                <div class="mb-3">
                  <label for="userPassword" class="form-label">Mật khẩu <span class="text-danger">*</span></label>
                  <input type="password" class="form-control" id="userPassword" required v-model="formData.password" />
                </div>
                <!-- <div class="mb-3">
                  <label for="userConfirmPassword" class="form-label">Xác nhận mật khẩu <span class="text-danger">*</span></label>
                  <input type="password" class="form-control" id="userConfirmPassword" required />
                </div> -->
                <div class="mb-3">
                  <label for="userRole" class="form-label">Vai trò <span class="text-danger">*</span></label>
                  <select class="form-select" id="userRole" required v-model="formData.role">
                    <option :value="true">Quản trị viên</option>
                    <option :value="false">Khách hàng</option>
                  </select>
                </div>
              </div>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
          <button type="button" class="btn btn-success" @click="addUser">Lưu</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Edit User Modal -->
  <div class="modal fade" id="editUserModal" tabindex="-1" aria-labelledby="editUserModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header bg-warning">
          <h5 class="modal-title" id="editUserModalLabel">Chỉnh sửa người dùng</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="row">
              <div class="col-md-6">
                <div class="mb-3">
                  <label for="editUserName" class="form-label">Họ tên <span class="text-danger">*</span></label>
                  <input type="text" class="form-control" id="editUserName" value="Nguyễn Văn A" required v-model="formData.fullName" />
                </div>
                <div class="mb-3">
                  <label for="editUserEmail" class="form-label">Email <span class="text-danger">*</span></label>
                  <input type="email" class="form-control" id="editUserEmail" value="nguyenvana@example.com" required v-model="formData.email" />
                </div>
              </div>
              <div class="col-md-6">
                <!-- <div class="mb-3">
                  <label for="editUserPassword" class="form-label">Mật khẩu mới</label>
                  <input type="password" class="form-control" id="editUserPassword" />
                  <small class="text-muted">Để trống nếu không muốn thay đổi mật khẩu</small>
                </div> -->
                <!-- <div class="mb-3">
                  <label for="editUserConfirmPassword" class="form-label">Xác nhận mật khẩu mới</label>
                  <input type="password" class="form-control" id="editUserConfirmPassword" />
                </div> -->
                <div class="mb-3">
                  <label for="editUserRole" class="form-label">Vai trò <span class="text-danger">*</span></label>
                  <select class="form-select" id="editUserRole" required v-model="formData.role">
                    <option value="">Chọn vai trò</option>
                    <option :value="true">Quản trị viên</option>
                    <option :value="false">Khách hàng</option>
                  </select>
                </div>
                <div class="mb-3">
                  <label for="editUserPhone" class="form-label">Số điện thoại</label>
                  <input type="tel" class="form-control" id="editUserPhone" v-model="formData.phoneNumber" />
                </div>
              </div>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
          <button type="button" class="btn btn-primary" @click="updateUser">Cập nhật</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Delete User Modal -->
  <div class="modal fade" id="deleteUserModal" tabindex="-1" aria-labelledby="deleteUserModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header bg-danger text-white">
          <h5 class="modal-title" id="deleteUserModalLabel">Xác nhận xóa</h5>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <p>Bạn có chắc chắn muốn xóa người dùng này không? Hành động này không thể hoàn tác.</p>
          <div class="alert alert-warning">
            <i class="bi bi-exclamation-triangle-fill me-2"></i>
            Cảnh báo: Tất cả dữ liệu liên quan đến người dùng này sẽ bị xóa vĩnh viễn.
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
          <button type="button" class="btn btn-danger">Xóa</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { onBeforeMount, onMounted, ref, watch } from "vue";

// khai báo biến
const formData = ref({
  id: "",
  fullName: "",
  email: "",
  phoneNumber: "",
  password: "",
  role: false,
});
const limits = ref([5, 10, 15, 25]);
const limit = ref(5);
const currentPage = ref(1);
const totalPage = ref(null);
const keyword = ref("");
const listUser = ref([]);
const isEdit = ref(false);
// methods
// validate
const validateForm = () => {
  if (!formData.value.fullName) {
    alert("Vui lòng nhập họ tên");
    return false;
  }
  if (!formData.value.email) {
    alert("Vui lòng nhập email");
    return false;
  }
  if (!formData.value.phoneNumber) {
    alert("Vui lòng nhập số điện thoại");
    return false;
  }
  if (!formData.value.password && !isEdit.value) {
    alert("Vui lòng nhập mật khẩu");
    return false;
  }

  return true;
};

// get
const getPaginatedUser = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/user/Get", {
      params: {
        size: limit.value,
        page: currentPage.value,
      },
    });
    listUser.value = resp.data.result.data;
    totalPage.value = resp.data.result.totalPages;
  } catch (error) {
    console.log(error.message);
  }
};
// create
const addUser = async () => {
  try {
    if (!validateForm()) return; // Validate form before sending request
    const newUser = { ...formData.value };
    const resp = await axios.post("http://localhost:8080/asm/api/v1/user/", newUser);

    if (resp.data.result) {
      alert("Thêm thành công");
      getPaginatedUser();
    }
    formData.value = { fullName: "", email: "", phoneNumber: "", password: "", role: false };
  } catch (error) {
    console.log(error.message);
    if (error.response.data.code === 1002) alert("Email hoặc số điện thoại đã được sử dụng");
    console.log(error.response.data.message);
  }
};
// update
const openEditModal = (selectedProducType) => {
  formData.value = { ...selectedProducType };
  isEdit.value = true;
};
const closeEditModal = () => {
  formData.value = { id: "", categoryName: "", parentCategory: "" };
};
const updateUser = async () => {
  try {
    if (!validateForm()) return; // Validate form before sending request
    const updateUser = { ...formData.value };
    const resp = await axios.put("http://localhost:8080/asm/api/v1/user/" + updateUser.id, updateUser);

    if (resp.data.result) {
      alert("Cập nhật thành công thành công");
      getPaginatedUser();
    }
    formData.value = { fullName: "", email: "", phoneNumber: "", password: "", role: "" };
  } catch (error) {
    console.log(error.message);
    if (error.response.data.code === 1002) alert("Email hoặc số điện thoại đã được sử dụng");
    console.log(error.response.data.message);
  }
};
// // delete
// const deleteUser = async () => {
//   try {
//     const resp = await axios.delete("http://localhost:8080/asm/api/v1/user/" + formData.value.id);

//     if (resp.data.result) {
//       alert("Xóa thành cong");
//       getPaginatedUser();
//     }
//   } catch (error) {
//     console.log(error.message);
//   }
// };
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
const search = async () => {
  try {
    if (!keyword.value.trim()) {
      await getPaginatedUser();
      currentPage.value = 1;
      return;
    }
    const resp = await axios.get("http://localhost:8080/asm/api/v1/user/search", {
      params: {
        size: limit.value,
        page: currentPage.value,
        keyword: keyword.value.trim(),
      },
    });
    listUser.value = resp.data.result.data;
    totalPage.value = resp.data.result.totalPages;
  } catch (error) {
    console.log(error.message);
  }
};
// computed
// watch
watch([currentPage, limit], () => {
  getPaginatedUser();
});
// mounted
onBeforeMount(async () => {
  await getPaginatedUser();
});

onMounted(async () => {
  const modalEdit = document.getElementById("editUserModal");
  modalEdit.addEventListener("hidden.bs.modal", closeEditModal);
});
</script>

<style lang="scss" scoped></style>

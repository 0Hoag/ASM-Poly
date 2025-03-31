<template>
  <div
    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
  >
    <h1 class="h2">Quản Lý Người Dùng</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
      <div class="btn-group me-2">
        <button type="button" class="btn btn-sm btn-outline-secondary">
          <i class="bi bi-file-earmark-excel"></i> Xuất Excel
        </button>
        <button type="button" class="btn btn-sm btn-outline-secondary">
          <i class="bi bi-file-earmark-pdf"></i> Xuất PDF
        </button>
      </div>
    </div>
  </div>

  <div class="card mb-4">
    <div class="card-header bg-primary text-white">
      <i class="bi bi-people me-1"></i> Danh sách người dùng
    </div>
    <div class="card-body">
      <div class="row mb-3">
        <div class="col-md-6 d-flex align-items-center">
          <button
            type="button"
            class="btn btn-success me-2"
            data-bs-toggle="modal"
            data-bs-target="#addUserModal"
          >
            <i class="bi bi-plus-circle"></i> Thêm người dùng
          </button>
          <div class="dropdown">
            <button
              class="btn btn-secondary dropdown-toggle"
              type="button"
              id="bulkActionDropdown"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Thao tác hàng loạt
            </button>
            <ul class="dropdown-menu" aria-labelledby="bulkActionDropdown">
              <li>
                <a class="dropdown-item" href="#"
                  ><i class="bi bi-trash"></i> Xóa đã chọn</a
                >
              </li>
              <li>
                <a class="dropdown-item" href="#"
                  ><i class="bi bi-check-circle"></i> Kích hoạt đã chọn</a
                >
              </li>
              <li>
                <a class="dropdown-item" href="#"
                  ><i class="bi bi-x-circle"></i> Vô hiệu hóa đã chọn</a
                >
              </li>
            </ul>
          </div>
        </div>
        <div class="col-md-6">
          <div class="input-group search-container ms-auto">
            <input
              type="text"
              class="form-control"
              placeholder="Tìm kiếm người dùng..."
              aria-label="Tìm kiếm"
            />
            <button class="btn btn-primary" type="button">
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
                  <input
                    class="form-check-input"
                    type="checkbox"
                    id="selectAll"
                  />
                  <label class="form-check-label" for="selectAll"></label>
                </div>
              </th>
              <th>ID <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Họ tên <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Email <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>
                Số điện thoại <i class="bi bi-arrow-down-up sort-icon"></i>
              </th>
              <th>Vai trò <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Trạng thái <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th width="150">Thao tác</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <div class="form-check">
                  <input class="form-check-input" type="checkbox" />
                </div>
              </td>
              <td>1</td>
              <td>Nguyễn Văn A</td>
              <td>nguyenvana@example.com</td>
              <td>0901234567</td>
              <td>Quản trị viên</td>
              <td><span class="badge bg-success">Hoạt động</span></td>
              <td class="action-buttons">
                <button
                  class="btn btn-primary btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#viewUserModal"
                >
                  <i class="bi bi-eye"></i>
                </button>
                <button
                  class="btn btn-warning btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#editUserModal"
                >
                  <i class="bi bi-pencil"></i>
                </button>
                <button
                  class="btn btn-danger btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#deleteUserModal"
                >
                  <i class="bi bi-trash"></i>
                </button>
              </td>
            </tr>
            <tr>
              <td>
                <div class="form-check">
                  <input class="form-check-input" type="checkbox" />
                </div>
              </td>
              <td>2</td>
              <td>Trần Thị B</td>
              <td>tranthib@example.com</td>
              <td>0912345678</td>
              <td>Nhân viên</td>
              <td><span class="badge bg-success">Hoạt động</span></td>
              <td class="action-buttons">
                <button
                  class="btn btn-primary btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#viewUserModal"
                >
                  <i class="bi bi-eye"></i>
                </button>
                <button
                  class="btn btn-warning btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#editUserModal"
                >
                  <i class="bi bi-pencil"></i>
                </button>
                <button
                  class="btn btn-danger btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#deleteUserModal"
                >
                  <i class="bi bi-trash"></i>
                </button>
              </td>
            </tr>
            <tr>
              <td>
                <div class="form-check">
                  <input class="form-check-input" type="checkbox" />
                </div>
              </td>
              <td>3</td>
              <td>Lê Văn C</td>
              <td>levanc@example.com</td>
              <td>0923456789</td>
              <td>Khách hàng</td>
              <td><span class="badge bg-danger">Bị khóa</span></td>
              <td class="action-buttons">
                <button
                  class="btn btn-primary btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#viewUserModal"
                >
                  <i class="bi bi-eye"></i>
                </button>
                <button
                  class="btn btn-warning btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#editUserModal"
                >
                  <i class="bi bi-pencil"></i>
                </button>
                <button
                  class="btn btn-danger btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#deleteUserModal"
                >
                  <i class="bi bi-trash"></i>
                </button>
              </td>
            </tr>
            <tr>
              <td>
                <div class="form-check">
                  <input class="form-check-input" type="checkbox" />
                </div>
              </td>
              <td>4</td>
              <td>Phạm Thị D</td>
              <td>phamthid@example.com</td>
              <td>0934567890</td>
              <td>Khách hàng</td>
              <td><span class="badge bg-success">Hoạt động</span></td>
              <td class="action-buttons">
                <button
                  class="btn btn-primary btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#viewUserModal"
                >
                  <i class="bi bi-eye"></i>
                </button>
                <button
                  class="btn btn-warning btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#editUserModal"
                >
                  <i class="bi bi-pencil"></i>
                </button>
                <button
                  class="btn btn-danger btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#deleteUserModal"
                >
                  <i class="bi bi-trash"></i>
                </button>
              </td>
            </tr>
            <tr>
              <td>
                <div class="form-check">
                  <input class="form-check-input" type="checkbox" />
                </div>
              </td>
              <td>5</td>
              <td>Hoàng Văn E</td>
              <td>hoangvane@example.com</td>
              <td>0945678901</td>
              <td>Nhân viên</td>
              <td>
                <span class="badge bg-warning text-dark">Chờ xác nhận</span>
              </td>
              <td class="action-buttons">
                <button
                  class="btn btn-primary btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#viewUserModal"
                >
                  <i class="bi bi-eye"></i>
                </button>
                <button
                  class="btn btn-warning btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#editUserModal"
                >
                  <i class="bi bi-pencil"></i>
                </button>
                <button
                  class="btn btn-danger btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#deleteUserModal"
                >
                  <i class="bi bi-trash"></i>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="d-flex justify-content-between align-items-center mt-3">
        <div>
          <span>Hiển thị 1-5 của 25 mục</span>
          <select
            class="form-select form-select-sm d-inline-block ms-2"
            style="width: auto"
          >
            <option value="5">5</option>
            <option value="10">10</option>
            <option value="25">25</option>
            <option value="50">50</option>
            <option value="100">100</option>
          </select>
        </div>
        <nav aria-label="Page navigation">
          <ul class="pagination">
            <li class="page-item disabled">
              <a class="page-link" href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
            <li class="page-item active">
              <a class="page-link" href="#">1</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
              <a class="page-link" href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>

  <!-- View User Modal -->
  <div
    class="modal fade"
    id="viewUserModal"
    tabindex="-1"
    aria-labelledby="viewUserModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header bg-primary text-white">
          <h5 class="modal-title" id="viewUserModalLabel">
            Thông tin người dùng
          </h5>
          <button
            type="button"
            class="btn-close btn-close-white"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
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
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Đóng
          </button>
        </div>
      </div>
    </div>
  </div>

  <!-- Add User Modal -->
  <div
    class="modal fade"
    id="addUserModal"
    tabindex="-1"
    aria-labelledby="addUserModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header bg-success text-white">
          <h5 class="modal-title" id="addUserModalLabel">
            Thêm người dùng mới
          </h5>
          <button
            type="button"
            class="btn-close btn-close-white"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="row">
              <div class="col-md-6">
                <div class="mb-3">
                  <label for="userName" class="form-label"
                    >Họ tên <span class="text-danger">*</span></label
                  >
                  <input
                    type="text"
                    class="form-control"
                    id="userName"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="userEmail" class="form-label"
                    >Email <span class="text-danger">*</span></label
                  >
                  <input
                    type="email"
                    class="form-control"
                    id="userEmail"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="userPhone" class="form-label"
                    >Số điện thoại</label
                  >
                  <input type="tel" class="form-control" id="userPhone" />
                </div>
              </div>
              <div class="col-md-6">
                <div class="mb-3">
                  <label for="userPassword" class="form-label"
                    >Mật khẩu <span class="text-danger">*</span></label
                  >
                  <input
                    type="password"
                    class="form-control"
                    id="userPassword"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="userConfirmPassword" class="form-label"
                    >Xác nhận mật khẩu <span class="text-danger">*</span></label
                  >
                  <input
                    type="password"
                    class="form-control"
                    id="userConfirmPassword"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="userRole" class="form-label"
                    >Vai trò <span class="text-danger">*</span></label
                  >
                  <select class="form-select" id="userRole" required>
                    <option value="">Chọn vai trò</option>
                    <option value="admin">Quản trị viên</option>
                    <option value="staff">Nhân viên</option>
                    <option value="customer">Khách hàng</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="mb-3">
              <label for="userStatus" class="form-label">Trạng thái</label>
              <select class="form-select" id="userStatus">
                <option value="1">Hoạt động</option>
                <option value="0">Bị khóa</option>
                <option value="2">Chờ xác nhận</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="userAvatar" class="form-label">Ảnh đại diện</label>
              <input class="form-control" type="file" id="userAvatar" />
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Hủy
          </button>
          <button type="button" class="btn btn-success">Lưu</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Edit User Modal -->
  <div
    class="modal fade"
    id="editUserModal"
    tabindex="-1"
    aria-labelledby="editUserModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header bg-warning">
          <h5 class="modal-title" id="editUserModalLabel">
            Chỉnh sửa người dùng
          </h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="row">
              <div class="col-md-6">
                <div class="mb-3">
                  <label for="editUserName" class="form-label"
                    >Họ tên <span class="text-danger">*</span></label
                  >
                  <input
                    type="text"
                    class="form-control"
                    id="editUserName"
                    value="Nguyễn Văn A"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="editUserEmail" class="form-label"
                    >Email <span class="text-danger">*</span></label
                  >
                  <input
                    type="email"
                    class="form-control"
                    id="editUserEmail"
                    value="nguyenvana@example.com"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="editUserPhone" class="form-label"
                    >Số điện thoại</label
                  >
                  <input
                    type="tel"
                    class="form-control"
                    id="editUserPhone"
                    value="0901234567"
                  />
                </div>
              </div>
              <div class="col-md-6">
                <div class="mb-3">
                  <label for="editUserPassword" class="form-label"
                    >Mật khẩu mới</label
                  >
                  <input
                    type="password"
                    class="form-control"
                    id="editUserPassword"
                  />
                  <small class="text-muted"
                    >Để trống nếu không muốn thay đổi mật khẩu</small
                  >
                </div>
                <div class="mb-3">
                  <label for="editUserConfirmPassword" class="form-label"
                    >Xác nhận mật khẩu mới</label
                  >
                  <input
                    type="password"
                    class="form-control"
                    id="editUserConfirmPassword"
                  />
                </div>
                <div class="mb-3">
                  <label for="editUserRole" class="form-label"
                    >Vai trò <span class="text-danger">*</span></label
                  >
                  <select class="form-select" id="editUserRole" required>
                    <option value="">Chọn vai trò</option>
                    <option value="admin" selected>Quản trị viên</option>
                    <option value="staff">Nhân viên</option>
                    <option value="customer">Khách hàng</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="mb-3">
              <label for="editUserStatus" class="form-label">Trạng thái</label>
              <select class="form-select" id="editUserStatus">
                <option value="1" selected>Hoạt động</option>
                <option value="0">Bị khóa</option>
                <option value="2">Chờ xác nhận</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="editUserAvatar" class="form-label"
                >Ảnh đại diện</label
              >
              <input class="form-control" type="file" id="editUserAvatar" />
              <div class="mt-2">
                <small class="text-muted"
                  >Ảnh đại diện hiện tại: avatar-1.jpg</small
                >
              </div>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Hủy
          </button>
          <button type="button" class="btn btn-primary">Cập nhật</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Delete User Modal -->
  <div
    class="modal fade"
    id="deleteUserModal"
    tabindex="-1"
    aria-labelledby="deleteUserModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header bg-danger text-white">
          <h5 class="modal-title" id="deleteUserModalLabel">Xác nhận xóa</h5>
          <button
            type="button"
            class="btn-close btn-close-white"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <p>
            Bạn có chắc chắn muốn xóa người dùng này không? Hành động này không
            thể hoàn tác.
          </p>
          <div class="alert alert-warning">
            <i class="bi bi-exclamation-triangle-fill me-2"></i>
            Cảnh báo: Tất cả dữ liệu liên quan đến người dùng này sẽ bị xóa vĩnh
            viễn.
          </div>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Hủy
          </button>
          <button type="button" class="btn btn-danger">Xóa</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>

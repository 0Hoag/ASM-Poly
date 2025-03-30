<template>
  <div
    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
  >
    <h1 class="h2">Quản Lý Đơn Hàng</h1>
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
      <i class="bi bi-cart me-1"></i> Danh sách đơn hàng
    </div>
    <div class="card-body">
      <div class="row mb-3">
        <div class="col-md-6 d-flex align-items-center">
          <router-link
            :to="{ name: 'create-order' }"
            class="btn btn-success me-2"
          >
            <i class="bi bi-plus-circle"></i> Tạo đơn hàng
          </router-link>
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
                  ><i class="bi bi-check-circle"></i> Xác nhận đã chọn</a
                >
              </li>
              <li>
                <a class="dropdown-item" href="#"
                  ><i class="bi bi-x-circle"></i> Hủy đã chọn</a
                >
              </li>
            </ul>
          </div>
          <div class="ms-2">
            <router-link
              class="btn btn-primary"
              :to="{ name: 'order-status-management' }"
            >
              <i class="bi bi-check2-circle"></i> Trạng thái đơn hàng
            </router-link>
          </div>
        </div>
        <div class="col-md-6">
          <div class="input-group search-container ms-auto">
            <input
              type="text"
              class="form-control"
              placeholder="Tìm kiếm đơn hàng..."
              aria-label="Tìm kiếm"
            />
            <button class="btn btn-primary" type="button">
              <i class="bi bi-search"></i>
            </button>
          </div>
        </div>
      </div>

      <div class="row mb-3">
        <div class="col-md-12">
          <div class="btn-group" role="group" aria-label="Order filters">
            <button type="button" class="btn btn-outline-primary active">
              Tất cả
            </button>
            <button type="button" class="btn btn-outline-primary">
              Chờ xác nhận
            </button>
            <button type="button" class="btn btn-outline-primary">
              Đã xác nhận
            </button>
            <button type="button" class="btn btn-outline-primary">
              Hoàn thành
            </button>
            <button type="button" class="btn btn-outline-primary">
              Đã hủy
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
              <th>Mã đơn <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Khách hàng <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Ngày đặt <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Tổng tiền <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>
                Phương thức thanh toán
                <i class="bi bi-arrow-down-up sort-icon"></i>
              </th>
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
              <td>#ORD-0025</td>
              <td>Nguyễn Văn A</td>
              <td>25/03/2023</td>
              <td>2,500,000 ₫</td>
              <td>Thanh toán khi nhận hàng</td>
              <td><span class="badge bg-success">Hoàn thành</span></td>
              <td class="action-buttons">
                <button
                  class="btn btn-primary btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#viewOrderModal"
                >
                  <i class="bi bi-eye"></i>
                </button>
                <button
                  class="btn btn-warning btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#editOrderModal"
                >
                  <i class="bi bi-pencil"></i>
                </button>
                <button
                  class="btn btn-danger btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#deleteOrderModal"
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
              <td>#ORD-0024</td>
              <td>Trần Thị B</td>
              <td>24/03/2023</td>
              <td>1,800,000 ₫</td>
              <td>Chuyển khoản ngân hàng</td>
              <td><span class="badge bg-warning text-dark">Đang giao</span></td>
              <td class="action-buttons">
                <button
                  class="btn btn-primary btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#viewOrderModal"
                >
                  <i class="bi bi-eye"></i>
                </button>
                <button
                  class="btn btn-warning btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#editOrderModal"
                >
                  <i class="bi bi-pencil"></i>
                </button>
                <button
                  class="btn btn-danger btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#deleteOrderModal"
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
              <td>#ORD-0023</td>
              <td>Lê Văn C</td>
              <td>23/03/2023</td>
              <td>3,200,000 ₫</td>
              <td>Ví điện tử</td>
              <td><span class="badge bg-info">Đã xác nhận</span></td>
              <td class="action-buttons">
                <button
                  class="btn btn-primary btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#viewOrderModal"
                >
                  <i class="bi bi-eye"></i>
                </button>
                <button
                  class="btn btn-warning btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#editOrderModal"
                >
                  <i class="bi bi-pencil"></i>
                </button>
                <button
                  class="btn btn-danger btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#deleteOrderModal"
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
              <td>#ORD-0022</td>
              <td>Phạm Thị D</td>
              <td>22/03/2023</td>
              <td>950,000 ₫</td>
              <td>Thẻ tín dụng</td>
              <td><span class="badge bg-danger">Đã hủy</span></td>
              <td class="action-buttons">
                <button
                  class="btn btn-primary btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#viewOrderModal"
                >
                  <i class="bi bi-eye"></i>
                </button>
                <button
                  class="btn btn-warning btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#editOrderModal"
                >
                  <i class="bi bi-pencil"></i>
                </button>
                <button
                  class="btn btn-danger btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#deleteOrderModal"
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
              <td>#ORD-0021</td>
              <td>Hoàng Văn E</td>
              <td>21/03/2023</td>
              <td>4,500,000 ₫</td>
              <td>Thanh toán khi nhận hàng</td>
              <td><span class="badge bg-success">Hoàn thành</span></td>
              <td class="action-buttons">
                <button
                  class="btn btn-primary btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#viewOrderModal"
                >
                  <i class="bi bi-eye"></i>
                </button>
                <button
                  class="btn btn-warning btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#editOrderModal"
                >
                  <i class="bi bi-pencil"></i>
                </button>
                <button
                  class="btn btn-danger btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#deleteOrderModal"
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
</template>

<script setup></script>

<style lang="scss" scoped></style>

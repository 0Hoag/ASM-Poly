<template>
  <div
    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
  >
    <h1 class="h2">Tổng Quan Hệ Thống</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
      <div class="btn-group me-2">
        <button type="button" class="btn btn-sm btn-outline-secondary">
          <i class="bi bi-file-earmark-excel"></i> Xuất báo cáo
        </button>
        <button type="button" class="btn btn-sm btn-outline-secondary">
          <i class="bi bi-printer"></i> In báo cáo
        </button>
      </div>
      <button
        type="button"
        class="btn btn-sm btn-outline-primary dropdown-toggle"
      >
        <i class="bi bi-calendar3"></i> Tuần này
      </button>
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
              <h2 class="mt-2 mb-0">1,258</h2>
              <p class="text-success mb-0">
                <i class="bi bi-arrow-up"></i> 12.5%
                <small class="text-muted">so với tháng trước</small>
              </p>
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
              <h2 class="mt-2 mb-0">358</h2>
              <p class="text-success mb-0">
                <i class="bi bi-arrow-up"></i> 8.2%
                <small class="text-muted">so với tháng trước</small>
              </p>
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
              <h2 class="mt-2 mb-0">125.5M</h2>
              <p class="text-success mb-0">
                <i class="bi bi-arrow-up"></i> 5.7%
                <small class="text-muted">so với tháng trước</small>
              </p>
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
              <h2 class="mt-2 mb-0">852</h2>
              <p class="text-success mb-0">
                <i class="bi bi-arrow-up"></i> 3.2%
                <small class="text-muted">so với tháng trước</small>
              </p>
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
            <router-link
              :to="{ name: 'order-management' }"
              class="btn btn-sm btn-primary"
              >Xem tất cả</router-link
            >
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
                <tr>
                  <td>#ORD-0025</td>
                  <td>Nguyễn Văn A</td>
                  <td><span class="badge bg-success">Hoàn thành</span></td>
                  <td>2,500,000 ₫</td>
                  <td>25/03/2023</td>
                </tr>
                <tr>
                  <td>#ORD-0024</td>
                  <td>Trần Thị B</td>
                  <td>
                    <span class="badge bg-warning text-dark">Đang giao</span>
                  </td>
                  <td>1,800,000 ₫</td>
                  <td>24/03/2023</td>
                </tr>
                <tr>
                  <td>#ORD-0023</td>
                  <td>Lê Văn C</td>
                  <td><span class="badge bg-info">Đã xác nhận</span></td>
                  <td>3,200,000 ₫</td>
                  <td>23/03/2023</td>
                </tr>
                <tr>
                  <td>#ORD-0022</td>
                  <td>Phạm Thị D</td>
                  <td><span class="badge bg-danger">Đã hủy</span></td>
                  <td>950,000 ₫</td>
                  <td>22/03/2023</td>
                </tr>
                <tr>
                  <td>#ORD-0021</td>
                  <td>Hoàng Văn E</td>
                  <td><span class="badge bg-success">Hoàn thành</span></td>
                  <td>4,500,000 ₫</td>
                  <td>21/03/2023</td>
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
            <a href="product-management.html" class="btn btn-sm btn-primary"
              >Xem tất cả</a
            >
          </div>
        </div>
        <div class="card-body">
          <ul class="list-group list-group-flush">
            <li
              class="list-group-item px-0 d-flex justify-content-between align-items-center"
            >
              <div class="d-flex align-items-center">
                <div
                  class="bg-light rounded"
                  style="width: 40px; height: 40px"
                ></div>
                <div class="ms-3">
                  <h6 class="mb-0">Điện thoại XYZ Pro</h6>
                  <small class="text-muted">Điện tử</small>
                </div>
              </div>
              <span class="badge bg-primary rounded-pill">125</span>
            </li>
            <li
              class="list-group-item px-0 d-flex justify-content-between align-items-center"
            >
              <div class="d-flex align-items-center">
                <div
                  class="bg-light rounded"
                  style="width: 40px; height: 40px"
                ></div>
                <div class="ms-3">
                  <h6 class="mb-0">Laptop ABC Ultra</h6>
                  <small class="text-muted">Điện tử</small>
                </div>
              </div>
              <span class="badge bg-primary rounded-pill">98</span>
            </li>
            <li
              class="list-group-item px-0 d-flex justify-content-between align-items-center"
            >
              <div class="d-flex align-items-center">
                <div
                  class="bg-light rounded"
                  style="width: 40px; height: 40px"
                ></div>
                <div class="ms-3">
                  <h6 class="mb-0">Tai nghe không dây</h6>
                  <small class="text-muted">Phụ kiện</small>
                </div>
              </div>
              <span class="badge bg-primary rounded-pill">87</span>
            </li>
            <li
              class="list-group-item px-0 d-flex justify-content-between align-items-center"
            >
              <div class="d-flex align-items-center">
                <div
                  class="bg-light rounded"
                  style="width: 40px; height: 40px"
                ></div>
                <div class="ms-3">
                  <h6 class="mb-0">Đồng hồ thông minh</h6>
                  <small class="text-muted">Thiết bị đeo</small>
                </div>
              </div>
              <span class="badge bg-primary rounded-pill">65</span>
            </li>
            <li
              class="list-group-item px-0 d-flex justify-content-between align-items-center"
            >
              <div class="d-flex align-items-center">
                <div
                  class="bg-light rounded"
                  style="width: 40px; height: 40px"
                ></div>
                <div class="ms-3">
                  <h6 class="mb-0">Máy ảnh mirrorless</h6>
                  <small class="text-muted">Thiết bị điện tử</small>
                </div>
              </div>
              <span class="badge bg-primary rounded-pill">42</span>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>

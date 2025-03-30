<template>
  <div
    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
  >
    <h1 class="h2">Quản Lý Loại Hàng</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
      <button
        type="button"
        class="btn btn-primary"
        data-bs-toggle="modal"
        data-bs-target="#addProductTypeModal"
      >
        <i class="bi bi-plus-circle"></i> Thêm loại hàng mới
      </button>
    </div>
  </div>

  <!-- Search and Filter -->
  <div class="row mb-4">
    <div class="col-md-6">
      <div class="input-group">
        <input
          type="text"
          class="form-control"
          placeholder="Tìm kiếm loại hàng..."
        />
        <button class="btn btn-outline-secondary" type="button">
          <i class="bi bi-search"></i>
        </button>
      </div>
    </div>
    <div class="col-md-6 d-flex justify-content-md-end">
      <div class="d-flex gap-2">
        <select class="form-select">
          <option selected>Danh mục</option>
          <option value="1">Điện thoại</option>
          <option value="2">Laptop</option>
          <option value="3">Phụ kiện</option>
          <option value="4">Thiết bị đeo</option>
          <option value="5">Máy ảnh</option>
        </select>
        <select class="form-select">
          <option selected>Sắp xếp theo</option>
          <option value="name-asc">Tên (A-Z)</option>
          <option value="name-desc">Tên (Z-A)</option>
          <option value="date-newest">Mới nhất</option>
          <option value="date-oldest">Cũ nhất</option>
        </select>
      </div>
    </div>
  </div>

  <!-- Product Types Table -->
  <div class="card border-0 shadow-sm">
    <div class="card-body">
      <div class="table-responsive">
        <table class="table table-hover">
          <thead>
            <tr>
              <th>ID</th>
              <th>Tên loại hàng</th>
              <th>Thao tác</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>TYPE-001</td>

              <td>Điện thoại</td>

              <td>
                <div class="btn-group">
                  <button
                    type="button"
                    class="btn btn-sm btn-outline-primary"
                    data-bs-toggle="modal"
                    data-bs-target="#editProductTypeModal"
                  >
                    <i class="bi bi-pencil"></i>
                  </button>
                  <button type="button" class="btn btn-sm btn-outline-danger">
                    <i class="bi bi-trash"></i>
                  </button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <!-- Pagination -->
  <nav class="d-flex justify-content-center mt-4">
    <ul class="pagination">
      <li class="page-item disabled">
        <a class="page-link" href="#" tabindex="-1" aria-disabled="true"
          >Trước</a
        >
      </li>
      <li class="page-item active"><a class="page-link" href="#">1</a></li>
      <li class="page-item"><a class="page-link" href="#">2</a></li>
      <li class="page-item"><a class="page-link" href="#">3</a></li>
      <li class="page-item">
        <a class="page-link" href="#">Sau</a>
      </li>
    </ul>
  </nav>

  <!-- Add Product Type Modal -->
  <div
    class="modal fade"
    id="addProductTypeModal"
    tabindex="-1"
    aria-labelledby="addProductTypeModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addProductTypeModalLabel">
            Thêm loại hàng mới
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
            <div class="mb-3">
              <label for="productTypeName" class="form-label"
                >Tên loại hàng</label
              >
              <input
                type="text"
                class="form-control"
                id="productTypeName"
                required
              />
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
          <button type="button" class="btn btn-primary">Lưu loại hàng</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Edit Product Type Modal -->
  <div
    class="modal fade"
    id="editProductTypeModal"
    tabindex="-1"
    aria-labelledby="editProductTypeModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="editProductTypeModalLabel">
            Chỉnh sửa loại hàng
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
            <div class="mb-3">
              <label for="editProductTypeName" class="form-label"
                >Tên loại hàng</label
              >
              <input
                type="text"
                class="form-control"
                id="editProductTypeName"
                value="Điện thoại Android"
                required
              />
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
          <button type="button" class="btn btn-primary">Lưu thay đổi</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>

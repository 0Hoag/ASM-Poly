<template>
  <!-- Main content -->
  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">Quản Lý Danh Mục</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
      <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCategoryModal"><i class="bi bi-plus-circle"></i> Thêm danh mục mới</button>
    </div>
  </div>

  <!-- Search and Filter -->
  <div class="row mb-4">
    <div class="col-md-6">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Tìm kiếm danh mục..." />
        <button class="btn btn-outline-secondary" type="button">
          <i class="bi bi-search"></i>
        </button>
      </div>
    </div>
    <div class="col-md-6 d-flex justify-content-md-end">
      <select class="form-select w-50">
        <option selected>Sắp xếp theo</option>
        <option value="name-asc">Tên (A-Z)</option>
        <option value="name-desc">Tên (Z-A)</option>
        <option value="date-newest">Mới nhất</option>
        <option value="date-oldest">Cũ nhất</option>
      </select>
    </div>
  </div>

  <!-- Categories Table -->
  <div class="card border-0 shadow-sm">
    <div class="card-body">
      <div class="table-responsive">
        <table class="table table-hover">
          <thead>
            <tr>
              <th>No.</th>
              <th>Tên danh mục</th>
              <th>Danh mục cha</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>CAT-001</td>
              <td>Điện thoại</td>
              <td>Không có</td>

              <td>
                <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#editCategoryModal">
                    <i class="bi bi-pencil"></i>
                  </button>
                  <button type="button" class="btn btn-sm btn-outline-danger">
                    <i class="bi bi-trash"></i>
                  </button>
                </div>
              </td>
            </tr>
            <tr>
              <td>CAT-001</td>
              <td>Điện thoại</td>
              <td>Không có</td>

              <td>
                <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#editCategoryModal">
                    <i class="bi bi-pencil"></i>
                  </button>
                  <button type="button" class="btn btn-sm btn-outline-danger">
                    <i class="bi bi-trash"></i>
                  </button>
                </div>
              </td>
            </tr>
            <tr>
              <td>CAT-001</td>
              <td>Điện thoại</td>
              <td>Không có</td>

              <td>
                <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#editCategoryModal">
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
        <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Trước</a>
      </li>
      <li class="page-item active"><a class="page-link" href="#">1</a></li>
      <li class="page-item"><a class="page-link" href="#">2</a></li>
      <li class="page-item"><a class="page-link" href="#">3</a></li>
      <li class="page-item">
        <a class="page-link" href="#">Sau</a>
      </li>
    </ul>
  </nav>

  <!-- Add Category Modal -->
  <div class="modal fade" id="addCategoryModal" tabindex="-1" aria-labelledby="addCategoryModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addCategoryModalLabel">Thêm danh mục mới</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="categoryStatus" class="form-label">Danh mục cha</label>
              <select class="form-select" id="categoryStatus">
                <option value="active" selected>Chọn danh mục</option>
                <option value="inactive">Điện thoại</option>
                <option value="updating">Đang cập nhật</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="categoryName" class="form-label">Tên danh mục</label>
              <input type="text" class="form-control" id="categoryName" required />
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
          <button type="button" class="btn btn-primary">Lưu danh mục</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Edit Category Modal -->
  <div class="modal fade" id="editCategoryModal" tabindex="-1" aria-labelledby="editCategoryModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="editCategoryModalLabel">Chỉnh sửa danh mục</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="editCategoryStatus" class="form-label">Danh mục cha</label>
              <select class="form-select" id="editCategoryStatus">
                <option value="active" selected>Chọn danh mục</option>
                <option value="inactive">Điện thoại</option>
                <option value="updating">Đang cập nhật</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="editCategoryName" class="form-label">Tên danh mục</label>
              <input type="text" class="form-control" id="editCategoryName" value="Điện thoại" required />
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
          <button type="button" class="btn btn-primary">Lưu thay đổi</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { onBeforeMount, ref } from "vue";

const categories = ref([]);
// getAllCategories
const getAllCategories = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/category/List");
    categories.value = resp.data.result;
  } catch (error) {
    console.log(error.message);
  }
};
onBeforeMount(async () => {
  await getAllCategories();
});

// deleteCategory
// paginatedCategories
// filterCategories
</script>

<style lang="scss" scoped></style>

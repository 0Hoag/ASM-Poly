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
        <input type="text" class="form-control" placeholder="Tìm kiếm danh mục..." v-model="keyword" @keyup.enter="findByitle" />
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
        <table class="table table-striped table-hover table-bordered">
          <thead>
            <tr>
              <th>No.</th>
              <th>Tên danh mục</th>
              <th>Danh mục cha</th>
              <th>Số lượng sản phẩm</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(category, index) in filterCategories" :key="category.id">
              <td>{{ index + 1 + limit * (currentPage - 1) }}</td>
              <td>{{ category.categoryName }}</td>
              <td>{{ getParentCategoryName(category.parentCategory) }}</td>
              <td>
                <span class="badge bg-success">{{ category.products ? category.products.length : 0 }}</span>
              </td>

              <td>
                <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#editCategoryModal" @click="openEditModal(category)">
                    <i class="bi bi-pencil"></i>
                  </button>
                  <button type="button" class="btn btn-sm btn-outline-danger" @click="deleteCategory(category.id)">
                    <i class="bi bi-trash"></i>
                  </button>
                </div>
              </td>
            </tr>
            <tr v-if="!filterCategories.length">
              <td colspan="5" class="text-center">Khong co du lieu</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <!-- Pagination -->
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
              <select class="form-select" v-model="category.parentCategory" id="categoryStatus">
                <option :value="null">Chọn danh mục</option>
                <option :value="cat.id" v-for="cat in categories" :key="cat.id">{{ cat.categoryName }}</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="categoryName" class="form-label">Tên danh mục</label>
              <input type="text" class="form-control" id="categoryName" placeholder="Nhập tên danh mục" v-model="category.categoryName" required />
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
          <button type="button" class="btn btn-primary" @click="createCategory">Thêm danh mục</button>
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
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="closeEditModal"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="editCategoryStatus" class="form-label">Danh mục cha</label>
              <select class="form-select" v-model="category.parentCategory">
                <option :value="0">Chọn danh mục</option>
                <option :value="cat.id" v-for="cat in categories.filter((c) => !c.parentCategory)" :key="cat.id">{{ cat.categoryName }}</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="editCategoryName" class="form-label">Tên danh mục</label>
              <input type="text" class="form-control" id="categoryName" placeholder="Nhập tên danh mục" v-model="category.categoryName" required />
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" @click="closeEditModal">Hủy</button>
          <button type="button" class="btn btn-primary" @click="editCategory">Cập nhật</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { computed, onBeforeMount, onMounted, ref, watch } from "vue";
//khởi tạo biến
const categories = ref([]);
const filterCategories = ref([]);
const limits = ref([5, 10, 15, 25]);
const limit = ref(5);
const currentPage = ref(1);
const totalPage = ref(null);
const keyword = ref("");
const category = ref({
  id: "",
  categoryName: "",
  parentCategory: null,
});
// methods

// getAllCategories
const getAllCategories = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/category/List");
    categories.value = resp.data.result;
  } catch (error) {
    console.log(error.message);
  }
};

const pageinatedCategories = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/category/Get", {
      params: {
        size: limit.value,
        page: currentPage.value,
      },
    });
    filterCategories.value = resp.data.result.data;
    totalPage.value = resp.data.result.totalPages;
  } catch (error) {
    console.log(error.message);
  }
};

// create Category
const createCategory = async () => {
  try {
    const newCategory = { ...category.value };
    if (!newCategory.categoryName) {
      alert("Tên danh mục không được để trống!");
      return;
    }

    const resp = await axios.post("http://localhost:8080/asm/api/v1/category/", newCategory);
    if (resp.data.result) {
      // filterCategories.value.push(newCategory);
      // categories.value.push(newCategory);
      await pageinatedCategories();
      alert("Thêm thành công");
    }
    category.value = { id: "", categoryName: "", parentCategory: "" };
  } catch (error) {
    console.log(error.message);
  }
};
// open model để lấy id category để cập nhật
const openEditModal = (selectedCategory) => {
  category.value = { ...selectedCategory };
};
const closeEditModal = () => {
  category.value = { id: "", categoryName: "", parentCategory: null };
};
// edit category
const editCategory = async () => {
  try {
    const { id, categoryName, parentCategory } = { ...category.value };

    if (id === category.value.parentCategory) {
      alert("Danh mục cha không phù hợp!");
      return;
    }
    if (!categoryName) {
      alert("Tên danh mục không được để trống!");
      return;
    }

    const resp = await axios.put(`http://localhost:8080/asm/api/v1/category/${id}`, { categoryName, parentCategory });
    const index = filterCategories.value.findIndex((cat) => cat.id === id);
    if (index !== -1) {
      filterCategories.value[index] = resp.data.result;
      alert("Cập nhật thanh cong!");
    }
    category.value = { id: "", categoryName: "", parentCategory: "" };
  } catch (error) {
    console.log(error.message);
  }
};
// deleteCategory
const deleteCategory = async (id) => {
  try {
    const index = filterCategories.value.findIndex((cat) => cat.id === id);
    if (index === -1) {
      alert("Không tìm thấy danh mục!");
      return;
    }

    if (filterCategories.value[index].products.length > 0) {
      alert("Danh mục này có chứa sản phẩm nên không thể xóa!");
      return;
    }

    await axios.delete(`http://localhost:8080/asm/api/v1/category/${id}`);
    filterCategories.value = filterCategories.value.filter((category) => category.id !== id);
    categories.value = categories.value.filter((category) => category.id !== id);
    alert("Xoa thanh cong!");
    changePerPage();
  } catch (error) {
    console.log(error.message);
  }
};

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
// filterCategories

const findByitle = () => {
  if (!keyword.value.trim()) {
    filterCategories.value = [...categories.value]; // Giữ nguyên danh sách gốc nếu không nhập gì
  } else {
    filterCategories.value = categories.value.filter((categories) => categories.categoryName.toLowerCase().includes(keyword.value.toLowerCase()));
  }
  currentPage.value = 1;
};

const getParentCategoryName = (parentId) => {
  if (!parentId) return "Không có";
  const parent = categories.value.find((category) => category.id === parentId);
  return parent ? parent.categoryName : "Unknown";
};
onBeforeMount(async () => {
  await getAllCategories();
  await pageinatedCategories();
  // filterCategories.value = [...categories.value];
});

// computed
// const totalPage = computed(() => Math.ceil(categories.value.length / limit.value));
// watch

watch([currentPage, limit], () => {
  pageinatedCategories();
});

// mounted
onMounted(() => {
  const modalEdit = document.getElementById("editCategoryModal");
  modalEdit.addEventListener("hidden.bs.modal", closeEditModal);
});
</script>

<style lang="scss" scoped></style>

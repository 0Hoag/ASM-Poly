<template>
  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">Quản Lý Loại Hàng</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
      <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addProductTypeModal"><i class="bi bi-plus-circle"></i> Thêm loại hàng mới</button>
    </div>
  </div>

  <!-- Search and Filter -->
  <div class="row mb-4">
    <div class="col-md-6">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Tìm kiếm loại hàng..." v-model="keyword" @keyup.enter="findByTitle" />
        <button class="btn btn-outline-secondary" type="button" @click="findByitle">
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
        <table class="table table-striped table-hover table-bordered">
          <thead>
            <tr>
              <th>No.</th>
              <th>Tên loại hàng</th>
              <th>Thao tác</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(productType, index) in filteredProductTypes" :key="productType.id">
              <td>{{ (currentPage - 1) * limit + index + 1 }}</td>

              <td>{{ productType.nameType }}</td>

              <td>
                <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#editProductTypeModal" @click="openEditModal(productType)">
                    <i class="bi bi-pencil"></i>
                  </button>
                  <button type="button" class="btn btn-sm btn-outline-danger">
                    <i class="bi bi-trash" @click="deleteProductType(productType.id)"></i>
                  </button>
                </div>
              </td>
            </tr>
            <tr v-if="!filteredProductTypes.length">
              <td colspan="3" class="text-center">Khong co du lieu</td>
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

  <!-- Add Product Type Modal -->
  <div class="modal fade" id="addProductTypeModal" tabindex="-1" aria-labelledby="addProductTypeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addProductTypeModalLabel">Thêm loại hàng mới</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div>
            <div class="mb-3">
              <label for="productTypeName" class="form-label">Tên loại hàng</label>
              <input type="text" class="form-control" id="productTypeName" v-model="productType.nameType" required />
            </div>
            <div class="my-3">
              <label for="editProductTypeName" class="form-label">Nhóm thông số cấu hình</label>
              <div class="bg-light mb-3 p-3" v-for="(group, index) in specGroups.filter((g) => !g.isDelete)" :key="index">
                <div class="">
                  <input type="text" v-model="group.specName" class="form-control spec-title my-3" placeholder="Tên thông số (VD: Cấu hình & Bộ nhớ)" />
                </div>
                <div class="col-4">
                  <button type="button" class="btn btn-danger btn-remove-group" @click="removeSpecGroup(group)">Xóa nhóm</button>
                </div>
              </div>
            </div>
            <button @click="addSpecGroup" type="button" class="btn btn-success mt-3" id="btn-add-spec">Thêm nhóm thông số</button>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
          <button type="button" class="btn btn-primary" @click="createProductType">Lưu loại hàng</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Edit Product Type Modal -->
  <div class="modal fade" id="editProductTypeModal" tabindex="-1" aria-labelledby="editProductTypeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="editProductTypeModalLabel">Chỉnh sửa loại hàng</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div>
            <div class="mb-3">
              <label for="editProductTypeName" class="form-label">Tên loại hàng</label>
              <input type="text" class="form-control" id="editProductTypeName" v-model="productType.nameType" required />
            </div>
            <div class="my-3">
              <label for="editProductTypeName" class="form-label">Nhóm thông số cấu hình</label>
              <div class="bg-light mb-3 p-3" v-for="(group, index) in specGroups.filter((g) => !g.isDelete)" :key="index">
                <div class="">
                  <input type="text" v-model="group.specName" class="form-control spec-title my-3" placeholder="Tên thông số (VD: Cấu hình & Bộ nhớ)" />
                </div>
                <div class="col-4">
                  <button type="button" class="btn btn-danger btn-remove-group" @click="removeSpecGroup(group)">Xóa nhóm</button>
                </div>
              </div>
            </div>
            <button @click="addSpecGroup" type="button" class="btn btn-success mt-3" id="btn-add-spec">Thêm nhóm thông số</button>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
          <button type="button" class="btn btn-primary" @click="editProductType">Lưu thay đổi</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { computed, onBeforeMount, onMounted, ref, watch } from "vue";

// khai báo
const productTypes = ref([]);
const filteredProductTypes = ref([]);
const limit = ref(5);
const limits = ref([5, 10, 15, 25]);
const currentPage = ref(1);
const keyword = ref("");
const isEdit = ref(false);
const productType = ref({
  id: "",
  nameType: "",
});
const totalPage = ref(null);
const allSpecGroup = ref([]);
const specGroups = ref([{ id: null, isDelete: false, productType: null, specName: "" }]);
// methods
const getAllSpecGroup = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/specificationType/List");
    allSpecGroup.value = resp.data.result.map((spec) => {
      const type = productTypes.value.find((productType) => productType.nameType === spec.productTypeName);
      console.log("productType", type.id);
      return { ...spec, isDelete: false, productTypeId: type.id };
    });
    console.log("spec group", allSpecGroup.value);
  } catch (error) {
    console.log(error.message);
  }
};
// getAllProductType
const getAllProductType = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/productType/List");
    productTypes.value = resp.data.result;
  } catch (error) {
    console.log(error.mesesage);
  }
};

// pageinatedProductType
const pageinatedProductType = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/productType/Get", {
      params: {
        size: limit.value,
        page: currentPage.value,
      },
    });
    filteredProductTypes.value = resp.data.result.data;
    totalPage.value = resp.data.result.totalPages;
    console.log("paginated", filteredProductTypes.value);
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

// CRUD

const createProductType = async () => {
  try {
    const newProductType = { ...productType.value };
    if (!newProductType.nameType) {
      alert("Vui lòng nhập tên loại hàng!");
      return;
    }
    const resp = await axios.post("http://localhost:8080/asm/api/v1/productType/", newProductType);
    if (resp.data.result) {
      const newSpec = specGroups.value.filter((sp) => sp.id == null && sp.specName !== "" && !sp.isDelete).map((sp) => ({ ...sp, productType: resp.data.result.id }));
      console.log("newSpec", newSpec);
      for (let sp of newSpec) {
        await axios.post("http://localhost:8080/asm/api/v1/specificationType/", sp);
      }
      // await pageinatedProductType();
    }
    alert("Thêm thành công");
    productType.value = { id: "", nameType: "" };
    specGroups.value = [{ id: "", isDelete: false, productType: null, specName: "" }];
    window.location.reload();
    console.log("create", productTypes.value);
  } catch (error) {
    console.log(error.message);
  }
};
const openEditModal = (selectedProducType) => {
  productType.value = { ...selectedProducType };
  console.log("openEditModal", productType.value);
  const filtered = allSpecGroup.value
    .filter((group) => group.productTypeId === productType.value.id)
    .map((group) => {
      return {
        ...group,
        isDelete: false,
        productTypeId: productType.value.id,
      };
    });
  console.log("filtered", filtered);
  if (filtered.length > 0) {
    specGroups.value = filtered;
    console.log("specGroups", specGroups.value);
  } else {
    specGroups.value = [
      {
        id: null,
        isDelete: false,
        productSpecifications: [],
        productTypeName: productType.value.nameType || "",
        specName: "",
      },
    ];
  }
};
const closeEditModal = () => {
  productType.value = { id: "", categoryName: "", parentCategory: "" };
  // specGroups.value = specGroups.value.map((sp) => (sp.isDelete = false));
  specGroups.value = [{ id: null, isDelete: false, productType: null, specName: "" }];
};
const editProductType = async () => {
  try {
    const { id, nameType } = { ...productType.value };
    if (!nameType) {
      alert("Vui lòng nhập tên loại hàng!");
      return;
    }
    const resp = await axios.put(`http://localhost:8080/asm/api/v1/productType/${id}`, { nameType });

    if (resp.data.result) {
      const specUpdate = specGroups.value.filter((sp) => sp.id != null && sp.specName !== "" && !sp.isDelete).map((sp) => ({ ...sp, productType: id }));
      const specCreate = specGroups.value.filter((sp) => sp.id == null && sp.specName !== "" && !sp.isDelete).map((sp) => ({ ...sp, productType: id }));
      const specDelete = specGroups.value.filter((sp) => sp.isDelete).map((sp) => ({ ...sp, productType: id }));
      console.log("specCreate", specCreate);
      if (specCreate.length > 0) {
        for (let sp of specCreate) await axios.post("http://localhost:8080/asm/api/v1/specificationType/", sp);
      }
      if (specUpdate.length > 0) {
        for (let sp of specUpdate) await axios.put("http://localhost:8080/asm/api/v1/specificationType/" + sp.id, sp);
      }

      if (specDelete.length > 0) {
        for (let sp of specDelete) await axios.delete("http://localhost:8080/asm/api/v1/specificationType/" + sp.id);
      }
      // await pageinatedProductType();
    }
    const index = filteredProductTypes.value.findIndex((productType) => productType.id === id);
    console.log(id, index);

    if (index !== -1) {
      filteredProductTypes.value[index] = resp.data.result;
      console.log(filteredProductTypes.value);
      alert("Cập nhật thành công");
      productType.value = { id: "", nameType: "" };
      window.location.reload();
    }
  } catch (error) {
    console.log(error.message);
  }
};

const deleteProductType = async (id) => {
  try {
    const index = filteredProductTypes.value.findIndex((cat) => cat.id === id);
    console.log(filteredProductTypes.value);
    console.log(id);
    if (index === -1) {
      alert("Không tìm thấy loại sản phẩm!");
      return;
    }

    if (filteredProductTypes.value[index].products.length > 0) {
      alert("Loại sản phẩm này có chứa dữ liệu liên quan nên không thể xóa!");
      return;
    }
    await axios.delete(`http://localhost:8080/asm/api/v1/productType/${id}`);
    await pageinatedProductType();

    alert("Xóa thành công");
  } catch (error) {
    console.log(error.message);
  }
};

const findByTitle = () => {
  if (!keyword.value.trim()) {
    filteredProductTypes.value = [...productTypes.value]; // Giữ nguyên danh sách gốc nếu không nhập gì
  } else {
    filteredProductTypes.value = productTypes.value.filter((item) => item.nameType.toLowerCase().includes(keyword.value.toLowerCase()));
  }
  currentPage.value = 1;
};

// Spec Group
const addSpecGroup = () => {
  const newSpec = { id: null, specName: "", isDelete: false, productSpecifications: [] };
  specGroups.value.push(newSpec);
  console.log("add specGroups", specGroups.value);
};

const removeSpecGroup = (group) => {
  console.log("group", group);
  if (group.productSpecifications && group.productSpecifications.length > 0) {
    alert("Nhóm thông số kỹ thuật này có chứa dữ liệu liên quan nên không thể xóa!");
    return;
  }
  group.isDelete = true;
};

// computed

// watch
watch([currentPage, limit], () => {
  pageinatedProductType();
});

onBeforeMount(async () => {
  await getAllProductType();
  await pageinatedProductType();
  await getAllSpecGroup();
});

onMounted(async () => {
  // await getAllProductType();
  // await pageinatedProductType();
  const modalEdit = document.getElementById("editProductTypeModal");
  const modalAdd = document.getElementById("addProductTypeModal");
  modalAdd.addEventListener("hidden.bs.modal", closeEditModal);
  modalEdit.addEventListener("hidden.bs.modal", closeEditModal);
});
</script>

<style lang="scss" scoped></style>

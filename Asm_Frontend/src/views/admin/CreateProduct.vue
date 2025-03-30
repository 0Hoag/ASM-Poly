<template>
  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">Tạo Sản Phẩm Mới</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
      <a href="order-management.html" class="btn btn-outline-secondary"> <i class="bi bi-arrow-left"></i> Quay lại </a>
    </div>
  </div>

  <div class="card mb-4">
    <div class="card-body">
      <form>
        <div class="row">
          <div class="col-md-6">
            <div class="mb-3">
              <label for="productName" class="form-label">Tên sản phẩm <span class="text-danger">*</span></label>
              <input type="text" class="form-control" id="productName" required />
            </div>
            <div class="mb-3">
              <label for="productCategory" class="form-label">Danh mục <span class="text-danger">*</span></label>
              <select class="form-select" id="productCategory" required>
                <option value="">Chọn danh mục</option>
                <option value="1">Điện tử</option>
                <option value="2">Thiết bị đeo</option>
                <option value="3">Phụ kiện</option>
                <option value="4">Thiết bị điện tử</option>
                <option value="5">Máy tính</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="productType" class="form-label">Loại sản phẩm</label>
              <select class="form-select" id="productType">
                <option value="">Chọn loại sản phẩm</option>
                <option value="1">Điện thoại</option>
                <option value="2">Laptop</option>
                <option value="3">Máy tính bảng</option>
                <option value="4">Tai nghe</option>
                <option value="5">Đồng hồ thông minh</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="productPrice" class="form-label">Giá <span class="text-danger">*</span></label>
              <div class="input-group">
                <input type="number" class="form-control" id="productPrice" required />
                <span class="input-group-text">₫</span>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="mb-3">
              <label for="productSKU" class="form-label">Mã SKU</label>
              <input type="text" class="form-control" id="productSKU" />
            </div>
            <div class="mb-3">
              <label for="productBrand" class="form-label">Thương hiệu</label>
              <input type="text" class="form-control" id="productBrand" />
            </div>
            <div class="mb-3">
              <label for="productDiscount" class="form-label">Giảm giá (%)</label>
              <input type="number" class="form-control" id="productDiscount" min="0" max="100" />
            </div>
            <div class="mb-3">
              <label for="productQuantity" class="form-label">Số lượng <span class="text-danger">*</span></label>
              <input type="number" class="form-control" id="productQuantity" required />
            </div>
          </div>
        </div>
        <div class="mb-3">
          <label for="productDescription" class="form-label">Mô tả</label>
          <textarea class="form-control" id="productDescription" rows="3"></textarea>
        </div>

        <!-- Thông số kỹ thuật -->
        <div class="mb-3">
          <h3>Thông số kỹ thuật</h3>
          <!-- Loại sản phẩm -->
          <div class="mb-3">
            <label for="productType" class="form-label">Loại sản phẩm</label>
            <select class="form-select" id="productType">
              <option value="">Chọn loại sản phẩm</option>
              <option value="1">Điện thoại</option>
              <option value="2">Laptop</option>
              <option value="3">Máy tính bảng</option>
              <option value="4">Tai nghe</option>
              <option value="5">Đồng hồ thông minh</option>
            </select>
          </div>

          <!-- Tên của thông số kỹ thuật -->
          <div class="bg-light my-3 p-3" v-for="(group, index) in specGroups" :key="index">
            <div class="row">
              <div class="col-8">
                <input type="text" v-model="group.title" class="form-control spec-title" placeholder="Tên thông số (VD: Cấu hình & Bộ nhớ)" />
              </div>
              <div class="col-4">
                <button type="button" class="btn btn-danger btn-remove-group" @click="removeSpecGroup(index)">Xóa nhóm</button>
              </div>
            </div>
            <!-- Danh sách thuộc tính -->
            <div class="mt-2">
              <div class="row my-2" v-for="(attr, attrIndex) in group.attributes" :key="attrIndex">
                <div class="col-4">
                  <input type="text" class="form-control attr-name" placeholder="Tên thuộc tính (VD: RAM)" v-model="attr.name" />
                </div>
                <div class="col-4">
                  <input type="text" class="form-control attr-value" placeholder="Giá trị (VD: 8GB)" v-model="attr.value" />
                </div>
                <div class="col-3">
                  <button type="button" class="btn btn-danger" @click="removeAttribute(group, attrIndex)">Xóa</button>
                </div>
              </div>
              <!-- Nút thêm thuộc tính -->
              <button type="button" class="btn btn-primary mt-2" @click="addAttribute(group)"><i class="bi bi-plus-circle"></i> Thêm thuộc tính</button>
            </div>
          </div>
          <hr />
          <button @click="addSpecGroup" type="button" class="btn btn-success mt-3" id="btn-add-spec">Thêm nhóm thông số</button>
        </div>
        <!-- Hình ảnh -->
        <div class="mb-3">
          <label for="productImages" class="form-label">Hình ảnh sản phẩm</label>
          <input class="form-control" type="file" id="productImages" multiple />
          <small class="text-muted">Có thể chọn nhiều hình ảnh</small>
        </div>
        <button class="btn btn-success">Tạo sản phẩm</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
const specGroups = ref([
  {
    title: "",
    attributes: [{ name: "", value: "" }],
  },
]);

const addSpecGroup = () => {
  specGroups.value.push({
    title: "",
    attributes: [{ name: "", value: "" }],
  });
  console.log(specGroups.value);
};

// Xóa nhóm
const removeSpecGroup = (index) => {
  specGroups.value.splice(index, 1);
};

// Thêm thuộc tính vào nhóm
const addAttribute = (group) => {
  group.attributes.push({ name: "", value: "" });
};

// Xóa thuộc tính
const removeAttribute = (group, attrIndex) => {
  group.attributes.splice(attrIndex, 1);
};
</script>

<style lang="scss" scoped></style>

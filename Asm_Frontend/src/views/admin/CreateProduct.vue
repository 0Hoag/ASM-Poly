<template>
  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">Tạo Sản Phẩm Mới</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
      <router-link :to="{ name: 'product-management' }" class="btn btn-outline-secondary"> <i class="bi bi-arrow-left"></i> Quay lại </router-link>
    </div>
  </div>

  <div class="card mb-4">
    <div class="card-body">
      <form @submit.prevent="saveProduct" enctype="multipart/form-data">
        <!-- Form sản phẩm -->
        <div class="row g-3">
          <div class="col-md-12">
            <div class="mb-3">
              <label for="productName" class="form-label">Tên sản phẩm <span class="text-danger">*</span></label>
              <input type="text" class="form-control" id="productName" placeholder="Nhập tên sản phẩm" v-model="product.title" required />
            </div>
          </div>
          <div class="col-md-12">
            <div class="mb-3">
              <label for="productCategory" class="form-label">Danh mục <span class="text-danger">*</span></label>
              <select class="form-select" v-if="product.category" v-model="product.category.id" required @change="categoryChange">
                <option value="">Chọn danh mục</option>
                <option v-for="category in categories" :key="category.id" :value="category.id">{{ category.name }}</option>
              </select>
            </div>
          </div>
          <div class="col-md-12">
            <div class="mb-3">
              <label for="productDescription" class="form-label">Mô tả sản phẩm</label>
              <textarea class="form-control" id="productDescription" rows="3" v-model="product.description" placeholder="Nhập mô tả sản phẩm"></textarea>
            </div>
          </div>
          <div class="col-md-6">
            <div class="mb-3">
              <label for="productPrice" class="form-label">Giá <span class="text-danger">*</span></label>
              <div class="input-group">
                <input type="number" class="form-control" id="productPrice" v-model="product.price" min="0" placeholder="Nhập giá" />
                <span class="input-group-text">₫</span>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="mb-3">
              <label for="productSalePrice" class="form-label">Giá sale</label>
              <div class="input-group">
                <input type="number" class="form-control" id="productSalePrice" />
                <span class="input-group-text">₫</span>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="mb-3">
              <label for="productStock" class="form-label">Số lượng kho <span class="text-danger">*</span></label>
              <input type="number" class="form-control" id="productStock" />
            </div>
          </div>
          <div class="col-md-6">
            <div class="mb-3">
              <label for="productSold" class="form-label">Số lượng bán được</label>
              <input type="number" class="form-control" id="productSold" />
            </div>
          </div>
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
          <input class="form-control" type="file" id="productImages" multiple @change="previewImage($event)" />
          <small class="text-muted">Có thể chọn nhiều hình ảnh</small>
        </div>
        <div class="mb-3">
          <img v-for="(img, index) in product.category.immages" :key="index" :src="img" class="preview-img" height="100" />
          <!-- <img :src="newImg[1]" height="100" /> -->
        </div>
        <button class="btn btn-success" tyoe="submit">{{ isEdit ? "Cập nhật" : "Thêm" }}</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { onBeforeMount, onUnmounted, ref } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();
const specGroups = ref([
  {
    title: "",
    attributes: [{ name: "", value: "" }],
  },
]);
const product = ref({
  category: {
    id: "",
    immages: [],
  },
});
const categories = ref({});
const isEdit = ref(false);
const props = defineProps(["id"]);

// Lấy sản phẩm theo id
const getProductById = async () => {
  try {
    const resp = await axios.get(`https://api.escuelajs.co/api/v1/products/${props.id}`);
    product.value = resp.data;
    console.log(product.value.category.id);
  } catch (error) {}
};
// Lấy danh mục sản phẩm
const getAllCategory = async () => {
  try {
    const resp = await axios.get("https://api.escuelajs.co/api/v1/categories");
    categories.value = resp.data;
  } catch (error) {}
};
onBeforeMount(() => {
  if (props.id) {
    isEdit.value = true;
    getProductById();
  }
  getAllCategory();
});
// Tạo sản phẩm
const saveProduct = async () => {
  try {
    let resp;
    if (isEdit.value) {
      resp = await axios.put("https://api.escuelajs.co/api/v1/products/" + props.id, product.value);

      alert("Sản phẩm đã update:");
      console.log("Sản phẩm đã update:", resp.data);
      router.push(`/admin/products/form/${resp.data.id}`);
    } else {
      resp = await axios.post("https://api.escuelajs.co/api/v1/products/", product.value);

      alert("Sản phẩm đã thêm:");
      console.log("Sản phẩm đã thêm:", resp.data);
      router.push(`/admin/products/form/${resp.data.id}`);
    }
    // Log dữ liệu phản hồi để kiểm tra
  } catch (error) {
    console.error("Lỗi khi thêm sản phẩm:", error.response?.data || error.message);
  }
};
onBeforeMount(() => {
  getAllCategory();
  if (props.id) {
    isEdit.value = true;
    getProductById();
  }
});

const categoryChange = () => {
  console.log(product.value);
};
// Xóa sản phẩm
//
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

const objectURLs = ref([]); // Lưu danh sách URL để giải phóng sau này

const previewImage = (event) => {
  const files = event.target.files;
  if (!files.length) return;

  // Xóa các Object URLs cũ trước khi cập nhật mới
  clearObjectURLs();

  // Lưu các URL mới và cập nhật UI
  product.value.category.immages = Array.from(files).map((file) => {
    const url = URL.createObjectURL(file);
    objectURLs.value.push(url); // Lưu để sau này giải phóng
    return url;
  });
  console.log(product.value.category.immages);
};

// Xóa Object URLs khi không dùng nữa
const clearObjectURLs = () => {
  objectURLs.value.forEach((url) => URL.revokeObjectURL(url));
  objectURLs.value = []; // Xóa danh sách URL
  product.value.category.immages = []; // Xóa ảnh khỏi UI
};

// Xóa Object URLs khi component bị hủy
onUnmounted(() => {
  clearObjectURLs();
});
</script>

<style lang="scss" scoped></style>

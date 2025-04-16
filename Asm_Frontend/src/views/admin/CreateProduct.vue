<template>
  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">Tạo Sản Phẩm Mới</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
      <router-link :to="{ name: 'product-management' }" class="btn btn-outline-secondary"> <i class="bi bi-arrow-left"></i> Quay lại </router-link>
    </div>
  </div>

  <div class="card mb-4">
    <div class="card-body">
      <div>
        <!-- Form sản phẩm -->
        <div class="row g-3">
          <div class="col-md-12">
            <div class="mb-3">
              <label for="productName" class="form-label">Tên sản phẩm <span class="text-danger">*</span></label>
              <input type="text" class="form-control" id="productName" placeholder="Nhập tên sản phẩm" v-model="product.productName" required />
            </div>
          </div>
          <div class="col-md-12">
            <div class="mb-3">
              <label for="productCategory" class="form-label">Danh mục <span class="text-danger">*</span></label>
              <select class="form-select" v-if="product.category" v-model="product.category" required @change="categoryChange">
                <option value="">Chọn danh mục</option>
                <option v-for="category in categories" :key="category.id" :value="category.id">{{ category.categoryName }}</option>
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
                <input type="number" class="form-control" id="productSalePrice" v-model="product.salePrice" min="0" placeholder="Nhập giá sale" />
                <span class="input-group-text">₫</span>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="mb-3">
              <label for="productStock" class="form-label">Số lượng kho <span class="text-danger">*</span></label>
              <input type="number" class="form-control" id="productStock" v-model="product.stockQuantity" min="0" />
            </div>
          </div>
          <div class="col-md-6">
            <div class="mb-3">
              <label for="productSold" class="form-label">Số lượng bán được</label>
              <input type="number" class="form-control" id="productSold" v-model="product.soldQuantity" min="0" />
            </div>
          </div>
        </div>

        <!-- Thông số kỹ thuật -->
        <div class="mb-3">
          <h3>Thông số kỹ thuật</h3>
          <!-- Loại sản phẩm -->
          <div class="mb-3">
            <label for="productType" class="form-label">Loại sản phẩm</label>
            <select :disabled="isEdit" class="form-select" id="productType" v-model="product.productType">
              <option :value="null">Chọn loại sản phẩm</option>
              <option :value="type.id" v-for="type in productTypes" :key="type.id">{{ type.nameType }}</option>
            </select>
          </div>

          <!-- Tên của thông số kỹ thuật -->
          <div class="bg-light my-3 p-3" v-for="(group, index) in specGroups.filter((g) => !g.isDelete)" :key="index">
            <div class="row">
              <div class="col-8">
                <input type="text" v-model="group.specName" class="form-control spec-title" placeholder="Tên thông số (VD: Cấu hình & Bộ nhớ)" />
              </div>
              <!-- <div class="col-4">
                <button type="button" class="btn btn-danger btn-remove-group" @click="group.isDelete = true">Xóa nhóm</button>
              </div> -->
            </div>
            <!-- Danh sách thuộc tính -->
            <div class="mt-2">
              <div class="row my-2" v-for="(attr, attrIndex) in group.specs.filter((g) => !g.isDelete)" :key="attrIndex">
                <div class="col-4">
                  <input type="text" class="form-control attr-name" placeholder="Tên thuộc tính (VD: RAM)" v-model="attr.name" />
                </div>
                <div class="col-4">
                  <input type="text" class="form-control attr-value" placeholder="Giá trị (VD: 8GB)" v-model="attr.value" />
                </div>
                <div class="col-3">
                  <button type="button" class="btn btn-danger" @click="attr.isDelete = true">Xóa</button>
                </div>
              </div>
              <!-- Nút thêm thuộc tính -->
              <button type="button" class="btn btn-primary mt-2" @click="addProductSpec(group)"><i class="bi bi-plus-circle"></i> Thêm thuộc tính</button>
            </div>
          </div>
          <hr />
          <!-- <button @click="saveProductSpec" type="button" class="btn btn-success mt-3" id="btn-add-spec">Thêm thuộc tính vào db</button>
          <button @click="deleteProductSpec" type="button" class="btn btn-success mt-3" id="btn-add-spec">Xóa thuộc tính vào db</button>
          <button @click="updateProductSpec" type="button" class="btn btn-success mt-3" id="btn-add-spec">Update thuộc tính vào db</button> -->
        </div>
        <!-- Hình ảnh -->
        <div class="mb-3">
          <label for="productImages" class="form-label">Hình ảnh sản phẩm</label>
          <input class="form-control" type="file" name="file" id="productImages" multiple @change="previewImage($event)" ref="fileInput" />
          <small class="text-muted">Có thể chọn nhiều hình ảnh</small>
        </div>
        <div class="d-flex flex-wrap gap-2 overflow-x-auto">
          <div v-for="(image, index) in product.images" :key="index" class="position-relative">
            <!-- Khi click vào ảnh, đổi trạng thái selected -->
            <div class="position-relative" @click="toggleSelection(index)">
              <!-- Ảnh -->
              <img
                :src="image.url"
                class="img-fluid rounded shadow-sm border border-2"
                :class="{ 'border-success': image.selected }"
                style="cursor: pointer; transition: 0.3s; width: 100px; height: 100px; object-fit: cover"
              />

              <!-- Biểu tượng tích xanh nếu ảnh được chọn -->
              <div v-if="image.selected" class="position-absolute top-0 start-0 m-1">
                <span class="badge bg-success p-2"><i class="bi bi-check-lg"></i></span>
              </div>

              <!-- Nút xóa ảnh -->
              <button class="btn btn-danger btn-sm position-absolute top-0 end-0 m-1" @click.stop="removeImage(index)">✖</button>
            </div>
          </div>
        </div>
        <!-- <button class="btn" @click="saveImage">upload</button>
        <button class="btn" @click="deleteImage">delete</button>
        <button class="btn" @click="updateProduct">update</button> -->
      </div>
      <button class="btn btn-success my-2" @click="saveProduct">{{ isEdit ? "Cập nhật" : "Thêm" }}</button>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { computed, onBeforeMount, onMounted, onUnmounted, ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";

// khai báo biến
const router = useRouter();
const route = useRoute();
const productTypes = ref([]);
const allSpecGroup = ref([]);
const specGroups = ref([]);

const product = ref({
  category: {
    id: "",
  },
  images: [],
  productType: null,
});
// const productSpec = ref([]);
const productSpec = ref([]);
const categories = ref({});
const isEdit = ref(false);
const fileInput = ref(null);
const deletedImg = ref([]);
const currentIdProduct = route.params.idProduct || null;

// methods
const validateForm = () => {
  return (
    product.value.productName &&
    product.value.description &&
    product.value.price > 0 &&
    product.value.salePrice >= 0 &&
    product.value.soldQuantity >= 0 &&
    product.value.stockQuantity >= 0 &&
    product.value.category && // kiểm tra nếu category không phải null hoặc undefined
    product.value.productType && // kiểm tra nếu productType không phải null hoặc undefined
    productSpec.value.length > 0
  );
};
// form

// Lấy sản phẩm theo id
const getProductById = async () => {
  try {
    const resp = await axios.get(`http://localhost:8080/asm/api/v1/product/${route.params.idProduct}`);
    product.value = resp.data.result;

    const categoryId = categories.value.find((category) => category.categoryName === product.value.category);
    const producTypeId = productTypes.value.find((productType) => productType.nameType === product.value.productType);
    if (!categoryId) {
      console.log("Category not found for this product.");
      return;
    }
    if (!producTypeId) {
      console.log("Product type not found for this product.");
      return;
    }

    product.value.category = categoryId.id;
    product.value.productType = producTypeId.id;
  } catch (error) {
    console.log(error.message);
  }
};

// Lấy danh mục sản phẩm
const getAllCategory = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/category/List");
    categories.value = resp.data.result;
  } catch (error) {
    console.log(error.message);
  }
};

const getAllSpecGroup = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/specificationType/List");
    allSpecGroup.value = resp.data.result.map((spec) => ({ ...spec, isDelete: false, specs: [] }));
    console.log("spec group", allSpecGroup.value);
  } catch (error) {
    console.log(error.message);
  }
};
const getAllProductSpec = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/product-specification/List");
    productSpec.value = resp.data.result.map((spec) => {
      const matchingGroup = allSpecGroup.value.find((group) => group.specName === spec.specificationTypeName);

      return {
        ...spec,
        specificationTypeId: matchingGroup ? matchingGroup.id : null,
        isDelete: false,
      };
    });
    // productSpec.value = resp.data.result.map((spec) => ({ ...spec, isDelete: false }));
    console.log("product spec", productSpec.value);
  } catch (error) {
    console.log(error.message);
  }
};
const getAllProducType = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/productType/List");
    productTypes.value = resp.data.result;
    console.log(productTypes.value);
  } catch (error) {
    console.log(error.message);
  }
};

const categoryChange = () => {
  console.log(product.value);
};

// update sản phẩm
const updateImage = async () => {
  if (deletedImg.value.length) {
    deleteImage();
  }
  if (fileInput.value.files.length) {
    saveImage();
  }
  console.log("update thanh cong");
};

// Tạo sản phẩm
const saveProduct = async () => {
  try {
    let resp;

    if (isEdit.value) {
      // Update sản phẩm
      if (!validateForm()) {
        alert("Vui lòng nhập đầy đủ thống tin sản phẩm");
        return;
      }

      resp = await axios.put(`http://localhost:8080/asm/api/v1/product/${currentIdProduct}`, product.value);

      // Update hình ảnh & thông số kỹ thuật
      await updateProductSpec();
      await updateImage();

      alert("Sản phẩm đã được cập nhật thành công!");
      console.log("Sản phẩm đã update:", resp.data);
      window.location.reload();
    } else {
      // Kiểm tra hình
      if (!validateForm()) {
        alert("Vui lòng nhập đầy đủ thống tin sản phẩm");
        return;
      }
      if (!fileInput.value || fileInput.value.files.length === 0) {
        alert("Vui lòng chọn ít nhất 1 hình ảnh!");
        return;
      }

      const newProduct = {
        productName: product.value.productName || "",
        description: product.value.description || "",
        price: product.value.price || 0,
        salePrice: product.value.salePrice || 0,
        soldQuantity: product.value.soldQuantity || 0,
        stockQuantity: product.value.stockQuantity || 0,
        category: product.value.category || null, // nếu là object
        productType: product.value.productType || null, // nếu là object
      };

      // Tạo sản phẩm mới
      resp = await axios.post("http://localhost:8080/asm/api/v1/product/", newProduct);
      const idProduct = resp.data.result.id;
      console.log("idProduct", idProduct);
      // Gán productID cho productSpec nếu cần
      productSpec.value = productSpec.value.map((spec) => ({
        ...spec,
        productID: idProduct,
      }));
      console.log("productSpec", productSpec.value);

      // Lưu thông số kỹ thuật và ảnh
      await saveProductSpec();
      await saveImage(idProduct);

      alert("Sản phẩm đã thêm thành công!");
      console.log("Sản phẩm đã thêm:", resp.data);

      router.replace(`/admin/products/form/${idProduct}`);
    }
  } catch (error) {
    console.error("❌ Lỗi khi lưu sản phẩm:", error.response?.data || error.message);
    alert("Đã xảy ra lỗi khi lưu sản phẩm. Vui lòng thử lại. \nLỗi " + (error.response?.data.message || error.message));
  }
};

// Spec Group

// Prodcut Spec
const addProductSpec = (group) => {
  const newSpec = {
    id: null,
    name: "",
    value: "",
    specificationTypeName: group.specName,
    specificationTypeId: group.id,
    productID: currentIdProduct,
    isDelete: false,
  };
  group.specs.push(newSpec);
  productSpec.value.push(newSpec);
  console.log("productSpec", productSpec.value);
};
const saveProductSpec = async () => {
  try {
    const dataPost = productSpec.value.filter((spec) => spec.id === null && !spec.isDelete && spec.name !== "");
    for (let data of dataPost) {
      if (data.value == "") data.value = "Không có";
      const resp = await axios.post("http://localhost:8080/asm/api/v1/product-specification/", data);
    }
  } catch (error) {
    console.log(error.message);
  }
};
const updateProductSpec = async () => {
  try {
    const deletes = productSpec.value.filter((spec) => spec.id != null && spec.isDelete && spec.name !== "");
    const updates = productSpec.value.filter((spec) => spec.id != null && !spec.isDelete && spec.name !== "");
    const creates = productSpec.value.filter((spec) => spec.id === null && !spec.isDelete && spec.name !== "");
    console.log("creates", creates);
    console.log("deletes", deletes);
    console.log("updates", updates);
    if (creates.length > 0) {
      for (let data of creates) {
        if (data.value == "") data.value = "Không có";
        await axios.post("http://localhost:8080/asm/api/v1/product-specification/", data);
      }
    }

    if (deletes.length > 0) {
      for (let data of deletes) {
        await axios.delete(`http://localhost:8080/asm/api/v1/product-specification/${data.id}`);
      }
    }

    if (updates.length > 0) {
      for (let data of updates) {
        await axios.put(`http://localhost:8080/asm/api/v1/product-specification/${data.id}`, data);
      }
    }
  } catch (error) {
    console.log("Lỗi khi đồng bộ thông số kỹ thuật:", error.message);
  }
};
// Image

const previewImage = (event) => {
  const files = event.target.files;
  // images.value = [];
  Array.from(files).forEach((file) => {
    const reader = new FileReader();
    reader.onload = (e) => {
      product.value.images.push({ url: e.target.result, selected: false });
    };
    reader.readAsDataURL(file);
  });
  console.log(product.value.images);
};

const saveImage = async (idProduct) => {
  const formData = new FormData();
  for (let file of fileInput.value.files) {
    formData.append("file", file);
  }
  try {
    await axios.post(`http://localhost:8080/asm/api/v1/image/${idProduct}`, formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });
    console.log("Selected files:", product.value.images);
    console.log("FormData entries:", [...formData.entries()]);

    fileInput.value.value = "";
  } catch (error) {
    console.log(error.message);
  }
};
// Hàm chọn/bỏ chọn ảnh
const toggleSelection = (index) => {
  product.value.images[index].selected = !product.value.images[index].selected;
};

// Hàm xóa ảnh khỏi danh sách
const removeImage = (index) => {
  deletedImg.value.push(product.value.images[index].id);
  product.value.images.splice(index, 1);
  console.log(product.value.images);
  console.log(deletedImg.value);
};
const deleteImage = async () => {
  const requestData = { images: deletedImg.value };
  console.log(requestData);

  try {
    await axios.delete(`http://localhost:8080/asm/api/v1/image/${route.params.idProduct}`, {
      data: requestData,
      headers: { "Content-Type": "application/json" },
    });

    console.log("Image removed successfully!");
  } catch (error) {
    console.error("Error removing image:", error.response?.data || error);
  }
};

// computed
// const producTypeSelected = computed(() => {
//   return productTypes.value.find((productType) => productType.id === product.value.productType);
// });

// const changeProductTypes = () => {
//   producTypeSelected.value;
//   console.log("changeProductTypes", productSpecByTypeSelected.value);
// };

// Danh sách ảnh được chọn
// const selectedImages = computed(() => product.value.images.filter((img) => img.selected));

// watch
watch(
  () => product.value.productType,
  (typeId) => {
    const type = productTypes.value.find((t) => t.id === typeId);
    if (!type) {
      specGroups.value = [];
      return;
    }

    const groups = allSpecGroup.value.filter((group) => group.productTypeName === type.nameType);
    // Mỗi group thêm specs nếu đang ở chế độ edit
    specGroups.value = groups.map((group) => ({
      ...group,
      specificationTypeId: group.id,
      specs: isEdit.value ? productSpec.value.filter((spec) => spec.specificationTypeId === group.id && spec.productID === currentIdProduct) : [],
    }));

    console.log("specGroups", specGroups.value);
    console.log("isEdit", isEdit.value);
  }
);
// mounted

onBeforeMount(async () => {
  await getAllCategory();
  await getAllSpecGroup();
  await getAllProducType();

  if (route.params.idProduct) {
    isEdit.value = true;
    await getAllProductSpec();
    await getProductById();
  }
});

// onMounted(() => {
//   getAllCategory();
//   if (props.id) {
//     isEdit.value = true;
//     getProductById();
//   }
// });
</script>

<style lang="scss" scoped></style>

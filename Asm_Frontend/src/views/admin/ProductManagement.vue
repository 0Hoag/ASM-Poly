<template>
  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">Quản Lý Sản Phẩm</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
      <div class="btn-group me-2">
        <button type="button" class="btn btn-sm btn-outline-secondary"><i class="bi bi-file-earmark-excel"></i> Xuất Excel</button>
        <button type="button" class="btn btn-sm btn-outline-secondary"><i class="bi bi-file-earmark-pdf"></i> Xuất PDF</button>
      </div>
    </div>
  </div>

  <div class="card mb-4">
    <div class="card-header bg-primary text-white"><i class="bi bi-box me-1"></i> Danh sách sản phẩm</div>
    <div class="card-body">
      <div class="row mb-3">
        <div class="col-md-6 d-flex align-items-center">
          <router-link type="button" class="btn btn-success me-2" :to="{ name: 'create-product' }"> <i class="bi bi-plus-circle"></i> Thêm sản phẩm </router-link>
          <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="bulkActionDropdown" data-bs-toggle="dropdown" aria-expanded="false">Thao tác hàng loạt</button>
            <ul class="dropdown-menu" aria-labelledby="bulkActionDropdown">
              <li>
                <a class="dropdown-item" href="#"><i class="bi bi-trash"></i> Xóa đã chọn</a>
              </li>
              <li>
                <a class="dropdown-item" href="#"><i class="bi bi-check-circle"></i> Kích hoạt đã chọn</a>
              </li>
              <li>
                <a class="dropdown-item" href="#"><i class="bi bi-x-circle"></i> Vô hiệu hóa đã chọn</a>
              </li>
            </ul>
          </div>
        </div>
        <div class="col-md-6">
          <div class="input-group search-container ms-auto">
            <input type="text" class="form-control" placeholder="Tìm kiếm sản phẩm..." aria-label="Tìm kiếm" v-model="keyword" @input="findByitle" />
            <button class="btn btn-primary" type="button">
              <i class="bi bi-search"></i>
            </button>
          </div>
        </div>
      </div>
      <!-- Table product -->
      <div class="table-responsive">
        <table class="table table-striped table-hover table-bordered">
          <thead class="table-light">
            <tr>
              <th width="40">
                <div class="form-check">
                  <input class="form-check-input" type="checkbox" id="selectAll" />
                  <label class="form-check-label" for="selectAll"></label>
                </div>
              </th>
              <th>No.<i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Hình ảnh</th>
              <th>Tên sản phẩm <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Danh mục <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Giá <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Số lượng <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th>Trạng thái <i class="bi bi-arrow-down-up sort-icon"></i></th>
              <th width="150">Thao tác</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(product, index) in pageinatedProducts" :key="product.id">
              <td>
                <div class="form-check">
                  <input class="form-check-input" type="checkbox" />
                </div>
              </td>
              <td>{{ (currentPage - 1) * limit + index + 1 }}</td>
              <td>
                <img :src="Array.isArray(product.images) ? product.images[0] : product.images" class="product-image" :alt="product.title" width="100" />
              </td>
              <td>{{ product.title }}</td>
              <td>{{ product.category.name }}</td>
              <td>{{ product.price.toLocaleString("vi-VN", { style: "currency", currency: "VND" }) }}</td>
              <td>45</td>
              <td><span class="badge bg-success">Còn hàng</span></td>
              <td class="action-buttons">
                <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#viewProductModal">
                  <i class="bi bi-eye"></i>
                </button>
                <router-link :to="{ name: 'edit-product', params: { id: product.id } }" class="btn btn-warning btn-sm">
                  <i class="bi bi-pencil"></i>
                </router-link>
                <button @click="deleteProduct(product.id)" class="btn btn-danger btn-sm">
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
    </div>
  </div>
  <div class="modal fade" id="viewProductModal" tabindex="-1" aria-labelledby="viewProductModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header bg-primary text-white">
          <h5 class="modal-title" id="viewProductModalLabel">Thông tin sản phẩm</h5>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-md-4 text-center">
              <img src="https://via.placeholder.com/200" class="img-fluid rounded mb-3" alt="Sản phẩm" />
              <div class="d-flex justify-content-center">
                <div class="border rounded p-1 mx-1">
                  <img src="https://via.placeholder.com/50" class="img-fluid" alt="Thumbnail 1" />
                </div>
                <div class="border rounded p-1 mx-1">
                  <img src="https://via.placeholder.com/50" class="img-fluid" alt="Thumbnail 2" />
                </div>
                <div class="border rounded p-1 mx-1">
                  <img src="https://via.placeholder.com/50" class="img-fluid" alt="Thumbnail 3" />
                </div>
              </div>
            </div>
            <div class="col-md-8">
              <div class="mb-3">
                <label class="form-label fw-bold">ID:</label>
                <p>1</p>
              </div>
              <div class="mb-3">
                <label class="form-label fw-bold">Tên sản phẩm:</label>
                <p>Điện thoại XYZ Pro</p>
              </div>
              <div class="mb-3">
                <label class="form-label fw-bold">Danh mục:</label>
                <p>Điện tử</p>
              </div>
              <div class="mb-3">
                <label class="form-label fw-bold">Giá:</label>
                <p>12,500,000 ₫</p>
              </div>
              <div class="mb-3">
                <label class="form-label fw-bold">Số lượng:</label>
                <p>45</p>
              </div>
              <div class="mb-3">
                <label class="form-label fw-bold">Trạng thái:</label>
                <p><span class="badge bg-success">Còn hàng</span></p>
              </div>
              <div class="mb-3">
                <label class="form-label fw-bold">Ngày tạo:</label>
                <p>01/01/2023</p>
              </div>
              <div class="mb-3">
                <label class="form-label fw-bold">Cập nhật lần cuối:</label>
                <p>15/03/2023</p>
              </div>
            </div>
          </div>
          <div class="mb-3">
            <label class="form-label fw-bold">Mô tả:</label>
            <p>
              Điện thoại XYZ Pro là một sản phẩm cao cấp với màn hình AMOLED 6.7 inch, chip xử lý mạnh mẽ, camera 108MP và pin 5000mAh với sạc nhanh 65W. Sản phẩm có thiết kế sang trọng, chống nước
              IP68 và hỗ trợ 5G.
            </p>
          </div>
          <div class="mb-3">
            <label class="form-label fw-bold">Thông số kỹ thuật:</label>
            <ul>
              <li>Màn hình: AMOLED 6.7 inch, 120Hz</li>
              <li>CPU: Snapdragon 8 Gen 1</li>
              <li>RAM: 12GB</li>
              <li>Bộ nhớ trong: 256GB</li>
              <li>Camera sau: 108MP + 12MP + 8MP</li>
              <li>Camera trước: 32MP</li>
              <li>Pin: 5000mAh, sạc nhanh 65W</li>
            </ul>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Add Product Modal -->
  <div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header bg-success text-white">
          <h5 class="modal-title" id="addProductModalLabel">Thêm sản phẩm mới</h5>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
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
              <div class="bg-light p-3">
                <div class="row">
                  <div class="col-8">
                    <input type="text" class="form-control spec-title" placeholder="Tên thông số (VD: Cấu hình & Bộ nhớ)" />
                  </div>
                  <div class="col-4">
                    <button type="button" class="btn btn-danger btn-remove-group">Xóa nhóm</button>
                  </div>
                </div>
                <!-- Danh sách thuộc tính -->
                <div class="mt-2">
                  <div class="row">
                    <div class="col-4">
                      <input type="text" class="form-control attr-name" placeholder="Tên thuộc tính (VD: RAM)" />
                    </div>
                    <div class="col-4">
                      <input type="text" class="form-control attr-value" placeholder="Giá trị (VD: 8GB)" />
                    </div>
                    <div class="col-3">
                      <button type="button" class="btn btn-primary"><i class="bi bi-plus-circle"></i> Thêm thuộc tính</button>
                    </div>
                  </div>
                  <ul class="list-group mt-2"></ul>
                </div>
              </div>
              <button type="button" class="btn btn-success mt-3" id="btn-add-spec">Thêm nhóm thông số</button>
            </div>
            <!-- Hình ảnh -->
            <div class="mb-3">
              <label for="productImages" class="form-label">Hình ảnh sản phẩm</label>
              <input class="form-control" type="file" id="productImages" multiple />
              <small class="text-muted">Có thể chọn nhiều hình ảnh</small>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
          <button type="button" class="btn btn-success">Lưu</button>
        </div>
      </div>
    </div>
  </div>
  <!-- Edit Product Modal -->
  <div class="modal fade" id="editProductModal" tabindex="-1" aria-labelledby="editProductModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header bg-warning">
          <h5 class="modal-title" id="editProductModalLabel">Chỉnh sửa sản phẩm</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="row">
              <div class="col-md-6">
                <div class="mb-3">
                  <label for="editProductName" class="form-label">Tên sản phẩm <span class="text-danger">*</span></label>
                  <input type="text" class="form-control" id="editProductName" value="Điện thoại XYZ Pro" required />
                </div>
                <div class="mb-3">
                  <label for="editProductCategory" class="form-label">Danh mục <span class="text-danger">*</span></label>
                  <select class="form-select" id="editProductCategory" required>
                    <option value="">Chọn danh mục</option>
                    <option value="1" selected>Điện tử</option>
                    <option value="2">Thiết bị đeo</option>
                    <option value="3">Phụ kiện</option>
                    <option value="4">Thiết bị điện tử</option>
                    <option value="5">Máy tính</option>
                  </select>
                </div>
                <div class="mb-3">
                  <label for="editProductPrice" class="form-label">Giá <span class="text-danger">*</span></label>
                  <div class="input-group">
                    <input type="number" class="form-control" id="editProductPrice" value="12500000" required />
                    <span class="input-group-text">₫</span>
                  </div>
                </div>
                <div class="mb-3">
                  <label for="editProductQuantity" class="form-label">Số lượng <span class="text-danger">*</span></label>
                  <input type="number" class="form-control" id="editProductQuantity" value="45" required />
                </div>
              </div>
              <div class="col-md-6">
                <div class="mb-3">
                  <label for="editProductSKU" class="form-label">Mã SKU</label>
                  <input type="text" class="form-control" id="editProductSKU" value="XYZ-PRO-001" />
                </div>
                <div class="mb-3">
                  <label for="editProductBrand" class="form-label">Thương hiệu</label>
                  <input type="text" class="form-control" id="editProductBrand" value="XYZ" />
                </div>
                <div class="mb-3">
                  <label for="editProductDiscount" class="form-label">Giảm giá (%)</label>
                  <input type="number" class="form-control" id="editProductDiscount" value="0" min="0" max="100" />
                </div>
                <div class="mb-3">
                  <label for="editProductStatus" class="form-label">Trạng thái</label>
                  <select class="form-select" id="editProductStatus">
                    <option value="1" selected>Còn hàng</option>
                    <option value="0">Hết hàng</option>
                    <option value="2">Sắp hết hàng</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="mb-3">
              <label for="editProductDescription" class="form-label">Mô tả</label>
              <textarea class="form-control" id="editProductDescription" rows="3">
Điện thoại XYZ Pro là một sản phẩm cao cấp với màn hình AMOLED 6.7 inch, chip xử lý mạnh mẽ, camera 108MP và pin 5000mAh với sạc nhanh 65W. Sản phẩm có thiết kế sang trọng, chống nước IP68 và hỗ trợ 5G.</textarea
              >
            </div>
            <div class="mb-3">
              <label for="editProductSpecs" class="form-label">Thông số kỹ thuật</label>
              <textarea class="form-control" id="editProductSpecs" rows="3">
Màn hình: AMOLED 6.7 inch, 120Hz
CPU: Snapdragon 8 Gen 1
RAM: 12GB
Bộ nhớ trong: 256GB
Camera sau: 108MP + 12MP + 8MP
Camera trước: 32MP
Pin: 5000mAh, sạc nhanh 65W</textarea
              >
              <small class="text-muted">Nhập mỗi thông số trên một dòng</small>
            </div>
            <div class="mb-3">
              <label for="editProductImages" class="form-label">Hình ảnh sản phẩm</label>
              <input class="form-control" type="file" id="editProductImages" multiple />
              <div class="mt-2 d-flex">
                <div class="border rounded p-1 me-2 position-relative">
                  <img src="https://via.placeholder.com/50" class="img-fluid" alt="Thumbnail 1" />
                  <button type="button" class="btn-close position-absolute top-0 end-0 bg-danger" style="font-size: 0.5rem"></button>
                </div>
                <div class="border rounded p-1 me-2 position-relative">
                  <img src="https://via.placeholder.com/50" class="img-fluid" alt="Thumbnail 2" />
                  <button type="button" class="btn-close position-absolute top-0 end-0 bg-danger" style="font-size: 0.5rem"></button>
                </div>
                <div class="border rounded p-1 position-relative">
                  <img src="https://via.placeholder.com/50" class="img-fluid" alt="Thumbnail 3" />
                  <button type="button" class="btn-close position-absolute top-0 end-0 bg-danger" style="font-size: 0.5rem"></button>
                </div>
              </div>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
          <button type="button" class="btn btn-primary">Cập nhật</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Delete Product Modal -->
  <div class="modal fade" id="deleteProductModal" tabindex="-1" aria-labelledby="deleteProductModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header bg-danger text-white">
          <h5 class="modal-title" id="deleteProductModalLabel">Xác nhận xóa</h5>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <p>Bạn có chắc chắn muốn xóa sản phẩm này không? Hành động này không thể hoàn tác.</p>
          <div class="alert alert-warning">
            <i class="bi bi-exclamation-triangle-fill me-2"></i>
            Cảnh báo: Tất cả dữ liệu liên quan đến sản phẩm này sẽ bị xóa vĩnh viễn.
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
          <button type="button" class="btn btn-danger">Xóa</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { onBeforeMount, computed, ref, watch } from "vue";

const products = ref([]);
const limits = ref([5, 10, 15, 25]);
const limit = ref(5);
const currentPage = ref(1);
const keyword = ref("");
const filteredProducts = ref([]);
const getAllProducts = async () => {
  try {
    const resp = await axios.get("https://api.escuelajs.co/api/v1/products");
    products.value = resp.data;
  } catch (error) {
    console.log(error);
  }
};
onBeforeMount(async () => {
  await getAllProducts();
  filteredProducts.value = [...products.value];
});
const deleteProduct = async (id) => {
  try {
    const resp = await axios.delete("https://api.escuelajs.co/api/v1/products/" + id);
    products.value = products.value.filter((product) => product.id !== id);
    filteredProducts.value = filteredProducts.value.filter((product) => product.id !== id);
  } catch (error) {
    console.log(error);
  }
};

const pageinatedProducts = computed(() => {
  const start = (currentPage.value - 1) * limit.value;
  return filteredProducts.value.slice(start, start + limit.value);
});

const totalPage = computed(() => Math.ceil(filteredProducts.value.length / limit.value));
console.log(totalPage.value);
const prevPage = () => {
  if (currentPage.value > 1) {
    currentPage.value--;
  }
};
const nextPage = () => {
  console.log(totalPage.value);
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

const findByitle = () => {
  if (!keyword.value.trim()) {
    filteredProducts.value = [...products.value]; // Giữ nguyên danh sách gốc nếu không nhập gì
  } else {
    filteredProducts.value = products.value.filter((product) => product.title.toLowerCase().includes(keyword.value.toLowerCase()));
  }
  currentPage.value = 1;
};
</script>

<style lang="scss" scoped></style>

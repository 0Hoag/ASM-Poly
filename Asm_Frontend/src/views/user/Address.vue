<template>
  <div class="bg-white rounded shadow-sm p-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h4 class="mb-0">Địa chỉ của tôi</h4>
      <button class="btn btn-primary" @click="openAddAddress"><i class="bi bi-plus-lg me-1"></i> Thêm địa chỉ mới</button>
    </div>
    <hr />

    <!-- Danh sách địa chỉ -->
    <div v-for="(address, index) in addresses" :key="address.id" class="card mb-3 border">
      <div class="card-body">
        <div class="d-flex justify-content-between align-items-start mb-2">
          <h5 class="card-title mb-0">{{ address.username }}</h5>
          <div>
            <span v-if="address.defaultAddress" class="badge bg-primary me-1"> Mặc định </span>
          </div>
        </div>
        <p class="card-text mb-1"><i class="bi bi-telephone me-2"></i>{{ address.phoneNumber }}</p>
        <p class="card-text mb-3"><i class="bi bi-geo-alt me-2"></i>{{ address.address }}</p>
        <div class="d-flex gap-2">
          <button class="btn btn-outline-secondary btn-sm" v-if="!address.defaultAddress" @click="setDefault(index)">Đặt làm mặc định</button>
          <button class="btn btn-outline-primary btn-sm" @click="editAddress(index)">Chỉnh sửa</button>
          <button class="btn btn-outline-danger btn-sm" @click="deleteAddress(index)">Xóa</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Modal thêm/sửa địa chỉ -->
  <div class="modal fade" id="editCustomerInfoModal" tabindex="-1" aria-hidden="true" ref="modal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">
            {{ editingIndex === null ? "Thêm địa chỉ mới" : "Chỉnh sửa địa chỉ" }}
          </h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form @submit.prevent="saveCustomerInfo">
            <div class="mb-3">
              <label for="customerName" class="form-label">Tên người nhận:</label>
              <input v-model="form.name" type="text" class="form-control" />
            </div>
            <div class="mb-3">
              <label for="shippingAddress" class="form-label">Địa chỉ:</label>
              <input v-model="form.fullAddress" type="text" class="form-control" />
            </div>
            <div class="mb-3">
              <label for="phone" class="form-label">Số điện thoại:</label>
              <input v-model="form.phone" type="text" class="form-control" />
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
              <button type="submit" class="btn btn-primary">Lưu</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import { Modal } from "bootstrap";

// Khai báo dữ liệu ban đầu
const addresses = ref([]);

// Biến lưu thông tin form
const form = ref({
  name: "",
  phone: "",
  fullAddress: "",
});

// Biến lưu thông tin đang chỉnh sửa
const editingIndex = ref(null);
const modal = ref(null);

// Lấy dữ liệu từ API khi component được tải
onMounted(() => {
  loadAddresses();
});

async function loadAddresses() {
  try {
    const userId = 1; // Thay đổi theo userId thực tế
    const response = await axios.get(`/asm/api/v1/address/${userId}`); // Đảm bảo URL chính xác
    addresses.value = response.data.result.data; // Cập nhật dữ liệu trả về
  } catch (error) {
    console.error("Lỗi khi tải danh sách địa chỉ:", error);
  }
}

function openAddAddress() {
  editingIndex.value = null;
  form.value = { name: "", phone: "", fullAddress: "" };
  showModal();
}

function editAddress(index) {
  editingIndex.value = index;
  form.value = { ...addresses.value[index] };
  showModal();
}

function saveCustomerInfo() {
  if (editingIndex.value === null) {
    // Nếu thêm mới
    addresses.value.push({
      ...form.value,
      id: Date.now(),
      defaultAddress: false,
    });
  } else {
    // Nếu chỉnh sửa
    addresses.value[editingIndex.value] = { ...form.value };
  }
  hideModal();
}

function deleteAddress(index) {
  addresses.value.splice(index, 1);
}

function setDefault(index) {
  addresses.value.forEach((addr, i) => {
    addr.defaultAddress = i === index;
  });
}

function showModal() {
  const modalEl = modal.value;
  const bsModal = new Modal(modalEl);
  bsModal.show();
}

function hideModal() {
  const modalEl = modal.value;
  const bsModal = Modal.getInstance(modalEl) || new Modal(modalEl);
  bsModal.show();
}
</script>

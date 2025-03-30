<template>
  <div class="container mt-4 cart-container">
    <h3 class="fw-bold"><i>GIỎ HÀNG CỦA BẠN</i></h3>
    <div class="row">
      <div class="col-md-8">
        <div class="card p-3">
          <table class="table align-middle">
            <thead>
              <tr class="text-center">
                <th>
                  <input
                    type="checkbox"
                    class="form-check-input"
                    v-model="selectAll"
                  />
                </th>
                <th>Hình ảnh</th>
                <th>Tên sản phẩm</th>
                <th>Đơn giá</th>
                <th>Số lượng</th>
                <th>Thành tiền</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="item in cart"
                :key="item.id"
                class="align-middle text-center"
              >
                <td>
                  <input
                    type="checkbox"
                    class="form-check-input item-checkbox"
                    :value="item.id"
                    v-model="selectedProducts"
                  />
                </td>

                <td>
                  <img :src="item.image" width="80" class="me-3" />
                </td>
                <td>
                  <p class="mb-1 fw-bold text-ellipsis">
                    {{ item.name }}
                  </p>
                </td>

                <td>
                  <p class="fw-bold text-danger">
                    {{
                      (item.saleStatus
                        ? item.priceSale
                        : item.price
                      ).toLocaleString("vi-VN", {
                        style: "currency",
                        currency: "VND",
                      })
                    }}
                  </p>
                </td>
                <td>
                  <div class="input-group">
                    <input
                      type="number"
                      min="1"
                      class="form-control text-center"
                      v-model="item.quantity"
                    />
                  </div>
                </td>
                <td class="fw-bold text-danger amount">
                  {{
                    (
                      (item.saleStatus ? item.priceSale : item.price) *
                      item.quantity
                    ).toLocaleString("vi-VN", {
                      style: "currency",
                      currency: "VND",
                    })
                  }}
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="col-md-4">
        <div class="card p-3">
          <p class="fw-bold">
            Đã chọn:
            <span id="totalSelected">{{ selectedProducts.length }}</span> sản
            phẩm - Tổng tiền:
            <span id="totalAmount" class="text-danger">{{
              total.toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
              })
            }}</span>
          </p>
          <router-link
            :to="{ name: 'confirm-order' }"
            class="btn btn-warning w-100"
            id="buyNow"
            >Mua ngay</router-link
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, ref, watch } from "vue";
import ip14 from "../../assets/img/ip14.png";
// Danh sách các sản phẩm
const cart = ref([
  {
    id: 1,
    name: "iPhone 15 Pro",
    price: 34900000,
    priceSale: 33900000,
    saleStatus: true,
    quantity: 1,
    image: ip14,
  },
  {
    id: 2,
    name: "iPhone 15 Pro",
    price: 34900000,
    priceSale: 33900000,
    saleStatus: true,
    quantity: 1,
    image: ip14,
  },
]);
// Danh sách chứa id của các sản phẩm đã chọn
const selectedProducts = ref([]);

// Chọn tất cả
const selectAll = computed({
  get: () =>
    selectedProducts.value.length === cart.value.length &&
    cart.value.length > 0,
  set: (value) => {
    selectedProducts.value = value ? cart.value.map((item) => item.id) : [];
  },
});

const total = computed(() => {
  return cart.value
    .filter((item) => selectedProducts.value.includes(item.id))
    .reduce((total, item) => total + item.price * item.quantity, 0);
});

watch(selectedProducts, (newValue) => {
  console.log(selectedProducts.value);
});
</script>

<style scoped>
.card,
.cart-container {
  border: 2px solid #ddd;
  border-radius: 10px;
  padding: 20px;
}
.btn-warning {
  color: #000;
  font-weight: bold;
}
.custom-checkbox {
  width: 18px;
  height: 18px;
  accent-color: #007bff;
}
.text-sale {
  text-decoration: line-through;
  color: gray;
  font-size: 0.9em;
}
.text-ellipsis {
  max-width: 200px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>

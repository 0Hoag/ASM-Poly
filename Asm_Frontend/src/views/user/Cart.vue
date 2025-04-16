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
                  <input type="checkbox" class="form-check-input" v-model="selectAll" @change="toggleSelectAll" />
                </th>
                <th>Hình ảnh</th>
                <th>Tên sản phẩm</th>
                <th>Đơn giá</th>
                <th>Số lượng</th>
                <th>Thành tiền</th>
                <th>Hành động</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in cart" :key="item.cartDetailId" class="align-middle text-center">
                <td>
                  <input type="checkbox" class="form-check-input item-checkbox" :value="item.cartDetailId"
                    v-model="selectedProducts" />
                </td>
                <td>
                  <img :src="item.images[0].url" width="80" class="me-3" />
                </td>
                <td>
                  <p class="mb-1 fw-bold text-ellipsis">{{ item.productName }}</p>
                </td>
                <td>
                  <p class="fw-bold text-danger">
                    {{
                      (item.priceSale ?? item.price).toLocaleString("vi-VN", {
                        style: "currency",
                        currency: "VND",
                      })
                    }}
                  </p>
                </td>
                <td>
                  <input type="number" min="1" class="form-control text-center" v-model="item.quantity"
                    @change="updateQuantity(item)" />
                </td>
                <td class="fw-bold text-danger amount">
                  {{
                    ((item.priceSale ?? item.price) * item.quantity).toLocaleString("vi-VN", {
                      style: "currency",
                      currency: "VND",
                    })
                  }}
                </td>
                <td>
                  <button class="btn btn-sm btn-danger" @click="deleteItem(item.cartDetailId)">
                    <i class="bi bi-trash"></i>
                  </button>
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
            <span>{{ selectedProducts.length }}</span> sản phẩm - Tổng tiền:
            <span class="text-danger">
              {{
                selectedProducts.length > 0
                  ? total.toLocaleString("vi-VN", { style: "currency", currency: "VND" })
                  : 0
              }}
            </span>
          </p>
          <button v-if="selectedProducts.length > 0" class="btn btn-warning w-100" @click="goToConfirmOrder">
            Mua ngay
          </button>
          <!-- Nếu không có sản phẩm nào được chọn, không hiển thị nút Mua ngay -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      selectedProducts: [],
      cart: [],
      selectAll: false,
      total: 0,
      cartId: localStorage.getItem('cartId'),
      listProduct: [],
    };
  },
  async mounted() {
    await this.getAllProduct();
    await this.getCartForUser();
  },
  methods: {
    goToConfirmOrder() {
      if (this.selectedProducts.length === 0) {
        alert("Vui lòng chọn ít nhất một sản phẩm để mua.");
        return; // Ngăn việc tiếp tục nếu không có sản phẩm nào được chọn
      }

      // Lấy sản phẩm đã chọn từ giỏ hàng
      const selectedItems = this.cart.filter(item =>
        this.selectedProducts.includes(item.cartDetailId)
      );

      // Lưu vào localStorage
      localStorage.setItem('selectedCartItems', JSON.stringify(selectedItems));
      console.log("Sản phẩm đã chọn:", selectedItems);
      localStorage.setItem('selectedCartItems', JSON.stringify(selectedItems));
      // Điều hướng tới trang xác nhận đơn hàng
      this.$router.push({ name: 'confirm-order' });
    },
    // nút xóa sản phẩm
    async deleteItem(cartDetailId) {
      try {
        console.log("ID gửi đi:", cartDetailId);
        await axios.delete(`/api/v1/cart-detail/${cartDetailId}`);
        // Xoá thành công thì reload lại giỏ hàng
        await this.getCartForUser();
      } catch (error) {
        console.error("Lỗi khi xóa sản phẩm:", error);
      }
    },
    async getAllProduct() {
      try {
        const response = await axios.get(`/api/v1/product/List`);
        this.listProduct = response.data.result;
      } catch (error) {
        console.error("Lỗi khi lấy sản phẩm:", error);
      }
    },
    getUserIdFromSession() {
      return localStorage.getItem('userId');
    },
    getCartForUser() {
      const userId = this.getUserIdFromSession();
      if (userId) {
        fetch(`/api/v1/cart-detail/List`)
          .then(response => {
            if (!response.ok) {
              throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
          })
          .then(data => {
            if (Array.isArray(data.result)) {
              const userCartItems = data.result
                .filter(item => item.cart == this.cartId)
                .map(item => {
                  const product = this.listProduct.find(p => p.productName === item.productName);
                  return {
                    ...product,
                    quantity: item.quantity,
                    cartDetailId: item.id, // lưu id của cart_detail
                  };
                });
              this.cart = userCartItems;
              this.selectedProducts = []; // reset lựa chọn khi load lại
              this.calculateTotal();
            } else {
              console.error("Dữ liệu trả về không hợp lệ:", data);
            }
          })
          .catch(error => {
            console.error("Lỗi khi lấy giỏ hàng:", error);
          });
      } else {
        console.log("Không tìm thấy userId trong session.");
      }
    },
    calculateTotal() {
      this.total = this.selectedProducts.reduce((acc, cartDetailId) => {
        const item = this.cart.find(i => i.cartDetailId === cartDetailId);
        if (item) {
          const price = item.priceSale ?? item.price;
          return acc + price * item.quantity;
        }
        return acc;
      }, 0);
    },
    updateQuantity(item) {
      this.calculateTotal();
    },
    toggleSelectAll() {
      this.selectedProducts = this.selectAll ? this.cart.map(i => i.cartDetailId) : [];
    }
  },
  watch: {
    selectedProducts() {
      this.calculateTotal();
    }
  }
};
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

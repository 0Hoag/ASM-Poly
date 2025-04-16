<template>
  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">{{ isEdit ? "Chi tiết đơn hàng" : "Tạo đơn hàng mới" }}</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
      <router-link :to="{ name: 'order-management' }" class="btn btn-outline-secondary"> <i class="bi bi-arrow-left"></i> Quay lại </router-link>
    </div>
  </div>

  <div class="card mb-4">
    <div class="card-body">
      <div>
        <div class="row mb-3">
          <div class="col-md-12">
            <h6 class="fw-bold">Thông tin khách hàng</h6>
          </div>
          <div class="col-md-12">
            <div class="mb-3">
              <label for="customerType" class="form-label">Chọn khách hàng</label>
              <select class="form-select" id="customerType" v-model="formOrder.user">
                <option value="">Khách hàng mới</option>
                <option v-for="u in listCustomer" :key="u.id" :value="u.id">{{ u.fullName }} - {{ u.email }} - {{ u.phoneNumber }}</option>
              </select>
            </div>
          </div>
        </div>

        <div class="row mb-3">
          <div class="col-md-12">
            <h6 class="fw-bold">Địa chỉ giao hàng</h6>
          </div>
          <div class="col-md-12">
            <div class="mb-3">
              <label for="shippingAddress" class="form-label">Địa chỉ <span class="text-danger">*</span></label>
              <select class="form-select" v-model="formOrder.address">
                <option value="">Chọn địa chỉ</option>
                <option v-for="address in listAddress" :key="address.id" :value="address.id">{{ address.username }} - {{ address.phoneNumber }} - {{ address.address }}</option>
              </select>
            </div>
          </div>
        </div>

        <div class="row mb-3">
          <div class="col-md-12">
            <h6 class="fw-bold">Thông tin sản phẩm</h6>
            <!-- PHẦN SẢN PHẨM ĐÃ CHỌN - BẮT ĐẦU -->
            <div class="card mb-4">
              <div class="card-header bg-light">Sản phẩm đã chọn</div>
              <div class="card-body p-0">
                <table class="table mb-0">
                  <thead>
                    <tr>
                      <th>Sản phẩm</th>
                      <th>Giá</th>
                      <th>Số lượng</th>
                      <th>Thành tiền</th>
                      <th></th>
                    </tr>
                  </thead>
                  <tbody id="selectedProductsTable">
                    <tr v-for="p in cart.filter((p) => !p.isDelete)" :key="p.id">
                      <td>{{ p.productName }}</td>
                      <td>{{ p.currentPrice }} đ</td>
                      <td><input type="number" class="form-control w-50" min="1" v-model="p.quantity" @change="checkQuantity(p, p.stockQuantity)" /></td>
                      <td>{{ p.currentPrice * p.quantity }}</td>
                      <td>
                        <button class="btn btn-danger" @click="removeFromCart(p)"><i class="bi bi-trash"></i></button>
                      </td>
                    </tr>
                    <!-- Sản phẩm đã chọn sẽ được thêm vào đây bằng JavaScript -->
                  </tbody>
                  <tfoot>
                    <tr>
                      <td colspan="3" class="text-end">
                        <strong>Tổng tiền</strong>
                      </td>
                      <td>
                        <strong class="text-danger" id="totalAmount">{{ totalAmount }}</strong>
                      </td>
                      <td></td>
                    </tr>
                  </tfoot>
                </table>
              </div>
            </div>
            <!-- PHẦN SẢN PHẨM ĐÃ CHỌN - KẾT THÚC -->

            <!-- PHẦN DANH SÁCH SẢN PHẨM - BẮT ĐẦU -->
            <div class="card">
              <div class="card-header bg-light">Danh sách sản phẩm</div>
              <div class="card-body">
                <div class="row overflow-auto" id="productList">
                  <!-- Sản phẩm 1 -->
                  <div class="col-md-2 mb-3" v-for="product in listProduct" :key="product.id">
                    <div class="card h-100">
                      <img :src="product.images[0]?.url" class="card-img-top" alt="img" style="object-fit: contain; max-height: 100px" />
                      <div class="card-body">
                        <h6 class="card-title">{{ product.productName }}</h6>
                        <p class="card-text fw-bold text-danger" v-if="product.salePrice">{{ product.salePrice }} đ</p>
                        <p class="card-text fw-bold text-danger" v-else>{{ product.price }} đ</p>
                        <p class="card-text small">Còn lại: {{ product.stockQuantity }}</p>
                        <button type="button" class="btn btn-primary w-100 add-product-btn" @click="addToCart(product)">Thêm</button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- 
        <div class="row p-3">
          <div class="col-md-12">
            <div class="card">
              <div class="card-header bg-light">
                <h6 class="mb-0">Tổng quan đơn hàng</h6>
              </div>
              <div class="card-body">
                <div class="d-flex justify-content-between mb-2">
                  <span>Tổng tiền sản phẩm:</span>
                  <span id="totalProductAmount">0 ₫</span>
                </div>
                <div class="d-flex justify-content-between mb-2">
                  <span>Phí vận chuyển:</span>
                  <span id="shippingFee">0 ₫</span>
                </div>
                <div class="d-flex justify-content-between mb-2">
                  <div>
                    <span>Giảm giá:</span>
                    <button type="button" class="btn btn-sm btn-link p-0 ms-1" data-bs-toggle="modal" data-bs-target="#discountModal">Thêm mã</button>
                  </div>
                  <span id="discountAmount">0 ₫</span>
                </div>
                <hr />
                <div class="d-flex justify-content-between fw-bold">
                  <span>Tổng thanh toán:</span>
                  <span id="totalAmount">0 ₫</span>
                </div>
              </div>
            </div>
          </div>
        </div> -->
        <button class="btn btn-success" @click="saveOrder">{{ isEdit ? "Cập nhật" : "Tạo mới" }}</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { computed, onBeforeMount, onMounted, ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";

// khai báo biến
const listOrderStatus = ref([]);
const listCustomer = ref([]);
const listAddress = ref([]);
const listProduct = ref([]);
const cart = ref([]);
const formOrder = ref({
  user: "",
  totalAmount: "",
  address: "",
  orderStatus: 1,
});
const router = useRouter();
const route = useRoute();
const isEdit = ref(false);
// methods
const getAllCustomer = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/user/List");
    listCustomer.value = Array.from(resp.data.result).filter((user) => user.role === false);
    console.log(listCustomer.value);
  } catch (error) {
    console.log(error.message);
  }
};

const getAddressByUser = async () => {
  try {
    const userId = formOrder.value.user;
    console.log(userId);
    if (!userId) return;

    const resp = await axios.get("http://localhost:8080/asm/api/v1/address/" + userId);
    listAddress.value = resp.data.result.data;
    console.log("addrss", listAddress.value);
  } catch (error) {
    console.log(error.message);
  }
};
const getAllProduct = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/product/List");
    listProduct.value = resp.data.result;
    console.log("product", listProduct.value);
  } catch (error) {
    console.log(error.message);
  }
};

const getOrderDetailById = async (idOrder) => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/orders-detail/GetByOrderId/" + idOrder);
    cart.value = resp.data.result.map((item) => {
      const matchingProduct = listProduct.value.find((product) => product.productName === item.productName);
      return {
        ...item,
        productId: matchingProduct ? matchingProduct.id : null,
        isDelete: false,
        isCreate: false,
      };
    });
  } catch (error) {
    console.log(error.message);
  }
};
const getOrderById = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/orders/" + route.params.idOrder);
    formOrder.value = {
      id: resp.data.result.id,
      user: resp.data.result.user,
      totalAmount: resp.data.result.totalAmount,
      address: resp.data.result.address,
      orderStatus: resp.data.result.orderStatus,
      orderDetails: cart.value,
    };
    console.log("order by id", formOrder.value);
  } catch (error) {
    console.log(error.message);
  }
};
const getAllOrderStatus = async () => {
  try {
    const resp = await axios.get("http://localhost:8080/asm/api/v1/order-status/List");
    listOrderStatus.value = resp.data.result.map((item) => ({
      id: item.id,
      statusName: item.statusName,
    }));
    console.log("order status", listOrderStatus.value);
  } catch (error) {
    console.log(error.message);
  }
};

const checkQuantity = (item, stockQuantity) => {
  if (item.quantity > stockQuantity) {
    alert(`Chỉ còn ${stockQuantity} sản phẩm trong kho`);
    item.quantity = stockQuantity;
  }

  if (item.quantity < 1 || !item.quantity) {
    item.quantity = 1;
  }
};

// get

// create
const addToCart = (product) => {
  const index = cart.value.findIndex((item) => item.productId === product.id);

  if (product.stockQuantity === 0) {
    alert("Không còn hàng hóa nây trong kho");
    return;
  }

  if (index !== -1) {
    cart.value[index].quantity += 1;
    cart.value[index].isDelete = false;
    checkQuantity(cart.value[index], product.stockQuantity);
    console.log("cart", cart.value);
    return;
  }

  cart.value.push({
    currentPrice: product.price ? product.price : product.salePrice,
    productId: product.id,
    productName: product.productName,
    quantity: 1,
    stockQuantity: product.stockQuantity,
    isDelete: false,
    isCreate: isEdit.value ? true : false,
    orderId: isEdit.value ? route.params.idOrder : 0,
  });
  console.log("cart", cart.value);
};

const removeFromCart = (product) => {
  const index = cart.value.findIndex((item) => item.productId === product.productId);
  if (index === -1) {
    console.log("not found product");
    return;
  }
  cart.value[index].isDelete = true;
  cart.value[index].quantity = 0;
};
const validateInput = () => {
  if (!formOrder.value.user) {
    alert("Vui lòng chọn khách hàng");
    return false;
  }

  if (!formOrder.value.address) {
    alert("Vui lòng chọn địa chỉ giao hàng");
    return false;
  }

  const validProducts = cart.value.filter((item) => !item.isDelete);
  if (validProducts.length === 0) {
    alert("Vui lòng thêm ít nhất một sản phẩm vào đơn hàng");
    return false;
  }

  return true;
};

const saveOrder = async () => {
  let resp;
  try {
    if (!validateInput()) return;

    if (!isEdit.value) {
      const newOrder = {
        user: formOrder.value.user,
        totalAmount: totalAmount.value,
        address: formOrder.value.address,
        orderStatus: 1,
        orderDetails: cart.value.filter((item) => !item.isDelete),
      };
      console.log(newOrder);
      resp = await axios.post("http://localhost:8080/asm/api/v1/orders/", newOrder);
      if (resp.data.result) {
        alert("Tạo thành công");
        router.replace("/admin/orders/");
      }
    } else {
      const matchedStatus = listOrderStatus.value.find((item) => item.statusName === formOrder.value.orderStatus);
      if (matchedStatus.id != 1) {
        alert("Đơn đã được xác nhận nên không có quyền cập nhập");
        return;
      }
      const updateOrder = {
        user: formOrder.value.user,
        totalAmount: totalAmount.value,
        address: formOrder.value.address,
        orderStatus: matchedStatus ? matchedStatus.id : null,
      };
      const resp = await axios.put(`http://localhost:8080/asm/api/v1/orders/${route.params.idOrder}`, updateOrder);
      await updateOrderDetail();
      if (resp.status === 200) {
        alert("Cập nhật thành công");
        window.location.reload();
      }
      console.log(resp);
    }
  } catch (error) {
    console.log(error.message);
  }
};
// update
const updateOrderDetail = async () => {
  try {
    const updates = cart.value.filter((item) => !item.isDelete && item.quantity > 0 && !item.isCreate);
    const deletes = cart.value.filter((item) => item.isDelete && item.quantity === 0);
    const creates = cart.value.filter((item) => item.isCreate && !item.isDelete);
    console.log("updates", updates);
    console.log("deletes", deletes);
    console.log("creates", creates);

    if (creates.length > 0) {
      for (let data of creates) {
        await axios.post("http://localhost:8080/asm/api/v1/orders-detail/", data);
      }
    }

    if (deletes.length > 0) {
      for (let data of deletes) {
        await axios.delete(`http://localhost:8080/asm/api/v1/orders-detail/${data.id}`);
      }
    }

    for (let data of updates) {
      // console.log(data);
      await axios.put(`http://localhost:8080/asm/api/v1/orders-detail/${data.id}`, data);
    }
  } catch (error) {
    console.log(error.message);
  }
};
// delete

// pagination

// search

// computed
const totalAmount = computed(() => {
  return cart.value.filter((g) => !g.isDelete).reduce((total, item) => total + item.currentPrice * item.quantity, 0);
});
// watch
watch(
  () => formOrder.value.user,
  (newUser, oldUser) => {
    if (newUser) {
      getAddressByUser();
    }
  }
);

// mounted
onBeforeMount(async () => {
  await getAllCustomer();
  await getAllProduct();
  if (route.params.idOrder) {
    isEdit.value = true;
    await getAllOrderStatus();
    await getOrderDetailById(route.params.idOrder);
    await getOrderById();
  }
});
onMounted(async () => {
  console.log("da chuyen huojg");
});
</script>
<style lang="scss" scoped></style>

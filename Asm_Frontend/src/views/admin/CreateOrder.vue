<template>
  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">Tạo Đơn Hàng Mới</h1>
    <div class="btn-toolbar mb-2 mb-md-0">
      <a href="order-management.html" class="btn btn-outline-secondary"> <i class="bi bi-arrow-left"></i> Quay lại </a>
    </div>
  </div>

  <div class="card mb-4">
    <div class="card-body">
      <form>
        <div class="row mb-3">
          <div class="col-md-12">
            <h6 class="fw-bold">Thông tin khách hàng</h6>
          </div>
          <div class="col-md-6">
            <div class="mb-3">
              <label for="customerName" class="form-label">Họ tên <span class="text-danger">*</span></label>
              <input type="text" class="form-control" id="customerName" required />
            </div>
            <div class="mb-3">
              <label for="customerEmail" class="form-label">Email</label>
              <input type="email" class="form-control" id="customerEmail" />
            </div>
          </div>
          <div class="col-md-6">
            <div class="mb-3">
              <label for="customerPhone" class="form-label">Số điện thoại <span class="text-danger">*</span></label>
              <input type="tel" class="form-control" id="customerPhone" required />
            </div>
            <div class="mb-3">
              <label for="customerType" class="form-label">Loại khách hàng</label>
              <select class="form-select" id="customerType">
                <option value="new">Khách hàng mới</option>
                <option value="existing">Khách hàng hiện có</option>
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
              <input type="text" class="form-control" id="shippingAddress" required />
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
                      <th width="50"></th>
                    </tr>
                  </thead>
                  <tbody id="selectedProductsTable">
                    <!-- Sản phẩm đã chọn sẽ được thêm vào đây bằng JavaScript -->
                  </tbody>
                  <tfoot>
                    <tr>
                      <td colspan="3" class="text-end">
                        <strong>Tổng tiền</strong>
                      </td>
                      <td>
                        <strong class="text-danger" id="totalAmount">0đ</strong>
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
                <div class="row" id="productList">
                  <!-- Sản phẩm 1 -->
                  <div class="col-md-3 mb-3">
                    <div class="card h-100">
                      <img src="https://via.placeholder.com/150" class="card-img-top" alt="iPhone 14 Pro Max" />
                      <div class="card-body">
                        <h6 class="card-title">iPhone 14 Pro Max</h6>
                        <p class="card-text text-decoration-line-through">28,999,999đ</p>
                        <p class="card-text fw-bold text-danger">10,000,000đ</p>
                        <p class="card-text small">Còn lại: 86</p>
                        <button type="button" class="btn btn-primary w-100 add-product-btn" data-product-id="1" data-product-name="iPhone 14 Pro Max" data-product-price="10000000">Thêm</button>
                      </div>
                    </div>
                  </div>

                  <!-- Sản phẩm 2 -->
                  <div class="col-md-3 mb-3">
                    <div class="card h-100">
                      <img src="https://via.placeholder.com/150" class="card-img-top" alt="Samsung Galaxy S23 Ultra" />
                      <div class="card-body">
                        <h6 class="card-title">Samsung Galaxy S23 Ultra</h6>
                        <p class="card-text fw-bold text-danger">25,990,000đ</p>
                        <p class="card-text small">Còn lại: 85</p>
                        <button type="button" class="btn btn-primary w-100 add-product-btn" data-product-id="2" data-product-name="Samsung Galaxy S23 Ultra" data-product-price="25990000">Thêm</button>
                      </div>
                    </div>
                  </div>

                  <!-- Sản phẩm 3 -->
                  <div class="col-md-3 mb-3">
                    <div class="card h-100">
                      <img src="https://via.placeholder.com/150" class="card-img-top" alt="Xiaomi 13 Pro" />
                      <div class="card-body">
                        <h6 class="card-title">Xiaomi 13 Pro</h6>
                        <p class="card-text fw-bold text-danger">22,990,000đ</p>
                        <p class="card-text small">Còn lại: 96</p>
                        <button type="button" class="btn btn-primary w-100 add-product-btn" data-product-id="3" data-product-name="Xiaomi 13 Pro" data-product-price="22990000">Thêm</button>
                      </div>
                    </div>
                  </div>

                  <!-- Sản phẩm 4 -->
                  <div class="col-md-3 mb-3">
                    <div class="card h-100">
                      <img src="https://via.placeholder.com/150" class="card-img-top" alt="MacBook Pro 14" />
                      <div class="card-body">
                        <h6 class="card-title">MacBook Pro 14</h6>
                        <p class="card-text fw-bold text-danger">45,990,000đ</p>
                        <p class="card-text small">Còn lại: 58</p>
                        <button type="button" class="btn btn-primary w-100 add-product-btn" data-product-id="4" data-product-name="MacBook Pro 14" data-product-price="45990000">Thêm</button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="row p-3">
          <div class="col-md-6">
            <div class="mb-3">
              <label for="paymentMethod" class="form-label">Phương thức thanh toán <span class="text-danger">*</span></label>
              <select class="form-select" id="paymentMethod" required>
                <option value="">Chọn phương thức thanh toán</option>
                <option value="cod">Thanh toán khi nhận hàng</option>
                <option value="bank">Chuyển khoản ngân hàng</option>
                <option value="card">Thẻ tín dụng</option>
                <option value="ewallet">Ví điện tử</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="orderNote" class="form-label">Ghi chú</label>
              <textarea class="form-control" id="orderNote" rows="3"></textarea>
            </div>
          </div>
          <div class="col-md-6">
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
        </div>
        <button class="btn btn-success">Tạo đơn hàng</button>
      </form>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>

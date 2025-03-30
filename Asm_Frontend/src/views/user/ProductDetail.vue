<template>
  <div class="container my-5">
    <div class="row">
      <div class="col-md-6">
        <div
          id="productCarousel"
          class="carousel slide"
          data-bs-ride="carousel"
        >
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img
                src="https://cdn.hoanghamobile.com/i/previewV2/Uploads/2024/12/03/iphone-13-den-1.png"
                class="img-fluid"
                alt="Tai nghe Edifier W830BT"
              />
            </div>
            <div class="carousel-item">
              <img
                src="https://cdn.hoanghamobile.com/i/previewV2/Uploads/2024/12/03/iphone-13-trang-1.png"
                class="img-fluid"
                alt="Tai nghe Edifier W830BT"
              />
            </div>
            <div class="carousel-item">
              <img
                src="https://cdn.hoanghamobile.com/i/previewV2/Uploads/2024/12/03/iphone-13-xanh-duong-1.png"
                class="img-fluid"
                alt="Tai nghe Edifier W830BT"
              />
            </div>
          </div>
        </div>
        <div class="d-flex mt-2">
          <img
            src="https://cdn.hoanghamobile.com/i/previewV2/Uploads/2024/12/03/iphone-13-den-1.png"
            class="img-thumbnail me-2 thumbnail-img"
            alt="thumb1"
          />
          <img
            src="https://cdn.hoanghamobile.com/i/previewV2/Uploads/2024/12/03/iphone-13-trang-1.png"
            class="img-thumbnail me-2 thumbnail-img"
            alt="thumb2"
          />
          <img
            src="https://cdn.hoanghamobile.com/i/previewV2/Uploads/2024/12/03/iphone-13-xanh-duong-1.png"
            class="img-thumbnail me-2 thumbnail-img"
            alt="thumb3"
          />
        </div>
      </div>
      <div class="col-md-6">
        <h3>Tai nghe Edifier W830BT</h3>
        <p><strong>Thương hiệu:</strong> Razer</p>
        <h4 class="text-danger">
          4.350.000₫
          <small class="text-muted text-decoration-line-through"
            >5.220.000₫</small
          >
          <span class="badge bg-danger">-17%</span>
        </h4>

        <div class="mt-5">
          <button class="btn btn-outline-primary w-25 mx-2">
            <i class="bi bi-cart-fill"></i> Thêm vào giỏ
          </button>
          <button class="btn btn-danger w-25 mx-2">Mua ngay</button>
          <button class="btn btn-warning w-25 d-block m-2">
            <i class="bi bi-heart"></i> Yêu thích
          </button>
        </div>
      </div>
    </div>
    <!-- Start Thông số kỹ thuật -->
    <div class="mt-3">
      <h2>Thông số kỹ thuật</h2>
      <div class="container mt-4">
        <div class="accordion" id="specAccordion">
          <div class="accordion-item">
            <h2 class="accordion-header" id="headingConfig">
              <button
                class="accordion-button"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#collapseConfig"
                aria-expanded="true"
                aria-controls="collapseConfig"
              >
                Cấu hình & Bộ nhớ
              </button>
            </h2>
            <div
              id="collapseConfig"
              class="accordion-collapse collapse show"
              aria-labelledby="headingConfig"
              data-bs-parent="#specAccordion"
            >
              <div class="accordion-body">
                <table class="table table-bordered">
                  <tbody>
                    <tr>
                      <th>Chip xử lý</th>
                      <td>Snapdragon 695</td>
                    </tr>
                    <tr>
                      <th>RAM</th>
                      <td>8GB</td>
                    </tr>
                    <tr>
                      <th>Bộ nhớ trong</th>
                      <td>128GB</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

          <div class="accordion-item">
            <h2 class="accordion-header" id="headingCamera">
              <button
                class="accordion-button collapsed"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#collapseCamera"
                aria-expanded="false"
                aria-controls="collapseCamera"
              >
                Camera & Màn hình
              </button>
            </h2>
            <div
              id="collapseCamera"
              class="accordion-collapse collapse"
              aria-labelledby="headingCamera"
              data-bs-parent="#specAccordion"
            >
              <div class="accordion-body">
                <table class="table table-bordered">
                  <tbody>
                    <tr>
                      <th>Camera sau</th>
                      <td>50 MP + 2 MP</td>
                    </tr>
                    <tr>
                      <th>Camera trước</th>
                      <td>16 MP</td>
                    </tr>
                    <tr>
                      <th>Màn hình</th>
                      <td>6.5 inch, Full HD+</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

          <div class="accordion-item">
            <h2 class="accordion-header" id="headingBattery">
              <button
                class="accordion-button collapsed"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#collapseBattery"
                aria-expanded="false"
                aria-controls="collapseBattery"
              >
                Pin & Sạc
              </button>
            </h2>
            <div
              id="collapseBattery"
              class="accordion-collapse collapse"
              aria-labelledby="headingBattery"
              data-bs-parent="#specAccordion"
            >
              <div class="accordion-body">
                <table class="table table-bordered">
                  <tbody>
                    <tr>
                      <th>Dung lượng pin</th>
                      <td>5000 mAh</td>
                    </tr>
                    <tr>
                      <th>Công suất sạc</th>
                      <td>18W</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- End Thông số kỹ thuật -->

    <!-- Start Sản phẩm liên quan -->
    <div class="container mt-4">
      <div class="mt-4">
        <h4>Sản phẩm liên quan</h4>
        <div
          id="relatedProductsCarousel"
          class="carousel slide"
          data-bs-ride="carousel"
        >
          <div class="carousel-inner">
            <div class="carousel-item active">
              <div class="d-flex justify-content-around">
                <div class="product-card">
                  <img
                    src="http://localhost:5173/src/assets/img/ip15xanh.jpeg"
                    class="img-fluid"
                  />
                  <p>Sản phẩm 1 - 150.000₫</p>
                </div>
                <div class="product-card">
                  <img
                    src="http://localhost:5173/src/assets/img/ip15xanh.jpeg"
                    class="img-fluid"
                  />
                  <p>Sản phẩm 2 - 250.000₫</p>
                </div>
                <div class="product-card">
                  <img
                    src="http://localhost:5173/src/assets/img/ip15xanh.jpeg"
                    class="img-fluid"
                  />
                  <p>Sản phẩm 3 - 350.000₫</p>
                </div>
                <div class="product-card">
                  <img
                    src="http://localhost:5173/src/assets/img/ip15xanh.jpeg"
                    class="img-fluid"
                  />
                  <p>Sản phẩm 4 - 450.000₫</p>
                </div>
              </div>
            </div>
          </div>
          <button
            class="carousel-control-prev"
            type="button"
            data-bs-target="#relatedProductsCarousel"
            data-bs-slide="prev"
          >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          </button>
          <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#relatedProductsCarousel"
            data-bs-slide="next"
          >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- End Sản phẩm liên quan -->
</template>

<script setup></script>

<style scoped>
.thumbnail-img {
  width: 40px;
  height: 40px;
  object-fit: cover;
}

.carousel-inner {
  display: flex;
  overflow: hidden;
}
.carousel-item {
  display: flex;
  justify-content: space-around;
  flex: 0 0 100%;
}
.product-card {
  width: 200px;
  text-align: center;
}
</style>

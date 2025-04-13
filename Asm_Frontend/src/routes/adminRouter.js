import AdminLayout from "../layouts/admin/AdminLayout.vue";
import CategoryMagement from "../views/admin/CategoryMagement.vue";
import CreateOrder from "../views/admin/CreateOrder.vue";
import CreateProduct from "../views/admin/CreateProduct.vue";
import Dashboard from "../views/admin/Dashboard.vue";
import OrderManagement from "../views/admin/OrderManagement.vue";
import OrderStatusManagement from "../views/admin/OrderStatusManagement.vue";
import ProductManagement from "../views/admin/ProductManagement.vue";
import ProductTypeManagement from "../views/admin/ProductTypeManagement.vue";
import UserManagement from "../views/admin/UserManagement.vue";

const adminRouter = [
  {
    path: "/admin",
    meta: { requiresAuth: true, requiresAdmin: true },
    redirect: "/admin/dashboard",
    component: AdminLayout,
    children: [
      {
        path: "dashboard",
        name: "dashboard",
        component: Dashboard,
      },
      {
        path: "category",
        name: "category-management",
        component: CategoryMagement,
      },
      {
        path: "product-type",
        name: "product-type-management",
        component: ProductTypeManagement,
      },
      {
        path: "orders",
        name: "order-management",
        component: OrderManagement,
      },
      {
        path: "orders/orders-status",
        name: "order-status-management",
        component: OrderStatusManagement,
      },
      {
        path: "orders/form",
        name: "create-order",
        component: CreateOrder,
      },
      {
        path: "orders/form/:idOrder",
        name: "edit-order",
        component: CreateOrder,
      },

      {
        path: "products",
        name: "product-management",
        component: ProductManagement,
      },
      {
        path: "products/form",
        name: "create-product",
        component: CreateProduct,
      },
      {
        path: "products/form/:idProduct",
        name: "edit-product",
        component: CreateProduct,
        // props: true,
      },
      {
        path: "users",
        name: "user-management",
        component: UserManagement,
      },
    ],
  },
];

export default adminRouter;

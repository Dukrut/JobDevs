const routes = [
  {
    path: "/",
    component: () => import("pages/HomePage.vue"),
    meta: { requiresAuth: true },
  },
  {
    path: "/login",
    component: () => import("pages/LoginPage.vue"),
    meta: { guestOnly: true },
  },
  {
    path: "/register",
    component: () => import("pages/RegisterPage.vue"),
    meta: { guestOnly: true },
  },
  {
    path: "/register/wizard",
    component: () => import("pages/WizardPage.vue"),
    meta: { guestOnly: true },
  },
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;

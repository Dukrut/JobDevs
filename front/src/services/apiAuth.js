import axios from "axios";
import { useAuthStore } from "src/stores/auth-store";
console.log("VUE_APP_BASE_API:", process.env.VUE_APP_BASE_API);

const authStore = useAuthStore();
const apiAuth = axios.create({
  baseURL: `${process.env.VUE_APP_BASE_API}/api/`, // Corrija a URL base aqui
  headers: {
    Authorization: `Bearer ${authStore.jwt}`,
    "Content-Type": "application/json",
    common: {
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS",
    },
  },
});

export default apiAuth;

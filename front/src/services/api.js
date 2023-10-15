import axios from "axios";

console.log("VUE_APP_BASE_API:", process.env.VUE_APP_BASE_API);

const api = axios.create({
  baseURL: `${process.env.VUE_APP_BASE_API}/api/`, // Corrija a URL base aqui
  headers: {
    "Content-Type": "application/json",
    common: {
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS",
    },
  },
});

export default api;

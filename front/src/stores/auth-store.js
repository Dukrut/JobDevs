import { defineStore } from "pinia";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    user: {
      id: null,
      email: null,
      name: null,
    },
    jwt: null,
  }),
  actions: {
    setJWt(jwt) {
      this.jwt = jwt;

      const info = this.decodeJWT(this.jwt);

      this.user = {
        id: info.id,
        email: info.email,
        name: info.sub,
      };
    },

    getJWT() {
      return this.jwt;
    },

    decodeJWT(jwt) {
      const base64Url = jwt.split(".")[1];
      const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
      const jsonPayload = decodeURIComponent(
        atob(base64)
          .split("")
          .map(function (c) {
            return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
          })
          .join("")
      );

      return JSON.parse(jsonPayload);
    },
  },

  persist: true,
});

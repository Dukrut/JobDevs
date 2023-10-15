import api from "./api";

const login = async (form) => {
  return await api.post("auth/login", form);
};

export default login;

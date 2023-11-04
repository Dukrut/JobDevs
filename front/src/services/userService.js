import api from "./api";

const register = async (form) => {
  return await api.post("users/wizard", form);
};

export default register;

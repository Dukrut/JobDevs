import api from "./api";

const register = async (form) => {
  return await api.post("/users", form);
};

export default register;

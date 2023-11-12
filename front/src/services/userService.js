import api from "./api";
import apiAuth from "./apiAuth";

export const register = async (form) => {
  return await api.post("users/wizard", form);
};

export const update = async (form) => {
  return await apiAuth.put("users", form);
};


export const get = async (id) => {
  return await apiAuth.get(`/users/${id}`)
}
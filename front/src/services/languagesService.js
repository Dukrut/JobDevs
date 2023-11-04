import api from "./api";

const getLanguages = async () => {
  return await api.get("languages/options");
};

export default getLanguages;

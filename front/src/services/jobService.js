import api from "./apiAuth";

const searchJobs = async () => {
  return await api.get("jobs/search");
};

export default searchJobs;

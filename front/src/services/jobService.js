import apiAuth from "./apiAuth";

const searchJobs = async () => {
  return await apiAuth.get("jobs/search");
};

export default searchJobs;

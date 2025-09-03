import axios from "axios";
import { apiBaseUrl } from "../constants/constants";

const api = axios.create({
  baseURL: apiBaseUrl,
  timeout: 5000,
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true,
});

api.interceptors.response.use(
  (response) => {
    if (response.data !== undefined) {
      return response.data;
    }
    // return response.data;
  },
  (error) => {
    console.log(error);
    return Promise.reject({
      ...error.response.data,
      status: error.response.status,
    });
  }
);

export default api;

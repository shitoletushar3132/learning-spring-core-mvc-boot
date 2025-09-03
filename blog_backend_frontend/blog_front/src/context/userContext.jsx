import { createContext, useContext, useEffect, useState } from "react";
import api from "../api/api";

export const userContext = createContext();

export const UserProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await api.get("/user/profile");
        setUser(response.data);
      } catch (error) {
        setUser(null);
      } finally {
        setLoading(false);
      }
    };
    fetchUser();
  }, []);

  return (
    <userContext.Provider value={{ user, setUser, loading }}>
      {children}
    </userContext.Provider>
  );
};

export const useUser = () => {
  return useContext(userContext);
};

import { createContext, useContext, useState } from "react";

const blogContext = createContext();

export const BlogProvider = ({ children }) => {
  const [blogs, setBlogs] = useState([]);

  return (
    <blogContext.Provider value={{ blogs, setBlogs }}>
      {children}
    </blogContext.Provider>
  );
};

export const useBlogs = () => {
  return useContext(blogContext);
};

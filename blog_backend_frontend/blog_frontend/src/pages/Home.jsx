import React, { useEffect, useState } from "react";
import BlogCard from "../components/BlogCard";
import api from "../api/api";
import { useBlogs } from "../context/BlogContext";

const Home = () => {
  // Blog data array
  const { blogs, setBlogs } = useBlogs();

  useEffect(() => {
    const fetchBlogs = async () => {
      try {
        const response = await api.get("/");
        // if (!response.ok) {
        //   throw new Error("Network response was not ok");
        // }
        console.log(response);
        setBlogs(response.data);
      } catch (error) {
        console.error("Error fetching blogs:", error);
      }
    };
    fetchBlogs();
  }, []);

  return (
    <div className=" p-4 grid gap-6 sm:grid-cols-1 lg:grid-cols-2">
      {blogs?.map((blog, index) => (
        <BlogCard
          key={blog.id}
          imageUrl={blog?.images[0]?.fileName || "default-image.jpg"}
          title={blog.title}
          content={blog.content}
          date={blog.publishedAt}
          to={`/blog/${blog.id}`}
          maxChars={180}
        />
      ))}
    </div>
  );
};

export default Home;

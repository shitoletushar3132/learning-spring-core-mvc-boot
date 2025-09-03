import React, { useEffect, useState } from "react";
import BlogCard from "../components/BlogCard";
import toast from "react-hot-toast";
import api from "../api/api"; // Axios instance
import { useUser } from "../context/userContext";
import { Link } from "react-router-dom";

const Profile = () => {
  const { user } = useUser();
  const [blogs, setBlogs] = useState([]);
  const [loading, setLoading] = useState(true);

  const fetchUserBlogs = async () => {
    setLoading(true);
    try {
      const response = await api.get(`/author/blogs/${user?.id}`);
      setBlogs(response.data || []);
      console.log(response.data);
    } catch (error) {
      console.error("Error fetching user blogs:", error);
      toast.error("Failed to load your blogs.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (user) fetchUserBlogs();
  }, [user]);

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this blog?")) return;

    try {
      await api.delete(`/delete/${id}`);
      toast.success("Blog deleted successfully!");
      setBlogs(blogs.filter((b) => b.id !== id));
    } catch (error) {
      toast.error("Failed to delete blog.");
    }
  };

  if (loading)
    return (
      <div className="flex justify-center items-center h-[70vh] text-gray-600 text-lg">
        Loading...
      </div>
    );

  return (
    <div className="max-w-6xl mx-auto py-8 px-4">
      <h2 className="text-3xl font-bold mb-8 text-gray-800 text-center">
        My Blogs
      </h2>

      {blogs.length === 0 ? (
        <p className="text-center text-gray-500 text-lg">
          You haven't posted any blogs yet.
        </p>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {blogs.map((blog) => (
            <div
              key={blog.id}
              className="relative border border-gray-200 rounded-xl shadow hover:shadow-lg transition hover:scale-[1.02] duration-300 bg-white flex flex-col"
            >
              
              <BlogCard
                key={blog.id}
                imageUrl={blog?.images[0]?.fileName || "default-image.jpg"}
                title={blog.title}
                content={blog.content}
                date={blog.publishedAt}
                to={`/blog/${blog.id}`}
                maxChars={180}
              />

              {/* Edit & Delete Buttons */}
              <div className="absolute top-3 right-3 flex gap-5 z-10">
                <Link
                  to={`/edit-blog/${blog.id}`}
                  className="px-3 py-1 rounded-lg bg-blue-600 text-white text-sm hover:bg-blue-700 shadow"
                >
                  Edit
                </Link>
                <button
                  onClick={() => handleDelete(blog.id)}
                  className="px-3 py-1 rounded-lg bg-red-600 text-white text-sm hover:bg-red-700 shadow"
                >
                  Delete
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Profile;

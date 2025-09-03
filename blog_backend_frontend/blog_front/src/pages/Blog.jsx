import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../api/api"; // your axios instance
import toast from "react-hot-toast";
import { apiBaseUrl } from "../constants/constants";

const BlogDetail = () => {
  const { id } = useParams();
  const [blog, setBlog] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchBlog = async () => {
      try {
        const response = await api.get(`/${id}`);
        setBlog(response.data);
      } catch (err) {
        toast.error("Failed to load blog");
        console.error(err);
      } finally {
        setLoading(false);
      }
    };
    fetchBlog();
  }, [id]);

  if (loading) {
    return (
      <div className="flex justify-center items-center h-screen">
        <p className="text-xl font-semibold">Loading...</p>
      </div>
    );
  }

  if (!blog) {
    return (
      <div className="flex justify-center items-center h-screen">
        <p className="text-xl font-semibold text-red-500">Blog not found</p>
      </div>
    );
  }

  return (
    <div className="max-w-4xl mx-auto p-6">
      {/* Image */}
      {blog.images && blog.images.length > 0 && (
        <div className="grid grid-cols-1 sm:grid-cols-2 gap-4 mb-6">
          {blog.images.map((img) => (
            <div
              key={img.id}
              className="relative w-full h-64 rounded-xl overflow-hidden shadow-lg"
            >
              <img
                src={apiBaseUrl + `/uploads/${img?.fileName}`} // assuming your static resource handler serves /uploads/
                alt={blog?.title}
                className="w-full h-full object-cover"
              />
            </div>
          ))}
        </div>
      )}

      {/* Title & Meta */}
      <h1 className="text-4xl font-bold text-gray-900 mb-3">{blog.title}</h1>
      <div className="flex items-center text-gray-500 text-sm mb-6 space-x-4">
        <span>
          By <strong>{blog?.author?.email || "Admin"}</strong>
        </span>
        <span>•</span>
        <span>{new Date(blog?.publishedAt).toLocaleDateString()}</span>
      </div>

      {/* Content */}
      <div className="prose prose-lg prose-indigo max-w-none text-gray-700">
        <p>{blog?.content}</p>
        {/* Example: you can add more paragraphs or HTML content from backend */}
      </div>

      {/* Optional: back button */}
      <div className="mt-8">
        <button
          onClick={() => window.history.back()}
          className="px-5 py-2 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 transition"
        >
          ← Back to Blogs
        </button>
      </div>
    </div>
  );
};

export default BlogDetail;

import React, { useEffect, useState } from "react";
import api from "../api/api";
import toast from "react-hot-toast";
import { useParams } from "react-router-dom";

const CreateBlog = () => {
  const { id } = useParams();

  const [blogData, setBlogData] = useState({ title: "", content: "" });
  const [images, setImages] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (id) {
      api
        .get(`/${id}`)
        .then((res) => {
          setBlogData({ title: res.data.title, content: res.data.content });
          // If your API returns existing images
          if (res.data.images) setImages(res.data.images);
        })
        .catch(() => {
          toast.error("Failed to load blog data");
        });
    }
  }, [id]);

  const handleChange = (e) => {
    setBlogData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleImageChange = (e) => {
    setImages(Array.from(e.target.files)); // store selected files
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!blogData.title.trim() || !blogData.content.trim()) {
      toast.error("Please fill in both fields!");
      return;
    }

    const formData = new FormData();
    formData.append(
      "post",
      new Blob([JSON.stringify(blogData)], { type: "application/json" })
    );
    images.forEach((img) => formData.append("images", img));

    setLoading(true);
    try {
      let response;
      if (id) {
        // Edit mode
        response = await api.patch(`/update/${id}`, formData, {
          headers: { "Content-Type": "multipart/form-data" },
        });
        toast.success(response?.data?.message || "Blog updated successfully!");
      } else {
        // Create mode
        response = await api.post("/add-post", formData, {
          headers: { "Content-Type": "multipart/form-data" },
        });
        toast.success(response?.data?.message || "Blog created successfully!");
        setBlogData({ title: "", content: "" });
        setImages([]);
      }
      console.log(response);
    } catch (error) {
      toast.error(id ? "Failed to update blog" : "Failed to create blog");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-2xl mx-auto p-6 bg-white shadow-md rounded-xl mt-8">
      <h2 className="text-3xl font-bold text-gray-900 mb-6 text-center">
        {id ? "Edit Blog" : "Create a Blog"}
      </h2>

      <form onSubmit={handleSubmit} className="space-y-5">
        {/* Title */}
        <div>
          <label className="block mb-2 font-semibold text-gray-700">
            Title
          </label>
          <input
            type="text"
            name="title"
            required
            value={blogData.title}
            onChange={handleChange}
            placeholder="Enter blog title"
            className="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-green-500 transition"
          />
        </div>

        {/* Content */}
        <div>
          <label className="block mb-2 font-semibold text-gray-700">
            Content
          </label>
          <textarea
            name="content"
            value={blogData.content}
            required
            onChange={handleChange}
            placeholder="Write your blog content..."
            rows={6}
            className="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-green-500 transition"
          />
          <p className="text-right text-sm text-gray-500 mt-1">
            {blogData.content.length} / 2000 characters
          </p>
        </div>

        {/* Images */}
        <div>
          <label className="block mb-2 font-semibold text-gray-700">
            Images
          </label>
          <input
            type="file"
            multiple
            accept="image/*"
            onChange={handleImageChange}
            className="w-full"
          />
          {images.length > 0 && (
            <div className="mt-2 flex gap-2 flex-wrap">
              {images.map((img, idx) => (
                <img
                  key={idx}
                  src={
                    img instanceof File
                      ? URL.createObjectURL(img)
                      : `/uploads/${img.fileName}`
                  }
                  alt={`preview-${idx}`}
                  className="h-20 w-20 object-cover rounded-lg"
                />
              ))}
            </div>
          )}
        </div>

        {/* Submit */}
        <button
          type="submit"
          disabled={loading}
          className={`w-full py-3 rounded-lg text-white font-medium transition-colors ${
            loading
              ? "bg-green-300 cursor-not-allowed"
              : "bg-green-600 hover:bg-green-700"
          }`}
        >
          {loading
            ? id
              ? "Updating..."
              : "Creating..."
            : id
            ? "Update Blog"
            : "Create Blog"}
        </button>
      </form>
    </div>
  );
};

export default CreateBlog;

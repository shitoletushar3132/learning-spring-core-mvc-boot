import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import { UserProvider } from "./context/userContext.jsx";
import Login from "./pages/Login.jsx";
import Register from "./pages/Register.jsx";
import Blog from "./pages/Blog.jsx";
import CreateBlog from "./pages/CreateBlog.jsx";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./pages/Home.jsx";
import { Toaster } from "react-hot-toast";
import ProtectedRoute from "./context/ProtectedRoute.jsx";
import ErrorBoundary from "./components/ErrorBoundary.jsx";
import Profile from "./pages/Profile.jsx";
import { BlogProvider } from "./context/BlogContext.jsx";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <ErrorBoundary>
      <UserProvider>
        <BlogProvider>
          <Toaster />
          <BrowserRouter>
            <Routes>
              <Route path="/" element={<App />}>
                <Route index element={<Home />} />
                <Route path="login" element={<Login />} />
                <Route path="register" element={<Register />} />
                <Route path="blog/:id" element={<Blog />} />
                <Route element={<ProtectedRoute />}>
                  <Route path="create" element={<CreateBlog />} />
                  <Route path="profile" element={<Profile />} />
                  <Route path="edit-blog/:id" element={<CreateBlog />} />
                </Route>
              </Route>
            </Routes>
          </BrowserRouter>
        </BlogProvider>
      </UserProvider>
    </ErrorBoundary>
  </StrictMode>
);

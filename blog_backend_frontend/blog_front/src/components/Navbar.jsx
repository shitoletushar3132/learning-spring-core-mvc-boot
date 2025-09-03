import React, { useEffect, useState } from "react";
import { Search, Edit, Bell, User, Menu, X } from "lucide-react";
import { Link } from "react-router-dom";
import { useUser } from "../context/userContext";
import { useBlogs } from "../context/BlogContext";
import api from "../api/api";

const Navbar = () => {
  const [isSearchOpen, setIsSearchOpen] = useState(false);
  const [query, setQuery] = useState("");
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  const { user } = useUser();
  const { setBlogs } = useBlogs();

  useEffect(() => {
    if (!query.trim()) return;
    const fetchData = async () => {
      try {
        const response = await api.get(`/search?q=${query}`);
        setBlogs(response.data);
      } catch (error) {
        console.error("Error fetching search data:", error);
      }
    };
    fetchData();
  }, [query]);
  return (
    <nav className="w-full bg-white border-b border-gray-200 sticky top-0 z-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
          {/* Left section - Logo and Menu */}
          <div className="flex items-center space-x-4">
            <button
              onClick={() => setIsMobileMenuOpen(!isMobileMenuOpen)}
              className="md:hidden p-2 rounded-md text-gray-600 hover:text-gray-900 hover:bg-gray-100"
            >
              {isMobileMenuOpen ? <X size={20} /> : <Menu size={20} />}
            </button>

            <div className="flex items-center">
              <h1 className="text-2xl font-bold text-black tracking-tight">
                <Link to={"/"}>Thoughts</Link>
              </h1>
            </div>
          </div>

          {/* Center section - Search */}
          <div className="hidden md:flex flex-1 max-w-lg mx-8">
            <div className="relative w-full">
              <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                <Search className="h-4 w-4 text-gray-400" />
              </div>
              <input
                type="text"
                placeholder="Search"
                className="block w-full pl-10 pr-3 py-2 border border-gray-300 rounded-full bg-gray-50 text-sm placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent focus:bg-white"
                value={query}
                onChange={(e) => setQuery(e.target.value)}
              />
            </div>
          </div>

          {/* Right section - Actions */}
          <div className="flex items-center space-x-4">
            {/* Mobile search toggle */}
            <button
              onClick={() => setIsSearchOpen(!isSearchOpen)}
              className="md:hidden p-2 rounded-md text-gray-600 hover:text-gray-900 hover:bg-gray-100"
            >
              <Search size={20} />
            </button>

            {/* Write button */}
            <button className="hidden sm:flex items-center space-x-2 text-gray-600 hover:text-gray-900 transition-colors">
              <Edit size={18} />
              <span className="text-sm font-medium">
                <Link to="create">Write</Link>
              </span>
            </button>

            {/* User profile */}

            <div className="relative">
              <button className="flex items-center space-x-2 p-1 rounded-full hover:bg-gray-100 transition-colors">
                {user ? (
                  <div className="w-8 h-8 bg-gradient-to-r from-green-400 to-green-600 rounded-full flex items-center justify-center">
                    <Link to="profile" aria-label="Profile">
                      <User size={16} className="text-white" />
                    </Link>
                  </div>
                ) : (
                  <Link to="/login">
                    <User size={20} className="text-gray-600" />
                  </Link>
                )}
              </button>
            </div>

            {/* Sign up button */}

            {!user && (
              <button className="hidden sm:block bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded-full text-sm font-medium transition-colors">
                <Link to="/register">Get started</Link>
              </button>
            )}
          </div>
        </div>

        {/* Mobile search bar */}
        {isSearchOpen && (
          <div className="md:hidden py-3 border-t border-gray-200">
            <div className="relative">
              <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                <Search className="h-4 w-4 text-gray-400" />
              </div>
              <input
                type="text"
                placeholder="Search"
                className="block w-full pl-10 pr-3 py-2 border border-gray-300 rounded-full bg-gray-50 text-sm placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent focus:bg-white"
                value={query}
                onChange={(e) => setQuery(e.target.value)}
                autoFocus
              />
            </div>
          </div>
        )}

        {/* Mobile menu */}
        {isMobileMenuOpen && (
          <div className="md:hidden py-3 border-t border-gray-200 bg-white">
            <div className="flex flex-col space-y-3">
              <button className="flex items-center space-x-3 px-3 py-2 text-gray-700 hover:bg-gray-100 rounded-md transition-colors">
                <Edit size={18} />
                <Link to="create">Write</Link>
              </button>
              <button className="flex items-center space-x-3 px-3 py-2 text-gray-700 hover:bg-gray-100 rounded-md transition-colors">
                <User size={18} />
                <Link to={"profile"}>Profile</Link>
              </button>

              <div className="px-3 pt-2">
                {!user && (
                  <button className="w-full bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded-full text-sm font-medium transition-colors">
                    <Link to="/register">Get started</Link>
                  </button>
                )}
              </div>
            </div>
          </div>
        )}
      </div>
    </nav>
  );
};

export default Navbar;

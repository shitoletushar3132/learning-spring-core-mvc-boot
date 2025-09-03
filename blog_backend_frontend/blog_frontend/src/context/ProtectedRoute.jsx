import { Navigate, Outlet } from "react-router-dom";
import { useUser } from "./userContext";

const ProtectedRoute = () => {
  const { user, loading } = useUser(); // get user from context

  // If no user, redirect to login
  if (loading) {
    return <div className="text-center mt-8">Loading...</div>;
  }
  if (!user) {
    return <Navigate to="/login" replace />;
  }

  // If user exists, render child routes
  return <Outlet />;
};

export default ProtectedRoute;

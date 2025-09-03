import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import { Outlet } from "react-router-dom";

function App() {
  return (
    <div className=" bg-white text-gray-800  min-h-screen transition-colors duration-300 flex flex-col">
      <Navbar />
      <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 flex-1 flex flex-col items-center justify-center">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
}

export default App;

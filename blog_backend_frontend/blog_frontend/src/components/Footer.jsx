import React from "react";

function Footer() {
  return (
    <footer className="bg-gray-100  py-4 text-center">
      <p>
        &copy; {new Date().getFullYear()} Your Blog Name. All rights reserved.
      </p>
      <div className="mt-2">
        <a href="#" className=" hover:text-white mx-2">
          Facebook
        </a>
        <a href="#" className=" hover:text-white mx-2">
          Twitter
        </a>
        <a href="#" className=" hover:text-white mx-2">
          Instagram
        </a>
      </div>
    </footer>
  );
}

export default Footer;

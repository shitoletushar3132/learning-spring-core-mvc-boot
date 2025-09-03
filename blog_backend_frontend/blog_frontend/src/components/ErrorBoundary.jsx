import React from "react";

class ErrorBoundary extends React.Component {
  constructor(props) {
    super(props);
    this.state = { hasError: false };
  }

  // This method updates state when an error occurs
  static getDerivedStateFromError(error) {
    return { hasError: true };
  }

  // This method logs the error
  componentDidCatch(error, errorInfo) {
    console.error("Error caught by ErrorBoundary:", error, errorInfo);
  }

  render() {
    if (this.state.hasError) {
      // Fallback UI
      return (
        <div className="p-6 text-center">
          <h2 className="text-2xl font-bold">Something went wrong.</h2>
          <p>Please try again later.</p>
        </div>
      );
    }

    // Render children if no error
    return this.props.children;
  }
}

export default ErrorBoundary;

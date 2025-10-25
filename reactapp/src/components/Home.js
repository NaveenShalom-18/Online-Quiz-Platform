import React from "react";
import "./Home.css";

const Home = ({ onLogin, onSignup }) => {
  return (
    <div className="home-container">
      {/* Top Navbar */}
      <div className="navbar">
        <div className="logo">Quiz Management System</div>
        <div className="nav-buttons">
          <button className="nav-btn login-btn" onClick={onLogin}>
            Login
          </button>
          <button className="nav-btn signup-btn" onClick={onSignup}>
            Sign Up
          </button>
        </div>
      </div>

      {/* Center Content */}
      <div className="center-content">
        <h1 className="hero-title">Create your online quiz</h1>


        <button className="cta-btn" onClick={onLogin}>
          Get Started
        </button>

       
      </div>
    </div>
  );
};

export default Home;

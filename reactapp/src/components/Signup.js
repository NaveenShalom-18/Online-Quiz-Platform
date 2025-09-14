import React, { useState } from "react";
import { createUser } from "../utils/api";
import "./Signup.css";

const Signup = ({ onSignup, onSwitchToLogin }) => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("STUDENT");
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!name || !email || !password) {
      setError("Please fill in all fields");
      return;
    }
    
    if (password.length < 6) {
      setError("Password must be at least 6 characters");
      return;
    }

    try {
      const userData = await createUser({ name, email, password, role });
      if (userData && userData.id) {
        onSignup(userData);
      } else {
        setError("Failed to create account");
      }
    } catch (error) {
      if (error.response?.status === 409) {
        setError("Email already exists");
      } else {
        setError("Signup failed");
      }
    }
  };

  return (
    <div className="signup-container">
      <h2>Sign Up</h2>
      {error && <div className="error">{error}</div>}
      <form onSubmit={handleSubmit}>
        <label>Name:</label>
        <input type="text" value={name} onChange={(e) => setName(e.target.value)} placeholder="Enter your name" autoComplete="off" />

        <label>Email:</label>
        <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Enter your email" />

        <label>Password:</label>
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Enter your password" />

        <label>Role:</label>
        <select value={role} onChange={(e) => setRole(e.target.value)}>
          <option value="STUDENT">Student</option>
          <option value="ADMIN">Admin</option>
        </select>

        <button type="submit">Sign Up</button>
        <button type="button" onClick={onSwitchToLogin}>
          Login
        </button>
      </form>
    </div>
  );
};

export default Signup;

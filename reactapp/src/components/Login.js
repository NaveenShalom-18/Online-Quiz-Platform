import React, { Component } from "react";
import { loginUser } from "../utils/api";
import "./Login.css";

export default class Login extends Component {
  state = { email: "", password: "", error: "" };

  handleChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  };

  handleSubmit = async (e) => {
    e.preventDefault();
    const { email, password } = this.state;
    
    if (!email || !password) {
      this.setState({ error: "Please fill in all fields" });
      return;
    }
    
    try {
      const user = await loginUser(email, password);
      this.props.onLogin(user);
    } catch (error) {
      const errorMessage = error.response?.data || "Login failed. Please check your credentials.";
      this.setState({ error: errorMessage });
    }
  };

  render() {
    const { email, password, error } = this.state;
    return (
      <div className="login-container">
        <h2>Login</h2>
        {error && <div className="error">{error}</div>}
        <form onSubmit={this.handleSubmit}>
          <label>Email:</label>
          <input
            type="email"
            name="email"
            value={email}
            onChange={this.handleChange}
            placeholder="Enter your email"
            autoComplete="off"
          />
          <label>Password:</label>
          <input
            type="password"
            name="password"
            value={password}
            onChange={this.handleChange}
            placeholder="Enter your password"
            autoComplete="off"
          />
          <button type="submit">Login</button>
          <button type="button" onClick={this.props.onSwitchToSignup}>
            Signup
          </button>
        </form>
      </div>
    );
  }
}

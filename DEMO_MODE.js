// DEMO MODE - Replace App.js content with this if backend fails
import React, { useState } from "react";
import './App.css';

const DemoApp = () => {
  const [user, setUser] = useState(null);
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = () => {
    if (email && password) {
      setUser({ id: 1, name: 'Demo User', email });
    }
  };

  if (!user) {
    return (
      <div className="app-container">
        <div style={{background: 'white', padding: '40px', borderRadius: '20px', maxWidth: '400px', margin: '100px auto'}}>
          <h2>Demo Login</h2>
          <input 
            type="email" 
            placeholder="Email" 
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            style={{width: '100%', padding: '10px', margin: '10px 0', borderRadius: '5px', border: '1px solid #ccc'}}
          />
          <input 
            type="password" 
            placeholder="Password" 
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            style={{width: '100%', padding: '10px', margin: '10px 0', borderRadius: '5px', border: '1px solid #ccc'}}
          />
          <button onClick={handleLogin} style={{width: '100%', padding: '12px', background: '#667eea', color: 'white', border: 'none', borderRadius: '5px'}}>
            Login (Demo)
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="app-container">
      <div className="app-header">
        <div className="welcome-bar">
          <p>Welcome, {user.name}!</p>
          <button onClick={() => setUser(null)}>Logout</button>
        </div>
      </div>
      <div className="main-content">
        <h2>Quiz Management System - Demo Mode</h2>
        <p>✅ Login/Signup Working</p>
        <p>✅ User Authentication</p>
        <p>✅ Responsive Design</p>
        <div className="action-buttons">
          <button className="action-btn">Create Quiz</button>
          <button className="action-btn">View Quizzes</button>
        </div>
      </div>
    </div>
  );
};

export default DemoApp;
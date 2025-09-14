# Quiz Management System - Quick Start Guide

## 🚀 How to Run the Application

### Option 1: Use the Automated Script (Recommended)
1. Double-click `start-dev.bat` in the project root
2. Wait for both backend and frontend to start
3. Open your browser to `http://localhost:3000`

### Option 2: Manual Start

#### Start Backend (Spring Boot)
```bash
cd springapp
mvn spring-boot:run
```

#### Start Frontend (React)
```bash
cd reactapp
npm install  # Only needed first time
npm start
```

## 🔧 Troubleshooting Login/Signup Issues

### If you're getting login/signup errors:

1. **Check Backend Connection**: Look for the connection status at the top of the page
   - ✅ Backend Connected = Good to go
   - ❌ Backend Disconnected = Start the backend first

2. **Check Browser Console**: Press F12 and look for error messages

3. **Common Issues**:
   - **"Email already exists"**: Try a different email address
   - **"Invalid credentials"**: Make sure you're using the exact email/password you registered with
   - **Connection errors**: Ensure backend is running on port 8081

## 📝 Test the Application

### Create a Test Account
1. Click "Signup" 
2. Enter:
   - Name: Test User
   - Email: test@example.com
   - Password: password123
3. Click "Sign Up"

### Login
1. Use the same credentials to login
2. Email: test@example.com
3. Password: password123

## 🎯 Application Features

- ✅ User Registration & Login
- ✅ Create Quizzes
- ✅ Add Questions to Quizzes
- ✅ Take Quizzes
- ✅ View Quiz Results

## 🔍 API Endpoints

- Backend: `http://localhost:8081`
- Frontend: `http://localhost:3000`
- H2 Database Console: `http://localhost:8081/h2-console`

## 💾 Database

The application uses H2 in-memory database, so data will be lost when you restart the backend. This is perfect for testing!

## 🆘 Need Help?

If you're still having issues:
1. Check that both applications are running
2. Look at the browser console (F12) for errors
3. Check the backend console for error messages
4. Make sure you're using different email addresses for different accounts
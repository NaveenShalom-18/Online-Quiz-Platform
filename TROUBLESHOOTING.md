# Troubleshooting Guide

## Common Connection Issues and Solutions

### 1. "Failed to fetch" or "Network Error"

**Possible Causes:**
- Backend server is not running
- MySQL database is not running
- Port conflicts
- CORS issues

**Solutions:**
1. **Check if MySQL is running:**
   - Open XAMPP Control Panel and start MySQL
   - Or start MySQL service from Windows Services

2. **Start the backend server:**
   ```bash
   cd springapp
   mvn spring-boot:run
   ```
   - Backend should start on port 8081
   - Check console for any errors

3. **Start the frontend:**
   ```bash
   cd reactapp
   npm start
   ```
   - Frontend should start on port 3000

4. **Use the startup script:**
   - Double-click `start-dev.bat` to start both applications

### 2. Database Connection Issues

**Error:** `Communications link failure`

**Solutions:**
1. Ensure MySQL is running on port 3306
2. Create the database:
   ```sql
   CREATE DATABASE IF NOT EXISTS app_db;
   ```
3. Check MySQL credentials in `application.properties`

### 3. Port Already in Use

**Error:** `Port 8081 is already in use`

**Solutions:**
1. Kill the process using the port:
   ```bash
   netstat -ano | findstr :8081
   taskkill /PID <PID_NUMBER> /F
   ```
2. Or change the port in `application.properties`

### 4. CORS Issues

**Error:** `Access to fetch at 'http://localhost:8081' from origin 'http://localhost:3000' has been blocked by CORS policy`

**Solutions:**
- The CORS configuration has been updated to allow all origins during development
- If issues persist, try clearing browser cache or use incognito mode

### 5. API Endpoints Not Found

**Error:** `404 Not Found`

**Solutions:**
1. Verify the backend is running on port 8081
2. Check the API endpoints:
   - GET http://localhost:8081/api/quizzes
   - POST http://localhost:8081/api/quizzes
3. Test endpoints directly in browser or Postman

### 6. Validation Errors

**Error:** `Title must be between 3 and 100 characters`

**Solutions:**
- Ensure form data meets validation requirements:
  - Title: 3-100 characters
  - Description: Required
  - Time limit: 1-180 minutes

## Quick Test

1. Start both applications
2. Open browser to http://localhost:3000
3. Try creating a quiz with:
   - Title: "Test Quiz"
   - Description: "This is a test quiz"
   - Time Limit: 30

If this works, your connection is properly established!

## Getting Help

If issues persist:
1. Check browser console for JavaScript errors
2. Check Spring Boot console for backend errors
3. Verify MySQL is running and accessible
4. Try restarting all services
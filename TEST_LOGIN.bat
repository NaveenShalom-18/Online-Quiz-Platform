@echo off
echo Testing Login with Debug
echo ========================

echo 1. Creating test user...
curl -X POST http://localhost:8081/api/users -H "Content-Type: application/json" -d "{\"name\":\"Test User\",\"email\":\"test@login.com\",\"password\":\"test123\",\"role\":\"STUDENT\"}"

echo.
echo 2. Testing login with same credentials...
curl -X POST http://localhost:8081/api/users/login -H "Content-Type: application/json" -d "{\"email\":\"test@login.com\",\"password\":\"test123\"}"

echo.
echo Check backend console for debug messages
pause
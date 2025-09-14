@echo off
echo Testing Login and Signup functionality...
echo.

echo Starting backend...
start "Backend Test" cmd /k "cd /d springapp && mvn spring-boot:run"

echo Waiting for backend to start...
timeout /t 15 /nobreak > nul

echo.
echo Testing API endpoints...

echo Testing user creation...
curl -X POST http://localhost:8081/api/users ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"Test User\",\"email\":\"test@example.com\",\"password\":\"password123\",\"role\":\"STUDENT\"}"

echo.
echo.
echo Testing login...
curl -X POST http://localhost:8081/api/users/login ^
  -H "Content-Type: application/json" ^
  -d "{\"email\":\"test@example.com\",\"password\":\"password123\"}"

echo.
echo.
echo Starting frontend...
start "Frontend Test" cmd /k "cd /d reactapp && npm start"

echo.
echo Test complete! 
echo - Backend: http://localhost:8081
echo - Frontend: http://localhost:3000
echo - Try logging in with: test@example.com / password123
echo.
pause
@echo off
echo Setting up MySQL Database and Running Application
echo =================================================

echo Creating database...
mysql -u root -proot -e "CREATE DATABASE IF NOT EXISTS app_db;"

echo Database created. Starting backend...
cd springapp
start "Backend" cmd /k "mvnw.cmd clean spring-boot:run"

echo Waiting for backend...
timeout /t 20 /nobreak > nul

echo Creating test user...
curl -X POST http://localhost:8081/api/users -H "Content-Type: application/json" -d "{\"name\":\"Test User\",\"email\":\"admin@test.com\",\"password\":\"admin123\",\"role\":\"STUDENT\"}"

echo.
echo Testing login...
curl -X POST http://localhost:8081/api/users/login -H "Content-Type: application/json" -d "{\"email\":\"admin@test.com\",\"password\":\"admin123\"}"

echo.
echo Starting frontend...
cd ..\reactapp
start "Frontend" cmd /k "npm start"

echo.
echo READY! Login with: admin@test.com / admin123
pause
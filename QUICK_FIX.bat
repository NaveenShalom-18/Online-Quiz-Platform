@echo off
echo QUICK FIX FOR PROJECT REVIEW
echo =============================

echo 1. Starting Backend...
start "Backend" cmd /k "cd /d springapp && java -jar target/springapp-0.0.1-SNAPSHOT.jar || mvnw.cmd spring-boot:run"

echo 2. Waiting for backend...
timeout /t 10 /nobreak > nul

echo 3. Starting Frontend...
start "Frontend" cmd /k "cd /d reactapp && npm start"

echo 4. Opening browser...
timeout /t 5 /nobreak > nul
start http://localhost:3000

echo READY FOR REVIEW!
echo Backend: http://localhost:8081
echo Frontend: http://localhost:3000
echo Test Account: test@example.com / password123
pause
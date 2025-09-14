@echo off
echo EMERGENCY LOGIN/SIGNUP FIX
echo ==========================

echo Killing any running Java processes...
taskkill /f /im java.exe 2>nul

echo Starting backend...
cd springapp
start "Backend" cmd /k "mvnw.cmd clean spring-boot:run -Dspring-boot.run.jvmArguments=-Dspring.profiles.active=dev"

echo Waiting 15 seconds for backend...
timeout /t 15 /nobreak > nul

echo Testing backend...
curl -X GET http://localhost:8081/api/users
echo.

echo Starting frontend...
cd ..\reactapp
start "Frontend" cmd /k "npm start"

echo DONE! Try login/signup now.
pause
@echo off
echo Starting Quiz Management System...
echo.

echo Checking if MySQL is running...
tasklist /FI "IMAGENAME eq mysqld.exe" 2>NUL | find /I /N "mysqld.exe">NUL
if "%ERRORLEVEL%"=="0" (
    echo MySQL is running.
) else (
    echo WARNING: MySQL doesn't appear to be running. Please start MySQL first.
    echo You can start it from XAMPP Control Panel or MySQL Workbench.
    pause
)

echo.
echo Starting Spring Boot backend...
start "Spring Boot Backend" cmd /k "cd /d springapp && mvn spring-boot:run"

echo Waiting for backend to start...
timeout /t 10 /nobreak > nul

echo.
echo Starting React frontend...
start "React Frontend" cmd /k "cd /d reactapp && npm start"

echo.
echo Both applications are starting...
echo Backend will be available at: http://localhost:8081
echo Frontend will be available at: http://localhost:3000
echo.
echo Press any key to exit this script (applications will continue running)
pause > nul
@echo off
echo Checking if backend is running on port 8081...
netstat -an | find "8081"
if %errorlevel%==0 (
    echo Backend is running on port 8081
) else (
    echo Backend is NOT running on port 8081
    echo Starting backend...
    cd springapp
    start "Backend" mvnw.cmd spring-boot:run
)
pause
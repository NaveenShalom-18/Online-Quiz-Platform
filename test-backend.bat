@echo off
echo Testing backend connection...
curl -X GET http://localhost:8081/api/quizzes
echo.
echo If you see JSON data above, backend is working.
echo If you see connection error, backend is not running.
pause
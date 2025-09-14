@echo off
echo Testing backend connection...
echo.
echo Testing basic connection:
curl -X GET http://localhost:8081/api/test
echo.
echo.
echo Testing quizzes endpoint:
curl -X GET http://localhost:8081/api/quizzes
echo.
echo.
echo If you see responses above, backend is working.
echo If you see connection errors, backend is not running.
pause
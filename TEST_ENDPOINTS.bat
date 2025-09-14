@echo off
echo Testing all endpoints...

echo 1. Testing user creation...
curl -X POST http://localhost:8081/api/users -H "Content-Type: application/json" -d "{\"name\":\"Test User\",\"email\":\"test@test.com\",\"password\":\"123456\",\"role\":\"STUDENT\"}"

echo.
echo 2. Testing login...
curl -X POST http://localhost:8081/api/users/login -H "Content-Type: application/json" -d "{\"email\":\"test@test.com\",\"password\":\"123456\"}"

echo.
echo 3. Testing quiz fetch...
curl -X GET http://localhost:8081/api/quizzes

echo.
echo 4. Testing quiz creation...
curl -X POST http://localhost:8081/api/quizzes -H "Content-Type: application/json" -d "{\"title\":\"Test Quiz\",\"description\":\"Test Description\",\"timeLimit\":10}"

pause
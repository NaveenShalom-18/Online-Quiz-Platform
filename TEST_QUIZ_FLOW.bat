@echo off
echo Testing Complete Quiz Flow
echo =========================

echo 1. Creating a quiz...
curl -X POST http://localhost:8081/api/quizzes -H "Content-Type: application/json" -d "{\"title\":\"Test Quiz\",\"description\":\"Test Description\",\"timeLimit\":10}"

echo.
echo 2. Adding question to quiz ID 1...
curl -X POST http://localhost:8081/api/quizzes/1/questions -H "Content-Type: application/json" -d "{\"questionText\":\"What is 2+2?\",\"questionType\":\"MULTIPLE_CHOICE\",\"options\":[{\"optionText\":\"3\",\"isCorrect\":false},{\"optionText\":\"4\",\"isCorrect\":true},{\"optionText\":\"5\",\"isCorrect\":false}]}"

echo.
echo 3. Getting quiz with questions...
curl -X GET http://localhost:8081/api/quizzes/1

pause
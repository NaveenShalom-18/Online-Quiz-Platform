@echo off
echo Setting up MySQL Database...

echo Creating database...
mysql -u root -proot -e "CREATE DATABASE IF NOT EXISTS quizdb;"

echo Starting backend with MySQL...
cd springapp
mvnw.cmd clean spring-boot:run

pause
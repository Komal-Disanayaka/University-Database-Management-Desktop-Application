@echo off
echo Setting JAVA_HOME...
set JAVA_HOME=C:\Program Files\Java\jdk-24

echo Compiling and running the application...
call mvnw clean javafx:run

pause 
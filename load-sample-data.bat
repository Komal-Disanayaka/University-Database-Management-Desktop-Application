@echo off
echo Setting JAVA_HOME...
set JAVA_HOME=C:\Program Files\Java\jdk-24

echo Loading sample data into database...
call mvnw exec:java -Dexec.mainClass="com.example.java_web_app.SampleDataLoader"

pause 
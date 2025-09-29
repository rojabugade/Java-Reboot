@echo off
REM Set JAVA_HOME to Java 21 and run Maven with provided arguments
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-21.0.3.9-hotspot
set PATH=%JAVA_HOME%\bin;%PATH%
mvnw.cmd %*
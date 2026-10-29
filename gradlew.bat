@echo off
setlocal
set DIR=%~dp0
set WRAPPER_JAR=%DIR%gradle\wrapper\gradle-wrapper.jar
if not exist "%WRAPPER_JAR%" (
    echo Downloading Gradle wrapper…
    powershell -Command "Invoke-WebRequest -Uri https://services.gradle.org/distributions/gradle-8.5-bin.zip -OutFile wrapper.zip"
    powershell -Command "Expand-Archive -Path wrapper.zip -DestinationPath %DIR%gradle\wrapper -Force"
    del wrapper.zip
)
java -classpath "%WRAPPER_JAR%" org.gradle.wrapper.GradleWrapperMain %*

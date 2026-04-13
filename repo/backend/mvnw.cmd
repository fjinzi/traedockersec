@echo off
rem Maven wrapper script for Windows

set MAVEN_PROJECTBASEDIR=%~dp0
set MAVEN_WRAPPER_PROPERTIES_PATH=%MAVEN_PROJECTBASEDIR%.mvn\wrapper\maven-wrapper.properties
set MAVEN_WRAPPER_JAR_PATH=%MAVEN_PROJECTBASEDIR%.mvn\wrapper\maven-wrapper.jar

if not exist "%MAVEN_WRAPPER_JAR_PATH%" (
    echo Downloading Maven Wrapper...
    powershell -Command "(New-Object Net.WebClient).DownloadFile('https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar', '%MAVEN_WRAPPER_JAR_PATH%')"
)

java -cp "%MAVEN_WRAPPER_JAR_PATH%" org.apache.maven.wrapper.MavenWrapperMain %*

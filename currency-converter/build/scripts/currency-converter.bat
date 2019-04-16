@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      http://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  currency-converter startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and CURRENCY_CONVERTER_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\currency-converter-1.0-SNAPSHOT.jar;%APP_HOME%\lib\aspectjrt-1.9.3.jar;%APP_HOME%\lib\spring-web-5.1.2.RELEASE.jar;%APP_HOME%\lib\spring-data-redis-2.1.2.RELEASE.jar;%APP_HOME%\lib\spring-oxm-5.1.2.RELEASE.jar;%APP_HOME%\lib\spring-data-keyvalue-2.1.2.RELEASE.jar;%APP_HOME%\lib\spring-tx-5.1.2.RELEASE.jar;%APP_HOME%\lib\spring-context-support-5.1.2.RELEASE.jar;%APP_HOME%\lib\spring-context-5.1.2.RELEASE.jar;%APP_HOME%\lib\spring-aop-5.1.2.RELEASE.jar;%APP_HOME%\lib\spring-data-commons-2.1.2.RELEASE.jar;%APP_HOME%\lib\spring-beans-5.1.2.RELEASE.jar;%APP_HOME%\lib\spring-expression-5.1.2.RELEASE.jar;%APP_HOME%\lib\spring-core-5.1.2.RELEASE.jar;%APP_HOME%\lib\spring-aspects-5.1.2.RELEASE.jar;%APP_HOME%\lib\rxjava-2.0.8.jar;%APP_HOME%\lib\jedis-2.9.0.jar;%APP_HOME%\lib\jackson-databind-2.9.7.jar;%APP_HOME%\lib\jackson-core-2.9.7.jar;%APP_HOME%\lib\jaxb-runtime-2.3.0.jar;%APP_HOME%\lib\jaxb-core-2.3.0.jar;%APP_HOME%\lib\jaxb-api-2.3.1.jar;%APP_HOME%\lib\activation-1.1.1.jar;%APP_HOME%\lib\spring-jcl-5.1.2.RELEASE.jar;%APP_HOME%\lib\aspectjweaver-1.9.2.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\reactive-streams-1.0.0.jar;%APP_HOME%\lib\commons-pool2-2.4.2.jar;%APP_HOME%\lib\jackson-annotations-2.9.0.jar;%APP_HOME%\lib\javax.activation-api-1.2.0.jar;%APP_HOME%\lib\stax-ex-1.7.8.jar;%APP_HOME%\lib\FastInfoset-1.2.13.jar;%APP_HOME%\lib\txw2-2.3.0.jar;%APP_HOME%\lib\istack-commons-runtime-3.0.5.jar

@rem Execute currency-converter
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %CURRENCY_CONVERTER_OPTS%  -classpath "%CLASSPATH%" org.apilytic.currency.HelloWorld %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable CURRENCY_CONVERTER_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%CURRENCY_CONVERTER_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega

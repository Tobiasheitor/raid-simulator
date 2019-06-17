@echo off
set source=%1
set destination=%2

if not exist %source% echo Invalid directory.

if not exist %destination% mkdir %destination%

:while
robocopy %source% %destination% /MIR
echo. 
echo Finished copying %source% to %destination%
echo.
sleep 5
goto :while
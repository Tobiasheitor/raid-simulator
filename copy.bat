@echo off
set source=%1
set destination=%2

if not exist %source% echo Invalid directory.

if not exist %destination% mkdir %destination%

:while
rm -r %destination%
xcopy %source%* %destination% /s/y/c/v
echo. 
echo Finished copying %source% to %destination%
echo.
echo Created %destination%
sleep 5
goto :while
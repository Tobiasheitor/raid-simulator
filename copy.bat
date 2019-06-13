@echo off
set source=%1
set destination=%2

if not exist %source% echo Invalid directory.

if not exist %destination% mkdir %destination%

xcopy %source%* %destination% /s/y/c/v/r
echo. 
echo Finished copying %source% to %destination%
echo.
echo Created %destination%
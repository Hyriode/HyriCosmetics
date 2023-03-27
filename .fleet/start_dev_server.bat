del %1\plugins\%3.jar
echo F | xcopy %2 %1\plugins\%3.jar
cd %1
call %1\start.bat
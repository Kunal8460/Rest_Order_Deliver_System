@echo off
echo Starting application..
start npm run dev

echo Waiting for application to start...
timeout /t 3

echo Opening browser...
start http://localhost:5173

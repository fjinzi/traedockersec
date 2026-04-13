@echo off
echo ========================================
echo   用户管理系统 - 启动脚本
echo ========================================
echo.

echo [1/2] 正在启动后端服务...
cd backend
start "后端服务" cmd /k "java -jar target\user-management-1.0.0.jar"
echo 后端服务启动中，请等待 10 秒...
timeout /t 10 /nobreak >nul

echo.
echo [2/2] 正在启动前端服务...
cd ..\frontend
start "前端服务" cmd /k "python -m http.server 3000"
echo.
echo ========================================
echo   服务启动完成！
echo ========================================
echo.
echo 前端地址：http://localhost:3000
echo 后端地址：http://localhost:8080/api
echo.
echo 测试账号：
echo   admin / 123456 (管理员)
echo   zhangsan / 123456 (普通用户)
echo   wangwu / 123456 (已禁用)
echo.
echo 按任意键退出此窗口...
pause >nul

<template>
  <div class="login-container">
    <div class="background-decoration">
      <div class="circle circle-1"></div>
      <div class="circle circle-2"></div>
      <div class="circle circle-3"></div>
    </div>
    
    <div class="login-wrapper">
      <div class="brand-section">
        <div class="brand-logo">
          <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M12 2L2 7l10 5 10-5-10-5zM2 17l10 5 10-5M2 12l10 5 10-5"/>
          </svg>
        </div>
        <h1 class="brand-title">用户管理系统</h1>
        <p class="brand-subtitle">基于 Spring Boot 3 + Hibernate 的现代化用户管理平台</p>
        <div class="features-list">
          <div class="feature-item">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"/>
              <polyline points="22 4 12 14.01 9 11.01"/>
            </svg>
            <span>安全可靠的认证系统</span>
          </div>
          <div class="feature-item">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/>
              <circle cx="9" cy="7" r="4"/>
            </svg>
            <span>完善的用户权限管理</span>
          </div>
          <div class="feature-item">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"/>
            </svg>
            <span>实时数据分析仪表盘</span>
          </div>
        </div>
      </div>

      <div class="login-card">
        <div class="card-header">
          <h2>欢迎回来</h2>
          <p>请登录您的账户以继续</p>
        </div>

        <el-form
          ref="loginFormRef"
          :model="loginForm"
          :rules="rules"
          class="login-form"
        >
          <el-form-item prop="username">
            <div class="input-wrapper">
              <span class="input-icon">
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
                  <circle cx="12" cy="7" r="4"/>
                </svg>
              </span>
              <el-input
                v-model="loginForm.username"
                placeholder="用户名"
                size="large"
                clearable
                class="custom-input"
              />
            </div>
          </el-form-item>

          <el-form-item prop="password">
            <div class="input-wrapper">
              <span class="input-icon">
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="3" y="11" width="18" height="11" rx="2" ry="2"/>
                  <path d="M7 11V7a5 5 0 0 1 10 0v4"/>
                </svg>
              </span>
              <el-input
                v-model="loginForm.password"
                type="password"
                placeholder="密码"
                size="large"
                show-password
                @keyup.enter="handleLogin"
                class="custom-input"
              />
            </div>
          </el-form-item>

          <div class="form-options">
            <el-checkbox v-model="rememberMe">记住我</el-checkbox>
            <a href="#" class="forgot-link">忘记密码？</a>
          </div>

          <el-form-item>
            <el-button
              type="primary"
              size="large"
              :loading="loading"
              class="login-btn"
              @click="handleLogin"
            >
              <svg v-if="!loading" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/>
                <polyline points="16 17 21 12 16 7"/>
                <line x1="21" y1="12" x2="9" y2="12"/>
              </svg>
              登 录
            </el-button>
          </el-form-item>
        </el-form>

        <div class="test-accounts-panel">
          <div class="panel-title">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <circle cx="12" cy="12" r="10"/>
              <polyline points="12 6 12 12 16 14"/>
            </svg>
            <span>测试账号</span>
          </div>
          <div class="accounts-grid">
            <div class="account-item">
              <span class="account-role admin">管理员</span>
              <span class="account-credential">admin / 123456</span>
            </div>
            <div class="account-item">
              <span class="account-role user">普通用户</span>
              <span class="account-credential">zhangsan / 123456</span>
            </div>
            <div class="account-item">
              <span class="account-role disabled">已禁用</span>
              <span class="account-credential">wangwu / 123456</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage, type FormInstance, type FormRules } from 'element-plus';
import { authApi } from '@/services/api';
import type { LoginRequest } from '@/types';

const router = useRouter();
const loginFormRef = ref<FormInstance>();
const loading = ref(false);
const rememberMe = ref(false);

const loginForm = reactive<LoginRequest>({
  username: '',
  password: '',
});

const rules: FormRules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
  ],
};

const handleLogin = async () => {
  if (!loginFormRef.value) return;
  
  await loginFormRef.value.validate(async (valid) => {
    if (valid) {
      loading.value = true;
      try {
        const response = await authApi.login(loginForm);
        
        if (response.success) {
          localStorage.setItem('user', JSON.stringify(response.user));
          ElMessage.success('登录成功，欢迎回来！');
          router.push('/home');
        } else {
          ElMessage.error(response.message);
        }
      } catch (error) {
        ElMessage.error('登录失败，请检查网络连接');
      } finally {
        loading.value = false;
      }
    }
  });
};
</script>

<style scoped>
.login-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #1e293b 0%, #0f172a 50%, #1e293b 100%);
  position: relative;
  overflow: hidden;
}

.background-decoration {
  position: absolute;
  inset: 0;
  overflow: hidden;
}

.circle {
  position: absolute;
  border-radius: 50%;
  opacity: 0.1;
}

.circle-1 {
  width: 600px;
  height: 600px;
  background: linear-gradient(135deg, #409eff, #67c23a);
  top: -200px;
  left: -200px;
  animation: float 20s ease-in-out infinite;
}

.circle-2 {
  width: 400px;
  height: 400px;
  background: linear-gradient(135deg, #67c23a, #e6a23c);
  bottom: -100px;
  right: -100px;
  animation: float 25s ease-in-out infinite reverse;
}

.circle-3 {
  width: 300px;
  height: 300px;
  background: linear-gradient(135deg, #f56c6c, #409eff);
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  animation: float 30s ease-in-out infinite;
}

@keyframes float {
  0%, 100% { transform: translate(0, 0) scale(1); }
  33% { transform: translate(30px, -30px) scale(1.05); }
  66% { transform: translate(-20px, 20px) scale(0.95); }
}

.login-wrapper {
  position: relative;
  z-index: 1;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 60px;
  max-width: 1100px;
  width: 90%;
  align-items: center;
}

.brand-section {
  color: white;
}

.brand-logo {
  width: 72px;
  height: 72px;
  background: linear-gradient(135deg, #409eff 0%, #67c23a 100%);
  border-radius: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 24px;
  box-shadow: 0 8px 32px rgba(64, 158, 255, 0.4);
}

.brand-logo svg {
  width: 40px;
  height: 40px;
  color: white;
}

.brand-title {
  font-size: 42px;
  font-weight: 700;
  margin: 0 0 12px 0;
  background: linear-gradient(90deg, #ffffff, #94a3b8);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.brand-subtitle {
  color: rgba(255, 255, 255, 0.7);
  font-size: 16px;
  margin: 0 0 40px 0;
  line-height: 1.6;
}

.features-list {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.feature-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px 16px;
  background: rgba(255, 255, 255, 0.05);
  border-radius: 10px;
  border: 1px solid rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
}

.feature-item svg {
  width: 20px;
  height: 20px;
  color: #409eff;
}

.login-card {
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(20px);
  border-radius: 24px;
  padding: 40px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
  border: 1px solid rgba(255, 255, 255, 0.2);
}

.card-header {
  text-align: center;
  margin-bottom: 32px;
}

.card-header h2 {
  font-size: 28px;
  color: #1e293b;
  margin: 0 0 8px 0;
}

.card-header p {
  color: #64748b;
  margin: 0;
}

.login-form {
  margin-bottom: 24px;
}

.input-wrapper {
  position: relative;
}

.input-icon {
  position: absolute;
  left: 16px;
  top: 50%;
  transform: translateY(-50%);
  z-index: 1;
  color: #94a3b8;
  display: flex;
  align-items: center;
}

.input-icon svg {
  width: 20px;
  height: 20px;
}

.custom-input :deep(.el-input__wrapper) {
  padding-left: 48px;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  transition: all 0.3s ease;
}

.custom-input :deep(.el-input__wrapper:hover) {
  box-shadow: 0 4px 12px rgba(64, 158, 255, 0.15);
}

.custom-input :deep(.el-input__wrapper.is-focus) {
  box-shadow: 0 4px 16px rgba(64, 158, 255, 0.25);
}

.form-options {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
}

.forgot-link {
  color: #409eff;
  text-decoration: none;
  font-size: 14px;
  transition: color 0.3s ease;
}

.forgot-link:hover {
  color: #337ecc;
}

.login-btn {
  width: 100%;
  height: 50px;
  border-radius: 12px;
  font-size: 16px;
  font-weight: 600;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  background: linear-gradient(135deg, #409eff 0%, #337ecc 100%);
  border: none;
  box-shadow: 0 4px 16px rgba(64, 158, 255, 0.4);
  transition: all 0.3s ease;
}

.login-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 24px rgba(64, 158, 255, 0.5);
}

.login-btn svg {
  width: 20px;
  height: 20px;
}

.test-accounts-panel {
  background: linear-gradient(135deg, #f8fafc 0%, #f1f5f9 100%);
  border-radius: 16px;
  padding: 20px;
}

.panel-title {
  display: flex;
  align-items: center;
  gap: 8px;
  font-weight: 600;
  color: #475569;
  margin-bottom: 16px;
}

.panel-title svg {
  width: 18px;
  height: 18px;
  color: #409eff;
}

.accounts-grid {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.account-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 14px;
  background: white;
  border-radius: 8px;
  font-size: 13px;
}

.account-role {
  padding: 3px 10px;
  border-radius: 20px;
  font-size: 11px;
  font-weight: 600;
}

.account-role.admin {
  background: rgba(64, 158, 255, 0.1);
  color: #409eff;
}

.account-role.user {
  background: rgba(103, 194, 58, 0.1);
  color: #67c23a;
}

.account-role.disabled {
  background: rgba(245, 108, 108, 0.1);
  color: #f56c6c;
}

.account-credential {
  color: #64748b;
  font-family: 'Consolas', monospace;
}
</style>

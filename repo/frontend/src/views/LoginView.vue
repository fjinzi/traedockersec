<template>
  <div class="login-page">
    <div class="login-box">
      <div class="login-header">
        <div class="brand">
          <div class="logo">
            <el-icon size="28" color="#fff"><UserFilled /></el-icon>
          </div>
          <h1 class="brand-title">用户管理系统</h1>
        </div>
        <p class="brand-desc">高效、安全、便捷的用户管理平台</p>
      </div>

      <div class="login-form-section">
        <h2 class="form-title">欢迎登录</h2>
        <p class="form-subtitle">请使用您的账号密码登录系统</p>

        <el-form
          ref="loginFormRef"
          :model="loginForm"
          :rules="rules"
          class="login-form"
        >
          <el-form-item prop="username">
            <el-input
              v-model="loginForm.username"
              placeholder="请输入用户名"
              size="large"
              clearable
            >
              <template #prefix>
                <el-icon><User /></el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              v-model="loginForm.password"
              type="password"
              placeholder="请输入密码"
              size="large"
              show-password
              @keyup.enter="handleLogin"
            >
              <template #prefix>
                <el-icon><Lock /></el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              size="large"
              :loading="loading"
              class="login-button"
              @click="handleLogin"
            >
              登 录
            </el-button>
          </el-form-item>
        </el-form>
      </div>

      <div class="test-accounts-section">
        <el-divider>
          <span class="divider-text">测试账号</span>
        </el-divider>
        <div class="account-list">
          <div class="account-item">
            <el-tag type="success" effect="plain" size="small">admin / 123456</el-tag>
            <span class="account-role">管理员</span>
          </div>
          <div class="account-item">
            <el-tag type="primary" effect="plain" size="small">zhangsan / 123456</el-tag>
            <span class="account-role">普通用户</span>
          </div>
          <div class="account-item">
            <el-tag type="info" effect="plain" size="small">wangwu / 123456</el-tag>
            <span class="account-role">已禁用</span>
          </div>
        </div>
      </div>

      <div class="login-footer">
        <div class="feature-tags">
          <span class="feature-tag">
            <el-icon size="12"><CircleCheck /></el-icon>
            安全可靠
          </span>
          <span class="feature-tag">
            <el-icon size="12"><CircleCheck /></el-icon>
            简洁直观
          </span>
          <span class="feature-tag">
            <el-icon size="12"><CircleCheck /></el-icon>
            实时响应
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage, type FormInstance, type FormRules } from 'element-plus';
import { User, Lock, UserFilled, CircleCheck } from '@element-plus/icons-vue';
import { authApi } from '@/services/api';
import type { LoginRequest } from '@/types';

const router = useRouter();
const loginFormRef = ref<FormInstance>();
const loading = ref(false);

const loginForm = reactive<LoginRequest>({
  username: '',
  password: '',
});

const rules: FormRules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, message: '用户名至少2个字符', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码至少6个字符', trigger: 'blur' },
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
.login-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 20px;
}

.login-box {
  width: 100%;
  max-width: 440px;
  background: white;
  border-radius: 16px;
  box-shadow: 0 25px 50px -12px rgba(0, 0, 0, 0.25);
  overflow: hidden;
}

.login-header {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 32px 40px;
  text-align: center;
  color: white;
}

.brand {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 12px;
  margin-bottom: 8px;
}

.logo {
  width: 40px;
  height: 40px;
  background: rgba(255, 255, 255, 0.2);
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  backdrop-filter: blur(10px);
}

.brand-title {
  font-size: 24px;
  font-weight: 600;
  margin: 0;
  letter-spacing: 1px;
}

.brand-desc {
  font-size: 13px;
  opacity: 0.9;
  margin: 0;
}

.login-form-section {
  padding: 32px 40px 24px;
}

.form-title {
  font-size: 22px;
  font-weight: 600;
  color: #1a1a2e;
  margin: 0 0 6px 0;
  text-align: center;
}

.form-subtitle {
  font-size: 13px;
  color: #888;
  margin: 0 0 24px 0;
  text-align: center;
}

.login-form {
  margin: 0;
}

.login-form :deep(.el-input__wrapper) {
  border-radius: 8px;
  padding: 4px 12px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.04);
  border: 1px solid #e0e0e0;
  transition: all 0.3s;
}

.login-form :deep(.el-input__wrapper:hover) {
  border-color: #c0c4cc;
}

.login-form :deep(.el-input__wrapper.is-focus) {
  border-color: #667eea;
  box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
}

.login-button {
  width: 100%;
  border-radius: 8px;
  height: 44px;
  font-size: 15px;
  font-weight: 500;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border: none;
  transition: all 0.3s;
  margin-top: 4px;
}

.login-button:hover {
  transform: translateY(-1px);
  box-shadow: 0 6px 16px rgba(102, 126, 234, 0.35);
}

.test-accounts-section {
  padding: 0 40px 24px;
}

.divider-text {
  font-size: 12px;
  color: #aaa;
}

.account-list {
  display: flex;
  flex-direction: column;
  gap: 8px;
  margin-top: 12px;
}

.account-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 8px 12px;
  background: #f8f9fa;
  border-radius: 6px;
  transition: all 0.3s;
}

.account-item:hover {
  background: #f0f2f5;
}

.account-role {
  font-size: 12px;
  color: #888;
}

.login-footer {
  padding: 16px 40px 24px;
  border-top: 1px solid #f0f0f0;
}

.feature-tags {
  display: flex;
  justify-content: center;
  gap: 16px;
  flex-wrap: wrap;
}

.feature-tag {
  display: flex;
  align-items: center;
  gap: 4px;
  font-size: 12px;
  color: #666;
}

@media (max-width: 480px) {
  .login-page {
    padding: 16px;
    background: white;
  }

  .login-box {
    box-shadow: none;
    border-radius: 0;
  }

  .login-header {
    padding: 24px 24px;
  }

  .brand-title {
    font-size: 20px;
  }

  .login-form-section {
    padding: 24px 24px 20px;
  }

  .test-accounts-section {
    padding: 0 24px 20px;
  }

  .login-footer {
    padding: 12px 24px 20px;
  }

  .feature-tags {
    gap: 12px;
  }
}
</style>

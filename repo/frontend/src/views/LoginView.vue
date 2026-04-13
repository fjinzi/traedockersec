<template>
  <div class="login-container">
    <div class="login-box">
      <h1 class="login-title">用户管理系统</h1>
      <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="rules"
        label-width="80px"
      >
        <el-form-item label="用户名" prop="username">
          <el-input
            v-model="loginForm.username"
            placeholder="请输入用户名"
            clearable
          />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            placeholder="请输入密码"
            show-password
            @keyup.enter="handleLogin"
          />
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            :loading="loading"
            style="width: 100%"
            @click="handleLogin"
          >
            登录
          </el-button>
        </el-form-item>
      </el-form>
      <div class="test-accounts">
        <h3>测试账号：</h3>
        <p>admin / 123456 (管理员)</p>
        <p>zhangsan / 123456 (普通用户)</p>
        <p>wangwu / 123456 (已禁用)</p>
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
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.login-box {
  width: 450px;
  padding: 40px;
  background: white;
  border-radius: 10px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
}

.login-title {
  text-align: center;
  color: #333;
  margin-bottom: 30px;
  font-size: 28px;
}

.test-accounts {
  margin-top: 30px;
  padding: 20px;
  background: #f5f7fa;
  border-radius: 5px;
  font-size: 14px;
  color: #666;
}

.test-accounts h3 {
  margin-bottom: 10px;
  color: #333;
}

.test-accounts p {
  margin: 5px 0;
}
</style>

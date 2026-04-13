<template>
  <div class="home-container">
    <div class="header">
      <h1>用户管理系统</h1>
      <div class="user-info">
        <span>欢迎，{{ user?.username }}</span>
        <el-button type="danger" size="small" @click="handleLogout">退出登录</el-button>
      </div>
    </div>
    <div class="content">
      <el-card class="welcome-card">
        <template #header>
          <div class="card-header">
            <span>欢迎使用用户管理系统</span>
          </div>
        </template>
        <div class="welcome-content">
          <el-result icon="success" title="登录成功" sub-title="您已成功登录系统，可以开始使用各项功能">
            <template #extra>
              <el-button type="primary" @click="handleTestApi">测试后端 API</el-button>
            </template>
          </el-result>
          <div v-if="testResult" class="test-result">
            <h3>API 测试结果：</h3>
            <pre>{{ testResult }}</pre>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import type { User } from '@/types';

const router = useRouter();
const user = ref<User | null>(null);
const testResult = ref<string>('');

onMounted(() => {
  const userData = localStorage.getItem('user');
  if (userData) {
    user.value = JSON.parse(userData);
  } else {
    router.push('/');
  }
});

const handleLogout = () => {
  localStorage.removeItem('user');
  ElMessage.success('已退出登录');
  router.push('/');
};

const handleTestApi = async () => {
  try {
    const response = await fetch('/api/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username: 'admin',
        password: '123456',
      }),
    });
    const data = await response.json();
    testResult.value = JSON.stringify(data, null, 2);
    ElMessage.success('API 调用成功');
  } catch (error) {
    testResult.value = 'API 调用失败：' + error;
    ElMessage.error('API 调用失败');
  }
};
</script>

<style scoped>
.home-container {
  min-height: 100vh;
  background: #f5f7fa;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 40px;
  background: white;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.header h1 {
  color: #409eff;
  font-size: 24px;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 15px;
}

.content {
  padding: 40px;
}

.welcome-card {
  max-width: 800px;
  margin: 0 auto;
}

.card-header {
  font-weight: bold;
  font-size: 16px;
}

.welcome-content {
  text-align: center;
}

.test-result {
  margin-top: 20px;
  padding: 20px;
  background: #f5f7fa;
  border-radius: 5px;
  text-align: left;
}

.test-result pre {
  background: #2d2d2d;
  color: #f8f8f2;
  padding: 15px;
  border-radius: 5px;
  overflow-x: auto;
}
</style>

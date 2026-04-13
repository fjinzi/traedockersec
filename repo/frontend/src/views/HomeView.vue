<template>
  <div class="home-layout">
    <aside class="sidebar">
      <div class="sidebar-header">
        <div class="brand">
          <el-icon size="32" color="#667eea"><UserFilled /></el-icon>
          <span class="brand-text">用户管理系统</span>
        </div>
      </div>
      <nav class="sidebar-nav">
        <div class="nav-item active">
          <el-icon size="20"><HomeFilled /></el-icon>
          <span>首页</span>
        </div>
        <div class="nav-item">
          <el-icon size="20"><User /></el-icon>
          <span>用户管理</span>
        </div>
        <div class="nav-item">
          <el-icon size="20"><Setting /></el-icon>
          <span>系统设置</span>
        </div>
      </nav>
      <div class="sidebar-footer">
        <div class="user-profile">
          <el-avatar :size="36" :icon="UserFilled" class="user-avatar" />
          <div class="user-meta">
            <span class="user-name">{{ user?.username || '用户' }}</span>
            <span class="user-role">{{ user?.role || '访客' }}</span>
          </div>
        </div>
        <el-button
          type="danger"
          size="small"
          plain
          class="logout-btn"
          @click="handleLogout"
        >
          <el-icon><SwitchButton /></el-icon>
          退出
        </el-button>
      </div>
    </aside>

    <main class="main-content">
      <header class="page-header">
        <div class="header-top">
          <div class="header-brand">
            <el-icon size="24" color="#667eea"><UserFilled /></el-icon>
            <span class="header-brand-text">用户管理系统</span>
          </div>
          <div class="header-actions">
            <el-button
              type="danger"
              size="small"
              plain
              class="logout-btn-top"
              @click="handleLogout"
            >
              <el-icon><SwitchButton /></el-icon>
              <span>退出登录</span>
            </el-button>
          </div>
        </div>
        <div class="header-bottom">
          <div class="user-info-bar">
            <el-avatar :size="40" :icon="UserFilled" class="user-avatar-large" />
            <div class="user-greeting">
              <h2 class="greeting-title">欢迎回来，{{ user?.username || '用户' }}</h2>
              <p class="greeting-subtitle">{{ user?.role || '访客' }} · {{ currentTime }}</p>
            </div>
          </div>
        </div>
      </header>

      <div class="content-wrapper">
        <div class="welcome-section">
          <div class="welcome-stats">
            <div class="stat-card">
              <div class="stat-icon blue">
                <el-icon size="24" color="#fff"><User /></el-icon>
              </div>
              <div class="stat-info">
                <span class="stat-value">1,234</span>
                <span class="stat-label">总用户数</span>
              </div>
            </div>
            <div class="stat-card">
              <div class="stat-icon green">
                <el-icon size="24" color="#fff"><CircleCheck /></el-icon>
              </div>
              <div class="stat-info">
                <span class="stat-value">98%</span>
                <span class="stat-label">系统正常</span>
              </div>
            </div>
            <div class="stat-card">
              <div class="stat-icon purple">
                <el-icon size="24" color="#fff"><Timer /></el-icon>
              </div>
              <div class="stat-info">
                <span class="stat-value">24h</span>
                <span class="stat-label">运行时间</span>
              </div>
            </div>
          </div>
        </div>

        <div class="dashboard-grid">
          <el-card class="dashboard-card api-test-card" shadow="hover">
            <template #header>
              <div class="card-header">
                <div class="header-title">
                  <el-icon size="20" color="#667eea"><Connection /></el-icon>
                  <span>API 接口测试</span>
                </div>
                <el-tag type="success" effect="plain">在线</el-tag>
              </div>
            </template>
            <div class="api-test-content">
              <p class="test-desc">点击下方按钮测试后端登录接口，验证系统连接状态</p>
              <el-button
                type="primary"
                size="large"
                :loading="testing"
                class="test-btn"
                @click="handleTestApi"
              >
                <el-icon v-if="!testing"><Promotion /></el-icon>
                <span>{{ testing ? '测试中...' : '测试后端 API' }}</span>
              </el-button>
              <div v-if="testResult" class="test-result" :class="{ success: testSuccess, error: !testSuccess }">
                <div class="result-header">
                  <el-icon size="18" v-if="testSuccess"><CircleCheckFilled /></el-icon>
                  <el-icon size="18" v-else><CircleCloseFilled /></el-icon>
                  <span>{{ testSuccess ? '请求成功' : '请求失败' }}</span>
                </div>
                <pre class="result-body">{{ testResult }}</pre>
              </div>
            </div>
          </el-card>

          <el-card class="dashboard-card quick-actions-card" shadow="hover">
            <template #header>
              <div class="card-header">
                <div class="header-title">
                  <el-icon size="20" color="#667eea"><Operation /></el-icon>
                  <span>快捷操作</span>
                </div>
              </div>
            </template>
            <div class="quick-actions">
              <div class="action-card" @click="handleTestApi">
                <div class="action-icon blue">
                  <el-icon size="24" color="#fff"><Connection /></el-icon>
                </div>
                <span class="action-name">测试连接</span>
              </div>
              <div class="action-card" @click="showProfile">
                <div class="action-icon green">
                  <el-icon size="24" color="#fff"><User /></el-icon>
                </div>
                <span class="action-name">个人资料</span>
              </div>
              <div class="action-card" @click="showSettings">
                <div class="action-icon purple">
                  <el-icon size="24" color="#fff"><Setting /></el-icon>
                </div>
                <span class="action-name">系统设置</span>
              </div>
              <div class="action-card" @click="showHelp">
                <div class="action-icon orange">
                  <el-icon size="24" color="#fff"><QuestionFilled /></el-icon>
                </div>
                <span class="action-name">帮助中心</span>
              </div>
            </div>
          </el-card>
        </div>

        <el-card class="info-card" shadow="hover">
          <template #header>
            <div class="card-header">
              <div class="header-title">
                <el-icon size="20" color="#667eea"><InfoFilled /></el-icon>
                <span>系统信息</span>
              </div>
            </div>
          </template>
          <div class="system-info">
            <div class="info-item">
              <span class="info-label">当前用户</span>
              <span class="info-value">{{ user?.username || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">用户角色</span>
              <el-tag :type="user?.role === 'administrator' ? 'danger' : 'primary'" size="small">
                {{ user?.role || '-' }}
              </el-tag>
            </div>
            <div class="info-item">
              <span class="info-label">账号状态</span>
              <el-tag :type="user?.status === 'active' ? 'success' : 'info'" size="small">
                {{ user?.status === 'active' ? '正常' : '禁用' }}
              </el-tag>
            </div>
            <div class="info-item">
              <span class="info-label">登录时间</span>
              <span class="info-value">{{ currentTime }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </main>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import {
  UserFilled,
  HomeFilled,
  User,
  Setting,
  SwitchButton,
  Bell,
  FullScreen,
  CircleCheck,
  Timer,
  Connection,
  Promotion,
  CircleCheckFilled,
  CircleCloseFilled,
  Operation,
  QuestionFilled,
  InfoFilled,
} from '@element-plus/icons-vue';
import type { User as UserType } from '@/types';

const router = useRouter();
const user = ref<UserType | null>(null);
const testResult = ref<string>('');
const testSuccess = ref(false);
const testing = ref(false);
const currentTime = ref('');
let timeInterval: number | null = null;

const updateTime = () => {
  currentTime.value = new Date().toLocaleString('zh-CN');
};

onMounted(() => {
  const userData = localStorage.getItem('user');
  if (userData) {
    user.value = JSON.parse(userData);
  } else {
    router.push('/');
    return;
  }
  updateTime();
  timeInterval = window.setInterval(updateTime, 1000);
});

onUnmounted(() => {
  if (timeInterval) {
    clearInterval(timeInterval);
  }
});

const handleLogout = () => {
  localStorage.removeItem('user');
  ElMessage.success('已安全退出登录');
  router.push('/');
};

const handleTestApi = async () => {
  testing.value = true;
  testResult.value = '';
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
    testSuccess.value = data.success === true;
    if (testSuccess.value) {
      ElMessage.success('API 调用成功，后端服务运行正常');
    } else {
      ElMessage.warning('API 返回错误：' + data.message);
    }
  } catch (error) {
    testResult.value = '连接失败：' + (error as Error).message;
    testSuccess.value = false;
    ElMessage.error('API 调用失败，请检查后端服务');
  } finally {
    testing.value = false;
  }
};

const showProfile = () => {
  ElMessage.info('个人资料功能开发中...');
};

const showSettings = () => {
  ElMessage.info('系统设置功能开发中...');
};

const showHelp = () => {
  ElMessage.info('帮助中心功能开发中...');
};
</script>

<style scoped>
.home-layout {
  display: flex;
  min-height: 100vh;
  background: #f5f7fa;
}

.sidebar {
  width: 260px;
  background: #1a1a2e;
  display: flex;
  flex-direction: column;
  position: fixed;
  height: 100vh;
  left: 0;
  top: 0;
  z-index: 100;
}

.sidebar-header {
  padding: 24px 20px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.brand {
  display: flex;
  align-items: center;
  gap: 12px;
}

.brand-text {
  color: white;
  font-size: 18px;
  font-weight: 600;
  letter-spacing: 1px;
}

.sidebar-nav {
  flex: 1;
  padding: 20px 12px;
}

.nav-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 14px 16px;
  color: rgba(255, 255, 255, 0.7);
  border-radius: 10px;
  cursor: pointer;
  transition: all 0.3s;
  margin-bottom: 8px;
}

.nav-item:hover {
  background: rgba(255, 255, 255, 0.1);
  color: white;
}

.nav-item.active {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
}

.sidebar-footer {
  padding: 20px;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
}

.user-profile {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 16px;
}

.user-avatar {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.user-meta {
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.user-name {
  color: white;
  font-size: 14px;
  font-weight: 500;
}

.user-role {
  color: rgba(255, 255, 255, 0.5);
  font-size: 12px;
  text-transform: capitalize;
}

.logout-btn {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 6px;
}

.main-content {
  flex: 1;
  margin-left: 260px;
  display: flex;
  flex-direction: column;
}

.page-header {
  background: white;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.05);
  position: sticky;
  top: 0;
  z-index: 50;
}

.header-top {
  height: 56px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 32px;
  border-bottom: 1px solid #f0f0f0;
}

.header-brand {
  display: flex;
  align-items: center;
  gap: 10px;
}

.header-brand-text {
  font-size: 18px;
  font-weight: 600;
  color: #1a1a2e;
  letter-spacing: 0.5px;
}

.header-actions {
  display: flex;
  align-items: center;
}

.logout-btn-top {
  display: flex;
  align-items: center;
  gap: 6px;
  font-weight: 500;
}

.header-bottom {
  padding: 20px 32px;
  background: linear-gradient(135deg, #fafbfc 0%, #f5f7fa 100%);
}

.user-info-bar {
  display: flex;
  align-items: center;
  gap: 16px;
}

.user-avatar-large {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border: 3px solid white;
  box-shadow: 0 2px 8px rgba(102, 126, 234, 0.3);
}

.user-greeting {
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.greeting-title {
  font-size: 20px;
  font-weight: 600;
  color: #1a1a2e;
  margin: 0;
}

.greeting-subtitle {
  font-size: 13px;
  color: #888;
  margin: 0;
  text-transform: capitalize;
}

.content-wrapper {
  flex: 1;
  padding: 24px;
  overflow-y: auto;
}

.welcome-section {
  margin-bottom: 24px;
}

.welcome-stats {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 16px;
}

.stat-card {
  background: white;
  border-radius: 12px;
  padding: 20px;
  display: flex;
  align-items: center;
  gap: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.04);
}

.stat-icon {
  width: 48px;
  height: 48px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.stat-icon.blue {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.stat-icon.green {
  background: linear-gradient(135deg, #11998e 0%, #38ef7d 100%);
}

.stat-icon.purple {
  background: linear-gradient(135deg, #8e2de2 0%, #4a00e0 100%);
}

.stat-info {
  display: flex;
  flex-direction: column;
}

.stat-value {
  font-size: 24px;
  font-weight: 700;
  color: #1a1a2e;
}

.stat-label {
  font-size: 13px;
  color: #999;
  margin-top: 2px;
}

.dashboard-grid {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 24px;
  margin-bottom: 24px;
}

.dashboard-card :deep(.el-card__header) {
  padding: 16px 20px;
  border-bottom: 1px solid #f0f0f0;
}

.card-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.header-title {
  display: flex;
  align-items: center;
  gap: 8px;
  font-weight: 600;
  color: #1a1a2e;
}

.api-test-content {
  padding: 10px 0;
}

.test-desc {
  color: #666;
  font-size: 14px;
  margin-bottom: 20px;
  line-height: 1.6;
}

.test-btn {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border: none;
  border-radius: 10px;
  padding: 12px 24px;
  display: flex;
  align-items: center;
  gap: 8px;
}

.test-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(102, 126, 234, 0.4);
}

.test-result {
  margin-top: 20px;
  border-radius: 10px;
  overflow: hidden;
}

.test-result.success {
  border: 1px solid #67c23a;
}

.test-result.error {
  border: 1px solid #f56c6c;
}

.result-header {
  padding: 12px 16px;
  display: flex;
  align-items: center;
  gap: 8px;
  font-weight: 500;
}

.test-result.success .result-header {
  background: #f0f9eb;
  color: #67c23a;
}

.test-result.error .result-header {
  background: #fef0f0;
  color: #f56c6c;
}

.result-body {
  padding: 16px;
  background: #2d2d2d;
  color: #f8f8f2;
  font-size: 13px;
  line-height: 1.6;
  overflow-x: auto;
  margin: 0;
}

.quick-actions {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
  padding: 10px 0;
}

.action-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
  padding: 20px;
  background: #f8f9fa;
  border-radius: 12px;
  cursor: pointer;
  transition: all 0.3s;
}

.action-card:hover {
  background: #f0f2f5;
  transform: translateY(-4px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
}

.action-icon {
  width: 48px;
  height: 48px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.action-icon.blue {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.action-icon.green {
  background: linear-gradient(135deg, #11998e 0%, #38ef7d 100%);
}

.action-icon.purple {
  background: linear-gradient(135deg, #8e2de2 0%, #4a00e0 100%);
}

.action-icon.orange {
  background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
}

.action-name {
  font-size: 13px;
  color: #666;
  font-weight: 500;
}

.info-card :deep(.el-card__body) {
  padding: 20px;
}

.system-info {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 16px;
}

.info-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 12px 16px;
  background: #f8f9fa;
  border-radius: 8px;
}

.info-label {
  font-size: 13px;
  color: #666;
}

.info-value {
  font-size: 13px;
  color: #1a1a2e;
  font-weight: 500;
}

@media (max-width: 1200px) {
  .dashboard-grid {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 768px) {
  .sidebar {
    width: 0;
    overflow: hidden;
  }

  .main-content {
    margin-left: 0;
  }

  .welcome-stats {
    grid-template-columns: 1fr;
  }

  .system-info {
    grid-template-columns: 1fr;
  }
}
</style>

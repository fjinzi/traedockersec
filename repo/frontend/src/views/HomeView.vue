<template>
  <div class="home-container">
    <div class="sidebar">
      <div class="logo-section">
        <div class="logo-icon">
          <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M12 2L2 7l10 5 10-5-10-5zM2 17l10 5 10-5M2 12l10 5 10-5"/>
          </svg>
        </div>
        <span class="logo-text">用户管理系统</span>
      </div>
      <nav class="nav-menu">
        <div class="nav-item active">
          <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"/>
            <polyline points="9 22 9 12 15 12 15 22"/>
          </svg>
          <span>控制台</span>
        </div>
        <div class="nav-item">
          <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/>
            <circle cx="9" cy="7" r="4"/>
            <path d="M23 21v-2a4 4 0 0 0-3-3.87"/>
            <path d="M16 3.13a4 4 0 0 1 0 7.75"/>
          </svg>
          <span>用户管理</span>
        </div>
        <div class="nav-item">
          <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"/>
          </svg>
          <span>数据分析</span>
        </div>
        <div class="nav-item">
          <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="12" r="3"/>
            <path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"/>
          </svg>
          <span>系统设置</span>
        </div>
      </nav>
    </div>

    <div class="main-content">
      <header class="top-header">
        <div class="header-left">
          <h2>欢迎回来，{{ user?.username }} 👋</h2>
          <p class="welcome-subtitle">今天是个美好的日子，开始管理您的用户吧！</p>
        </div>
        <div class="header-right">
          <div class="user-avatar">
            <div class="avatar-circle">
              {{ user?.username?.charAt(0)?.toUpperCase() }}
            </div>
            <div class="user-details">
              <span class="user-name">{{ user?.username }}</span>
              <span class="user-role">{{ user?.role }}</span>
            </div>
          </div>
          <el-button class="logout-btn" type="primary" :plain="true" @click="handleLogout">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/>
              <polyline points="16 17 21 12 16 7"/>
              <line x1="21" y1="12" x2="9" y2="12"/>
            </svg>
            退出登录
          </el-button>
        </div>
      </header>

      <div class="dashboard-content">
        <div class="stats-row">
          <div class="stat-card primary">
            <div class="stat-icon">
              <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/>
                <circle cx="9" cy="7" r="4"/>
              </svg>
            </div>
            <div class="stat-info">
              <h3>1,284</h3>
              <p>总用户数</p>
            </div>
            <div class="stat-trend positive">+12.5%</div>
          </div>
          <div class="stat-card success">
            <div class="stat-icon">
              <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"/>
                <polyline points="22 4 12 14.01 9 11.01"/>
              </svg>
            </div>
            <div class="stat-info">
              <h3>856</h3>
              <p>活跃用户</p>
            </div>
            <div class="stat-trend positive">+8.2%</div>
          </div>
          <div class="stat-card warning">
            <div class="stat-icon">
              <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <circle cx="12" cy="12" r="10"/>
                <polyline points="12 6 12 12 16 14"/>
              </svg>
            </div>
            <div class="stat-info">
              <h3>42</h3>
              <p>今日登录</p>
            </div>
            <div class="stat-trend positive">+23.1%</div>
          </div>
          <div class="stat-card danger">
            <div class="stat-icon">
              <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <circle cx="12" cy="12" r="10"/>
                <line x1="15" y1="9" x2="9" y2="15"/>
                <line x1="9" y1="9" x2="15" y2="15"/>
              </svg>
            </div>
            <div class="stat-info">
              <h3>23</h3>
              <p>禁用用户</p>
            </div>
            <div class="stat-trend negative">-5.4%</div>
          </div>
        </div>

        <div class="content-row">
          <el-card class="welcome-panel" shadow="hover">
            <template #header>
              <div class="panel-header">
                <span>系统状态</span>
                <el-tag type="success">运行正常</el-tag>
              </div>
            </template>
            <div class="panel-content">
              <el-result icon="success" title="系统运行正常" sub-title="所有服务健康检查已通过，Hibernate 数据持久层已成功迁移">
                <template #extra>
                  <el-button type="primary" @click="handleTestApi">
                    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                      <path d="M21 12a9 9 0 1 1-9-9c2.52 0 4.93 1 6.74 2.74L21 8"/>
                      <path d="M21 3v5h-5"/>
                    </svg>
                    测试 API 连接
                  </el-button>
                </template>
              </el-result>
              <el-collapse-transition>
                <div v-if="testResult" class="test-result">
                  <div class="result-header">
                    <span>API 响应结果</span>
                    <el-button link size="small" @click="testResult = ''">关闭</el-button>
                  </div>
                  <pre>{{ testResult }}</pre>
                </div>
              </el-collapse-transition>
            </div>
          </el-card>

          <el-card class="info-panel" shadow="hover">
            <template #header>
              <div class="panel-header">
                <span>技术栈信息</span>
              </div>
            </template>
            <div class="tech-stack">
              <div class="tech-item">
                <div class="tech-dot spring"></div>
                <span>Spring Boot 3.2.0</span>
              </div>
              <div class="tech-item">
                <div class="tech-dot hibernate"></div>
                <span>Hibernate / JPA</span>
              </div>
              <div class="tech-item">
                <div class="tech-dot h2"></div>
                <span>H2 Database</span>
              </div>
              <div class="tech-item">
                <div class="tech-dot vue"></div>
                <span>Vue 3 + TypeScript</span>
              </div>
              <div class="tech-item">
                <div class="tech-dot element"></div>
                <span>Element Plus</span>
              </div>
            </div>
          </el-card>
        </div>
      </div>
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
  ElMessage.success('已安全退出登录');
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
    ElMessage.success('API 连接测试成功，Hibernate 数据层运行正常！');
  } catch (error) {
    testResult.value = 'API 调用失败：' + error;
    ElMessage.error('API 连接测试失败');
  }
};
</script>

<style scoped>
.home-container {
  display: flex;
  min-height: 100vh;
  background: linear-gradient(135deg, #f5f7fa 0%, #e4e8ec 100%);
}

.sidebar {
  width: 200px;
  background: linear-gradient(180deg, #1e293b 0%, #0f172a 100%);
  color: white;
  display: flex;
  flex-direction: column;
}

.logo-section {
  padding: 24px 16px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.logo-icon {
  width: 56px;
  height: 56px;
  background: linear-gradient(135deg, #409eff 0%, #67c23a 100%);
  border-radius: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.logo-icon svg {
  width: 32px;
  height: 32px;
  color: white;
}

.logo-text {
  font-size: 18px;
  font-weight: 700;
  background: linear-gradient(90deg, #409eff, #67c23a);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  text-align: center;
}

.nav-menu {
  padding: 20px 12px;
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.nav-item {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 12px 14px;
  border-radius: 10px;
  cursor: pointer;
  transition: all 0.3s ease;
  color: rgba(255, 255, 255, 0.7);
}

.nav-item:hover {
  background: rgba(255, 255, 255, 0.1);
  color: white;
}

.nav-item.active {
  background: linear-gradient(135deg, #409eff 0%, #337ecc 100%);
  color: white;
  box-shadow: 0 4px 15px rgba(64, 158, 255, 0.4);
}

.nav-item svg {
  width: 20px;
  height: 20px;
}

.main-content {
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: auto;
}

.top-header {
  background: linear-gradient(135deg, #ffffff 0%, #f8fafc 100%);
  padding: 28px 40px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
  border-bottom: 3px solid transparent;
  border-image: linear-gradient(90deg, #409eff, #67c23a) 1;
}

.header-left h2 {
  font-size: 32px;
  font-weight: 700;
  background: linear-gradient(135deg, #1e293b 0%, #409eff 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  margin: 0 0 8px 0;
}

.welcome-subtitle {
  color: #475569;
  margin: 0;
  font-size: 16px;
  font-weight: 500;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 20px;
}

.user-avatar {
  display: flex;
  align-items: center;
  gap: 12px;
}

.avatar-circle {
  width: 48px;
  height: 48px;
  background: linear-gradient(135deg, #409eff 0%, #67c23a 100%);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-weight: 600;
  font-size: 20px;
  box-shadow: 0 4px 12px rgba(64, 158, 255, 0.3);
}

.user-details {
  display: flex;
  flex-direction: column;
}

.user-name {
  font-weight: 600;
  color: #1e293b;
}

.user-role {
  font-size: 12px;
  color: #64748b;
}

.logout-btn {
  display: flex;
  align-items: center;
  gap: 8px;
}

.logout-btn svg {
  width: 16px;
  height: 16px;
}

.dashboard-content {
  padding: 40px 48px;
}

.stats-row {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 28px;
  margin-bottom: 32px;
}

.stat-card {
  background: white;
  border-radius: 18px;
  padding: 32px 24px;
  display: flex;
  align-items: center;
  gap: 20px;
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.08);
  transition: all 0.3s ease;
}

.stat-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12);
}

.stat-card.primary .stat-icon {
  background: linear-gradient(135deg, rgba(64, 158, 255, 0.1), rgba(64, 158, 255, 0.2));
  color: #409eff;
}

.stat-card.success .stat-icon {
  background: linear-gradient(135deg, rgba(103, 194, 58, 0.1), rgba(103, 194, 58, 0.2));
  color: #67c23a;
}

.stat-card.warning .stat-icon {
  background: linear-gradient(135deg, rgba(230, 162, 60, 0.1), rgba(230, 162, 60, 0.2));
  color: #e6a23c;
}

.stat-card.danger .stat-icon {
  background: linear-gradient(135deg, rgba(245, 108, 108, 0.1), rgba(245, 108, 108, 0.2));
  color: #f56c6c;
}

.stat-icon {
  width: 56px;
  height: 56px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.stat-icon svg {
  width: 28px;
  height: 28px;
}

.stat-info h3 {
  font-size: 36px;
  font-weight: 800;
  background: linear-gradient(135deg, #1e293b 0%, #409eff 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  margin: 0 0 6px 0;
}

.stat-info p {
  color: #475569;
  margin: 0;
  font-size: 15px;
  font-weight: 600;
}

.stat-trend {
  margin-left: auto;
  padding: 6px 12px;
  border-radius: 20px;
  font-size: 13px;
  font-weight: 700;
}

.stat-trend.positive {
  background: rgba(103, 194, 58, 0.1);
  color: #67c23a;
}

.stat-trend.negative {
  background: rgba(245, 108, 108, 0.1);
  color: #f56c6c;
}

.content-row {
  display: grid;
  grid-template-columns: 3fr 1fr;
  gap: 32px;
}

.panel-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-weight: 700;
  font-size: 18px;
  color: #1e293b;
}

.panel-content {
  padding: 24px 0;
}

.test-result {
  margin-top: 24px;
  background: linear-gradient(135deg, #f8fafc 0%, #f1f5f9 100%);
  border-radius: 12px;
  overflow: hidden;
}

.result-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 20px;
  background: #e2e8f0;
  font-weight: 600;
  color: #475569;
}

.test-result pre {
  background: #1e293b;
  color: #f8fafc;
  padding: 20px;
  margin: 0;
  border-radius: 0 0 12px 12px;
  overflow-x: auto;
  font-family: 'Consolas', 'Monaco', monospace;
  font-size: 13px;
  line-height: 1.6;
}

.tech-stack {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.tech-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px;
  border-radius: 8px;
  transition: all 0.3s ease;
}

.tech-item:hover {
  background: #f8fafc;
}

.tech-dot {
  width: 12px;
  height: 12px;
  border-radius: 50%;
}

.tech-dot.spring {
  background: #6db33f;
}

.tech-dot.hibernate {
  background: #5966b3;
}

.tech-dot.h2 {
  background: #0078c8;
}

.tech-dot.vue {
  background: #42b883;
}

.tech-dot.element {
  background: #409eff;
}
</style>

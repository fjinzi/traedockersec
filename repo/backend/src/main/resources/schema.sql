DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    role VARCHAR(20) DEFAULT 'user',
    status VARCHAR(20) DEFAULT 'active',
    deleted INT DEFAULT 0,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (username, password, email, role, status) VALUES
('admin', '123456', 'admin@example.com', 'administrator', 'active'),
('zhangsan', '123456', 'zhangsan@example.com', 'user', 'active'),
('lisi', '123456', 'lisi@example.com', 'user', 'active'),
('wangwu', '123456', 'wangwu@example.com', 'user', 'inactive'),
('test', '123456', 'test@example.com', 'tester', 'active');

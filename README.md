# 使用指南
## 1. 初始化 mysql
```$xlst
CREATE TABLE IF NOT EXISTS goods_settlement.USER(
username varchar(50) NOT NULL PRIMARY KEY COMMENT '用户名',
password varchar(10) NOT NULL COMMENT '密码'
)
COMMENT '用户表'
;

CREATE TABLE IF NOT EXISTS goods_settlement.COMMODITY(
imgid int AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '商品ID',
name VARCHAR(200) NOT NULL COMMENT '名称',
picture LONGBLOB NOT NULL COMMENT '图片',
cost DECIMAL(10, 2) NOT NULL COMMENT '价格',
inventory int NOT NULL COMMENT '库存'
)
COMMENT '商品表'
;

CREATE TABLE IF NOT EXISTS goods_settlement.SETTLEMENT(
payid int AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '支付ID',
username varchar(50) NOT NULL COMMENT '支付账户',
imgid int NOT NULL COMMENT '商品ID',
foreign key(imgid) references COMMODITY(imgid),
foreign key(username) references USER(username)
)
COMMENT '支付信息表'
;
```

## 2. 修改 mysql 和 redis 连接参数
相关参数在 `props.properties` 中。

## 3. 启动 linux 中的 redis
```$xlst
./bin/redis-server ./etc/redis.conf
```

## 4. 启动 tomcat 进入登录页面
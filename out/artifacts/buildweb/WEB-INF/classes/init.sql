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

delimiter //
DROP PROCEDURE IF EXISTS settlement_procedure;
CREATE PROCEDURE settlement_procedure(IN id varchar(50), IN img_id int(11), OUT insert_result int, OUT update_result int)
    BEGIN
        DECLARE changeCount int DEFAULT 0;
        START TRANSACTION;
        INSERT IGNORE INTO SETTLEMENT(username, imgid) VALUES(id, img_id);
        SELECT ROW_COUNT() INTO changeCount;
        IF changeCount = 0 THEN
            ROLLBACK;
            SET insert_result = -1;
        ELSEIF changeCount < 0 THEN
            ROLLBACK ;
            SET insert_result = -2;
        ELSE
            UPDATE COMMODITY SET inventory = inventory - 1 WHERE imgid = img_id AND inventory > 0;
            SELECT ROW_COUNT() INTO changeCount;
            IF changeCount = 0 THEN
                ROLLBACK;
                SET update_result = 0; -- 库存没有了
            ELSEIF changeCount < 0 THEN
                ROLLBACK;
                SET update_result = -1; -- 内部错误
            ELSE
                COMMIT;
                SET insert_result = 1;
                SET update_result = 1;
            END IF;
        END IF;
        COMMIT;
    END
//
delimiter ;

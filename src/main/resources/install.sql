

DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom` (
	id              INT(10)           NOT NULL  AUTO_INCREMENT
	COMMENT '教室ID',
	classroom_name  VARCHAR(20) COMMENT '教室号',
	building_name   VARCHAR(20) COMMENT '教学楼或其它楼名称',
	classroom_seats SMALLINT UNSIGNED NOT NULL
	COMMENT '教室座位数',
	available SET ('yes', 'no') not NULL DEFAULT 'yes',
	primary key(id)
);
alter table classroom comment '教室表';
insert into classroom(classroom_name, building_name, classroom_seats)
values('1101', '一号教学楼', '100');
insert into classroom(classroom_name, building_name, classroom_seats)
values('1102', '一号教学楼', '100');
insert into classroom(classroom_name, building_name, classroom_seats)
values('1103', '一号教学楼', '100');
insert into classroom(classroom_name, building_name, classroom_seats)
values('1104', '一号教学楼', '100');
insert into classroom(classroom_name, building_name, classroom_seats)
values('1105', '一号教学楼', '150');
insert into classroom(classroom_name, building_name, classroom_seats)
values('1106', '一号教学楼', '150');

DROP TABLE IF EXISTS classroom_administrator;
create table classroom_administrator(
	id bigint unsigned auto_increment comment '教室管理员',
	admin_name varchar(50) NOT NULL UNIQUE comment '教室管理员姓名',
	password varchar(30) not null comment '教室管理员密码',
	create_time TIMESTAMP NULL DEFAULT now() comment '教室管理员创建日期和时间',
	primary key(id)
);
alter table classroom_administrator comment '教室管理员表';
insert into classroom_administrator(admin_name, password) values('admin','admin');
insert into classroom_administrator(admin_name, password) values('tang', 'tang');



DROP TABLE IF EXISTS classroom_user;
create table classroom_user(
	id bigint unsigned auto_increment comment '教室用户ID',
	user_id bigint UNSIGNED COMMENT '职员id, 比如教师的工员',
	password VARCHAR(30) NOT NULL COMMENT '职员id对应的密码',
	user_name VARCHAR(50) NOT NULL COMMENT '教室用户姓名',
	user_title SET('student','teacher') NOT NULL
	COMMENT '一般教师都是讲师权限，教授级别以上为教授权限',
	privilege set('none', 'normal', 'senior') comment '教室用户权限,n代表没有权限,ss超级权限',
	create_time TIMESTAMP NULL DEFAULT now() comment '用户创建日期和时间',
	primary key(id)
);
alter table classroom_user comment '教室用户表';

insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3110020310, '张大妈', 'teacher', 'zhangdama', 'senior');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3110020311, '王大海', 'student', 'wangdahai', 'normal');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3110020312, '张大仙', 'student', 'zhangdaxian', 'normal');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3110020313, '王大海', 'student', 'wangdahai', 'normal');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3110020318, '李四', 'student', 'lisi', 'normal');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3114030302, '唐敦红', 'student', 'tangdunhong', 'normal');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3114030301, '没有名字', 'student', '123456', 'normal');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3113030302, '黄飞', 'student', '123456', 'normal');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3114030310, '京东', 'student', '123456', 'normal');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3114030311, '死神来了', 'student', '123456', 'normal');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3114030312, '没有办法', 'student', '123456', 'normal');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3114030313, '大海', 'teacher', '123456', 'senior');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3114030314, '黄家驹', 'teacher', '123456', 'senior');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3114030315, '相信自己', 'teacher', '123456', 'senior');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3114030316, '王主任', 'teacher', '123456', 'senior');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3114030317, '张心教授', 'teacher', '123456', 'senior');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3114030318, '欧阳克', 'teacher', '123456', 'senior');
insert into classroom_user(user_id, user_name, user_title, password, privilege)
VALUES (3114030319, '罗马', 'teacher', '123456', 'senior');


DROP TABLE IF EXISTS classroom_log;
create table classroom_log(
	id bigint unsigned auto_increment comment '借用教室日志ID',
	classroom_id bigint unsigned comment '教室ID',
	user_id bigint unsigned comment '教室用户ID',
	log_name varchar(50) comment '借用教室用途',
	log_date date comment '将要使用的教室的日期',
	log_time SET('1', '2', '3','4', '5')
	COMMENT '占用具体时间，数字表示第几节课',
	create_time TIMESTAMP NULL DEFAULT now() comment '日志创建日期和时间',
	primary key(id)
);
alter table classroom_log comment '借用教室日志表';

INSERT INTO  classroom_log(classroom_id, user_id, log_name, log_date, log_time) VALUES
	(1, 1, '大学语文考试', '2018-04-20', '2');
INSERT INTO  classroom_log(classroom_id, user_id, log_name, log_date, log_time) VALUES
	(2, 1, '高等数学考试Ⅰ', '2018-04-19', '3');
INSERT INTO  classroom_log(classroom_id, user_id, log_name, log_date, log_time) VALUES
	(2, 1, '高等数学考试(重修)Ⅱ', '2018-04-19', '3');
INSERT INTO  classroom_log(classroom_id, user_id, log_name, log_date, log_time) VALUES
	(2, 1, '大学英语考试Ⅰ', '2018-04-19', '3');
INSERT INTO  classroom_log(classroom_id, user_id, log_name, log_date, log_time) VALUES
	(2, 1, 'C语言程序设计', '2018-04-18', '4');
INSERT INTO  classroom_log(classroom_id, user_id, log_name, log_date, log_time) VALUES
	(2, 1, 'Java程序设计', '2018-04-19', '1');



CREATE TABLE course_1 (
	cid BIGINT(20) PRIMARY KEY,
	cname VARCHAR(50) NOT NULL,
	user_id BIGINT(20) NOT NULL,
	cstatus varchar(10) NOT NULL
);

CREATE TABLE course_2 (
	cid BIGINT(20) PRIMARY KEY,
	cname VARCHAR(50) NOT NULL,
	user_id BIGINT(20) NOT NULL,
	cstatus varchar(10) NOT NULL
);

-- 在三个库中创建
CREATE TABLE `t_dict`  (
  `dict_id` bigint(0) PRIMARY KEY NOT NULL,
  `ustatus` varchar(100) NOT NULL,
  `uvalue` varchar(100) NOT NULL
);
-- 在userdb中创建
CREATE TABLE `t_dict_1`  (
  `dict_id` bigint(0) PRIMARY KEY NOT NULL,
  `ustatus` varchar(100) NOT NULL,
  `uvalue` varchar(100) NOT NULL
);
CREATE TABLE `t_dict_2`  (
  `dict_id` bigint(0) PRIMARY KEY NOT NULL,
  `ustatus` varchar(100) NOT NULL,
  `uvalue` varchar(100) NOT NULL
);

--在userdb中创建
CREATE TABLE `t_user`  (
  `user_id` bigint(0) PRIMARY KEY NOT NULL,
  `username` varchar(100) NOT NULL,
  `ustatus` varchar(50) NOT NULL,
  `uage` int(3)
);

CREATE TABLE `t_user_1`  (
  `user_id` bigint(0) PRIMARY KEY NOT NULL,
  `username` varchar(100) NOT NULL,
  `ustatus` varchar(50) NOT NULL,
  `uage` int(3)
);
CREATE TABLE `t_user_2`  (
  `user_id` bigint(0) PRIMARY KEY NOT NULL,
  `username` varchar(100) NOT NULL,
  `ustatus` varchar(50) NOT NULL,
  `uage` int(3)
);
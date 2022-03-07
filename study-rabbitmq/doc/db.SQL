CREATE TABLE zzh_test.message_queue_log (
	id varchar(36) NULL,
	reference_id varchar(36) NULL,
	reference_type varchar(100) NULL,
	exchange_name varchar(100) NULL,
	routing_key varchar(100) NULL COMMENT '路由key',
	message varchar(1000) NULL COMMENT '消息内容',
	confirm_time DATE NULL,
	consume_num INT NULL COMMENT '消费次数',
	fail_cause varchar(1024) NULL,
	status varchar(100) NULL,
	is_pushed varchar(100) NULL,
	is_fail_notify varchar(100) NULL,
	is_deleted varchar(100) NULL,
	tenant_id varchar(100) NULL,
	app_id varchar(100) NULL,
	crt_user varchar(100) NULL,
	crt_time DATE NULL,
	upd_user varchar(100) NULL,
	upd_date DATE NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci;
create table zzh_order
(
	id varchar(36) not null,
	order_name varchar(64) null comment '订单名称',
	order_num varchar(36) null comment '订单编号',
	price int default 0 null comment '订单金额',
	crt_name varchar(36) null,
	crt_time timestamp null,
	upt_name varchar(36) null,
	upt_time timestamp null,
	constraint zzh_order_id_uindex unique (id)
)
comment '订单表';

alter table zzh_order add primary key (id);


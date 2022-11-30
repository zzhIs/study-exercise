create table zzh_goods
(
	id varchar(36) not null,
	goods_name varchar(64) null comment '商品名称',
	goods_num varchar(36) null comment '商品编号',
	goods_color varchar(36) null comment '商品颜色',
	count int default 0 null comment '库存数量',
	crt_name varchar(36) null,
	crt_time timestamp null,
	upt_name varchar(36) null,
	upt_time timestamp null,
	constraint zzh_goods_id_uindex unique (id)
)
comment '商品表';

alter table zzh_goods add primary key (id);


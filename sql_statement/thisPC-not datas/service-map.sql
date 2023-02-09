create table dic_district
(
    address_code        char(6)      not null comment '地区编码'
        primary key,
    address_name        varchar(128) null comment '地区名称',
    parent_address_code char(6)      null comment '父地区编码',
    level               tinyint      null comment '级别'
);
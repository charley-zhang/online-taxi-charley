create table dic_nation
(
    nation_code char(2)     not null comment '民族编码'
        primary key,
    nation_name varchar(64) null comment '民族名称'
);
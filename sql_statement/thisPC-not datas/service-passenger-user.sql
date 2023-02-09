create table passenger_user
(
    id               bigint unsigned auto_increment
        primary key,
    gmt_create       datetime     null on update CURRENT_TIMESTAMP,
    gmt_modified     datetime     null on update CURRENT_TIMESTAMP,
    passenger_phone  varchar(16)  null,
    passenger_name   varchar(16)  null,
    passenger_gender tinyint(1)   null comment '0：未知，1：男，2：女',
    state            tinyint(1)   null comment '0：有效，1：失效',
    profile_photo    varchar(128) null comment '头像图片地址的url'
);
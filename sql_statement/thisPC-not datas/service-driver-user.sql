create table car
(
    id               bigint unsigned auto_increment
        primary key,
    address          char(6)            null comment '车辆所在城市',
    vehicle_no       varchar(8)         null comment '车辆号牌',
    plate_color      char               null comment '车牌颜色（1：蓝色，2：黄色，3：黑色，4：白色，5：绿色，9：其他）',
    seats            int                null comment '核定载客位',
    brand            varchar(16)        null comment '车辆厂牌',
    model            varchar(16)        null comment '车辆型号',
    vehicle_type     varchar(8)         null comment '车辆类型',
    owner_name       varchar(16)        null comment '车辆所有人',
    vehicle_color    char(2)            null comment '车辆颜色（1：白色，2：黑色）',
    engine_id        varchar(32)        null comment '发动机号',
    vin              varchar(64)        null,
    certify_date_a   date               null comment '车辆注册日期',
    fue_type         char(2)            null comment '燃料类型(1：汽油，2：柴油，3：天然气，4：液化气，5：电动，9：其他）',
    engine_displace  varchar(8)         null comment '发动机排量（毫升）',
    trans_agency     varchar(32)        null comment '车辆运输证发证机构',
    trans_area       varchar(32)        null comment '车辆经验区域',
    trans_date_start date               null comment '车辆运输证有效期起',
    trans_date_end   date               null comment '车辆运输证有效期止',
    certify_date_b   date               null comment '车辆初次登记日期',
    fix_state        char(2)            null comment '车辆的检修状态(0：未检修，1：已检修，2：未知）',
    next_fix_date    date               null comment '下次年检时间',
    check_state      char(2) default '' null comment '年度审验状态（0：未年审，1：年审合格，2：年审不合格）',
    fee_print_id     varchar(64)        null comment '发票打印设备序列号',
    gps_brand        varchar(32)        null comment '卫星定位装置品牌',
    gps_model        varchar(32)        null comment '卫星型号',
    gps_install_date date               null comment '卫星定位设备安装日期',
    register_date    date               null comment '报备日期',
    commercial_type  int                null comment '服务类型：1：网络预约出租车，2：巡游出租车，3：私人小客车合乘',
    fare_type        varchar(16)        null comment '运价编码 关联计价规则',
    state            tinyint(1)         null comment '状态：0:有效，1：失效',
    tid              varchar(16)        null comment '终端Id',
    trid             varchar(16)        null comment '轨迹ID',
    trname           varchar(32)        null comment '轨迹名称',
    gmt_create       datetime           null on update CURRENT_TIMESTAMP comment '创建时间',
    gmt_modified     datetime           null on update CURRENT_TIMESTAMP comment '修改时间'
);

create table dic_district
(
    address_code        char(6)      not null comment '地区编码'
        primary key,
    address_name        varchar(128) null comment '地区名称',
    parent_address_code char(6)      null comment '父地区编码',
    level               tinyint      null comment '级别'
);

create table driver_car_binding_relationship
(
    id              bigint   not null
        primary key,
    driver_id       bigint   null,
    car_id          bigint   null,
    bind_state      int      null,
    binding_time    datetime null,
    un_binding_time datetime null
);

create table driver_user
(
    id                             bigint unsigned auto_increment
        primary key,
    address                        char(6)      null comment '司机注册地行政区划代码',
    driver_name                    varchar(16)  null comment '司机姓名',
    driver_phone                   varchar(16)  null,
    driver_gender                  tinyint      null comment '1:男，2：女',
    driver_birthday                date         null,
    driver_nation                  char(2)      null comment '驾驶员民族',
    driver_contact_address         varchar(255) null,
    license_id                     varchar(128) null comment '机动车驾驶证号',
    get_driver_license_date        date         null comment '初次领取驾驶证日期',
    driver_license_on              date         null comment '驾驶证有效期起',
    driver_license_off             date         null comment '驾驶证有效期止',
    taxi_driver                    tinyint      null comment '是否巡游出租汽车：1：是，0：否',
    certificate_no                 varchar(255) null comment '网络预约出租汽车驾驶员资格证号',
    network_car_issue_organization varchar(255) null comment '网络预约出租汽车驾驶员发证机构',
    network_car_issue_date         date         null comment '资格证发证日期',
    get_network_car_proof_date     date         null comment '初次领取资格证日期',
    network_car_proof_on           date         null comment '资格证有效起始日期',
    network_car_proof_off          date         null comment '资格证有效截止日期',
    register_date                  date         null comment '报备日期',
    commercial_type                tinyint      null comment '服务类型：1：网络预约出租汽车，2：巡游出租汽车，3：私人小客车合乘',
    contract_company               varchar(255) null comment '驾驶员合同（协议）签署公司',
    contract_on                    date         null comment '合同（协议）有效期起',
    contract_off                   date         null comment '合同有效期止',
    state                          tinyint      null comment '司机状态：0：有效，1：失效',
    gmt_create                     datetime     null on update CURRENT_TIMESTAMP comment '创建时间',
    gmt_modified                   datetime     null on update CURRENT_TIMESTAMP comment '修改时间'
);

create table driver_user_work_status
(
    id           bigint   not null
        primary key,
    driver_id    bigint   null,
    work_status  int      null comment '收车：0；出车：1，暂停：2',
    gmt_create   datetime null on update CURRENT_TIMESTAMP comment '创建时间',
    gmt_modified datetime null on update CURRENT_TIMESTAMP comment '修改时间'
);

create definer = root@localhost view v_ctity_driver_user_work_status as
select `t1`.`id` AS `driver_id`, `t1`.`address` AS `city_code`, `t2`.`work_status` AS `work_status`
from (`service-driver-user`.`driver_user` `t1` left join `service-driver-user`.`driver_user_work_status` `t2`
      on ((`t1`.`id` = `t2`.`driver_id`)));

-- comment on column v_ctity_driver_user_work_status.city_code not supported: 司机注册地行政区划代码

-- comment on column v_ctity_driver_user_work_status.work_status not supported: 收车：0；出车：1，暂停：2


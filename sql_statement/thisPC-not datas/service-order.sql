create table order_info
(
    id                             bigint auto_increment comment '订单ID'
        primary key,
    passenger_id                   bigint        null comment '乘客ID',
    passenger_phone                varchar(16)   null comment '乘客手机号',
    driver_id                      bigint        null comment '司机ID',
    driver_phone                   varchar(16)   null comment '司机手机号',
    car_id                         bigint        null comment '车辆Id',
    vehicle_type                   varchar(8)    null,
    address                        char(6)       null comment '发起地行政区划代码',
    order_time                     datetime      null comment '订单发起时间',
    depart_time                    datetime      null comment '预计用车时间',
    departure                      varchar(128)  null comment '预计出发地点详细地址',
    dep_longitude                  varchar(16)   null comment '预计出发地点经度',
    dep_latitude                   varchar(16)   null comment '预计出发地点纬度',
    destination                    varchar(128)  null comment '预计目的地',
    dest_longitude                 varchar(16)   null comment '预计目的地经度',
    dest_latitude                  varchar(16)   null comment '预计目的地纬度',
    encrypt                        int           null comment '坐标加密标识
1:GCJ-02测绘局标准
2:WGS84 GPS标准
3:BD-09 百度标准
4:CGCS2000 北斗标准
0:其他',
    fare_type                      varchar(16)   null comment '运价类型编码',
    fare_version                   int           null,
    receive_order_car_longitude    varchar(16)   null comment '接单时车辆经度',
    receive_order_car_latitude     varchar(16)   null comment '接单时车辆纬度',
    receive_order_time             datetime      null comment '接单时间，派单成功时间',
    license_id                     varchar(128)  null comment '机动车驾驶证号',
    vehicle_no                     varchar(8)    null comment '车辆号牌',
    to_pick_up_passenger_time      datetime      null comment '司机去接乘客出发时间',
    to_pick_up_passenger_longitude varchar(16)   null comment '去接乘客时，司机的经度',
    to_pick_up_passenger_latitude  varchar(16)   null comment '去接乘客时，司机的纬度',
    to_pick_up_passenger_address   varchar(128)  null comment '去接乘客时，司机的地点',
    driver_arrived_departure_time  datetime      null comment '司机到达上车点时间',
    pick_up_passenger_time         datetime      null comment '接到乘客，乘客上车时间',
    pick_up_passenger_longitude    varchar(16)   null comment '接到乘客，乘客上车经度',
    pick_up_passenger_latitude     varchar(16)   null comment '接到乘客，乘客上车纬度',
    passenger_getoff_time          datetime      null comment '乘客下车时间',
    passenger_getoff_longitude     varchar(16)   null comment '乘客下车经度',
    passenger_getoff_latitude      varchar(16)   null comment '乘客下车纬度',
    cancel_time                    datetime      null comment '订单撤销时间',
    cancel_operator                int           null comment '撤销发起者：1:乘客
2:驾驶员
3:平台公司',
    cancel_type_code               int           null comment '撤销类型代码
1:乘客提前撤销
2:驾驶员提前撤销
3:平台公司撤销
4;乘客违约撤销
5:驾驶员违约撤销',
    drive_mile                     bigint        null comment '载客里程（米）',
    drive_time                     bigint        null comment '载客时间(分)',
    order_status                   int           null comment '订单状态1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.订单取消''',
    price                          double(10, 2) null,
    gmt_create                     datetime      null on update CURRENT_TIMESTAMP comment '创建时间',
    gmt_modified                   datetime      null on update CURRENT_TIMESTAMP comment '修改时间'
);
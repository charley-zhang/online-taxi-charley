create table price_rule
(
    city_code             char(6)      not null comment '城市代码',
    vehicle_type          varchar(8)   not null comment '车辆类型',
    start_fare            double(4, 2) null comment '起步价',
    start_mile            int          null,
    unit_price_per_mile   double(4, 2) null,
    unit_price_per_minute double(4, 2) null,
    fare_version          int          not null comment '计价规则',
    fare_type             varchar(16)  null,
    primary key (city_code, vehicle_type, fare_version)
);
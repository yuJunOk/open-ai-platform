create database db_multi

create table tb_interface_info
(
    id              bigint auto_increment comment '主键'
        primary key,
    name            varchar(256)                       not null comment '名称',
    description     varchar(256)                       null comment '描述',
    url             varchar(512)                       not null comment '接口地址',
    request_params  text                               not null comment '请求参数',
    request_header  text                               null comment '请求头',
    response_header text                               null comment '响应头',
    status          int      default 0                 not null comment '接口状态（0-关闭，1-开启）',
    method          varchar(256)                       not null comment '请求类型',
    user_id         bigint                             not null comment '创建人',
    create_time     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted         tinyint  default 0                 not null comment '是否删除(0-未删, 1-已删)'
)
    comment '接口信息';

create table tb_user
(
    id          bigint auto_increment comment 'id'
        primary key,
    user_name   varchar(256)                       null comment '用户名',
    avatar_url  varchar(1024)                      null comment '头像链接',
    gender      tinyint                            null comment '性别',
    login_name  varchar(256)                       null comment '账号',
    login_pwd   varchar(512)                       not null comment '密码',
    access_key  varchar(512)                       not null comment 'accessKey',
    secret_key  varchar(512)                       not null comment 'secretKey',
    phone       varchar(128)                       null comment '电话',
    email       varchar(512)                       null comment '邮箱',
    status      int      default 0                 not null comment '用户状态',
    user_role   int      default 0                 not null comment '用户角色 0为普通用户 1为管理员',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP null comment '更新时间',
    deleted     tinyint  default 0                 not null comment '是否删除',
    constraint uni_login_name
        unique (login_name)
)
    comment '用户表';

create table tb_user_interface_info
(
    id                bigint auto_increment comment '主键'
        primary key,
    user_id           bigint                             not null comment '调用用户 id',
    interface_info_id bigint                             not null comment '接口 id',
    total_num         int      default 0                 not null comment '总调用次数',
    left_num          int      default 0                 not null comment '剩余调用次数',
    status            int      default 0                 not null comment '0-正常，1-禁用',
    create_time       datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time       datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted           tinyint  default 0                 not null comment '是否删除(0-未删, 1-已删)'
)
    comment '用户调用接口关系';


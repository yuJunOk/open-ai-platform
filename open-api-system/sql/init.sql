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

INSERT INTO `db_multi`.`tb_interface_info` (`name`, `description`, `url`, `request_params`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `deleted`) VALUES ('GetUserName', '获取用户名', 'http://localhost:8081/api/name/user', '[\n	{\"name\": \"userName\", \"type\": \"string\"}\n]', '{\n  \"Content-Type\": \"application/json\"}', '{\n \"Content-Type\": \"application/json\"\n}', 1, 'POST', 1, '2025-04-21 18:37:37', '2025-04-22 22:39:18', 0);

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

INSERT INTO `db_multi`.`tb_user` (`user_name`, `avatar_url`, `gender`, `login_name`, `login_pwd`, `access_key`, `secret_key`, `phone`, `email`, `status`, `user_role`, `create_time`, `update_time`, `deleted`) VALUES ('小a', 'https://vitejs.cn/vite3-cn/logo.svg', 0, '13416393834', '3371e00731ba0313ea06d12d97f0844e', 'f00b0e9cde309651143331bf73772934', 'f00b0e9cde309651143331bf73772934', '13416393834', '1375841038@qq.com', 0, 1, '2025-04-15 00:06:41', '2025-04-15 00:06:41', 0);

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

INSERT INTO `db_multi`.`tb_user_interface_info` ( `user_id`, `interface_info_id`, `total_num`, `left_num`, `status`, `create_time`, `update_time`, `deleted`) VALUES (1, 6, 51, 49, 0, '2025-04-23 00:57:12', '2025-04-23 01:05:16', 0);


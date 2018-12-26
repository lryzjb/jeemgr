drop table t_jeemgr_sys_user cascade constraints;
drop table t_jeemgr_sys_role cascade constraints;
drop table t_jeemgr_sys_user_role cascade constraints;
drop table t_jeemgr_sys_menu cascade constraints;
drop table t_jeemgr_sys_role_menu cascade constraints;

/*==============================================================*/
/* Table: t_jeemgr_sys_user                                */
/*==============================================================*/
CREATE TABLE t_jeemgr_sys_user 
(
   user_id        varchar2(50)       not null,
   username       varchar2(50)       default ' ' not null,
   password       varchar2(200)      default ' ' not null,
   email          varchar2(100)      default ' ' not null,
   mobile         varchar2(100)      default ' ' not null,
   status         NUMBER(5)          default 0 not null,
   create_time    timestamp          default TO_TIMESTAMP('1000-01-01 00:00:00','yyyy-MM-dd hh24:mi:ss') not null,
   create_id      varchar2(50)       default ' ' not null,
   update_time    timestamp          default TO_TIMESTAMP('1000-01-01 00:00:00','yyyy-MM-dd hh24:mi:ss') not null,
   update_id      varchar2(50)       default ' ' not null,
   online_sign    varchar2(20)       default ' ' not null,
   constraint pk_t_jeemgr_sys_user PRIMARY KEY (user_id)
);

comment on table t_jeemgr_sys_user is
'用户表';

comment on column t_jeemgr_sys_user.user_id is
'用户ID';

comment on column t_jeemgr_sys_user.username is
'用户名';

comment on column t_jeemgr_sys_user.password is
'密码';

comment on column t_jeemgr_sys_user.email is
'用户邮箱';

comment on column t_jeemgr_sys_user.mobile is
'用户电话';

comment on column t_jeemgr_sys_user.status is
'用户状态0-正常;1-停用';

comment on column t_jeemgr_sys_user.create_time is
'创建时间';

comment on column t_jeemgr_sys_user.create_id is
'创建ID';

comment on column t_jeemgr_sys_user.update_time is
'修改时间';

comment on column t_jeemgr_sys_user.update_id is
'修改ID';

comment on column t_jeemgr_sys_user.online_sign is
'平台标识';


/*==============================================================*/
/* Table: t_jeemgr_sys_role                                */
/*==============================================================*/
CREATE TABLE t_jeemgr_sys_role 
(
   role_id           varchar2(50)      NOT NULL,
   role_name         varchar2(100)     default ' ' not null,
   creat_id          varchar2(50)      default ' ' not null,
   create_time       timestamp         default TO_TIMESTAMP('1000-01-01 00:00:00','yyyy-MM-dd hh24:mi:ss') not null,
   update_id         varchar2(50)      default ' ' not null,
   update_time       timestamp         default TO_TIMESTAMP('1000-01-01 00:00:00','yyyy-MM-dd hh24:mi:ss') not null,
   online_sign       varchar2(20)      default ' ' not null,
   constraint pk_t_jeemgr_sys_role PRIMARY KEY (role_id)
);

comment on table t_jeemgr_sys_role is
'角色表';

comment on column t_jeemgr_sys_role.role_id is
'角色ID';

comment on column t_jeemgr_sys_role.role_name is
'角色名称';

comment on column t_jeemgr_sys_role.creat_id is
'创建ID';

comment on column t_jeemgr_sys_role.create_time is
'创建时间';

comment on column t_jeemgr_sys_role.update_id is
'修改ID';

comment on column t_jeemgr_sys_role.update_time is
'修改时间';

comment on column t_jeemgr_sys_role.online_sign is
'平台标识';


/*==============================================================*/
/* Table: t_jeemgr_sys_user_role                                */
/*==============================================================*/
CREATE TABLE t_jeemgr_sys_user_role 
(
   id            varchar2(50)          NOT NULL,
   user_id       varchar2(50)          NOT NULL,
   role_id       varchar2(50)          NOT NULL,
   online_sign   varchar2(20)          default ' ' not null,
   constraint pk_t_jeemgr_sys_user_role PRIMARY KEY (id)
);

comment on table t_jeemgr_sys_user_role is
'用户角色表';

comment on column t_jeemgr_sys_user_role.id is
'ID';

comment on column t_jeemgr_sys_user_role.user_id is
'用户ID';

comment on column t_jeemgr_sys_user_role.role_id is
'角色ID';

comment on column t_jeemgr_sys_user_role.online_sign is
'平台标识';


/*==============================================================*/
/* Table: t_jeemgr_sys_menu                                */
/*==============================================================*/
CREATE TABLE t_jeemgr_sys_menu 
(
   menu_id          varchar2(50)        NOT NULL,
   parent_id        varchar2(50)        default ' ' not null,
   name             varchar2(50)        default ' ' not null,
   url              varchar2(200)       default ' ' not null,
   type             varchar2(5)         default ' ' not null,
   icon             varchar2(50)        default ' ' not null,
   level_no         NUMBER(5)           NOT NULL,
   level_seq        NUMBER(5)           NOT NULL,
   online_sign      varchar2(20)        default ' ' not null,
   constraint pk_t_jeemgr_sys_menu PRIMARY KEY (menu_id)
);

comment on table t_jeemgr_sys_menu is
'权限表';

comment on column t_jeemgr_sys_menu.menu_id is
'权限ID';

comment on column t_jeemgr_sys_menu.parent_id is
'父级ID';

comment on column t_jeemgr_sys_menu.name is
'权限名称';

comment on column t_jeemgr_sys_menu.url is
'权限url';

comment on column t_jeemgr_sys_menu.type is
'类型，M菜单;B按钮';

comment on column t_jeemgr_sys_menu.icon is
'权限图标';

comment on column t_jeemgr_sys_menu.level_no is
'级别';

comment on column t_jeemgr_sys_menu.level_seq is
'同级别内序号';

comment on column t_jeemgr_sys_menu.online_sign is
'平台标识';



/*==============================================================*/
/* Table: t_jeemgr_sys_role_menu                                */
/*==============================================================*/
CREATE TABLE t_jeemgr_sys_role_menu (
   id             varchar2(50)         NOT NULL,
   role_id        varchar2(50)         NOT NULL,
   menu_id        varchar2(50)         NOT NULL,
   online_sign    varchar2(20)         default ' ' not null,
   constraint pk_t_jeemgr_sys_role_menu PRIMARY KEY (id)
);
comment on table t_jeemgr_sys_role_menu is
'角色权限表';

comment on column t_jeemgr_sys_role_menu.role_id is
'角色ID';

comment on column t_jeemgr_sys_role_menu.menu_id is
'权限ID';

comment on column t_jeemgr_sys_role_menu.online_sign is
'平台标识';

/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     17.02.2016 15:11:03                          */
/*==============================================================*/


/*==============================================================*/
/* Table: BRANCH                                                */
/*==============================================================*/
create table BRANCH 
(
   BRANCH_ID            INTEGER              not null,
   BRANCH_CODE          VARCHAR2(20 CHAR)    not null,
   BRANCH_CODE_DESC     VARCHAR2(200 CHAR),
   CITY                 VARCHAR2(40 CHAR)    not null,
   STREET               VARCHAR2(30 CHAR)    not null,
   HOME                 VARCHAR2(20 CHAR),
   OFFICE               VARCHAR2(10 CHAR),
   POSTCODE             VARCHAR2(10 CHAR),
   PHONE                VARCHAR2(15 CHAR),
   CREATED_BY           VARCHAR2(40 CHAR)    not null,
   CREATED_DATE         DATE                 default SYSDATE not null,
   constraint PK_BRANCH_ID primary key (BRANCH_ID)
)
/

comment on table BRANCH is
'Таблица филиалов'
/

comment on column BRANCH.BRANCH_ID is
'Уникальный ID филиала'
/

comment on column BRANCH.CITY is
'Название города'
/

comment on column BRANCH.STREET is
'Название улицы'
/

comment on column BRANCH.HOME is
'Номер дома'
/

comment on column BRANCH.OFFICE is
'Номер офиса'
/

comment on column BRANCH.POSTCODE is
'Почтовый индекс'
/

comment on column BRANCH.PHONE is
'Номер телефона'
/

comment on column BRANCH.CREATED_BY is
'Автор'
/

comment on column BRANCH.CREATED_DATE is
'Дата записи'
/

/*==============================================================*/
/* Table: CLIENTS_CHILDREN                                      */
/*==============================================================*/
create table CLIENTS_CHILDREN 
(
   CHILDREN_ID          INTEGER              not null,
   BRANCH_ID            INTEGER              not null,
   LAST_NAME            VARCHAR2(50 CHAR)    not null,
   FIRST_NAME           VARCHAR2(50 CHAR)    not null,
   MIDDLE_NAME          VARCHAR2(50 CHAR),
   AGE                  SMALLINT             not null,
   SEX                  CHAR(1 CHAR)         not null,
   MED_CERTEF_FLG       CHAR(1 CHAR)         default 'N' not null,
   LOYALTY_FLG          CHAR(1 CHAR)         default 'N' not null,
   DISCOUNTS_DESC       VARCHAR2(1000 CHAR),
   NOTES                VARCHAR2(1000 CHAR),
   CREATED_BY           VARCHAR2(40 CHAR)    not null,
   CREATED_DATE         DATE                 default SYSDATE not null,
   constraint PK_CHILDREN_ID primary key (CHILDREN_ID)
)
/

comment on table CLIENTS_CHILDREN is
'Таблица детей занимающихся в секциях'
/

comment on column CLIENTS_CHILDREN.CHILDREN_ID is
'Уникальный ID ребёнка'
/

comment on column CLIENTS_CHILDREN.BRANCH_ID is
'Внешний ID для связи ребёнка с филиалом'
/

comment on column CLIENTS_CHILDREN.LAST_NAME is
'Фамилия ребёнка'
/

comment on column CLIENTS_CHILDREN.FIRST_NAME is
'Имя ребёнка'
/

comment on column CLIENTS_CHILDREN.MIDDLE_NAME is
'Отчество ребёнка'
/

comment on column CLIENTS_CHILDREN.AGE is
'Возраст ребёнка'
/

comment on column CLIENTS_CHILDREN.SEX is
'Пол ребёнка'
/

comment on column CLIENTS_CHILDREN.MED_CERTEF_FLG is
'Флаг предоставленной медицинской справки. Предоставлена - Y. Не предоставлена - N'
/

comment on column CLIENTS_CHILDREN.LOYALTY_FLG is
'Флаг лояльности клиента. Постоянный клиент - Y. Не постоянный клиент - N'
/

comment on column CLIENTS_CHILDREN.DISCOUNTS_DESC is
'Описание персональных скидок клиента'
/

comment on column CLIENTS_CHILDREN.NOTES is
'Дополнительные примечания о ребёнке'
/

comment on column CLIENTS_CHILDREN.CREATED_BY is
'Автор'
/

comment on column CLIENTS_CHILDREN.CREATED_DATE is
'Дата записи'
/

/*==============================================================*/
/* Table: CLIENTS_PARENT                                        */
/*==============================================================*/
create table CLIENTS_PARENT 
(
   PARENT_ID            INTEGER              not null,
   CHILDREN_ID          INTEGER              not null,
   LAST_NAME            VARCHAR2(50 CHAR)    not null,
   FIRST_NAME           VARCHAR2(50 CHAR)    not null,
   MIDDLE_NAME          VARCHAR2(50 CHAR),
   MOB_PHONE            VARCHAR2(15 CHAR)    not null,
   EMAIL                VARCHAR2(40 CHAR),
   ADD_MOB_PHONE        VARCHAR2(15 CHAR),
   OWNR_ADD_MOB_PHONE   VARCHAR2(150 CHAR),
   NOTES                VARCHAR2(1000 CHAR),
   CREATED_BY           VARCHAR2(40 CHAR)    not null,
   CREATED_DATE         DATE                 default SYSDATE not null,
   constraint PK_PARENT_ID primary key (PARENT_ID)
)
/

comment on table CLIENTS_PARENT is
'Таблица родителей, чьи дети занимаются в секциях'
/

comment on column CLIENTS_PARENT.PARENT_ID is
'Уникальный ID Родителя'
/

comment on column CLIENTS_PARENT.CHILDREN_ID is
'Внешний ID для связи родителя с ребёнком'
/

comment on column CLIENTS_PARENT.LAST_NAME is
'Фамилия родителя'
/

comment on column CLIENTS_PARENT.FIRST_NAME is
'Имя родителя'
/

comment on column CLIENTS_PARENT.MIDDLE_NAME is
'Отчество родителя'
/

comment on column CLIENTS_PARENT.MOB_PHONE is
'Мобильный телефон родителя'
/

comment on column CLIENTS_PARENT.EMAIL is
'Электронная почта родителя'
/

comment on column CLIENTS_PARENT.ADD_MOB_PHONE is
'Дополнительный мобильный телефон'
/

comment on column CLIENTS_PARENT.OWNR_ADD_MOB_PHONE is
'Владелец дополнительного мобильного телефона'
/

comment on column CLIENTS_PARENT.NOTES is
'Дополнительные примечания о родителе'
/

comment on column CLIENTS_PARENT.CREATED_BY is
'Автор'
/

comment on column CLIENTS_PARENT.CREATED_DATE is
'Дата записи'
/

/*==============================================================*/
/* Table: CONTRACT                                              */
/*==============================================================*/
create table CONTRACT 
(
   CONTRACT_ID          INTEGER              not null,
   CHILD_CONTR_ID       INTEGER              not null,
   CONTRACT_NUMBER      VARCHAR2(50 CHAR)    not null,
   CONTRACT_DATE        DATE                 not null,
   CONTRACT_TYPE        VARCHAR2(20 CHAR)    default 'Single ticket' not null,
   NUMBER_OF_CLASSES    SMALLINT             not null,
   CONTRACT_VALUE       NUMBER(10,2)         not null,
   EXPARETION_DATE      DATE,
   CREATED_BY           VARCHAR2(40 CHAR)    not null,
   CREATED_DATE         DATE                 default SYSDATE not null,
   constraint PK_CONTRACT_ID primary key (CONTRACT_ID)
)
/

comment on table CONTRACT is
'Таблица договоров'
/

comment on column CONTRACT.CONTRACT_ID is
'Уникальный ID договора'
/

comment on column CONTRACT.CHILD_CONTR_ID is
'Внешний ID договора для связи с ребёнком'
/

comment on column CONTRACT.CONTRACT_NUMBER is
'Номер договора'
/

comment on column CONTRACT.CONTRACT_DATE is
'Дата заключения договора'
/

comment on column CONTRACT.CONTRACT_TYPE is
'Тип договора. Разовый - Single ticket. Абонемент - Season ticket.'
/

comment on column CONTRACT.NUMBER_OF_CLASSES is
'Количество оплаченных занятий'
/

comment on column CONTRACT.CONTRACT_VALUE is
'Стоимость договора'
/

comment on column CONTRACT.CREATED_BY is
'Автор'
/

comment on column CONTRACT.CREATED_DATE is
'Дата записи'
/

/*==============================================================*/
/* Table: EMPLOYEES                                             */
/*==============================================================*/
create table EMPLOYEES 
(
   EMPLOYEE_ID          INTEGER              not null,
   BRANCH_ID            INTEGER,
   POSITION             VARCHAR2(50 CHAR)    not null,
   LAST_NAME            VARCHAR2(50 CHAR)    not null,
   FIRST_NAME           VARCHAR2(50 CHAR)    not null,
   MIDDLE_NAME          VARCHAR2(50 CHAR),
   PHONE                VARCHAR2(15 CHAR),
   EMAIL                VARCHAR2(40 CHAR),
   NOTES                VARCHAR2(1000 CHAR),
   CREATED_BY           VARCHAR2(40 CHAR)    not null,
   CREATED_DATE         DATE                 default SYSDATE not null,
   constraint PK_EMPLOYEES_ID primary key (EMPLOYEE_ID)
)
/

comment on table EMPLOYEES is
'Таблица сотрудников'
/

comment on column EMPLOYEES.EMPLOYEE_ID is
'Уникальный ID сотрудника'
/

comment on column EMPLOYEES.BRANCH_ID is
'Внешний ID для связи сотрудника с филиалом'
/

comment on column EMPLOYEES.POSITION is
'Должность сотрудника'
/

comment on column EMPLOYEES.LAST_NAME is
'Фамилия сотрудника'
/

comment on column EMPLOYEES.FIRST_NAME is
'Имя сотрудника'
/

comment on column EMPLOYEES.MIDDLE_NAME is
'Отчество сотрудника'
/

comment on column EMPLOYEES.PHONE is
'Телефон сотрудника'
/

comment on column EMPLOYEES.EMAIL is
'Электронная почта сотрудника'
/

comment on column EMPLOYEES.NOTES is
'Дополнительные примечания о сотруднике'
/

comment on column EMPLOYEES.CREATED_BY is
'Автор'
/

comment on column EMPLOYEES.CREATED_DATE is
'Дата записи'
/

/*==============================================================*/
/* Table: MEDICAL_CERTIFICATE                                   */
/*==============================================================*/
create table MEDICAL_CERTIFICATE 
(
   CERTEFICATION_ID     INTEGER              not null,
   CHILD_CERT_ID        INTEGER              not null,
   HOSPITAL             VARCHAR2(100 CHAR)   not null,
   DOCTOR               VARCHAR2(150 CHAR)   not null,
   CONCLUSION           VARCHAR2(1000 CHAR)  not null,
   EXPARETION_DATE      DATE                 not null,
   CREATED_BY           VARCHAR2(40 CHAR)    not null,
   CREATED_DATE         DATE                 default SYSDATE not null,
   constraint PK_CERTEFICATION_ID primary key (CERTEFICATION_ID)
)
/

comment on table MEDICAL_CERTIFICATE is
'Медицинские справки'
/

comment on column MEDICAL_CERTIFICATE.CERTEFICATION_ID is
'Уникальный ID справки'
/

comment on column MEDICAL_CERTIFICATE.CHILD_CERT_ID is
'Внешний ID справки для связи с ребёнком'
/

comment on column MEDICAL_CERTIFICATE.HOSPITAL is
'Больница, где выдали справку'
/

comment on column MEDICAL_CERTIFICATE.DOCTOR is
'Врач, который выдал справку'
/

comment on column MEDICAL_CERTIFICATE.CONCLUSION is
'Заключение врача, который выдавал справку'
/

comment on column MEDICAL_CERTIFICATE.CREATED_BY is
'Автор'
/

comment on column MEDICAL_CERTIFICATE.CREATED_DATE is
'Дата записи'
/

/*==============================================================*/
/* Table: ROLES                                                 */
/*==============================================================*/
create table ROLES 
(
   ROLE_ID              INTEGER              not null,
   USER_ID              INTEGER              not null,
   ROLE                 VARCHAR2(40 CHAR)    not null,
   ROLE_DESC            VARCHAR2(1000 CHAR),
   CREATED_BY           VARCHAR2(40 CHAR)    not null,
   CREATED_DATE         DATE                 default SYSDATE not null,
   constraint PK_ROLE_ID primary key (ROLE_ID)
)
/

comment on table ROLES is
'Таблица ролей'
/

comment on column ROLES.ROLE_ID is
'Уникальный ID роли'
/

comment on column ROLES.USER_ID is
'Внешний ID для связи роли с пользователем'
/

comment on column ROLES.ROLE is
'Роль'
/

comment on column ROLES.ROLE_DESC is
'Детальное описание роли'
/

comment on column ROLES.CREATED_BY is
'Автор записи'
/

comment on column ROLES.CREATED_DATE is
'Дата записи'
/

/*==============================================================*/
/* Table: TRAINING                                              */
/*==============================================================*/
create table TRAINING 
(
   TRAINING_ID          INTEGER              not null,
   CHILD_TRAINING_ID    INTEGER,
   TRAINING_DESC        VARCHAR2(200 CHAR),
   TRAINING_COACH       VARCHAR2(150 CHAR),
   PILOT_TRAINING       CHAR(1 CHAR)         default 'N' not null,
   TRAINING_DATE        DATE                 not null,
   CREATED_BY           VARCHAR2(40 CHAR)    not null,
   CREATED_DATE         DATE                 default SYSDATE not null,
   constraint PK_TRAINING_ID primary key (TRAINING_ID)
)
/

comment on table TRAINING is
'Таблица учёта тренировок'
/

comment on column TRAINING.PILOT_TRAINING is
'Пробная тренировка. Разрешена один раз. Использована - Y, Не использована - N'
/

comment on column TRAINING.CREATED_BY is
'Автор'
/

comment on column TRAINING.CREATED_DATE is
'Дата записи'
/

/*==============================================================*/
/* Table: USERS                                                 */
/*==============================================================*/
create table USERS 
(
   USER_ID              INTEGER              not null,
   EMPLOYEE_ID          INTEGER              not null,
   LOGIN                VARCHAR2(50 CHAR)    not null,
   PASSWD               VARCHAR2(100 CHAR)   not null,
   FIRST_LOGIN          CHAR(1 CHAR)         default 'Y' not null,
   CREATED_BY           VARCHAR2(40 CHAR)    not null,
   CREATED_DATE         DATE                 default SYSDATE not null,
   constraint PK_USER_ID primary key (USER_ID)
)
/

comment on table USERS is
'Таблица пользователе и ролей'
/

comment on column USERS.USER_ID is
'Уникальный ID пользователя'
/

comment on column USERS.EMPLOYEE_ID is
'Внешний ID для связи пользователя с сотрудником'
/

comment on column USERS.LOGIN is
'Логин'
/

comment on column USERS.PASSWD is
'Пароль'
/

comment on column USERS.FIRST_LOGIN is
'Флаг первого входа'
/

comment on column USERS.CREATED_BY is
'Автор'
/

comment on column USERS.CREATED_DATE is
'Дата записи'
/

alter table CLIENTS_CHILDREN
   add constraint FK_BRNCH_CHILD_BRANCH_ID foreign key (BRANCH_ID)
      references BRANCH (BRANCH_ID)
/

alter table CLIENTS_PARENT
   add constraint FK_CHILDREN_ID foreign key (CHILDREN_ID)
      references CLIENTS_CHILDREN (CHILDREN_ID)
/

alter table CONTRACT
   add constraint FK_CHILD_CONTR_ID foreign key (CHILD_CONTR_ID)
      references CLIENTS_CHILDREN (CHILDREN_ID)
/

alter table EMPLOYEES
   add constraint FK_BRNCH_EMPL_BRANCH_ID foreign key (BRANCH_ID)
      references BRANCH (BRANCH_ID)
/

alter table MEDICAL_CERTIFICATE
   add constraint FK_CHILD_CERT_ID foreign key (CHILD_CERT_ID)
      references CLIENTS_CHILDREN (CHILDREN_ID)
/

alter table ROLES
   add constraint FK_USER_ID foreign key (USER_ID)
      references USERS (USER_ID)
/

alter table TRAINING
   add constraint FK_CHILD_TRAINING_ID foreign key (CHILD_TRAINING_ID)
      references CLIENTS_CHILDREN (CHILDREN_ID)
/

alter table USERS
   add constraint FK_EMPLOYEE_ID foreign key (EMPLOYEE_ID)
      references EMPLOYEES (EMPLOYEE_ID)
/


CREATE SEQUENCE EMPLOYEES_SEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE;
/


CREATE SEQUENCE ROLES_SEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE;
/


CREATE SEQUENCE USERS_SEQ
START WITH 1
INCREMENT BY 1
NOMAXVALUE;
/


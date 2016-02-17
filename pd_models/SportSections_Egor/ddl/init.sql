insert into EMPLOYEES(EMPLOYEE_ID, BRANCH_ID, POSITION, LAST_NAME, FIRST_NAME, MIDDLE_NAME,
                      PHONE, NOTES, CREATED_BY)
values (null, null, 'Технический Администратор', 'Технический Администратор', 'Технический Администратор',
        null, null, 'Данный сотрудник отвечает только за заведение в системе Администраторов', 
        'Инициализация системы');
commit;

insert into USERS(USER_ID, EMPLOYEE_ID, LOGIN, PASSWD, CREATED_BY)
values (null, 1, 'Technical Administrator', 'b50303af0efffbbc77c3d0fe3f8f1790', 'Инициализация системы');
commit;

insert into ROLES(ROLE_ID, USER_ID, ROLE, ROLE_DESC, CREATED_BY)
values (null, 1, 'TECH_ADMIN', 'Роль технического администратора', 'Инициализация системы');
commit;

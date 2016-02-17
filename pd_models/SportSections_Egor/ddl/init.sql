insert into EMPLOYEES(EMPLOYEE_ID, BRANCH_ID, POSITION, LAST_NAME, FIRST_NAME, MIDDLE_NAME,
                      PHONE, NOTES, CREATED_BY, CREATED_DATE)
select employees_seq.nextval as EMPLOYEE_ID,
       null as BRANCH_ID,
       '����������� �������������' as POSITION,
       '����������� �������������' as LAST_NAME,
       '����������� �������������' as FIRST_NAME,
       null as MIDDLE_NAME,
       null as PHONE,
       '������ ��������� �������� ������ �� ��������� � ������� ���������������' as NOTES,
       '������������� �������' as CREATED_BY,
       sysdate as CREATED_DATE
from dual;
commit;

insert into USERS(USER_ID, EMPLOYEE_ID, LOGIN, PASSWD, FIRST_LOGIN, CREATED_BY, CREATED_DATE)
select users_seq.nextval as USER_ID,
       employees_seq.currval as EMPLOYEE_ID,
       'Technical Administrator' as LOGIN,
       'b50303af0efffbbc77c3d0fe3f8f1790' as PASSWD,
       'Y' as FIRST_LOGIN,
       '������������� �������',
       sysdate as CREATED_DATE
from dual;
commit;

insert into ROLES(ROLE_ID, USER_ID, ROLE, ROLE_DESC, CREATED_BY, CREATED_DATE)
select roles_seq.nextval as ROLE_ID,
       users_seq.currval as USER_ID,
       'TECH_ADMIN' as ROLE,
       '���� ������������ ��������������' as ROLE_DESC,
       '������������� �������' as CREATED_BY,
       sysdate as CREATED_DATE
from dual;
commit;

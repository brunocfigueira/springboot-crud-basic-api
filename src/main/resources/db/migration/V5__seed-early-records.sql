-- profiles
insert into profiles (name, description, acronym)
values ('Root', 'Profile Root', 'ROOT'),
       ('Administrador', 'Administrador de Sistema', 'ADM'),
       ('Funcionário', 'Usuário Padrão de Sistema', 'FUN');

-- user root
-- password: root
insert into users(profile_id, username, login, email, password)
values (1, 'root', 'root', 'root@root.com.br', '$2a$12$YD5jwmt1Do4JPh3nMK7an.lF4l7mcjWINJT2iYcsaVl2NmIp5UE2.');

-- permissions
insert into permissions(name, description)
values ('GRANT_ALL', 'Permission only for user root'),
       ('CRUD', 'Permission for crud records'),
       ('CREATE', 'Permission for create records'),
       ('READ', 'Permission for show details records'),
       ('UPDATE', 'Permission for update records'),
       ('DELETE', 'Permission for remove records'),
       ('SEARCH', 'Permission for search records');

-- profile_permission
insert into profile_permission(profile_id, permission_id)
values (1, 1),
       (2, 2),
       (3, 3),
       (3, 4),
       (3, 5),
       (3, 6),
       (3, 7);


insert into users_dev (username,name,lastname,password,email,enabled) values ('carlos23','Carlos','Perez','12345','carlos@gmail.com',true);
insert into users_dev (username,name,lastname,password,email,enabled) values ('jose23','Jose','Perez','12345','jose@gmail.com',true);


INSERT INTO roles_dev (name_role) VALUES ('ROLE_USER');
INSERT INTO roles_dev (name_role) VALUES ('ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);
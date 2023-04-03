CREATE TABLE products
(
    id    bigserial primary key,
    title varchar(255),
    price int
);

INSERT INTO products (title, price)
values ('Milk', 100),
       ('Bread', 120),
       ('Cheese', 60);



CREATE TABLE users
(
    id       bigserial primary key,
    username varchar(36) not null,
    password varchar(80) not null
);

CREATE TABLE roles
(
    id   bigserial primary key,
    name varchar(50) not null
);

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

INSERT INTO roles(name)
VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users (username, password)
VALUES ('bob', '$2a$12$AhKMSub0SuWtXrjGfC8P7.cdIoBE2cOFjBqy0vKKwPhWYhleIP0Pm'),
       ('John', '$2a$12$AhKMSub0SuWtXrjGfC8P7.cdIoBE2cOFjBqy0vKKwPhWYhleIP0Pm');


INSERT INTO users_roles(user_id, role_id)
VALUES (1, 1),
       (2, 2);
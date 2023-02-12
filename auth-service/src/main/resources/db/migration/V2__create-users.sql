
create table users (
    id bigserial primary key,
    username varchar(36) not null unique,
    password varchar(255) not null );

create table roles (
    id bigserial primary key,
    "name" varchar(50) not null);

create table users_roles (
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id));


insert into roles (name) values ('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password) values
    ('artjom', '1234');

insert into users_roles (user_id, role_id)
    values (1, 1);


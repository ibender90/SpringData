create table categories (
    id bigserial primary key,
    title varchar(255) unique,
    product_id bigint
    );

create table products (
    id bigserial primary key,
    "name" varchar(255),
    price decimal(12, 2),
    category_id bigint references categories (id));

create table users (
    id bigserial primary key,
    first_name varchar(36),
    last_name varchar(36),
    email varchar(80) unique ,
    password varchar(255));

create table roles (
    id bigserial primary key,
    "name" varchar(50) not null);

create table users_roles (
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id));


insert into roles (name) values ('ROLE_USER'), ('ROLE_ADMIN');

insert into users (first_name, last_name, email, password) values
    ('Artjom', 'Stalev', 'stalev.artjom@gmail.com', '$2a$12$ZvZWpJrXUEWnggbLeaS7Xu9JejWFlU1gjqfh.jSbp/JYgTAiuhEA.');

insert into users_roles (user_id, role_id)
    values (1, 1);

create table carts (
    id bigserial primary key,
    user_id bigint not null,
    product_id bigint,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users (id),
    CONSTRAINT fk_product FOREIGN KEY(product_id) REFERENCES products (id));


insert into categories (title) values ('Other vegetables'), ('Exotic vegetables');

create table orders (
    id bigserial primary key,
    user_id bigint references users (id),
    total_price_from_cart decimal(12, 2)
);

create table order_items
(
    id                      bigserial primary key,
    order_id                bigint references users (id),
    product_id              bigint references products (id),
    price_per_product       decimal(12, 2),
    quantity                int,
    total_price             decimal(12, 2)
);
create table categories (
    id bigserial primary key,
    title varchar(255) unique,
    product_id bigint
    );

create table products (
    id bigserial primary key,
    "name" varchar(255),
    price numeric(8, 2),
    category_id bigint references categories (id));


create table carts (
    id bigserial primary key,
    user_name varchar(255) not null,
    product_id bigint,
    CONSTRAINT fk_product FOREIGN KEY(product_id) REFERENCES products (id));


insert into categories (title) values ('Other vegetables'), ('Exotic vegetables');

create table orders (
    id bigserial primary key,
    user_name varchar(255),
    total_price_from_cart numeric(8, 2)
);

create table order_items
(
    id                      bigserial primary key,
    order_id                bigint references orders,
    product_id              bigint references products (id),
    price_per_product       numeric(8, 2),
    quantity                int,
    total_price             numeric(8, 2)
);


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


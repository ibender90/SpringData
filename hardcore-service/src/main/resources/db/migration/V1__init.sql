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


create table carts (
    id bigserial primary key,
    user_name varchar(255) not null,
    product_id bigint,
    CONSTRAINT fk_product FOREIGN KEY(product_id) REFERENCES products (id));


insert into categories (title) values ('Other vegetables'), ('Exotic vegetables');

create table orders (
    id bigserial primary key,
    user_name varchar(255),
    total_price_from_cart decimal(12, 2)
);

create table order_items
(
    id                      bigserial primary key,
    order_id                bigint references orders,
    product_id              bigint references products (id),
    price_per_product       decimal(12, 2),
    quantity                int,
    total_price             decimal(12, 2)
);
CREATE SCHEMA IF NOT EXISTS operation;
CREATE TABLE IF NOT EXISTS operation.purchase_carts
(
    id uuid not null
    constraint purchase_carts_pkey primary key,
    product_id uuid,
    quantity int,
    price decimal,
    purchase_id uuid
);
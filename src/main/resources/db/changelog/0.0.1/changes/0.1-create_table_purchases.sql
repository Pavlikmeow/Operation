CREATE SCHEMA IF NOT EXISTS operation;
CREATE TABLE IF NOT EXISTS operation.purchases
(
    id uuid not null
    constraint purchases_pkey primary key,
    user_id uuid,
    date date
);
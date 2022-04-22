CREATE TABLE IF NOT EXISTS product (

    id bigserial NOT NULL PRIMARY KEY,
    name varchar(25),
    category varchar(20),
    substrate varchar(50),
    color varchar(4),
    height integer,
    width integer,
    finishings text[],
    quantity integer,
    price decimal,
    created_at date

);


create table customer (
id int primary key,
name varchar
);

create table customer_address (
customer_id int ,
type varchar,
name varchar,
street varchar,
code varchar,
city varchar,
constraint pk_customer_address primary key(customer_id, type),
constraint fk_customer_address_customer foreign key  (customer_id) references customer (id)
);


create table users(
  id serial primary key,
  name text not null,
  password_hash text not null,
  salt text not null
);


create table source(
  id serial primary key,
  address text not null,
  login text,
  password text
);


create table downloads(
  id serial primary key,
  source integer references source(id),
  time timestamp not null default current_timestamp,
  who integer references users(id),
  path text not null
);

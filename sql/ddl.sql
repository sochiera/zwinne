create table users(
  id integer primary key,
  name text not null,
  password_hash text not null,
  salt text not null
);


create table source(
  id integer primary key,
  address text not null,
  login text,
  password text
);


create table downloads(
  id integer primary key,
  source integer refrerences source(id),
  when timestamp not null default current_timestamp(),
  who integer references users(id),
  path text not null
);

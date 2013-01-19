begin;

create table users(
  id serial primary key,
  name text not null,
  password_hash text not null,
  salt text not null
);


create table sources(
  id serial primary key,
  protocol text not null,
  address text not null,
  path text not null,
  login text,
  password text
);


create table downloads(
  id serial primary key,
  source integer references sources(id) on delete set null,
  time timestamp not null default current_timestamp,
  who integer references users(id),
  path text not null
);

insert into users values 
  (default, 'zenon', 'asdfasdfasdfasd', 'ddd');


grant all on users to greedy;
grant all on users_id_seq to greedy;

grant all on sources to greedy;
grant all on sources_id_seq to greedy;

grant all on downloads to greedy;
grant all on downloads_id_seq to greedy;

commit;

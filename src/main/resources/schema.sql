create sequence hibernate_sequence start with 1 increment by 1;
create table if not exists race (id bigint not null, country varchar(255), end_time varchar(255), start_time varchar(255), title varchar(255), primary key (id));
create table if not exists race_result (id bigint not null, race_id bigint, primary key (id));
create table if not exists rider (id bigint not null, age integer, duration double, email varchar(255), name varchar(255) not null, race_riders bigint, primary key (id));

create table IF NOT EXISTS specie (
  id int not null AUTO_INCREMENT,
  name varchar(50) not null,
  PRIMARY KEY ( id )
);

create table if not exists breederqr.breeder
(
    id               int auto_increment
    primary key,
    created_at       datetime(6)  null,
    created_by       int          null,
    deleted          bit          null,
    deleted_at       datetime(6)  null,
    deleted_by       int          null,
    last_name        varchar(20)  not null,
    mail             varchar(30)  not null,
    name             varchar(20)  not null,
    password         varchar(200) not null,
    second_last_name varchar(20)  not null,
    updated_at       datetime(6)  null,
    updated_by       int          null,
    username         varchar(20)  not null
    );
create table IF NOT EXISTS specie (
  id int not null AUTO_INCREMENT,
  name varchar(50) not null,
  PRIMARY KEY ( id )
);

create table IF NOT EXISTS breeder (
  id int not null AUTO_INCREMENT,
  name varchar(20) not null,
  last_name varchar(20) not null,
  second_last_name varchar(20) not null,
  username varchar(20) not null,
  password varchar(20) not null,
  mail varchar(30) not null,
  PRIMARY KEY ( id )
);

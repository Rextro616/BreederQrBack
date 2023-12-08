create table IF NOT EXISTS specie (
  id int not null AUTO_INCREMENT,
  name varchar(50) not null,
  PRIMARY KEY ( id )
);

create table if not exists breederqr.breeder
(
    id               int auto_increment primary key,
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

CREATE TABLE if not exists breeding_place (
  id int NOT NULL AUTO_INCREMENT,
  address varchar(20) NOT NULL,
  created_at datetime(6) DEFAULT NULL,
  created_by int DEFAULT NULL,
  deleted bit(1) DEFAULT NULL,
  deleted_at datetime(6) DEFAULT NULL,
  deleted_by int DEFAULT NULL,
  description varchar(20) NOT NULL,
  logo varchar(200) NOT NULL,
  name varchar(20) NOT NULL,
  register_number varchar(20) NOT NULL,
  updated_at datetime(6) DEFAULT NULL,
  updated_by int DEFAULT NULL,
  id_breeder int NOT NULL,
  PRIMARY KEY (id),
  KEY FKcfqgqgp84i0r0wpnlv0gjmefy (id_breeder),
  CONSTRAINT FKcfqgqgp84i0r0wpnlv0gjmefy FOREIGN KEY (id_breeder) REFERENCES breeder (id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE if not exists animal (
  id int NOT NULL AUTO_INCREMENT,
  birthday varchar(20) NOT NULL,
  created_at datetime(6) DEFAULT NULL,
  created_by int DEFAULT NULL,
  deleted bit(1) DEFAULT NULL,
  deleted_at datetime(6) DEFAULT NULL,
  deleted_by int DEFAULT NULL,
  description varchar(200) NOT NULL,
  gender varchar(1) NOT NULL,
  name varchar(20) DEFAULT NULL,
  qr varchar(200) NOT NULL,
  register_number varchar(20) NOT NULL,
  updated_at datetime(6) DEFAULT NULL,
  updated_by int DEFAULT NULL,
  id_breeding_place int NOT NULL,
  id_specie int NOT NULL,
  PRIMARY KEY (id),
  KEY FK2pw9hkf6nh2je544bowuprgoq (id_breeding_place),
  KEY FKk60fmmjc49n3ufxm4cgxat86m (id_specie),
  CONSTRAINT FK2pw9hkf6nh2je544bowuprgoq FOREIGN KEY (id_breeding_place) REFERENCES breeding_place (id),
  CONSTRAINT FKk60fmmjc49n3ufxm4cgxat86m FOREIGN KEY (id_specie) REFERENCES specie (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE if not exists laying (
  id int NOT NULL AUTO_INCREMENT,
  amount int NOT NULL,
  created_at datetime(6) DEFAULT NULL,
  created_by int DEFAULT NULL,
  deads int DEFAULT NULL,
  deleted bit(1) DEFAULT NULL,
  deleted_at datetime(6) DEFAULT NULL,
  deleted_by int DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  updated_by int DEFAULT NULL,
  id_animal int NOT NULL,
  PRIMARY KEY (id),
  KEY FK424judptye4vso7bk9lb199n2 (id_animal),
  CONSTRAINT FK424judptye4vso7bk9lb199n2 FOREIGN KEY (id_animal) REFERENCES animal (id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE if not exists photo (
  id int NOT NULL AUTO_INCREMENT,
  created_at datetime(6) DEFAULT NULL,
  created_by int DEFAULT NULL,
  deleted bit(1) DEFAULT NULL,
  deleted_at datetime(6) DEFAULT NULL,
  deleted_by int DEFAULT NULL,
  photo varchar(200) NOT NULL,
  updated_at datetime(6) DEFAULT NULL,
  updated_by int DEFAULT NULL,
  id_animal int NOT NULL,
  PRIMARY KEY (id),
  KEY FKsg8hqe58hyj74uwh94fmjqlk2 (id_animal),
  CONSTRAINT FKsg8hqe58hyj74uwh94fmjqlk2 FOREIGN KEY (id_animal) REFERENCES animal (id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table users (   
  username varchar(50) not null primary key,
  password varchar(255) not null,
  enabled boolean not null) ;

create table authorities (
  username varchar(50) not null,
  authority varchar(50) not null,
  foreign key (username) references users (username),
  unique index authorities_idx_1 (username, authority));

INSERT INTO users(username,password,enabled)
VALUES('user', '$2a$12$58ayKeegWPF5P6SrIkhVb.GzZ8qa9vcsEUgha0fEBqe73iTovVKuW', '1'),('admin', '$2a$12$damCEuV8QcoAWKALCSH5A.1FRCWn0452Werg2j18odaYjBqtwYcvq', '1');

INSERT INTO authorities(username,authority) 
VALUES ('user', 'ROLE_USER'),('admin', 'ROLE_ADMIN');
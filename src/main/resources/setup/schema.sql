CREATE SEQUENCE visitor_seq;

CREATE TABLE visitor (
  id bigint NOT NULL DEFAULT NEXTVAL ('visitor_seq'),
  ip_address varchar(255) NOT NULL,
    country_code varchar(255) NOT NULL,
  country_name varchar(255) NOT NULL,
  region varchar(255) NOT NULL,
  region_name varchar(255) NOT NULL,
  city varchar(255) NOT NULL,
  postal_code varchar(255) NOT NULL,
  latitude varchar(255) NOT NULL,
    longtitude varchar(255) NOT NULL,
  visited_at timestamp(0) DEFAULT NULL,

  PRIMARY KEY (id)
)

alter sequence visitor_seq restart with 0;



CREATE TABLE authorities (
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  CONSTRAINT authorities_idx_1 UNIQUE  (username,authority),
  CONSTRAINT authorities_ibfk_1 FOREIGN KEY (username) REFERENCES users (username)
)



CREATE TABLE users (
  username varchar(50) NOT NULL,
  password varchar(255) NOT NULL,
  enabled smallint NOT NULL,
  PRIMARY KEY (username)
)

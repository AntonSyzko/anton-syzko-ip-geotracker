INSERT INTO visitor (ipAddress,countryCode,countryName,region,regionName,city,postalCode,latitude,longtitude,visitedAt) VALUES
  ('127.0.0.0.1','US','USA','Texas','Texas','Texas','22','123.123','3453.3453','2016-09-29 00:00:00');


-- CREATE TABLE `users` (
--   `username` varchar(50) NOT NULL,
--   `password` varchar(255) NOT NULL,
--   `enabled` tinyint(1) NOT NULL,
--   PRIMARY KEY (`username`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8

INSERT INTO users (username,password,enabled) VALUES
('test','$2a$10$VE93gbba.AsZxOv.uTY4kuLoYGaYt5fKABjGXdDROvrVSY1PSMbVy',1);

-- CREATE TABLE authorities (
--   username varchar(50) NOT NULL,
--   authority varchar(50) NOT NULL,
--   CONSTRAINT authorities_idx_1 UNIQUE  (username,authority),
--   CONSTRAINT authorities_ibfk_1 FOREIGN KEY (username) REFERENCES users (username)
-- )

INSERT INTO authorities (
username,authority
) VALUES ('test','ADMIN');
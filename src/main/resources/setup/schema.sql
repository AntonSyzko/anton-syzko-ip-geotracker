CREATE TABLE `visitor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ipAddress` varchar(255) NOT NULL,
    `countryCode` varchar(255) NOT NULL,
  `countryName` varchar(255) NOT NULL,
  `region` varchar(255) NOT NULL,
  `regionName` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `postalCode` varchar(255) NOT NULL,
  `latitude` varchar(255) NOT NULL,
    `longtitude` varchar(255) NOT NULL,
  `visitedAt` datetime DEFAULT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8




CREATE TABLE users (
  username VARCHAR(100) NOT NULL PRIMARY KEY,
  password VARCHAR(100) NOT NULL,
  enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
  username VARCHAR(100) NOT NULL,
  authority VARCHAR(100) NOT NULL,

  CONSTRAINT fk_authorities_users
  FOREIGN KEY(username) REFERENCES users(username)
);
CREATE UNIQUE INDEX ix_auth_username ON authorities(username,authority);




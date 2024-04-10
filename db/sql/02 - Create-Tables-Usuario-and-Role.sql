 CREATE TABLE IF NOT EXISTS roles (
     id           TINYINT UNSIGNED   PRIMARY KEY AUTO_INCREMENT,
     nome         VARCHAR(55)        UNIQUE NOT NULL
 );
 
 CREATE TABLE IF NOT EXISTS users (
     id           SMALLINT UNSIGNED  PRIMARY KEY AUTO_INCREMENT,
     username     VARCHAR(55)        UNIQUE NOT NULL,
     password     VARCHAR(100)       NOT NULL,
	 fk_role_id   TINYINT UNSIGNED   NOT NULL
 );
 
 ALTER TABLE users ADD FOREIGN KEY(fk_role_id) REFERENCES roles(id);

DESC roles;
DESC users;

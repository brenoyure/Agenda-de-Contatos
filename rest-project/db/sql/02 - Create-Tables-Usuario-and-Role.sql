 CREATE TABLE IF NOT EXISTS role (
     id           TINYINT UNSIGNED   PRIMARY KEY AUTO_INCREMENT,
     nome         VARCHAR(55)        UNIQUE NOT NULL
 );
 
 CREATE TABLE IF NOT EXISTS usuario (
     id           SMALLINT UNSIGNED  PRIMARY KEY AUTO_INCREMENT,
     username     VARCHAR(55)        UNIQUE NOT NULL,
     password     VARCHAR(100)       NOT NULL,
	 fk_role_id   TINYINT UNSIGNED   NOT NULL
 );
 
 ALTER TABLE usuario ADD FOREIGN KEY(fk_role_id) REFERENCES role(id);

DESC role;
DESC usuario;

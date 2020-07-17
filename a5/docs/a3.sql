drop database if exists jdc16d;
create database if not exists jdc16d;
use jdc16d;

DROP TABLE IF EXISTS customer;
CREATE TABLE IF NOT EXISTS customer
(
    cus_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    cus_fname VARCHAR(15) NOT NULL,
    cus_lname VARCHAR(30) NOT NULL,
    cus_street VARCHAR(30) NOT NULL,
    cus_city VARCHAR(30) NOT NULL,
    cus_state CHAR(2) NOT NULL,
    cus_zip int(9) unsigned ZEROFILL NOT NULL,
    cus_phone bigint unsigned NOT NULL,
    cus_email VARCHAR(100) NOT NULL,
    cus_balance DECIMAL(6,2) NOT NULL,
    cus_total_sales DECIMAL(6,2) NOT NULL,
    cus_notes VARCHAR(255) NULL,
    PRIMARY KEY (cus_id)
)

ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

SHOW WARNINGS;

INSERT INTO customer
VALUES
(1,'John','Doe','908 W. Capital Way','Seattle','WA','081226749','2065559857','Johndoe123@gmail.com',1234.00,1345.00,'customer notes1'),
(2,'Elizabeth','Brown','1 Rockcrest Ave.','Arlington Heights','IL','60004','4566547908','ElizabethB@gmail.com',2234.00,2345.00,'customer notes2'),
(3,'Sean','Patterson','272 Thomas Dr.','Vienna','VA','22180','4560899785','SeanP@gmail.com',3234.00,3345.00,'customer notes3'),
(4,'Nancy','Richardson','569 Prospect St.','Traverse City','MI','49684','3459879283','NacyR@gmail.com',4234.00,4345.00,'customer notes4'),
(5,'Carol','Cox','9751 W. Hillside Road','Englewood','NJ','07631','3450398576','CarolC@gmail.com',5234.00,5345.00,'customer notes5'),
(6,'Lois','Campbell','5 Elizabeth Drive','Sun City','AZ','85351','2348768764','LouisC@gmail.com',6234.00,6345.00,'customer notes6'),
(7,'Bruce','Mitchell','67 South Canal Avenue','Braintree','MA','02184','2347658675','BruceM@gmail.com',7234.00,7345.00,'customer notes7'),
(8,'Patricia','Jenkins','208 Brandywine Drive','Winter Park','FL','32792','4350987564','PatriciaJ@gmail.com',8234.00,8345.00,'customer notes8'),
(9,'Thomas','Ramirez','7300 W. Franklin St.','Atlanta','GA','30303','8769549034','ThomasR@gmail.com',9234.00,9345.00,'customer notes9'),
(10,'Raymond','Smith','7399 N. Roosevelt St.','Clearwater','FL','33756','7689872365','RaymondS@gmail.com',345.00,536.00,'customer notes10');

SHOW WARNINGS;

DROP TABLE IF EXISTS petstore;
CREATE TABLE IF NOT EXISTS petstore
(
    pst_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    pst_name VARCHAR(30) NOT NULL,
    pst_street VARCHAR(30) NOT NULL,
    pst_city VARCHAR(30) NOT NULL,
    pst_state CHAR(2) NOT NULL,
    pst_zip int(9) unsigned ZEROFILL NOT NULL,
    pst_phone bigint unsigned NOT NULL,
    pst_email VARCHAR(100) NOT NULL,
    pst_url VARCHAR(100) NOT NULL,
    pst_ytd_sales DECIMAL(10,2) NOT NULL,
    pst_notes VARCHAR(255) NULL,
    PRIMARY KEY (pst_id)
)

ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

INSERT INTO petstore
(pst_id,pst_name,pst_street,pst_city,pst_state,pst_zip,pst_phone,pst_email,pst_url,pst_ytd_sales,pst_notes)
values
(1,'Tom','1089 sw 66 st','Long Beach','CA','324509890','7777777777','tonygon@gmail.com','https://tonygon.com',4000.00,'loud'),
(2,'Sophia','510 Daroco ave','Coral Gables','FL','673790675','9546113230','Emilyuck@hotmail.com','https://emilyuck.com',5000.00,'cute'),
(3,'Sara','6510 sw 10th st','Mexico City','MO','33134000','3059748140','Saranot@yahoo.com','https://saranot.com',6000.00,'none'),
(4,'Timmy','6969 nw 666 st','Hell','MI','768921345','5183924612','Whothere@aim.com','https://whothere.com',7000.00,'Turn around'),
(5,'Tyler','1800 Pennsylvania ave','Washington','DC','760934864','4046147728','look@me.com','https://lookme.com',8000.00,'Winning'),
(6,'Roger','9653 Maple Drive','Atwater','CA','95301','9058537849','wownow@gmail.com','https://wownow.com',9000.00,'cheep'),
(7,'Rita','4 South Parker St.','Paramount','CA','90723','2348798495','ritano@gmail.com','https://ritano.com',4200.00,'good'),
(8,'Kelly','37 Arnold Dr.','Westminster','CA','92683','2903958675','kellyblue@gmail.com','https://kellyblue.com',4300.00,'bad'),
(9,'Roy','96 Inverness Street','San Francisco','CA','94110','2943445678','royspets@gmail.com','https://royspets.com',4400.00,'rough'),
(10,'come get them','9304 Westport St.','San Jose','CA','95127','2340984365','comeandgetthem@gmail.com','https://comeandgetthme.com',4500.00,'nope');

SHOW WARNINGS;
DROP TABLE IF EXISTS pet;
CREATE TABLE IF NOT EXISTS pet
(
    pet_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    pst_id SMALLINT UNSIGNED NOT NULL,
    cus_id SMALLINT UNSIGNED NULL,
    pet_type VARCHAR(45) NOT NULL,
    pet_sex enum('m','f') NOT NULL,
    pet_cost DECIMAL (6,2) NOT NULL,
    pet_price DECIMAL (6,2) NOT NULL,
    pet_age TINYINT NOT NULL,
    pet_color VARCHAR(30) NOT NULL,
    pet_sale_date DATE NULL,
    pet_vaccine enum('y','n') NOT NULL,
    pet_neuter enum('y','n') NOT NULL,
    pet_notes VARCHAR(255) NULL,
    primary key(pet_id),

  INDEX `fk_pet_petstore_idx` (`pst_id` ASC),
  INDEX `fk_pet_customer1_idx` (`cus_id` ASC),

  CONSTRAINT `fk_pet_petstore`
    FOREIGN KEY (`pst_id`)
    REFERENCES `jdc16d`.`petstore` (`pst_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

  CONSTRAINT `fk_pet_customer`
    FOREIGN KEY (`cus_id`)
    REFERENCES `jdc16d`.`customer` (`cus_id`)
    ON DELETE CASCADE
    ON UPDATE SET NULL

)

ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

SHOW WARNINGS;

INSERT INTO pet
(pet_id,pst_id,pet_type,pet_sex,pet_cost,pet_price,pet_age,pet_color,pet_vaccine,pet_neuter,pet_notes)
values
(1,1,'rabbit','f',300.00,320.00,2,'brown','y','y','wild'),
(2,2,'snake','m',700.00,710.00,6,'red','y','n','rare'),
(3,3,'wolf','m',500.00,940.00,4,'grey','n','n','fast'),
(4,4,'shark','m',200.00,520.00,2,'grey','y','y','gives fin'),
(5,5,'bird','f',600.00,230.00,10,'blue','n','y','flies'),
(6,6,'turtle','f',400.00,420.00,20,'green','y','y','aquatic'),
(7,7,'hedgehog','m',720.00,730.00,16,'grey','y','n','cute'),
(8,8,'marmot','m',870.00,970.00,5,'brown','n','n','small'),
(9,9,'squirrel','m',300.00,320.00,12,'red','y','y','nut'),
(10,10,'dog','f',500.00,530.00,1,'black','n','y','squirrel');

SHOW WARNINGS;

select * from customer;
select * from pet;
select * from petstore;

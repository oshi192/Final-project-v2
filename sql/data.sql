CREATE DATABASE IF NOT EXISTS taxi_services
/*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE TABLE IF NOT EXISTS `taxi_services`.`role` (
  `idrole` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idrole`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `taxi_services`.`users` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `summ` INT NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `role_idrole` INT NOT NULL,
  PRIMARY KEY (`iduser`),
  INDEX `fk_users_userRole1_idx` (`role_idrole` ASC),
  CONSTRAINT `fk_users_userRole1`
    FOREIGN KEY (`role_idrole`)
    REFERENCES `taxi_services`.`role` (`idrole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `taxi_services`.`discounts` (
  `iddiscounts` INT NOT NULL AUTO_INCREMENT,
  `sum_with_usersDiscount` TINYINT(1) NOT NULL,
  `text` VARCHAR(45) NOT NULL,
  `text_uk` VARCHAR(45) NOT NULL,
  `startdate` DATE NOT NULL,
  `enddate` DATE NOT NULL,
  `author_id` INT NOT NULL,
  `percent` INT NOT NULL,
  PRIMARY KEY (`iddiscounts`),
  INDEX `fk_discounts_users1_idx` (`author_id` ASC),
  CONSTRAINT `fk_discounts_users1`
    FOREIGN KEY (`author_id`)
    REFERENCES `taxi_services`.`users` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `taxi_services`.`carType` (
  `idCarType` INT NOT NULL AUTO_INCREMENT,
  `carTypeName` VARCHAR(45) NOT NULL,
  `price_city_km` INT NOT NULL,
  `price_over_the_city_km` INT NOT NULL,
  `price_waiting_time_minute` INT NOT NULL,
  `price_waiting_time_free` INT NOT NULL,
  PRIMARY KEY (`idCarType`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `taxi_services`.`taxiStatus` (
  `idTaxiStatus` INT NOT NULL AUTO_INCREMENT,
  `taxiStatusName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTaxiStatus`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `taxi_services`.`taxi` (
  `idtaxi` INT NOT NULL AUTO_INCREMENT,
  `carType_idCarType` INT NOT NULL,
  `status_idstatus` INT NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `description_uk` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idtaxi`),
  INDEX `fk_taxi_carType1_idx` (`carType_idCarType` ASC),
  INDEX `fk_taxi_status1_idx` (`status_idstatus` ASC),
  CONSTRAINT `fk_taxi_carType1`
    FOREIGN KEY (`carType_idCarType`)
    REFERENCES `taxi_services`.`carType` (`idCarType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_taxi_status1`
    FOREIGN KEY (`status_idstatus`)
    REFERENCES `taxi_services`.`taxiStatus` (`idTaxiStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `taxi_services`.`orderStatus` (
  `idorderStatus` INT NOT NULL AUTO_INCREMENT,
  `orderStatusName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idorderStatus`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `taxi_services`.`city` (
  `idcity` INT NOT NULL AUTO_INCREMENT,
  `city_name` VARCHAR(45) NOT NULL,
  `city_name_uk` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idcity`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `taxi_services`.`city_distance` (
  `idcity_distance` INT NOT NULL AUTO_INCREMENT,
  `distance_km` INT NOT NULL,
  `tocity_idcity` INT NOT NULL,
  `fromcity_idcity1` INT NOT NULL,
  PRIMARY KEY (`idcity_distance`),
  INDEX `fk_city_distance_city1_idx` (`tocity_idcity` ASC),
  INDEX `fk_city_distance_city2_idx` (`fromcity_idcity1` ASC),
  CONSTRAINT `fk_city_distance_city1`
    FOREIGN KEY (`tocity_idcity`)
    REFERENCES `taxi_services`.`city` (`idcity`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_city_distance_city2`
    FOREIGN KEY (`fromcity_idcity1`)
    REFERENCES `taxi_services`.`city` (`idcity`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `taxi_services`.`order` (
  `idorder` INT NOT NULL AUTO_INCREMENT,
  `order_time` VARCHAR(45) NOT NULL,
  `user_iduser` INT NOT NULL,
  `carType_idCarType` INT NOT NULL,
  `comment` VARCHAR(255) NULL,
  `orderStatus_idorderStatus` INT NOT NULL,
  `taxi_idtaxi` INT,
  `city_distance_idcity_distance` INT NOT NULL,
  PRIMARY KEY (`idorder`, `user_iduser`),
  INDEX `fk_order_user_idx` (`user_iduser` ASC),
  INDEX `fk_order_carType1_idx` (`carType_idCarType` ASC),
  INDEX `fk_order_orderStatus1_idx` (`orderStatus_idorderStatus` ASC),
  INDEX `fk_order_taxi1_idx` (`taxi_idtaxi` ASC),
  INDEX `fk_order_city_distance1_idx` (`city_distance_idcity_distance` ASC),
  CONSTRAINT `fk_order_user`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `taxi_services`.`users` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_carType1`
    FOREIGN KEY (`carType_idCarType`)
    REFERENCES `taxi_services`.`carType` (`idCarType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_orderStatus1`
    FOREIGN KEY (`orderStatus_idorderStatus`)
    REFERENCES `taxi_services`.`orderStatus` (`idorderStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_taxi1`
    FOREIGN KEY (`taxi_idtaxi`)
    REFERENCES `taxi_services`.`taxi` (`idtaxi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_city_distance1`
    FOREIGN KEY (`city_distance_idcity_distance`)
    REFERENCES `taxi_services`.`city_distance` (`idcity_distance`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

start transaction;
use taxi_services;
insert into `taxi_services`.`role` values(DEFAULT,'GUEST');
insert into `taxi_services`.`role` values(DEFAULT,'USER');
insert into `taxi_services`.`role` values(DEFAULT,'ADMIN');
insert into `taxi_services`.`role` values(DEFAULT,'DRIVER');
commit;

start transaction;
use taxi_services;
insert into `taxi_services`.`users` values(DEFAULT,"User", "TrueUser", "user@gmail.com",  "Userpass1", 20001, "0960969090", 2);
insert into `taxi_services`.`users` values(DEFAULT,"Admin", "TrueAdmin", "admin@gmail.com",  "Adminpass1", 2000000, "0930969090", 3);
insert into `taxi_services`.`users` values(DEFAULT,"Vasya", "Pupkin", "vpypkin@gmail.com",  "12345678a", 0, "0970969012", 2);
commit;

start transaction;
use taxi_services;
insert into `taxi_services`.`discounts` values(DEFAULT, true,
    "some text",
    "якийсь текст",
    '2018-12-08', '2019-01-08',2,10);
insert into `taxi_services`.`discounts` values(DEFAULT, false,
    "some text2",
    "якийсь текст2",
    '2010-02-01', '2019-04-01',2,5);
commit;

start transaction;
use taxi_services;
insert into `taxi_services`.`carType` values(DEFAULT,"STANDARD",600,800,150,5);
insert into `taxi_services`.`carType` values(DEFAULT,"COMFORT",650,850,200,5);
insert into `taxi_services`.`carType` values(DEFAULT,"BUSINESS",750,900,200,5);
insert into `taxi_services`.`carType` values(DEFAULT,"WAGON",750,900,200,5);
insert into `taxi_services`.`carType` values(DEFAULT,"MINIBUS",700,900,200,5);
commit;

start transaction;
use taxi_services;
insert into `taxi_services`.`taxiStatus` values(DEFAULT,"free");
insert into `taxi_services`.`taxiStatus` values(DEFAULT,"busy");
insert into `taxi_services`.`taxiStatus` values(DEFAULT,"break");
insert into `taxi_services`.`taxiStatus` values(DEFAULT,"off");
commit;

start transaction;
use taxi_services;
insert into `taxi_services`.`taxi` values(DEFAULT, 1,  1, 'mersedes a092ab12', 'мерседес a092ab12');
insert into `taxi_services`.`taxi` values(DEFAULT, 1, 2, "audi d092ad21", "ауді d092ad21");
insert into `taxi_services`.`taxi` values(DEFAULT, 1, 1, "wv a092ab12", "wv a092ab12");
insert into `taxi_services`.`taxi` values(DEFAULT, 1, 3, "lanos a092ab12", "ланос a092ab12");
insert into `taxi_services`.`taxi` values(DEFAULT, 1, 4, "reno a092ab12", "рено a092ab12");
commit;

start transaction;
use taxi_services;
insert into `taxi_services`.`orderStatus` values(DEFAULT,'REQUEST');
insert into `taxi_services`.`orderStatus` values(DEFAULT,'CONFIRMED');
insert into `taxi_services`.`orderStatus` values(DEFAULT,'ENDED');
commit;

start transaction;
use taxi_services;
insert into `taxi_services`.`city` values(DEFAULT,'Vynnicia','Вінниця');
insert into `taxi_services`.`city` values(DEFAULT,'Kyiv','Київ');
insert into `taxi_services`.`city` values(DEFAULT,'Odessa','Одеса');
insert into `taxi_services`.`city` values(DEFAULT,'Lviv','Львів');
insert into `taxi_services`.`city` values(DEFAULT,'Kharkiv','Харків');
commit;


start transaction;
use taxi_services;
insert into `taxi_services`.`city_distance` values(DEFAULT,100,1,2);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,1,3);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,1,4);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,1,5);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,2,1);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,2,3);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,2,4);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,2,5);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,3,1);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,3,2);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,3,4);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,3,5);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,4,1);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,4,2);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,4,3);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,4,5);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,5,1);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,5,2);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,5,3);
insert into `taxi_services`.`city_distance` values(DEFAULT,100,5,4);
commit;


start transaction;
use taxi_services;
insert into `taxi_services`.`order` values(DEFAULT,'2018-01-01',1, 1,'someComent',1,1,1);
commit;

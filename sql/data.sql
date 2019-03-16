CREATE DATABASE IF NOT EXISTS taxi_services;

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
  `idshares` INT NOT NULL AUTO_INCREMENT,
  `sum_with_usersDiscount` TINYINT(1) NOT NULL,
  `text` VARCHAR(45) NOT NULL,
  `startdate` DATE NOT NULL,
  `enddate` DATE NOT NULL,
  `author_id` INT NOT NULL,
  PRIMARY KEY (`idshares`),
  INDEX `fk_discounts_users1_idx` (`author_id` ASC),
  CONSTRAINT `fk_discounts_users1`
    FOREIGN KEY (`author_id`)
    REFERENCES `taxi_services`.`users` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `taxi_services`.`carType` (
  `idCarType` INT NOT NULL AUTO_INCREMENT,
  `typeName` VARCHAR(45) NOT NULL,
  `price_city_km` INT NOT NULL,
  `price_over_the_city_km` INT NOT NULL,
  `price_waiting_time_minute` INT NOT NULL,
  `price_waiting_time_free` INT NOT NULL,
  PRIMARY KEY (`idCarType`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `taxi_services`.`taxiStatus` (
  `idstatus` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idstatus`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `taxi_services`.`taxi` (
  `idtaxi` INT NOT NULL AUTO_INCREMENT,
  `carType_idCarType` INT NOT NULL,
  `status_idstatus` INT NOT NULL,
  `descryption` VARCHAR(45) NOT NULL,
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
    REFERENCES `taxi_services`.`taxiStatus` (`idstatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `taxi_services`.`orderStatus` (
  `idorderStatus` INT NOT NULL AUTO_INCREMENT,
  `orderStatuscol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idorderStatus`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `taxi_services`.`order` (
  `idorder` INT NOT NULL AUTO_INCREMENT,
  `startPoint` VARCHAR(45) NOT NULL,
  `endPoint` VARCHAR(45) NOT NULL,
  `order_time` VARCHAR(45) NOT NULL,
  `user_iduser` INT NOT NULL,
  `carType_idCarType` INT NOT NULL,
  `comment` VARCHAR(255) NULL,
  PRIMARY KEY (`idorder`, `user_iduser`),
  INDEX `fk_order_user_idx` (`user_iduser` ASC),
  INDEX `fk_order_carType1_idx` (`carType_idCarType` ASC),
  CONSTRAINT `fk_order_user`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `taxi_services`.`users` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_carType1`
    FOREIGN KEY (`carType_idCarType`)
    REFERENCES `taxi_services`.`carType` (`idCarType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

start transaction;
use taxi_services;
insert into `taxi_services`.`role` values(DEFAULT,'GUEST');
insert into `taxi_services`.`role` values(DEFAULT,'USER');
insert into `taxi_services`.`role` values(DEFAULT,'ADMIN');
commit;

start transaction;
use taxi_services;
insert into `taxi_services`.`users` values(DEFAULT,"User", "TrueUser", "user@gmail.com",  "Userpass1", 20001, "0960969090", 2);
insert into `taxi_services`.`users` values(DEFAULT,"Admin", "TrueAdmin", "admin@gmail.com",  "Adminpass1", 2000000, "0930969090", 3);
insert into `taxi_services`.`users` values(DEFAULT,"Vasya", "Pupkin", "vpypkin@gmail.com",  "12345678a", 0, "0970969012", 2);
commit;

start transaction;
use taxi_services;
insert into `taxi_services`.`discounts` values(DEFAULT, true, "some text", '2018-12-08', '2019-01-08',2);
insert into `taxi_services`.`discounts` values(DEFAULT, false, "some text2", '2010-02-01', '2019-04-01',2);
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
insert into `taxi_services`.`taxi` values(DEFAULT, 1,  1, 'mersedes a092ab12');
insert into `taxi_services`.`taxi` values(DEFAULT, 1, 2, "audi d092ad21");
insert into `taxi_services`.`taxi` values(DEFAULT, 1, 1, "wv a092ab12");
insert into `taxi_services`.`taxi` values(DEFAULT, 1, 3, "lanos a092ab12");
insert into `taxi_services`.`taxi` values(DEFAULT, 1, 4, "reno a092ab12");
commit;

start transaction;
use taxi_services;
insert into `taxi_services`.`orderStatus` values(DEFAULT,'REQUEST');
insert into `taxi_services`.`orderStatus` values(DEFAULT,'INPROGRESS');
insert into `taxi_services`.`orderStatus` values(DEFAULT,'ENDED');
commit;

start transaction;
use taxi_services;
insert into `taxi_services`.`order` values(DEFAULT,'', '', '2018-12-08',1,1,'someComent');
commit;

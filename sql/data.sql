CREATE DATABASE IF NOT EXISTS taxi_services;


CREATE TABLE IF NOT EXISTS `taxi_services`.`shares` (
  `idshares` INT NOT NULL,
  `sum_with_usersDiscount` TINYINT(1) NOT NULL,
  `text` VARCHAR(45) NOT NULL,
  `startdate` DATE NOT NULL,
  `enddate` DATE NOT NULL,
  PRIMARY KEY (`idshares`))
ENGINE = InnoDB

CREATE TABLE IF NOT EXISTS `taxi_services`.`users` (
  `iduser` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `summ` INT NULL,
  PRIMARY KEY (`iduser`))
ENGINE = InnoDB

CREATE TABLE IF NOT EXISTS `taxi_services`.`carType` (
  `idCarType` INT NOT NULL,
  `typeName` VARCHAR(45) NULL,
  'price_city_km' INT NOT NULL,
  'price_over_the_city_km' INT NOT NULL,
  'price_waiting_time_minute' INT NOT NULL,
  'price_waiting_time_free' INT NOT NULL,
  PRIMARY KEY (`idCarType`))
ENGINE = InnoDB

CREATE TABLE IF NOT EXISTS `taxi_services`.`taxistatus` (
  `idtaxistatus` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`idtaxistatus`))
ENGINE = InnoDB

CREATE TABLE IF NOT EXISTS `taxi_services`.`taxi` (
  `idtaxi` INT NOT NULL,
  `car` VARCHAR(45) NULL,
  `carType_idCarType` INT NOT NULL,
  `status_idstatus` INT NOT NULL,
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
    REFERENCES `taxi_services`.`status` (`idstatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB

CREATE TABLE IF NOT EXISTS `taxi_services`.`order` (
  `idorder` INT NOT NULL,
  `startPoint` VARCHAR(45) NULL,
  `endPoint` VARCHAR(45) NULL,
  `ordercol` VARCHAR(45) NULL,
  `order_time` VARCHAR(45) NULL,
  `user_iduser` INT NOT NULL,
  `carType_idCarType` INT NOT NULL,
  'comment' VARCHAR(255),
  PRIMARY KEY (`idorder`, `user_iduser`),
  INDEX `fk_order_user_idx` (`user_iduser` ASC),
  INDEX `fk_order_carType1_idx` (`carType_idCarType` ASC),
  CONSTRAINT `fk_order_user`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `taxi_services`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_carType1`
    FOREIGN KEY (`carType_idCarType`)
    REFERENCES `taxi_services`.`carType` (`idCarType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB


start transaction;
use taxi_services;2018-12-08
insert into `taxi_services`.`shares` values(DEFAULT,true, "some text", '2018-12-08', '2019-01-08');
commit;

start transaction;
use taxi_services;
insert into `taxi_services`.`users` values(DEFAULT,"User", "user", "0960969090", "user@gmail.com",  "passWord1", 20001);
commit;

start transaction;
use taxi_services;
insert into `taxi_services`.`carType` values(DEFAULT,"Standart",600,800,150,5);
insert into `taxi_services`.`carType` values(DEFAULT,"komfort",650,850,200,5);
insert into `taxi_services`.`carType` values(DEFAULT,"Business",750,900,200,5);
insert into `taxi_services`.`carType` values(DEFAULT,"WAGON",600,800,150,5);
insert into `taxi_services`.`carType` values(DEFAULT,"Minibus",700,900,200,5);
commit;

start transaction;
use taxi_services;
insert into `taxi_services`.`taxistatus` values(DEFAULT,"free");
insert into `taxi_services`.`taxistatus` values(DEFAULT,"busy");
insert into `taxi_services`.`taxistatus` values(DEFAULT,"break");
insert into `taxi_services`.`taxistatus` values(DEFAULT,"off");
commit;

start transaction;
use taxi_services;
insert into `taxi_services`.`taxi` values(DEFAULT,"mersedes a092ab12",  1, 0);
insert into `taxi_services`.`taxi` values(DEFAULT,"audi d092ad21",  2, 1);
insert into `taxi_services`.`taxi` values(DEFAULT,"wv a092ab12",  3, 1);
insert into `taxi_services`.`taxi` values(DEFAULT,"lanos a092ab12",  0, 3);
insert into `taxi_services`.`taxi` values(DEFAULT,"reno a092ab12",  0, 1);
commit;

start transaction;
use taxi_services;
commit;
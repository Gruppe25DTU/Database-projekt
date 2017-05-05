-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema databaseproject
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema databaseproject
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `databaseproject` DEFAULT CHARACTER SET latin1 ;
USE `databaseproject` ;


drop table if exists `databaseproject`.`leverandoer`;
drop table if exists `databaseproject`.`operatoer_info`;
drop table if exists `databaseproject`.`operatoer`;
drop table if exists `databaseproject`.`recept`;
drop table if exists `databaseproject`.`produktbatch`;
drop table if exists `databaseproject`.`raavare`;
drop table if exists `databaseproject`.`raavarebatch`;
drop table if exists `databaseproject`.`produktbatchkomponent`;
drop table if exists `databaseproject`.`receptkomponent`;
-- -----------------------------------------------------
-- Table `databaseproject`.`leverandoer`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `databaseproject`.`leverandoer` (
  `leverandoer_navn` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`leverandoer_navn`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `databaseproject`.`operatoer_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databaseproject`.`operatoer_info` (
  `opr_navn` VARCHAR(45) NULL DEFAULT NULL,
  `ini` VARCHAR(8) NULL DEFAULT NULL,
  `cpr` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`cpr`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `databaseproject`.`operatoer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databaseproject`.`operatoer` (
  `opr_id` INT(11) NOT NULL,
  `cpr` VARCHAR(11) NOT NULL,
  `password` VARCHAR(40) NULL DEFAULT NULL,
  PRIMARY KEY (`opr_id`),
  INDEX `cpr` (`cpr` ASC),
  CONSTRAINT `operatoer_ibfk_1`
    FOREIGN KEY (`cpr`)
    REFERENCES `databaseproject`.`operatoer_info` (`cpr`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `databaseproject`.`recept`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databaseproject`.`recept` (
  `recept_id` INT(11) NOT NULL,
  `recept_navn` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`recept_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `databaseproject`.`produktbatch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databaseproject`.`produktbatch` (
  `pb_id` INT(11) NOT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `recept_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`pb_id`),
  INDEX `recept_id` (`recept_id` ASC),
  CONSTRAINT `produktbatch_ibfk_1`
    FOREIGN KEY (`recept_id`)
    REFERENCES `databaseproject`.`recept` (`recept_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `databaseproject`.`raavare`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databaseproject`.`raavare` (
  `raavare_id` INT(11) NOT NULL,
  `raavare_navn` TEXT NULL DEFAULT NULL,
  `leverandoer` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`raavare_id`),
  INDEX `leverandoer` (`leverandoer` ASC),
  CONSTRAINT `raavare_ibfk_1`
    FOREIGN KEY (`leverandoer`)
    REFERENCES `databaseproject`.`leverandoer` (`leverandoer_navn`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `databaseproject`.`raavarebatch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databaseproject`.`raavarebatch` (
  `rb_id` INT(11) NOT NULL,
  `raavare_id` INT(11) NULL DEFAULT NULL,
  `maengde` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`rb_id`),
  INDEX `raavare_id` (`raavare_id` ASC),
  CONSTRAINT `raavarebatch_ibfk_1`
    FOREIGN KEY (`raavare_id`)
    REFERENCES `databaseproject`.`raavare` (`raavare_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `databaseproject`.`produktbatchkomponent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databaseproject`.`produktbatchkomponent` (
  `pb_id` INT(11) NOT NULL DEFAULT '0',
  `rb_id` INT(11) NOT NULL DEFAULT '0',
  `tara` DOUBLE NULL DEFAULT NULL,
  `netto` DOUBLE NULL DEFAULT NULL,
  `opr_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`pb_id`, `rb_id`),
  INDEX `rb_id` (`rb_id` ASC),
  INDEX `opr_id` (`opr_id` ASC),
  CONSTRAINT `produktbatchkomponent_ibfk_1`
    FOREIGN KEY (`pb_id`)
    REFERENCES `databaseproject`.`produktbatch` (`pb_id`),
  CONSTRAINT `produktbatchkomponent_ibfk_2`
    FOREIGN KEY (`rb_id`)
    REFERENCES `databaseproject`.`raavarebatch` (`rb_id`),
  CONSTRAINT `produktbatchkomponent_ibfk_3`
    FOREIGN KEY (`opr_id`)
    REFERENCES `databaseproject`.`operatoer` (`opr_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `databaseproject`.`receptkomponent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databaseproject`.`receptkomponent` (
  `recept_id` INT(11) NOT NULL DEFAULT '0',
  `raavare_id` INT(11) NOT NULL DEFAULT '0',
  `nom_netto` DOUBLE NULL DEFAULT NULL,
  `tolerance` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`recept_id`, `raavare_id`),
  INDEX `raavare_id` (`raavare_id` ASC),
  CONSTRAINT `receptkomponent_ibfk_1`
    FOREIGN KEY (`recept_id`)
    REFERENCES `databaseproject`.`recept` (`recept_id`),
  CONSTRAINT `receptkomponent_ibfk_2`
    FOREIGN KEY (`raavare_id`)
    REFERENCES `databaseproject`.`raavare` (`raavare_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

USE `databaseproject` ;

-- -----------------------------------------------------
-- Placeholder table for view `databaseproject`.`operatoer_view`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databaseproject`.`operatoer_view` (`cpr` INT, `opr_id` INT, `password` INT, `opr_navn` INT, `ini` INT);

-- -----------------------------------------------------
-- View `databaseproject`.`operatoer_view`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `databaseproject`.`operatoer_view`;
USE `databaseproject`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `databaseproject`.`operatoer_view` AS select `databaseproject`.`operatoer`.`cpr` AS `cpr`,`databaseproject`.`operatoer`.`opr_id` AS `opr_id`,`databaseproject`.`operatoer`.`password` AS `password`,`databaseproject`.`operatoer_info`.`opr_navn` AS `opr_navn`,`databaseproject`.`operatoer_info`.`ini` AS `ini` from (`databaseproject`.`operatoer` join `databaseproject`.`operatoer_info` on((`databaseproject`.`operatoer`.`cpr` = `databaseproject`.`operatoer_info`.`cpr`))) where (`databaseproject`.`operatoer`.`cpr` = `databaseproject`.`operatoer_info`.`cpr`);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

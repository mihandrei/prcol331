SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `proj_col` DEFAULT CHARACTER SET utf8 ;
USE `proj_col` ;

-- -----------------------------------------------------
-- Table `proj_col`.`persoane`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proj_col`.`persoane` ;

CREATE  TABLE IF NOT EXISTS `proj_col`.`persoane` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `cnp` VARCHAR(13) NULL ,
  `pasaport` VARCHAR(45) NULL ,
  `prenume` VARCHAR(45) NULL ,
  `nume` VARCHAR(45) NOT NULL ,
  `version` INT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `cnp_unique` (`cnp` ASC) ,
  UNIQUE INDEX `pass_unique` (`pasaport` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proj_col`.`facultati`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proj_col`.`facultati` ;

CREATE  TABLE IF NOT EXISTS `proj_col`.`facultati` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nume` VARCHAR(45) NOT NULL ,
  `version` INT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proj_col`.`sectii`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proj_col`.`sectii` ;

CREATE  TABLE IF NOT EXISTS `proj_col`.`sectii` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `facultate` INT NOT NULL ,
  `nume` VARCHAR(45) NOT NULL ,
  `version` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_sectii_facultati1` (`facultate` ASC) ,
  CONSTRAINT `fk_sectii_facultati1`
    FOREIGN KEY (`facultate` )
    REFERENCES `proj_col`.`facultati` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proj_col`.`studenti`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proj_col`.`studenti` ;

CREATE  TABLE IF NOT EXISTS `proj_col`.`studenti` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_pers` INT NOT NULL ,
  `sectieId` INT NOT NULL ,
  `version` INT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_studenti_persoane` (`id_pers` ASC) ,
  INDEX `fk_studenti_sectii1` (`sectieId` ASC) ,
  CONSTRAINT `fk_studenti_persoane`
    FOREIGN KEY (`id_pers` )
    REFERENCES `proj_col`.`persoane` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_studenti_sectii1`
    FOREIGN KEY (`sectieId` )
    REFERENCES `proj_col`.`sectii` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `proj_col`.`cursuri`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proj_col`.`cursuri` ;

CREATE  TABLE IF NOT EXISTS `proj_col`.`cursuri` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nume` VARCHAR(45) NOT NULL ,
  `version` INT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proj_col`.`curicule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proj_col`.`curicule` ;

CREATE  TABLE IF NOT EXISTS `proj_col`.`curicule` (
  `curs` INT NOT NULL ,
  `sectie` INT NOT NULL ,
  `semestru` INT NOT NULL ,
  `credite` INT NOT NULL ,
  `tip` VARCHAR(15) NOT NULL ,
  PRIMARY KEY (`curs`, `sectie`) ,
  INDEX `fk_curicule_cursuri1` (`curs` ASC) ,
  INDEX `fk_curicule_sectii1` (`sectie` ASC) ,
  CONSTRAINT `fk_curicule_cursuri1`
    FOREIGN KEY (`curs` )
    REFERENCES `proj_col`.`cursuri` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_curicule_sectii1`
    FOREIGN KEY (`sectie` )
    REFERENCES `proj_col`.`sectii` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proj_col`.`contracte_studiu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proj_col`.`contracte_studiu` ;

CREATE  TABLE IF NOT EXISTS `proj_col`.`contracte_studiu` (
  `nrmat` INT NOT NULL AUTO_INCREMENT ,
  `id_curs` INT NOT NULL ,
  `nota` VARCHAR(45) NULL ,
  PRIMARY KEY (`nrmat`) ,
  INDEX `fk_contracte_studiu_cursuri1` (`id_curs` ASC) ,
  CONSTRAINT `fk_contracte_studiu_studenti1`
    FOREIGN KEY (`nrmat` )
    REFERENCES `proj_col`.`studenti` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contracte_studiu_cursuri1`
    FOREIGN KEY (`id_curs` )
    REFERENCES `proj_col`.`cursuri` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proj_col`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proj_col`.`users` ;

CREATE  TABLE IF NOT EXISTS `proj_col`.`users` (
  `login_name` VARCHAR(45) NOT NULL ,
  `password_hash` VARCHAR(45) NOT NULL ,
  `salt` VARCHAR(45) NOT NULL ,
  `lastlogin` VARCHAR(45) NULL ,
  `lastip` VARCHAR(45) NULL ,
  PRIMARY KEY (`login_name`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `proj_col`.`conturi_studenti`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `proj_col`.`conturi_studenti` ;

CREATE  TABLE IF NOT EXISTS `proj_col`.`conturi_studenti` (
  `login_name` VARCHAR(45) NOT NULL ,
  `id_student` INT NOT NULL ,
  UNIQUE INDEX `un_student_id_unique` (`id_student` ASC) ,
  INDEX `fk_conturi_studenti_users1` (`login_name` ASC) ,
  PRIMARY KEY (`login_name`) ,
  CONSTRAINT `fk_conturi_studenti_studenti1`
    FOREIGN KEY (`id_student` )
    REFERENCES `proj_col`.`studenti` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_conturi_studenti_users1`
    FOREIGN KEY (`login_name` )
    REFERENCES `proj_col`.`users` (`login_name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

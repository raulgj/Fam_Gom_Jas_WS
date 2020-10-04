-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fam_gom_jas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fam_gom_jas
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `fam_gom_jas` ;
CREATE SCHEMA IF NOT EXISTS `fam_gom_jas` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `fam_gom_jas` ;

-- -----------------------------------------------------
-- Table `fam_gom_jas`.`t_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fam_gom_jas`.`t_user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user` VARCHAR(50) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `last_name_mother` VARCHAR(20) NULL DEFAULT NULL,
  `birthdate` DATE NOT NULL,
  `gender` VARCHAR(15) NOT NULL,
  `is_active` BIT(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_UNIQUE` (`user` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fam_gom_jas`.`t_email`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fam_gom_jas`.`t_email` (
  `email_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `is_active` BIT(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`email_id`),
  INDEX `fk_email_1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_email_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `fam_gom_jas`.`t_user` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fam_gom_jas`.`t_group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fam_gom_jas`.`t_group` (
  `group_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fam_gom_jas`.`t_event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fam_gom_jas`.`t_event` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `date` DATE NOT NULL,
  `group_id` INT NOT NULL,
  `created_by` INT NOT NULL,
  `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`event_id`),
  INDEX `fk_event_1_idx` (`created_by` ASC) VISIBLE,
  INDEX `fk_event_2_idx` (`group_id` ASC) VISIBLE,
  CONSTRAINT `fk_event_1`
    FOREIGN KEY (`created_by`)
    REFERENCES `fam_gom_jas`.`t_user` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_event_2`
    FOREIGN KEY (`group_id`)
    REFERENCES `fam_gom_jas`.`t_group` (`group_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fam_gom_jas`.`t_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fam_gom_jas`.`t_comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT NOT NULL,
  `comment` VARCHAR(300) NOT NULL,
  `created_by` INT NOT NULL,
  `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `related_comment_id` INT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `fk_event_comment_1_idx` (`event_id` ASC) VISIBLE,
  INDEX `fk_event_comment_2_idx` (`created_by` ASC) VISIBLE,
  INDEX `fk_comment_1_idx` (`related_comment_id` ASC) VISIBLE,
  CONSTRAINT `fk_event_comment_1`
    FOREIGN KEY (`event_id`)
    REFERENCES `fam_gom_jas`.`t_event` (`event_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_event_comment_2`
    FOREIGN KEY (`created_by`)
    REFERENCES `fam_gom_jas`.`t_user` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_comment_1`
    FOREIGN KEY (`related_comment_id`)
    REFERENCES `fam_gom_jas`.`t_comment` (`comment_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fam_gom_jas`.`t_media`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fam_gom_jas`.`t_media` (
  `media_id` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT NOT NULL,
  `file` BLOB NOT NULL,
  `file_name` VARCHAR(100) NOT NULL,
  `is_active` BIT(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`media_id`),
  INDEX `fk_event_media_1_idx` (`event_id` ASC) VISIBLE,
  CONSTRAINT `fk_event_media_1`
    FOREIGN KEY (`event_id`)
    REFERENCES `fam_gom_jas`.`t_event` (`event_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fam_gom_jas`.`t_phone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fam_gom_jas`.`t_phone` (
  `phone_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `label` VARCHAR(20) NOT NULL COMMENT 'This columns is to set an text to spesify the type of phone; ej; Cellphone, Work, Home, etc',
  `phone` VARCHAR(20) NOT NULL,
  `is_active` BIT(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`phone_id`),
  INDEX `fk_phone_1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_phone_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `fam_gom_jas`.`t_user` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fam_gom_jas`.`t_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fam_gom_jas`.`t_role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `is_active` BIT(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fam_gom_jas`.`t_user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fam_gom_jas`.`t_user_role` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `user_role_user_idx` (`user_id` ASC) VISIBLE,
  INDEX `user_role_role_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_role_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `fam_gom_jas`.`t_user` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `user_role_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `fam_gom_jas`.`t_role` (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fam_gom_jas`.`t_user_group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fam_gom_jas`.`t_user_group` (
  `user_id` INT NOT NULL,
  `group_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `group_id`),
  INDEX `fk_user_group_2_idx` (`group_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_group_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `fam_gom_jas`.`t_user` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_group_2`
    FOREIGN KEY (`group_id`)
    REFERENCES `fam_gom_jas`.`t_group` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `fam_gom_jas`.`t_role` (`name`, `is_active`) VALUES ('ROLE_USER', b'1');
INSERT INTO `fam_gom_jas`.`t_role` (`name`, `is_active`) VALUES ('ROLE_ADMIN', b'1');
INSERT INTO `fam_gom_jas`.`t_user` (`user_id`,`user`,`password`,`name`,`last_name`,`last_name_mother`,`birthdate`,`gender`,`is_active`) VALUES (1,'raulgj','$2a$10$HW99uzaIAYHmScDwHokD1./2JnDO90V3LCPW/.N9fxGd7AIhPLery','Raul Angel','Gomez','Jasso','1986-12-31','Masculino','0');

INSERT INTO `fam_gom_jas`.`t_group` (`name`, `description`) VALUES ('Usuario', 'Es el grupo al cual pertenecen todos los usuarios');


insert into `fam_gom_jas`.`t_user_role`
select u.user_id, r.role_id 
from `fam_gom_jas`.`t_user` u 
inner join `fam_gom_jas`.`t_role` r on r.name in ('ROLE_USER', 'ROLE_ADMIN')
where user = 'raulgj';

insert into `fam_gom_jas`.`t_user_group`
select u.user_id, g.group_id 
from `fam_gom_jas`.`t_user` u 
inner join `fam_gom_jas`.`t_group` g on g.name = 'Usuario'
where user = 'raulgj';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


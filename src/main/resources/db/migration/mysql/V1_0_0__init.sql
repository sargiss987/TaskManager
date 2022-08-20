-- -----------------------------------------------------
-- Schema task_manager_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `task_manager_db` DEFAULT CHARACTER SET utf8 ;
USE `task_manager_db` ;

-- -----------------------------------------------------
-- Table `task_manager_db`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `task_manager_db`.`user_role` (
  `id` BIGINT NOT NULL,
  `role_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `task_manager_db`.`task_manager_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `task_manager_db`.`task_manager_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(45) NOT NULL UNIQUE,
  `user_role_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_task_manager_user_user_role_idx` (`user_role_id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  CONSTRAINT `fk_task_manager_user_user_role1`
      FOREIGN KEY (`user_role_id`)
      REFERENCES `task_manager_db`.`user_role` (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `task_manager_db`.`task_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `task_manager_db`.`task_status` (
  `id` BIGINT NOT NULL,
  `status_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `task_manager_db`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `task_manager_db`.`task` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `task_name` VARCHAR(45) NOT NULL,
  `task_description` TEXT NOT NULL,
  `task_creation_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `task_update_date` TIMESTAMP NULL ,
  `task_status_id` BIGINT NOT NULL,
  `task_manager_user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_task_task_status_idx` (`task_status_id` ASC) VISIBLE,
  INDEX `fk_task_task_manager_user_idx` (`task_manager_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_task_task_status1`
      FOREIGN KEY (`task_status_id`)
      REFERENCES `task_manager_db`.`task_status` (`id`),
  CONSTRAINT `fk_task_task_manager_user1`
        FOREIGN KEY (`task_manager_user_id`)
        REFERENCES `task_manager_db`.`task_manager_user` (`id`))
ENGINE = InnoDB;

USE `task_manager_db` ;

INSERT INTO user_role (id,role_type) VALUES (1,'MANAGER');
INSERT INTO user_role (id,role_type) VALUES (2,'EMPLOYEE');

INSERT INTO task_status (id,status_type) VALUES (1,'NEW_TASK');
INSERT INTO task_status (id,status_type) VALUES (2,'BUG');
INSERT INTO task_status (id,status_type) VALUES (3,'IN_PROCESS');
INSERT INTO task_status (id,status_type) VALUES (4,'RE_OPEN');
INSERT INTO task_status (id,status_type) VALUES (5,'RESOLVED');
INSERT INTO task_status (id,status_type) VALUES (6,'DONE');



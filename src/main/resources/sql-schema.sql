drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) DEFAULT NULL,
    `value` FLOAT DEFAULT NULL,
    PRIMARY KEY (`id`)
);

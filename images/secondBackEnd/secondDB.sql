DROP DATABASE IF EXISTS secondeApi;
CREATE DATABASE secondeApi;
USE secondeApi;

CREATE TABLE `videogame_entity` (
  `id` INT(6) UNSIGNED AUTO_INCREMENT,
  `kind` varchar(255),
  `name` varchar(255),
  `supported_on` varchar(255),

  CONSTRAINT FK_videoGame_ID PRIMARY KEY (id)
);

CREATE TABLE `user_entity` (
  `email` varchar(255) UNIQUE NOT NULL,
  CONSTRAINT FK_user_ID PRIMARY KEY (email)
);


CREATE TABLE `purchase_entity` (
  `purchase_id`INT(6) UNSIGNED AUTO_INCREMENT,
  `videogames_id` INT(6) UNSIGNED  NOT NULL,
  `user_id` varchar(255) NOT NULL,
  CONSTRAINT FK_purchase_ID PRIMARY KEY (purchase_id),
  INDEX `FK_purchase_videogame_ID` (`videogames_id` ASC),
  INDEX `FK_purchase_user_ID` (`user_id` ASC),
  CONSTRAINT `FK_purchase_videogame_ID`
    FOREIGN KEY (`videogames_id`)
    REFERENCES `videogame_entity` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `FK_purchase_user_ID`
    FOREIGN KEY (`user_id`)
    REFERENCES `user_entity` (`email`)
    ON DELETE CASCADE
    )
ENGINE = InnoDB;


CREATE TABLE `Biblioteca`.`pessoa` ( 
	`id` INT NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(255) NOT NULL ,
	`telefone` VARCHAR(255) NOT NULL ,
	`cpf` VARCHAR(255) NOT NULL ,
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;
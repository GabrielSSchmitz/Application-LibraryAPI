CREATE TABLE `Biblioteca`.`material` (
	`id` INT NOT NULL AUTO_INCREMENT ,
	`codigo` VARCHAR(255) NOT NULL , 
	`tipo` INT NOT NULL , 
	`nome` VARCHAR(255) NOT NULL , 
	`descricao` VARCHAR(255) NOT NULL , 
	`material` VARCHAR(255) NOT NULL , 
	`estante` VARCHAR(255) NOT NULL , 
	`quantidade` INT NOT NULL , 
	`quantidade_emprestimo` INT NOT NULL , 
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;
CREATE TABLE `Biblioteca`.`periodico` ( 
	`id` INT NOT NULL AUTO_INCREMENT ,
	 `codigo` VARCHAR(255) NOT NULL , 
	 `tipo` INT NOT NULL , 
	 `nome` VARCHAR(255) NOT NULL , 
	 `volume` INT NOT NULL , 
	 `quantidade` INT NOT NULL , 
	 `quantidade_emprestimo` INT NOT NULL , 
	 PRIMARY KEY (`id`)
) ENGINE = InnoDB;
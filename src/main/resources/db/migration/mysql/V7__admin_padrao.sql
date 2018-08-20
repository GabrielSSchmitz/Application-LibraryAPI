INSERT INTO `autor` (`id`, `codigo`, `nome`, `sobrenome`) VALUES (NULL, '8888', 'Admin', 'Admin');

INSERT INTO `editora` (`id`, `codigo`, `nome`, `nacional`) VALUES (NULL, '88888', 'Saraiva', '0');

INSERT INTO `livro` (`id`, `codigo`, `tipo`, `nome`, `data`, `edicao`, `volume`, `id_editora`, `id_autor`, `quantidade`, `quantidade_emprestimo`) VALUES (NULL, '88888', '1', 'Label Teste', '31/12/2018', '1', '1', '1', '1', '2', '2');

INSERT INTO `material` (`id`, `codigo`, `tipo`, `nome`, `descricao`, `material`, `estante`, `quantidade`, `quantidade_emprestimo`) VALUES (NULL, '88888', '2', 'Teste', 'Teste', '2', '2', '2', '2');

INSERT INTO `periodico` (`id`, `codigo`, `tipo`, `nome`, `volume`, `quantidade`, `quantidade_emprestimo`) VALUES (NULL, '88888', '3', 'Teste P', '2', '2', '2');

INSERT INTO `pessoa` (`id`, `nome`, `telefone`, `cpf`) VALUES (NULL, 'Admin', '48 888888888', '33526465037');
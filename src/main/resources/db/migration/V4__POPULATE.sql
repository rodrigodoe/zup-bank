INSERT INTO `client` (`id`, `birth_day`, `cpf`, `email`, `first_name`, `last_name`, `status`) VALUES
	(1, '1997-01-01', '53009314035', 'test@test.com', 'Rodrigo', 'Carvalho', 'PENDING'),
	(2, '1997-01-01', '64262358062', 'a@test.com', 'Pafuncio', 'Batista', 'PENDING');
	
INSERT INTO `address` (`id`, `address_line1`, `address_line2`, `city`, `state`, `suburb`, `zipcode`, `client_id`) VALUES
	(1, 'address', 'Numero 9', 'cidade', 'estado', 'bairro', '54000-000', 1);
	
INSERT INTO `file_storage` (`id`, `filename`, `client_id`) VALUES
	(1, 'JB4mdWIvtS.png', 1);
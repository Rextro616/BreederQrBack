INSERT INTO `specie` (`id`, `name`)
    VALUES (1, 'Eublepharis macularius')
ON DUPLICATE KEY UPDATE name = 'Eublepharis macularius';

INSERT INTO `breederqr`.`breeder` (`id`, `last_name`, `mail`, `name`, `password`, `second_last_name`, `username`)
    VALUES (1, 'lastName', 'user@gmail.com', 'user1', '1234', 'secondLastName', 'user1')
ON DUPLICATE KEY UPDATE name = 'user1';


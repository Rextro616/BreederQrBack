INSERT INTO `specie` (`id`, `name`)
    VALUES (1, 'Eublepharis macularius')
ON DUPLICATE KEY UPDATE name = 'Eublepharis macularius';

INSERT INTO breederqr.breeder (id, created_at, created_by, deleted, deleted_at, deleted_by, last_name, mail, name, password, second_last_name, updated_at, updated_by, username)
VALUES (1, '2023-11-03 14:06:32.000000', 1, null, null, null, 'lastName', 'user@gmail.com', 'user1', '$2a$10$zzwYOB.9henpExzR7fA.zOwMO09FN8jBVA36HgtPajMFR82/fgoS2', 'secondLastName', null, null, 'user1')

ON DUPLICATE KEY UPDATE name = 'user1';





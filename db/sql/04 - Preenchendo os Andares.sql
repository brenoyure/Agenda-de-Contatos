START TRANSACTION;

INSERT INTO andar VALUES 
    (1, '1º Andar'), 
    (2, '2º Andar'), 
    (3, '3º Andar'), 
    (4, '4º Andar'), 
    (5, '5º Andar'), 
    (6, '6º Andar'), 
    (7, '7º Andar'), 
    (8, '8º Andar'), 
    (9, '9º Andar'),
    (10, 'Térreo');

COMMIT;

SELECT * FROM andar;


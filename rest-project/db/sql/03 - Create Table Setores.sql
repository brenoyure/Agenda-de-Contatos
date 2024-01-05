###################################################################################################################################################
###                                                                                                                                             ###
###                                            V_03 - Criação da entidade de Setores                                                            ###
###                                                                                                                                             ###
###  Com a nova regra de negócio, foi necessário definir os setores como entidades separadas, e não mais um simples campo texto.                ###
###  O SQL a seguir, criará a tabela para armazenar os setores e criará um relacionamento com a tabela de contatos, via chave estrangeira (FK). ###
###                                                                                                                                             ###
###  Em seguida os dados dos setores serão migrados para a nova tabela, tendo como fonte de dados, a coluna 'setor' da tabela de contatos.      ###
###                                                                                                                                             ###
###  Este SQL garante a integridade dos dados, pois, a operação será feita dentro de uma transaction do MySQL.                                  ###
###                                                                                                                                             ###
###                                                                                                                                             ###
###################################################################################################################################################

### Iniciando a transação ###
START TRANSACTION;

### Criando a Tabela de Setor ###
CREATE TABLE setor (
    id     INT         PRIMARY KEY AUTO_INCREMENT,
    sigla  VARCHAR(50) UNIQUE      NOT NULL
);

### Migrando (utilizando o DISTINCT) os dados dos setores para a tabela de setores a partir da coluna da tabela de contatos ###
INSERT INTO setor (sigla) SELECT DISTINCT(c.setor) FROM contato c;

### Criando a coluna de setor na tabela de contatos, porém, como chave estrangeira
ALTER TABLE contato ADD COLUMN fk_setor_id INT;

### Preenchendo a tabela de contatos, definindo o valor da chave estrangeira (fk_setor_id)  ###
### com os valores corretos dos IDs da tabela de setor, verificando a sigla dos setores antes ###
UPDATE contato c SET c.fk_setor_id = (SELECT s.id FROM setor s WHERE s.sigla = c.setor);

COMMIT;

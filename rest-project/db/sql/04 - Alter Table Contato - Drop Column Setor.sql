###################################################################################################################################################
###                                                                                                                                             ###
###                                      V_04 - Dropando a coluna de setor da tabela de contato                                                 ###
###                                                                                                                                             ###
###  Com a nova regra de negócio, foi necessário definir os setores como entidades separadas, e não mais um simples campo texto.                ###
###  O SQL a seguir, fará um drop da coluna de setor da tabela de contato. A entidade contato continuará tendo um setor,                        ###
###  porém agora relacionado via chave estrangeira, conforme o SQL anterior.                                                                    ###
###                                                                                                                                             ###
###  Este SQL garante a integridade dos dados, pois, a operação será feita dentro de uma transaction do MySQL.                                  ###
###                                                                                                                                             ###
###                                                                                                                                             ###
###################################################################################################################################################

START TRANSACTION;

ALTER TABLE contato DROP COLUMN setor;

COMMIT;

###################################################################################################################################################
###                                                                                                                                             ###
###                                      V_05 - Aumentando o tamanho dos campos nome e número                                                   ###
###                                                                                                                                             ###
###                                                                                                                                             ###
###################################################################################################################################################


ALTER TABLE contato MODIFY COLUMN nome   VARCHAR(55) UNIQUE NOT NULL;
ALTER TABLE contato MODIFY COLUMN numero VARCHAR(55)        NOT NULL;


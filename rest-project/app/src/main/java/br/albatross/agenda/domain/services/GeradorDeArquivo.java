package br.albatross.agenda.domain.services;

import java.io.File;
import java.util.List;

public interface GeradorDeArquivo<T> {

	public File gerar(List<T> t) ;

}

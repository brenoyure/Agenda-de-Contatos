package br.albatross.agenda.domain.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface GeradorDeArquivo<T> {

	public File gerar(List<T> contatos) throws IOException;

}

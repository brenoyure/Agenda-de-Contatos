package br.albatross.agenda.services.spi.relatorios;

import java.io.File;
import java.util.List;

public interface RelatorioService<T> {

    File gerar(List<T> t);

}

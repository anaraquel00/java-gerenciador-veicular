package br.com.fuctura.br.com.fuctura.service;


import java.sql.SQLException;

import br.com.fuctura.entidade.Loja;
import br.com.fuctura.repository.LojaRepository;

public class LojaService {
	 private LojaRepository lojaRepository = new LojaRepository();
	 
	 public LojaService(LojaRepository LojaDAO ) {
		 this.lojaRepository = LojaDAO;
		 
	 }
	 
	 public void cadastrasLoja(Loja loja) {
		 this.lojaRepository.salvar(loja);
	 }

	 public Loja buscarLojaPorCodigo(int codigo) throws SQLException {
		return lojaRepository.buscarLojaPorCodigo(codigo);
		 
	 }
	 public Loja getUltimaLojaCadastrada() {
		 return lojaRepository.getUltimaLojaCadastrada();
	 }
}

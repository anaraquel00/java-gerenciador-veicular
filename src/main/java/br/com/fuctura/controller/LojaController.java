package br.com.fuctura.controller;

import java.sql.SQLException;

import br.com.fuctura.br.com.fuctura.service.LojaService;
import br.com.fuctura.entidade.Loja;

public class LojaController {
	private LojaService lojaService;
	
	public LojaController(LojaService lojaService) {
        this.lojaService = lojaService;
    }

	public void cadastrarLoja(Loja loja) {
		 if (loja == null) {
	            throw new IllegalArgumentException("Objeto Loja não pode ser nulo!");
	        }
		lojaService.cadastrasLoja(loja);
			
	}

	public Loja getUltimaLojaCadastrada() {
		return this.lojaService.getUltimaLojaCadastrada();
		
			}

	public Loja buscarLojaPorCodigo(int codigoBusca) throws SQLException {
		
		return this.lojaService.buscarLojaPorCodigo(codigoBusca);
	}

}

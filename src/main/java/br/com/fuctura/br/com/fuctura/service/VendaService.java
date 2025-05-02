package br.com.fuctura.br.com.fuctura.service;

import java.sql.SQLException;
import java.util.List;

import br.com.fuctura.dto.VendaRelatorioDTO;
import br.com.fuctura.repository.VendaRepository;

public class VendaService {
	 private final VendaRepository repository;

	    public VendaService(VendaRepository repository) {
	        this.repository = repository;
	    }

	    public List<VendaRelatorioDTO> gerarRelatorioCompleto() {
	        try {
	            return repository.listarParaRelatorio();
	        } catch (SQLException e) {
	            throw new RuntimeException("Erro ao gerar relatório", e);
	        }
	    }

	    public List<VendaRelatorioDTO> pesquisarPorVendedor(String nome) throws SQLException {
	        return repository.listarPorVendedor(nome);
	    }
}

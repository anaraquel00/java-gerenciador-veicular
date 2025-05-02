package br.com.fuctura.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fuctura.dto.VendaRelatorioDTO;
import br.com.fuctura.entidade.Vendas;

public class VendaRepository {
	
	    private static Connection conexao;

	    public VendaRepository(Connection conexao) {
	        this.conexao = conexao;
	    }

	    public static List<VendaRelatorioDTO> listarParaRelatorio() throws SQLException {
	        List<VendaRelatorioDTO> vendas = new ArrayList<>();
	        String sql = """
	        SELECT v.placa, v.modelo, v.ano, l.nome AS loja, l.vendedor 
            FROM veiculo v
            JOIN loja l ON v.cod_loja = l.codigo""";
	        try (PreparedStatement stmt = conexao.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            
	            while (rs.next()) {
	                vendas.add(new VendaRelatorioDTO(
	                ));
	            }
	        }
	        return vendas;
	    }

	    

	    private VendaRelatorioDTO mapearParaDTO(ResultSet rs) throws SQLException {
	        return new VendaRelatorioDTO(
	        );
	    }

		public List<VendaRelatorioDTO> listarPorVendedor(String nomeVendedor) throws SQLException {
	        List<VendaRelatorioDTO> vendas = new ArrayList<>();
	        String sql = """
	        SELECT v.placa, v.modelo, v.ano, l.nome AS loja, l.vendedor 
            FROM veiculo v
            JOIN loja l ON v.cod_loja = l.codigo
            WHERE l.vendedor ILIKE ?""";
	        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setString(1, "%" + nomeVendedor + "%");
	            
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    vendas.add(mapearParaDTO(rs));
	                }
	            }
	        }
	        return vendas;
	    }

		

		
	}



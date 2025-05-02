package br.com.fuctura.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fuctura.entidade.Vendedor;

public class VendedorRepository {

	private static Connection conexao;

    public VendedorRepository(Connection conexao) {
        this.conexao = conexao;
    }

    public static List<Vendedor> buscarPorLoja(int codigoLoja) throws SQLException {
        List<Vendedor> vendedores = new ArrayList<>();
        String sql = "SELECT codigo, nome, cpf FROM vendedor WHERE id_loja = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, codigoLoja);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vendedor v = new Vendedor();
                   
                    v.setNome(rs.getString("nome"));
                    v.setCpf(rs.getString("cpf"));
                   
                    vendedores.add(v);
                }
            }
        }
        return vendedores;
    }

	public Vendedor salvar(Vendedor vendedor) {
		return vendedor;
		
	}

	public List<Vendedor> listarTodos() throws SQLException {
        List<Vendedor> vendedores = new ArrayList<>();
        String sql = "SELECT * FROM vendedor";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Vendedor v = new Vendedor();
                v.setCodigo(rs.getInt("codigo"));
                v.setNome(rs.getString("nome"));
                v.setCpf(rs.getString("cpf"));
                v.setIdLoja(rs.getInt("id_loja"));
                vendedores.add(v);
            }
        }
        return vendedores;
    }

	 public void atualizar(Vendedor vendedor) throws SQLException {
	        String sql = "UPDATE vendedor SET nome = ?, cpf = ?, id_loja = ? WHERE codigo = ?";
	        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setString(1, vendedor.getNome());
	            stmt.setString(2, vendedor.getCpf());
	            stmt.setInt(3, vendedor.getIdLoja());
	            stmt.setInt(4, vendedor.getCodigo());
	            stmt.executeUpdate();
	        }
	    }

	 public void deletar(int codigoVendedor) throws SQLException {
	        String sql = "DELETE FROM vendedor WHERE codigo = ?";
	        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setInt(1, codigoVendedor);
	            stmt.executeUpdate();
	        }
	    }

	
}

package br.com.fuctura.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fuctura.entidade.Loja;

public class LojaRepository {
private static Connection conexao;
    
    public LojaRepository() {
        this.conexao = criarConexao();
    }
    
    private static Connection criarConexao() {
        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String usuario = "postgres";
            String senha = "admin";
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
    
    public void salvar(Loja loja) {
        String sql = "INSERT INTO loja (loja, vendedor, veiculos_vendidos) VALUES (?, ?, ?)"; 
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
        	if (loja.getNomeLoja() == null || loja.getNomeLoja().isEmpty()) {
                throw new IllegalArgumentException("Nome da loja é obrigatório!");
            }
            if (loja.getVendedor() == null || loja.getVendedor().isEmpty()) {
                throw new IllegalArgumentException("O nome do vendedor é obrigatório!");
            }
            
        	stmt.setString(1, loja.getNomeLoja()); 
            stmt.setString(2, loja.getVendedor()); 
            stmt.setInt(3, loja.getVeiculosVendidos());

            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    loja.setCodigo(rs.getInt("codigo"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar loja", e);
        }
    }

    
    public List<Loja> listarTodos() {
        List<Loja> lojas = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Loja loja = new Loja();
                loja.setCodigo(1);
                loja.setNomeLoja(rs.getString("Nome da Loja: "));
                loja.setVendedor(rs.getString("Vendedor: "));
                loja.setVeiculosVendidos(rs.getInt("Veiculos vendidos"));
                lojas.add(loja);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar lojass", e);
        }
        return lojas;
    }
    public Loja buscarLojaPorCodigo(int codigo) throws SQLException {
    	 String sql = "SELECT codigo, loja, vendedor, veiculos_vendidos FROM loja WHERE codigo = ?";
        
    	 try (Connection conn = this.conexao;  
    	      PreparedStatement stmt = conn.prepareStatement(sql)) {
    		    stmt.setInt(1, codigo);
           try (ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                Loja loja = new Loja();
                loja.setCodigo(rs.getInt("codigo")); 
                loja.setNomeLoja(rs.getString("loja"));
                loja.setVendedor(rs.getString("vendedor"));
                loja.setVeiculosVendidos(rs.getInt("veiculos_vendidos"));
                
                return loja;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar loja por codigo", e);
        }
    	
      }
    }
    public Loja getUltimaLojaCadastrada() {
        String sql = "SELECT codigo, loja, vendedor, veiculos_vendidos FROM loja ORDER BY codigo DESC LIMIT 1";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             
             ResultSet rs = stmt.executeQuery()) {
            
                if (rs.next()) {
                Loja loja = new Loja();
                loja.setCodigo(rs.getInt("codigo"));
                loja.setNomeLoja(rs.getString("loja"));
                loja.setVendedor(rs.getString("vendedor"));
                loja.setVeiculosVendidos(rs.getInt("veiculos_vendidos"));
                
                return loja;
            }
              return null;
             } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar a última loja", e);
        }
		
    }
    

}

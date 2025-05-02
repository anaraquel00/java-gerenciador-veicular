package br.com.fuctura.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fuctura.entidade.Cliente;
import br.com.fuctura.entidade.Veiculo;

public class ClienteRepository {
private static Connection conexao;
    
    public ClienteRepository() {
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
    
    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf) VALUES (?, ?)"; 
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
        	if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
                throw new IllegalArgumentException("Nome do cliente é obrigatório!");
            }
            if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
                throw new IllegalArgumentException("CPF do cliente é obrigatório!");
            }
        	
        	stmt.setString(1, cliente.getNome()); 
            stmt.setString(2, cliente.getCpf()); 

            stmt.executeUpdate();

            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId_cliente(rs.getInt("id_cliente"));//coluna no postgres int4 not null
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente", e);
        }
    }

    
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(1);
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes", e);
        }
        return clientes;
    }
    public Cliente buscarClientePorId(int id_cliente) throws SQLException {
    	 String sql = "SELECT id_cliente, nome, cpf FROM cliente WHERE id_cliente = ?";
        
    	 try (Connection conn = this.conexao;  
    	      PreparedStatement stmt = conn.prepareStatement(sql)) {
    		    stmt.setInt(1, id_cliente);
           try (ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                Cliente client = new Cliente();
                client.setId_cliente(rs.getInt("id_cliente")); 
                client.setNome(rs.getString("nome"));
                client.setCpf(rs.getString("cpf"));
                
                return client;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por id", e);
        }
    	
      }
    }
    public Cliente getUltimoClienteCadastrado() {
        String sql = "SELECT nome, cpf FROM cliente ORDER BY id_cliente DESC LIMIT 1";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                Cliente cliente = new Cliente();
                
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                
                return cliente;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar último cliente", e);
        }
    }
    

}


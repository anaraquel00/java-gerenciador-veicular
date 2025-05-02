package br.com.fuctura.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.fuctura.dto.ClienteDTO;
import br.com.fuctura.entidade.Cliente;
import br.com.fuctura.utils.JDBCUtils;

public class ClienteDAO {
    public void salvar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nome, cpf) VALUES (?, ?)";
        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.executeUpdate();

            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId_cliente(rs.getInt(1));
                }
            }
        }
    }

    private Connection obterConexao() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "admin");
    }

	    
		public void salvar(ClienteDTO cliente) {
			// TODO Auto-generated method stub
			
		}
}

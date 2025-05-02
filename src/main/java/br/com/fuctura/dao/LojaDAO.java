package br.com.fuctura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.fuctura.entidade.Loja;
import br.com.fuctura.utils.JDBCUtils;

public class LojaDAO {
	public Loja salvar(Loja loja) throws SQLException {
        Connection connection = JDBCUtils.getConnection();

        String comandoSQL = "INSERT INTO loja (codigo, loja, vendedor, veiculos_vendidos) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, loja.getCodigo());
        preparedStatement.setString(2, loja.getNomeLoja());
        preparedStatement.setString(3, loja.getVendedor());
        preparedStatement.setInt(4, loja.getVeiculosVendidos());

        int affectedRow = preparedStatement.executeUpdate();

        if(affectedRow > 0){
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                int id = resultSet.getInt(1);
                Loja response = clone(loja);
                response.setCodigo(id);
                return response;
            }
        }
        throw new RuntimeException("Error on insert Loja");
    }

    private Loja clone(Loja loja){
        return new Loja(loja.getCodigo(), loja.getNomeLoja(), loja.getVendedor(), loja.getVeiculosVendidos());
    }
}

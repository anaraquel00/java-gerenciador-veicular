package br.com.fuctura.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fuctura.entidade.Veiculo;

public class VeiculoRepository {
    private static Connection conexao;
    
    public VeiculoRepository() {
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
    
    public void salvar(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (placa, modelo, ano, valor) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3,veiculo.getAno());
            stmt.setDouble(4, veiculo.getValor());
            
            
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
            veiculo.setCodigo(rs.getInt(1));  
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar veículo", e);
        }
    }
    
    public List<Veiculo> listarTodos() {
        List<Veiculo> veiculo = new ArrayList<>();
        String sql = "SELECT * FROM veiculo";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Veiculo car = new Veiculo(
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getInt("ano"),
                    rs.getDouble("valor")
                    
                );
                ((Veiculo) veiculo).setCodigo(rs.getInt("id"));
                veiculo.add(car);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar veículos", e);
        }
        return veiculo;
    }
    public Veiculo getUltimoVeiculoCadastrado() {
        String sql = "SELECT codigo, placa, modelo, ano, valor FROM veiculo ORDER BY codigo DESC LIMIT 1";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setCodigo(rs.getInt("codigo")); 
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setValor(rs.getDouble("valor"));
                return veiculo;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar último veículo", e);
        }
    }
    
    public Veiculo buscarVeiculoPorCodigo(int codigo) throws SQLException {
    	 String sql = "SELECT codigo, placa, modelo, ano, valor FROM veiculo WHERE codigo = ?";
        
    	 try (Connection conn = this.conexao;  
    	      PreparedStatement stmt = conn.prepareStatement(sql)) {
    		    stmt.setInt(1, codigo);
           try (ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setCodigo(rs.getInt("codigo")); 
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setValor(rs.getDouble("valor"));
                return veiculo;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar veículo por codigo", e);
        }
    	
      }
    }

    // Método para buscar por vendedor
    public List<VeiculoLojaDTO> buscarPorVendedor(String nomeVendedor) throws SQLException {
        List<VeiculoLojaDTO> resultados = new ArrayList<>();
        String sql = """
            SELECT v.placa, v.modelo, v.ano, l.nome AS loja, l.vendedor 
            FROM veiculo v
            JOIN loja l ON v.cod_loja = l.codigo
            WHERE l.vendedor ILIKE ?""";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, "%" + nomeVendedor + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    resultados.add(new VeiculoLojaDTO(
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getInt("ano"),
                        rs.getString("loja"),
                        rs.getString("vendedor")
                    ));
                }
            }
        }
        return resultados;
    }

    // Método para listar todos com dados da loja
    public List<VeiculoLojaDTO> listarTodosComLoja() throws SQLException {
        List<VeiculoLojaDTO> resultados = new ArrayList<>();
        String sql = """
            SELECT v.placa, v.modelo, v.ano, l.nome AS loja, l.vendedor 
            FROM veiculo v
            JOIN loja l ON v.cod_loja = l.codigo""";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                resultados.add(new VeiculoLojaDTO(
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getInt("ano"),
                    rs.getString("loja"),
                    rs.getString("vendedor")
                ));
            }
        }
        return resultados;
    }

}

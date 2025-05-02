package br.com.fuctura;

import java.sql.SQLException;
import java.util.List;

import br.com.fuctura.entidade.Vendedor;
import br.com.fuctura.repository.VendedorRepository;

public class VendedorService {
    private final VendedorRepository repository;

    public VendedorService(VendedorRepository repository) {
        this.repository = repository;
    }

   
    public void cadastrarVendedor(Vendedor vendedor) throws SQLException {
        if (!validarCPF(vendedor.getCpf())) {
		    throw new IllegalArgumentException("CPF inválido!");
		}
		repository.salvar(vendedor);
    }

  
    public List<Vendedor> buscarPorLoja(int codigoLoja) {
        try {
            return repository.buscarPorLoja(codigoLoja);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa: " + e.getMessage(), e);
        }
    }

   
    public List<Vendedor> listarTodos() throws SQLException {
        return repository.listarTodos();
    }

    // Validação básica de CPF
    private boolean validarCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    
    public void atualizarVendedor(Vendedor vendedor) throws SQLException {
        repository.atualizar(vendedor);
    }

    
    public void deletarVendedor(int codigoVendedor) throws SQLException {
        repository.deletar(codigoVendedor);
    }


	public Vendedor lerDadosVendedor() {
		// TODO Auto-generated method stub
		return null;
	}


	public void exibirMensagem(String string) {
		// TODO Auto-generated method stub
		
	}


	public void exibirErro(String message) {
		// TODO Auto-generated method stub
		
	}


}
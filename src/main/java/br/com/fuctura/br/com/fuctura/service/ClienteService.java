package br.com.fuctura.br.com.fuctura.service;

import java.sql.SQLException;

import br.com.fuctura.dto.ClienteDTO;
import br.com.fuctura.entidade.Cliente;
import br.com.fuctura.repository.ClienteRepository;

public class ClienteService {

	 private final ClienteRepository clienteRepository;

	 public ClienteService(ClienteRepository clienteDAO) {
	        this.clienteRepository = clienteDAO;
	    }
	 
	  public void cadastrarCliente(Cliente cliente) {
	        clienteRepository.salvar(cliente);
	    }

    public void salvarCliente(ClienteDTO clienteDTO) {
        
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());

        clienteRepository.salvar(cliente);
    }

    
    private Cliente converterParaCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        return cliente;
    }

		
	public Cliente buscarClientePorId(int id) {
		try {
			return clienteRepository.buscarClientePorId(id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	public Cliente getUltimoClienteCadastrado() {
        return clienteRepository.getUltimoClienteCadastrado();
    }

	
	
}

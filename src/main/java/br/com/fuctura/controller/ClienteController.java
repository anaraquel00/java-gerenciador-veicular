package br.com.fuctura.controller;

import java.sql.SQLException;

import br.com.fuctura.br.com.fuctura.service.ClienteService;
import br.com.fuctura.controller.request.ClienteRequestDTO;
import br.com.fuctura.dto.ClienteDTO;
import br.com.fuctura.entidade.Cliente;

public class ClienteController {

	 private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void cadastrarCliente(Cliente cliente) {
        clienteService.cadastrarCliente(cliente);
    }
    
    public Cliente getUltimoClienteCadastrado() {
        return clienteService.getUltimoClienteCadastrado();
    }

    private ClienteDTO convertToClienteDTO(ClienteRequestDTO requestDTO) {
        var clienteDTO = new ClienteDTO();
        clienteDTO.setNome(requestDTO.getNome());
        clienteDTO.setCpf(requestDTO.getCpf());
        
        return clienteDTO;
    }
    public Cliente buscarClientePorId(int id_cliente) throws SQLException {
        return clienteService.buscarClientePorId(id_cliente);
    }
    
}

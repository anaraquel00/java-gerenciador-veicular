package br.com.fuctura.controller;

import java.sql.SQLException;

import br.com.fuctura.br.com.fuctura.service.VeiculoService;
import br.com.fuctura.controller.request.VeiculoRequestDTO;
import br.com.fuctura.dto.VeiculoDTO;
import br.com.fuctura.entidade.Veiculo;

public class VeiculoController {

    private final VeiculoService veiculoService;
    private Veiculo ultimoVeiculoCadastrado;
    

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    public void cadastrarVeiculo(VeiculoRequestDTO requestDTO){

        var veiculoDTO = convertToVeiculoDTO(requestDTO);

        veiculoService.salvar(veiculoDTO);
        
    }
    
    public Veiculo getUltimoVeiculoCadastrado() {
        return veiculoService.getUltimoVeiculoCadastrado();
    }

    private VeiculoDTO convertToVeiculoDTO(VeiculoRequestDTO requestDTO) {
        var veiculoDTO = new VeiculoDTO();
        veiculoDTO.setPlaca(requestDTO.getPlaca());
        veiculoDTO.setModelo(requestDTO.getModelo());
        veiculoDTO.setAno(requestDTO.getAno());
        veiculoDTO.setValor(requestDTO.getValor());
        return veiculoDTO;
    }
    public Veiculo buscarVeiculoPorCodigo(int codigo) throws SQLException {
        return veiculoService.buscarVeiculoPorCodigo(codigo);
    }
    
}

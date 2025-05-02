package br.com.fuctura.controller;

import java.sql.SQLException;
import java.util.List;

import br.com.fuctura.br.com.fuctura.service.VeiculoService;
import br.com.fuctura.controller.request.VeiculoRequestDTO;
import br.com.fuctura.dto.VeiculoDTO;
import br.com.fuctura.entidade.Veiculo;
import br.com.fuctura.repository.VeiculoLojaDTO;
import br.com.fuctura.view.VeiculoView;

public class VeiculoController {
	private VeiculoService service;
	private VeiculoView view;
    private VeiculoService veiculoService;
    private Veiculo ultimoVeiculoCadastrado;
    
    public VeiculoController(VeiculoService service, VeiculoView view) {
        this.veiculoService = service;
		this.service = service;
        this.view = view;
    }
    

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    public void cadastrarVeiculo(VeiculoRequestDTO requestDTO) throws SQLException{

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
    
    public void pesquisarPorVendedor(String nomeVendedor) {
        List<VeiculoLojaDTO> resultados = veiculoService.buscarPorVendedor(nomeVendedor);
        view.exibirVeiculos(resultados);
    }

    public void exibirTodosComLoja() {
        List<VeiculoLojaDTO> resultados = veiculoService.listarTodosComLoja();
        view.exibirVeiculos(resultados);
    }
    
}

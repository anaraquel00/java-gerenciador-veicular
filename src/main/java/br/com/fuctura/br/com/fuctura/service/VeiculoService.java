package br.com.fuctura.br.com.fuctura.service;

import br.com.fuctura.controller.request.VeiculoRequestDTO;
import br.com.fuctura.dao.VeiculoDAO;
import br.com.fuctura.dto.VeiculoDTO;
import br.com.fuctura.entidade.Veiculo;
import br.com.fuctura.repository.VeiculoRepository;

import java.sql.SQLException;



public class VeiculoService {
	private VeiculoRepository repository = new VeiculoRepository();
    private final VeiculoDAO veiculoDAO;

    public VeiculoService(VeiculoDAO veiculoDAO) {
        this.veiculoDAO = veiculoDAO;
    }
    
    public Veiculo converterParaEntidade(VeiculoRequestDTO dto) {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setAno(dto.getAno());
        veiculo.setValor(dto.getValor());
        return veiculo;
    }

    public void salvar(VeiculoDTO veiculoDTO){
        Veiculo veiculo = convertTOVeiculo(veiculoDTO);

        try {
            veiculoDAO.salvar(veiculo);
            System.out.println("Veiculo salvo com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Veiculo convertTOVeiculo(VeiculoDTO veiculoDTO){
        return new Veiculo(veiculoDTO.getPlaca(), veiculoDTO.getModelo(), veiculoDTO.getAno(), veiculoDTO.getValor());
    }
    
    public Veiculo getUltimoVeiculoCadastrado() {
        return repository.getUltimoVeiculoCadastrado();
    }
    
    public Veiculo buscarVeiculoPorCodigo(int codigo) throws SQLException {
       
        if (codigo <= 0) {
            throw new IllegalArgumentException("Código inválido");
        }
        return repository.buscarVeiculoPorCodigo(codigo);
    }

}

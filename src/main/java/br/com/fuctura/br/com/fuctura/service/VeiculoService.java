package br.com.fuctura.br.com.fuctura.service;

import br.com.fuctura.controller.request.VeiculoRequestDTO;
import br.com.fuctura.dao.VeiculoDAO;
import br.com.fuctura.dto.VeiculoDTO;
import br.com.fuctura.entidade.Veiculo;
import br.com.fuctura.repository.VeiculoLojaDTO;
import br.com.fuctura.repository.VeiculoRepository;

import java.sql.SQLException;
import java.util.List;



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

    public void salvar(VeiculoDTO veiculoDTO) throws SQLException{
        Veiculo veiculo = convertTOVeiculo(veiculoDTO);

        veiculoDAO.salvar(veiculo);
		System.out.println("Veiculo salvo com sucesso!");

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
    
    public List<VeiculoLojaDTO> buscarPorVendedor(String nomeVendedor) {
        try {
            return repository.buscarPorVendedor(nomeVendedor);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na pesquisa", e);
        }
    }

    public List<VeiculoLojaDTO> listarTodosComLoja() {
        try {
            return repository.listarTodosComLoja();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar veículos", e);
        }
    }

}

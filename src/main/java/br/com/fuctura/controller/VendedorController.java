package br.com.fuctura.controller;

import java.sql.SQLException;
import java.util.List;

import br.com.fuctura.VendedorService;
import br.com.fuctura.entidade.Vendedor;
import br.com.fuctura.repository.VendedorRepository;
import br.com.fuctura.view.VendedorView;

public class VendedorController {

	private static VendedorView vendedorView;
	
    private final VendedorView repository;

    public VendedorController(VendedorView vendedorService, VendedorView vendedorView) {
        this.vendedorView = vendedorService;
        this.repository = vendedorView;
    }

    public static void cadastrarVendedor() throws SQLException {
        Vendedor vendedor = vendedorView.cadastrarVendedor(vendedorView);
		try {
			vendedor = vendedorView.cadastrarVendedor(vendedorView);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        if (vendedor != null) {
            
            System.out.println("Vendedor selecionado: " + vendedor.getNome());
        }
    }

	public List<Vendedor> VendedorController() throws SQLException {
		return vendedorView.listarTodos();
		
	}

	public List<Vendedor> buscarPorLoja(int codigo) throws SQLException {
		
		return vendedorView.buscarPorLoja(codigo);
	}
}

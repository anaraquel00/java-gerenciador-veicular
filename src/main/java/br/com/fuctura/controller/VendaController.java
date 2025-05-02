package br.com.fuctura.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.fuctura.br.com.fuctura.service.VendaService;
import br.com.fuctura.dto.VendaRelatorioDTO;
import br.com.fuctura.entidade.Vendas;
import br.com.fuctura.entidade.Vendedor;
import br.com.fuctura.repository.VendedorRepository;
import br.com.fuctura.view.VendedorView;

public class VendaController {
	
	private final VendaService service;
    private final VendedorView view;
    Scanner scanner = new Scanner(System.in);
    public VendaController(VendaService service, VendedorView view) {
        this.service = service;
        this.view = view;
    }
    public Vendedor cadastrarVendedor() throws SQLException {
        System.out.println("\n=== CADASTRO DE VENDEDOR ===");
        
        try {
            System.out.print("Código da Loja: ");
            int codigoLoja = Integer.parseInt(scanner.nextLine());
            
            List<Vendedor> vendedores = null;
			try {
				vendedores = VendedorRepository.buscarPorLoja(codigoLoja);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
            
            if (vendedores.isEmpty()) {
                System.out.println("Nenhum vendedor encontrado para esta loja!");
                return null;
            }
   
        }    finally {
        	
        }
		return null;
    }

    }

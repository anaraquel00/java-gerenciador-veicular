package br.com.fuctura.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.com.fuctura.dto.VendaRelatorioDTO;
import br.com.fuctura.entidade.Loja;
import br.com.fuctura.entidade.Vendedor;
import br.com.fuctura.repository.VendedorRepository;

public class VendedorView {

	private final Scanner scanner;
    private final VendedorRepository repository;

    public VendedorView(Scanner scanner, VendedorRepository repository) {
        this.scanner = scanner;
        this.repository = repository;
    }

    public Vendedor cadastrarVendedor(VendedorView vendedorView) throws SQLException {
        System.out.println("\n=== CADASTRO DE VENDEDOR ===");
        
        try {
            System.out.print("Código da Loja: ");
            int codigoLoja = Integer.parseInt(scanner.nextLine());
            
            List<Vendedor> vendedores = repository.buscarPorLoja(codigoLoja);
            
            if (vendedores.isEmpty()) {
                System.out.println("Nenhum vendedor encontrado para esta loja!");
                return null;
            }
            
            System.out.println("\nVendedores disponíveis:");
            for (int i = 0; i < vendedores.size(); i++) {
                Vendedor v = vendedores.get(i);
                System.out.printf("%d - %s (CPF: %s)\n", 
                    (i + 1), v.getNome(), v.getCpf());
            }
            
            System.out.print("Escolha o vendedor (número): ");
            int escolha = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (escolha >= 0 && escolha < vendedores.size()) {
                return vendedores.get(escolha);
            } else {
                System.out.println("Escolha inválida!");
                
                 } }
                        finally {
                            	
                     }
		         return new Vendedor();
		}
        
           public Vendedor lerDadosVendedor(Loja loja) {
		        System.out.println("\n=== NOVO VENDEDOR ===");
		        System.out.println("Loja Associada: " + loja.getNomeLoja());

		        Vendedor v = new Vendedor();
		        v.setIdLoja(loja.getCodigo());

		        System.out.print("Nome: ");
		        v.setNome(scanner.nextLine());

		        System.out.print("CPF: ");
		        v.setCpf(scanner.nextLine());

		        return v;
		    }

		

			 public void exibirVendedores(List<Vendedor> vendedores) {
			        System.out.println("\nCOD | NOME          | CPF          | LOJA");
			        System.out.println("--------------------------------------------");
			        for (Vendedor v : vendedores) {
			            System.out.printf("%-4d| %-13s| %-13s| %-20s\n",
			                v.getCodigo(), 
			                v.getNome(), 
			                v.getCpf(), 
			                v.getIdLoja()
			            );
			        }
			    }

			public List<Vendedor> listarTodos() throws SQLException {
				
				return repository.listarTodos();
			}

			public List<Vendedor> buscarPorLoja(int codigo) throws SQLException {
				
				return repository.buscarPorLoja(codigo);
			}
		
    
    
    }
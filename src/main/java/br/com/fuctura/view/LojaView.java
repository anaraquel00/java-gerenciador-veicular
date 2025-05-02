package br.com.fuctura.view;

import java.util.Scanner;

import br.com.fuctura.controller.request.VeiculoRequestDTO;
import br.com.fuctura.entidade.Loja;
import br.com.fuctura.entidade.Veiculo;

public class LojaView implements IView{

	private Scanner scanner;

	@Override
	public void exibirMenu() {
		// TODO Auto-generated method stub
		
	}

	
	    public LojaView() {
	        this.scanner = new Scanner(System.in);
	    }

	    public Loja lerDadosLoja() {
	        Loja loja = new Loja();

	        System.out.println("=== CADASTRO DA LOJA ===");
	        
	        try {
	            System.out.print("Nome da Loja: ");
	            loja.setNomeLoja(scanner.nextLine().trim());

	            System.out.print("Vendedor: ");
	            loja.setVendedor(scanner.nextLine().trim());

	            System.out.print("Veículos Vendidos: ");
	            loja.setVeiculosVendidos(Integer.parseInt(scanner.nextLine()));
	        } catch (Exception e) {
	            System.err.println("Erro ao ler dados: " + e.getMessage());
	            return null; 
	        }

	        return loja; 
	    }
	

private Loja Loja() {
	
	return null;
}

public void exibirDetalhes(Loja loja) {
	System.out.println("\n--- DADOS DO LOJA CADASTRADA ---");
    System.out.println("Codigo: " + loja.getCodigo());
    System.out.println("Nome da Loja: " + loja.getNomeLoja());
    System.out.println("Vendedor: " + loja.getVendedor());
    System.out.println("Veiculos vendidos: " + loja.getVeiculosVendidos());
    System.out.println("--------------------------------------");
}

	}

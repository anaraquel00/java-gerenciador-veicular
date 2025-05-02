package br.com.fuctura.view;

import java.util.Scanner;

import br.com.fuctura.controller.ClienteController;
import br.com.fuctura.controller.request.ClienteRequestDTO;
import br.com.fuctura.entidade.Cliente;


public class ClienteView implements IView{
	
	private final Scanner scanner;

    public ClienteView(ClienteController clienteController){
        this.scanner = new Scanner(System.in);
    }


	@Override
	public void exibirMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Exibir Cliente cadastrado");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
}

	    public ClienteView() {
	        this.scanner = new Scanner(System.in);
	    }

	    public Cliente lerDadosCliente() {
	        System.out.println("=== CADASTRO DE CLIENTES ===");
	        
	        Cliente cliente = new Cliente();
	        
	        System.out.print("Nome: ");
	        String nome = scanner.nextLine().trim();
	        if (nome.isEmpty()) {
	            throw new IllegalArgumentException("Nome não pode ser vazio!");
	        }
	        cliente.setNome(nome);

	        System.out.print("CPF: ");
	        String cpf = scanner.nextLine().trim();
	        if (cpf.isEmpty()) {
	            throw new IllegalArgumentException("CPF não pode ser vazio!");
	        }
	        cliente.setCpf(cpf);

	        return cliente;
	    }
	


public void exibirDetalhes(Cliente cliente) {
	System.out.println("\n--- DADOS DO CLIENTE CADASTRADO ---");
    System.out.println("Nome: " +cliente.getNome() );
    System.out.println("Cpf: " + cliente.getCpf());
    System.out.println("--------------------------------------");
}
		
	}



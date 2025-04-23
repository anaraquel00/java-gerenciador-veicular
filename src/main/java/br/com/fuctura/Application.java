package br.com.fuctura;

import br.com.fuctura.br.com.fuctura.service.VeiculoService;
import br.com.fuctura.controller.VeiculoController;
import br.com.fuctura.dao.VeiculoDAO;
import br.com.fuctura.entidade.Veiculo;
import br.com.fuctura.view.VeiculoView;

import java.sql.SQLException;
import java.util.Scanner;

public class Application {

    private final Scanner scanner;
    private final VeiculoDAO veiculoDAO;
    private final VeiculoService veiculoService;
    private final VeiculoController veiculoController;
    private final VeiculoView veiculoView;

    public Application(){
        this.scanner = new Scanner(System.in);
        this.veiculoDAO = new VeiculoDAO();
        this.veiculoService = new VeiculoService(veiculoDAO);
        this.veiculoController = new VeiculoController(veiculoService);
        this.veiculoView = new VeiculoView();
    }

    public void run() throws SQLException {
        System.out.println("- Sistema Gerenciador - Veicular - ");
        System.out.println("\nMenu:");
        System.out.println("1. Cadastrar Veículo");
        System.out.println("2. Exibir Veículo");
        System.out.println("3. Buscar Veículo pelo código");
        System.out.println("4. Sair");
        System.out.print("Escolha a opção desejada: ");


        while (true){
            int opcao = scanner.nextInt();
            switch (opcao){
                 case 1:
                 var veiculo = veiculoView.lerDadosVeiculo();
                 veiculoController.cadastrarVeiculo(veiculo);
                    
            case 2:
            	Veiculo ultimo = veiculoController.getUltimoVeiculoCadastrado();
                if (ultimo != null) {
                    System.out.println("\n=== ÚLTIMO VEÍCULO CADASTRADO ===");
                    System.out.println("Código: " + ultimo.getCodigo());
                    System.out.println("Placa: " + ultimo.getPlaca());
                    System.out.println("Modelo: " + ultimo.getModelo());
                    System.out.println("Ano: " + ultimo.getAno());
                    System.out.printf("Valor: R$ %.2f%n", ultimo.getValor());
                    System.out.println("=================================");
                } else {
                    System.out.println("Nenhum veículo cadastrado ainda!");
                }
                break;
            case 3:
            	System.out.print("Digite o código do veículo: ");
                int codigoBusca = scanner.nextInt();
                scanner.nextLine(); 
                
                Veiculo encontrado = veiculoController.buscarVeiculoPorCodigo(codigoBusca);
                if (encontrado != null) {
                    System.out.println("\n=== VEÍCULO ENCONTRADO ===");
                    System.out.println("Código: " + encontrado.getCodigo());
                    System.out.println("Placa: " + encontrado.getPlaca());
                    System.out.println("Modelo: " + encontrado.getModelo());
                    System.out.println("Ano: " + encontrado.getAno());
                    System.out.printf("Valor: R$ %.2f%n", encontrado.getValor());
                    System.out.println("==========================");
                } else {
                    System.out.println("Veículo não encontrado com o código: " + codigoBusca);
                }
                break;

            case 4:
            	 System.out.println("Saindo...");
            	    System.exit(0);  
            	    break;
            default:
                System.out.println("Opção inválida!");
        }
            	
        }
    }}

    



package br.com.fuctura.view;

import br.com.fuctura.controller.request.VeiculoRequestDTO;
import br.com.fuctura.entidade.Veiculo;

import java.util.Scanner;

public class VeiculoView implements IView {

    private final Scanner scanner;

    public VeiculoView(){
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void exibirMenu() {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar Veículo");
            System.out.println("2. Exibir Veículo");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
    }

    public VeiculoRequestDTO lerDadosVeiculo() {
        System.out.println("=== CADASTRO DE VEICULOS ===");

        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Ano: ");
        Integer ano = scanner.nextInt();

        System.out.print("Valor: ");
        Double valor = scanner.nextDouble();
        scanner.nextLine(); // limpar buffer

        return new VeiculoRequestDTO(placa, modelo, ano, valor);
        
    }


    public void exibirDetalhes(Veiculo veiculo) {
    	System.out.println("\n--- DADOS DO VEÍCULO CADASTRADO ---");
        System.out.println("ID: " + veiculo.getCodigo());
        System.out.println("Placa: " + veiculo.getPlaca());
        System.out.println("Modelo: " + veiculo.getModelo());
        System.out.println("Ano: " + veiculo.getAno());
        System.out.println("Valor: R$ " + String.format("%.2f", veiculo.getValor()));
        System.out.println("--------------------------------------");
    }
}

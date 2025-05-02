package br.com.fuctura;

import br.com.fuctura.br.com.fuctura.service.ClienteService;
import br.com.fuctura.br.com.fuctura.service.LojaService;
import br.com.fuctura.br.com.fuctura.service.VeiculoService;
import br.com.fuctura.br.com.fuctura.service.VendaService;

import br.com.fuctura.controller.ClienteController;
import br.com.fuctura.controller.LojaController;
import br.com.fuctura.controller.VeiculoController;
import br.com.fuctura.controller.VendaController;
import br.com.fuctura.controller.VendedorController;
import br.com.fuctura.dao.ClienteDAO;
import br.com.fuctura.dao.LojaDAO;
import br.com.fuctura.dao.VeiculoDAO;
import br.com.fuctura.dto.VendaRelatorioDTO;
import br.com.fuctura.entidade.Cliente;
import br.com.fuctura.entidade.Loja;
import br.com.fuctura.entidade.Veiculo;
import br.com.fuctura.entidade.Vendas;
import br.com.fuctura.entidade.Vendedor;
import br.com.fuctura.repository.ClienteRepository;
import br.com.fuctura.repository.LojaRepository;
import br.com.fuctura.repository.VeiculoRepository;
import br.com.fuctura.repository.VendaRepository;
import br.com.fuctura.repository.VendedorRepository;
import br.com.fuctura.view.ClienteView;
import br.com.fuctura.view.LojaView;
import br.com.fuctura.view.VeiculoView;
import br.com.fuctura.view.VendedorView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Application {

	private Scanner scanner;
    private VeiculoDAO veiculoDAO;
    private VeiculoService veiculoService;
    private VeiculoController veiculoController;
    private VeiculoView veiculoView;
    
    private  ClienteRepository clienteRepository;
    private  ClienteService clienteService;
    private  ClienteController clienteController;
    private  ClienteView clienteView;
    
    private LojaDAO lojaDAO;
    private LojaService lojaService;
    private LojaController lojaController;
    private LojaView lojaView;
    private LojaRepository lojaRepository;
      
    private VendedorRepository vendedorRepository;
    private VendedorService vendedorService;
    private VendedorView vendedorView;
   
    private Connection conexao;
	private VendedorController vendedorController;
	


    public Application(){
    
    	
            try {
                String url = "jdbc:postgresql://localhost:5432/postgres";
                String usuario = "postgres";
                String senha = "admin";
                this.conexao = DriverManager.getConnection(url, usuario, senha);
                this.scanner = new Scanner(System.in);

                this.veiculoDAO = new VeiculoDAO();
                this.veiculoService = new VeiculoService(veiculoDAO);
                this.veiculoView = new VeiculoView();
                this.veiculoController = new VeiculoController(veiculoService, veiculoView);

                this.clienteRepository = new ClienteRepository();
                this.clienteService = new ClienteService(clienteRepository);
                this.clienteView = new ClienteView();
                this.clienteController = new ClienteController(clienteService);

                this.lojaRepository = new LojaRepository();
                this.lojaDAO = new LojaDAO();
                this.lojaService = new LojaService(lojaRepository);
                this.lojaView = new LojaView();
                this.lojaController = new LojaController(lojaService);
                
                this.vendedorRepository = new VendedorRepository(conexao);
                this.vendedorService = new VendedorService(vendedorRepository);
                this.vendedorView = new VendedorView(scanner, vendedorRepository);
                this.vendedorController = new VendedorController(vendedorView, vendedorView);

                

            } catch (SQLException e) {
                System.err.println("Erro ao conectar ao banco: " + e.getMessage());
                System.exit(1);
            }
    
			this.veiculoView = veiculoView;
			this.veiculoService = veiculoService;
			this.veiculoDAO = veiculoDAO;
			this.veiculoController = veiculoController;
			this.scanner = scanner;
			this.lojaView = lojaView;
			this.vendedorView = vendedorView;
			this.vendedorService = vendedorService;
    }

        public void run() throws SQLException {
            while (true) {
                exibirMenuPrincipal();
                int opcao = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcao) {
                    case 1:
                        menuVeiculo();
                        break;
                    case 2:
                        menuCliente();
                        break;
                    case 3:
                        menuLoja();
                        break;
                    case 4:
                        menuVendedor();
                        break;
                    case 5:
                        System.out.println("Saindo do sistema...");
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        }

    private void exibirMenuPrincipal() {
    	System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Menu Veículos");
        System.out.println("2. Menu Clientes");
        System.out.println("3. Menu Lojas");
        System.out.println("4. Menu Vendedor");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
		
	}
    
    public void menuVeiculo() throws SQLException {
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
            case 5:
            	return;
            default:
                System.out.println("Opção inválida!");
        }
            	
        }
    }
    
    private void menuCliente() throws SQLException {
    	System.out.println("- Sistema Gerenciador - Veicular - ");
        System.out.println("\nMenu:");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Exibir Cliente");
        System.out.println("3. Buscar Cliente pelo id");
        System.out.println("4. Sair");
        System.out.print("Escolha a opção desejada: ");


        while (true){
            int opcao = scanner.nextInt();
            switch (opcao){
                 case 1:
                	 Cliente novoCliente = clienteView.lerDadosCliente();
                     clienteController.cadastrarCliente(novoCliente);
                     System.out.println("Cliente cadastrado com sucesso!");
                     break;
                    
            case 2:
            	Cliente ultimo = clienteController.getUltimoClienteCadastrado();
                if (ultimo != null) {
                    System.out.println("\n=== ÚLTIMO CLIENTE CADASTRADO ===");
                    System.out.println("Id do cliente:"  + ultimo.getId_cliente());
                    System.out.println("Nome: " + ultimo.getNome());
                    System.out.println("Cpf: " + ultimo.getCpf());
                    System.out.println("=================================");
                } else {
                    System.out.println("Nenhum veículo cadastrado ainda!");
                }
                break;
            case 3:
            	System.out.print("Digite o id do cliente: ");
                int idBusca = scanner.nextInt();
                scanner.nextLine(); 
                
                Cliente encontrado = clienteController.buscarClientePorId(idBusca);
                if (encontrado != null) {
                    System.out.println("\n=== CLIENTE ENCONTRADO ===");
                    System.out.println("Id do cliente: " + encontrado.getId_cliente());
                    System.out.println("Nome: " + encontrado.getNome());
                    System.out.println("Cpf: " + encontrado.getCpf());
                    System.out.println("==========================");
                } else {
                    System.out.println("Cliente não encontrado com o id: " + idBusca);
                }
                break;

            case 4:
            	 System.out.println("Saindo...");
            	    System.exit(0);  
            	    break;
            case 5:
            	return;
            default:
                System.out.println("Opção inválida!");
        }
            	
        }
		
	}
    
    private void menuLoja() throws SQLException {
    	System.out.println("- Sistema Gerenciador - Veicular - ");
        System.out.println("\nMenu:");
        System.out.println("1. Cadastrar Loja");
        System.out.println("2. Exibir Loja");
        System.out.println("3. Buscar Loja pelo codigo");
        System.out.println("4. Sair");
        System.out.print("Escolha a opção desejada: ");


        while (true){
            int opcao = scanner.nextInt();
            Loja codigoBusca;
			switch (opcao){
                 case 1:
                 var loja = lojaView.lerDadosLoja();
                 if (loja != null) {
                     lojaController.cadastrarLoja(loja);
                 } else {
                     System.out.println("Erro: Dados da loja inválidos!");
                 }
                
                    
            case 2:
            	Loja ultimo = lojaController.getUltimaLojaCadastrada();
                if (ultimo != null) {
                    System.out.println("\n=== ÚLTIMO LOJA CADASTRADA ===");
                    System.out.println("Codigo: " + ultimo.getCodigo());
                    System.out.println("Nome da Loja: " + ultimo.getNomeLoja());
                    System.out.println("Vendedor: " + ultimo.getVendedor());
                    System.out.println("Veiculos vendidos: " + ultimo.getVeiculosVendidos());
                    System.out.println("=================================");
                } else {
                    System.out.println("Nenhum loja cadastrada ainda!");
                }
                break;
            case 3:
            	System.out.print("Digite o codigo da loja: ");
                int codigoBusca1 = scanner.nextInt();
                scanner.nextLine(); 
                
                Loja encontrada = lojaController.buscarLojaPorCodigo(codigoBusca1);
                if (encontrada != null) {
                    System.out.println("\n=== LOJA ENCONTRADA ===");
                    System.out.println("Codigo: " + encontrada.getCodigo());
                    System.out.println("Nome da Loja: " + encontrada.getNomeLoja());
                    System.out.println("Vendedor: " + encontrada.getVendedor());
                    System.out.println("Veiculos vendidos: " + encontrada.getVeiculosVendidos());
                    System.out.println("==========================");
                } else {
                    System.out.println("Loja não encontrada com o codigo: " + codigoBusca1);
                }
                break;

            case 4:
            	 System.out.println("Saindo...");
            	    System.exit(0);  
            	    break;
            case 5:
            	return;
            default:
                System.out.println("Opção inválida!");
			}}
            	
        }
		
    private void menuVendedor() throws SQLException {
        while (true) {
            System.out.println("\n=== MENU VENDEDORES ===");
            System.out.println("1. Cadastrar Vendedor");
            System.out.println("2. Buscar Vendedor por Loja");
            System.out.println("3. Listar Todos Vendedores");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha a opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                   
                    System.out.print("Digite o código da loja: ");
                    int codigoLoja = scanner.nextInt();
                    scanner.nextLine();

                    Loja loja = lojaController.buscarLojaPorCodigo(codigoLoja);
                    if (loja == null) {
                        System.out.println("Loja não encontrada!");
                        break;
                    }

                   
                    Vendedor novoVendedor = vendedorView.lerDadosVendedor(loja);
                    VendedorController.cadastrarVendedor();
                    break;

                case 2:
                    
                    System.out.print("Digite o código da loja: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();
                    
                    List<Vendedor> vendedores = vendedorController.buscarPorLoja(codigo);
                    vendedorView.exibirVendedores(vendedores);
                    break;

                case 3:
                    vendedorController.VendedorController();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }}
        public static void main(String[] args) {
            try {
                new Application().run();
            } catch (SQLException e) {
                System.err.println("Erro fatal: " + e.getMessage());
            }
        }
    }

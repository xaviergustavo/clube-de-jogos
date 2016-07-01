package interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import clube.Clube;
import gerenciadores.GerenciadorLocal;
import local.Local;
import logger.ClubeLogger;


public class GerenciaLocalInterface {

	private GerenciadorLocal<Local> gerenciador;
	
	private ClubeLogger log;

	public GerenciaLocalInterface() {
		this.gerenciador = new GerenciadorLocal<>();
		this.log = ClubeLogger.getInstance();
	}
	
	public void cadastrarNovoLocal (){
		log.registrar("Cadastrando novo local...");
		boolean cadastrou = gerenciador.cadastrarNovoLocal();
		if (cadastrou) {
			log.registrarComSaida("Local cadastrado com sucesso");
		} else {
			log.registrarComSaida("Nao foi possivel cadastrar o local!");
		}
	}
	
	private void menuCadastrarNovosLocais() {
		System.out.println("Deseja adicionar um local na lista:\n");
		System.out.println("1 - Sim");
		System.out.println("2 - Nao");
	}
	
	public void cadastrarNovosLocais() {
		log.registrar("Cadastrando lista de novos locais...");
		
		List<Local> locais = new ArrayList<>();
		
		System.out.println("Menu de cadastrar Lista de Novos Locais:\n");
		
		menuCadastrarNovosLocais();
		
		boolean finalizou = false;
		
		int opcao = 2;
		
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNext()) {
			
			try {
				opcao = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				e.printStackTrace();
				opcao = 2;
			}
			switch (opcao) {
			case 1:
				System.out.println ("Digite o nome do local:");
				String nomeLocal = scanner.nextLine();
				
				System.out.println ("Digite a categoria do local:");
				System.out.println ("1 - Quadras");
				System.out.println ("2 - Salas de atividades");
				int id = Integer.parseInt(scanner.nextLine());
				
				Local novo = new Local(nomeLocal, Clube.getInstance().getCategoria(id));
				locais.add(novo);
				
				menuCadastrarNovosLocais();
				break;
			case 2:
				finalizou = true;
				break;
			}
			if (finalizou) {
				break;
			}
		}
		if (locais.size() > 0) {
			boolean sucesso = gerenciador.cadastrarNovosLocais(locais);
			if (sucesso) {
				log.registrarComSaida("Lista de novos locais cadastrada com sucesso!");
			} else {
				log.registrarComSaida("Nao foi possivel cadastrar lista de novo locais");
			}
		}
	}
	
	private void menuVisualizarLocal() {
		System.out.println("Menu de Visualizacao de Local\n");
		
		System.out.println("1 - Visualizar local por nome");
		System.out.println("2 - Visualizar local por id");
		System.out.println("0 - Voltar para o Menu de Gerenciador de Local");
		
		System.out.println("Selecione uma opcao:");
	}
		
	public void visualizarLocal() {
			
			menuVisualizarLocal();
			
			Scanner scanner = new Scanner(System.in);
			
			boolean sair = false;
			
			while(scanner.hasNext()) {
				int opcao = scanner.nextInt();
				switch (opcao) {
				case 1:
					visualizarLocalNome();
					break;
				case 2:
					visualizarLocalId();
					break;
				case 0:
					sair = true;
					break;
				}
				if (sair) {
					break;
				}
				menuVisualizarLocal();
			}
			
	}
	
	private void visualizarLocalNome() {
		Scanner scanner = new Scanner(System.in);
		String nomeLocal = "";
		while (nomeLocal.equals("")) {
			System.out.println("Digite o nome do local:");
			nomeLocal = scanner.nextLine();
			if (nomeLocal.equals("")) {
				System.out.println("Nome do local invalido\n");
			}
		}
		log.registrar("Visualizando local por nome: " + nomeLocal);
		gerenciador.visualizarLocal(nomeLocal);
	}
	
	private void visualizarLocalId() {
		Scanner scanner = new Scanner(System.in);
		int id = 0;
		while (id == 0) {
			System.out.println("Digite o id do local:");
			try {
				id = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Id do local invalido\n");
				id = 0;
			}
		}
		log.registrar("Visualizando local por id: " + id);
		gerenciador.visualizarLocal(id);
	}
	
	public void visualizarTodosLocais() {
		log.registrar("Visualizando todos os locais...");
		System.out.format("Quantidade de locais: %s%n%n", gerenciador.visualizarTodosLocais());
	}
	
	private void menuEditarLocal() {
		System.out.println("Menu de Edicao de Local\n");
		
		System.out.println("1 - Selecionar local por nome");
		System.out.println("2 - Selecionar local por id");
		System.out.println("0 - Voltar para o Menu de Gerenciador de Local");
		
		System.out.println("Selecione uma opcao:");
	}
	
	public void editarLocal() {
		menuEditarLocal();
		
		Scanner scanner = new Scanner(System.in);
		
		boolean sair = false;
		
		while(scanner.hasNext()) {
			int opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				editarLocalNome();
				break;
			case 2:
				editarLocalId();
				break;
			case 0:
				sair = true;
				break;
			}
			if (sair) {
				break;
			}
			menuVisualizarLocal();
		}
		
	}
	
	private void editarLocalNome() {
		Scanner scanner = new Scanner(System.in);
		String nomeLocal = "";
		while (nomeLocal.equals("")) {
			System.out.println("Digite o nome do local:");
			nomeLocal = scanner.nextLine();
			if (nomeLocal.equals("")) {
				System.out.println("Nome do local invalido\n");
			}
		}
		log.registrar("Editando local por nome: " + nomeLocal);
		gerenciador.editarLocal(nomeLocal);
	}
	
	private void editarLocalId() {
		Scanner scanner = new Scanner(System.in);
		int id = 0;
		while (id == 0) {
			System.out.println("Digite o id do local:");
			try {
				id = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Id do local invalido\n");
				id = 0;
			}
		}
		log.registrar("Editando local por id: " + id);
		gerenciador.editarLocal(id);
	}
	
	private void menuRemoverLocal() {
		System.out.println("Menu de Remocao de Local\n");
		
		System.out.println("1 - Selecionar local por nome");
		System.out.println("2 - Selecionar local por matricula");
		System.out.println("0 - Voltar para o Menu de Gerenciador de Local");
		
		System.out.println("Selecione uma opcao:");
	}
	
	public void removerLocal() {
		menuRemoverLocal();
		
		boolean sair = false;
		
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			int opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				removerLocalNome();
				break;
			case 2:
				removerLocalId();
				break;
			case 0:
				sair = true;
				break;
			}
			if (sair) {
				break;
			}
			menuVisualizarLocal();
		}
		
	}
	
	public void removerLocalNome() {
		Scanner scanner = new Scanner(System.in);
		String nomeLocal = "";
		
		while (nomeLocal.equals("")) {
			System.out.println("Digite o nome do local:");
			nomeLocal = scanner.nextLine();
			if (nomeLocal.equals("")) {
				System.out.println("Nome de local invalido\n");
			}
		}
		
		log.registrarComSaida("Removendo local por nome: " + nomeLocal);
		
		boolean removido = gerenciador.removerLocal(nomeLocal);
		if (removido) {
			log.registrarComSaida("Local " + nomeLocal + " removido com sucesso!");
		} else {
			log.registrarComSaida("Local " + nomeLocal + " nao foi removido");
		}
		
	}
	
	public void removerLocalId() {
		Scanner scanner = new Scanner(System.in);
		int id = 0;
		
		while (id == 0) {
			System.out.println("Digite a id do local:");
			try {
				id = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("id do local invalida\n");
				id = 0;
			}
		}
		
		log.registrarComSaida("Removendo local por id: " + id);
		
		boolean removido = gerenciador.removerLocal(id);
		if (removido) {
			log.registrarComSaida("Local " + id + " removido com sucesso!");
		} else {
			log.registrarComSaida("Local " + id + " nao foi removido");
		}
	}
}

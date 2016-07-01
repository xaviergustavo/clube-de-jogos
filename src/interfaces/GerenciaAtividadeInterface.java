package interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import atividade.Atividade;
import clube.Clube;
import gerenciadores.GerenciadorAtividade;
import logger.ClubeLogger;

public class GerenciaAtividadeInterface {

	private GerenciadorAtividade<Atividade> gerenciador;
	
	private ClubeLogger log;
	
	private Clube clube;
	
	public GerenciaAtividadeInterface() {
		this.gerenciador = new GerenciadorAtividade<>();
		this.log = ClubeLogger.getInstance();
		this.clube = Clube.getInstance();
	}
	
	public void cadastrarAtividade() {
		log.registrar("Cadastrando nova atividade...");
		boolean cadastrou = gerenciador.cadastrarAtividade();
		if (cadastrou) {
			log.registrarComSaida("Atividade cadastrada com sucesso");
		} else {
			log.registrarComSaida("Nao foi possivel cadastrar a atividade!");
		}
	}
	
	private void menuCadastrarListaAtividade() {
		System.out.println("Deseja adicionar uma atividade na lista:\n");
		System.out.println("1 - Sim");
		System.out.println("2 - Nao");
	}
	
	public void cadastrarListaAtividades() {
		log.registrar("Cadastrando lista de atividades...");
		
		List<Atividade> atividades = new ArrayList<>();
		int opcao = 2;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Menu de cadastrar Lista de Atividades:\n");
		
		menuCadastrarListaAtividade();
		
		boolean finalizou = false;
		
		while(scanner.hasNext()) {
			
			try {
				opcao = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				e.printStackTrace();
				opcao = 2;
			}
			switch (opcao) {
			case 1:
				System.out.println("Digite a atividade:");
				String nome = scanner.nextLine();
				
				System.out.println("Digite a modalidade da atividade:");
				System.out.println("1 - Digital");
				System.out.println("2 - Analogico");
				System.out.println("3 - Fisico");
				int id = Integer.parseInt(scanner.nextLine());
				
				Atividade novo = new Atividade(nome, clube.getModalidade(id));
				atividades.add(novo);
				menuCadastrarListaAtividade();
				break;
			case 2:
				finalizou = true;
				break;
			}
			if (finalizou) {
				break;
			}
		}
		if (atividades.size() > 0) {
			boolean sucesso = gerenciador.cadastrarNovasAtividade(atividades);
			if (sucesso) {
				log.registrarComSaida("Lista de atividades cadastrada com sucesso!");
			} else {
				log.registrarComSaida("Nao foi possivel cadastrar lista de atividades");
			}
		}
	}
	
	private void menuVisualizarAtividade() {
		System.out.println("Menu de Visualizacao de Atividade\n");
		
		System.out.println("1 - Visualizar atividade por nome");
		System.out.println("2 - Visualizar atividade por id");
		System.out.println("0 - Voltar para o Menu de Gerenciador de Atividade");
		
		System.out.println("Selecione uma opcao:");
	}
	
	public void visualizarAtividade() {
		
		menuVisualizarAtividade();
		
		Scanner scanner = new Scanner(System.in);
		
		boolean sair = false;
		
		while(scanner.hasNext()) {
			int opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				visualizarAtividadeNome();
				break;
			case 2:
				visualizarAtividadeId();
				break;
			case 0:
				sair = true;
				break;
			}
			if (sair) {
				break;
			}
			menuVisualizarAtividade();
		}
		
	}
	
	private void visualizarAtividadeNome() {
		Scanner scanner = new Scanner(System.in);
		String nomeAtividade = "";
		while (nomeAtividade.equals("")) {
			System.out.println("Digite o nome da atividade:");
			nomeAtividade = scanner.nextLine();
			if (nomeAtividade.equals("")) {
				System.out.println("Nome de atividade invalido\n");
			}
		}
		log.registrar("Visualizando atividade por nome: " + nomeAtividade);
		gerenciador.visualizarAtividade(nomeAtividade);
	}
	
	private void visualizarAtividadeId() {
		Scanner scanner = new Scanner(System.in);
		int id = 0;
		while (id == 0) {
			System.out.println("Digite o id da atividade:");
			try {
				id = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Id de atividade invalido\n");
				id = 0;
			}
		}
		log.registrar("Visualizando atividade por id: " + id);
		gerenciador.visualizarAtividade(id);
	}
	
	public void visualizarTodasAtividades() {
		log.registrar("Visualizando todas as atividades...");
		System.out.format("Quantidade de atividades: %s%n%n", gerenciador.visualizarTodasAtividades());
	}
	
	private void menuEditarAtividade() {
		System.out.println("Menu de Edicao de Atividade\n");
		
		System.out.println("1 - Selecionar atividade por nome");
		System.out.println("2 - Selecionar atividade por id");
		System.out.println("0 - Voltar para o Menu de Gerenciador de Atividade");
		
		System.out.println("Selecione uma opcao:");
	}
	
	public void editarAtividade() {
		menuEditarAtividade();
		
		Scanner scanner = new Scanner(System.in);
		
		boolean sair = false;
		
		while(scanner.hasNext()) {
			int opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				editarAtividadeNome();
				break;
			case 2:
				editarAtividadeId();
				break;
			case 0:
				sair = true;
				break;
			}
			if (sair) {
				break;
			}
			menuEditarAtividade();
		}
		
	}
	
	public void editarAtividadeNome() {
		Scanner scanner = new Scanner(System.in);
		String nomeAtividade = "";
		while (nomeAtividade.equals("")) {
			System.out.println("Digite o nome da atividade:");
			nomeAtividade = scanner.nextLine();
			if (nomeAtividade.equals("")) {
				System.out.println("Nome de atividade invalido\n");
			}
		}
		log.registrar("Editando atividade por nome: " + nomeAtividade);
		gerenciador.editarAtividade(nomeAtividade);
	}
	
	public void editarAtividadeId() {
		Scanner scanner = new Scanner(System.in);
		int id = 0;
		while (id == 0) {
			System.out.println("Digite o id da atividade:");
			try {
				id = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Id de atividade invalido\n");
				id = 0;
			}
		}
		log.registrar("Editando atividade por id: " + id);
		gerenciador.editarAtividade(id);
	}
	
	private void menuRemoverAtividade() {
		System.out.println("Menu de Remocao de Atividade\n");
		
		System.out.println("1 - Selecionar atividade por nome");
		System.out.println("2 - Selecionar atividade por id");
		System.out.println("0 - Voltar para o Menu de Gerenciador de Atividade");
		
		System.out.println("Selecione uma opcao:");
	}
	
	public void removerAtividade() {
		menuRemoverAtividade();
		
		boolean sair = false;
		
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			int opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				removerAtividadeNome();
				break;
			case 2:
				removerAtividadeId();
				break;
			case 0:
				sair = true;
				break;
			}
			if (sair) {
				break;
			}
			menuVisualizarAtividade();
		}
		
	}
	
	public void removerAtividadeNome() {
		
		Scanner scanner = new Scanner(System.in);
		String nomeAtividade = "";
		
		while (nomeAtividade.equals("")) {
			System.out.println("Digite o nome da atividade:");
			nomeAtividade = scanner.nextLine();
			if (nomeAtividade.equals("")) {
				System.out.println("Nome de atividade invalido\n");
			}
		}
		
		log.registrarComSaida("Removendo atividade por nome: " + nomeAtividade);
		
		boolean removido = gerenciador.removerAtividade(nomeAtividade);
		if (removido) {
			log.registrarComSaida("Atividade " + nomeAtividade + " removida com sucesso!");
		} else {
			log.registrarComSaida("Atividade " + nomeAtividade + " nao foi removida");
		}
		
	}
	
	public void removerAtividadeId() {
		Scanner scanner = new Scanner(System.in);
		int id = 0;
		
		while (id == 0) {
			System.out.println("Digite o id da atividade:");
			try {
				id = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Id de atividade invalido\n");
				id = 0;
			}
		}
		
		log.registrarComSaida("Removendo atividade por id: " + id);
		
		boolean removido = gerenciador.removerAtividade(id);
		if (removido) {
			log.registrarComSaida("Atividade " + id + " removida com sucesso!");
		} else {
			log.registrarComSaida("Atividade " + id + " nao foi removida");
		}
	}
	
}

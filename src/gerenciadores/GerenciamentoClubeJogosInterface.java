package gerenciadores;

import java.util.Scanner;

import interfaces.GerenciaUsuarioInterface;
import interfaces.GerenciaAtividadeInterface;
import interfaces.GerenciaCalendarioInterface;
import interfaces.GerenciaEstadoSistemaInterface;
import logger.ClubeLogger;

public class GerenciamentoClubeJogosInterface {
	
	/**
	 * Cria uma instância do tipo GerenciaUsuarioInterface e exibe as opçőes de
	 * gerenciamento.
	 * As opçőes săo escolhidas por meio de entrada do usuário.	 * 
	 */
	public void gerenciaUsuario() {
		GerenciaUsuarioInterface gerenciador = new GerenciaUsuarioInterface();
		
		Scanner scanner = new Scanner(System.in);
		
		exibeMenuGerenciarUsuario();
		
		boolean sair = false;
		
		while(scanner.hasNext()) {
			int opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				gerenciador.cadastrarUsuariosAntigos();
				break;
			case 2:
				gerenciador.exportarUsuariosSistemaAntigo();
				break;
			case 3:
				gerenciador.cadastrarNovoUsuario();
				break;
			case 4:
				gerenciador.cadastrarListaUsuarios();
				break;
			case 5:
				gerenciador.visualizarUsuario();
				break;
			case 6:
				gerenciador.visualizarTodosUsuarios();
				break;
			case 7:
				gerenciador.editarUsuario();
				break;
			case 8:
				gerenciador.removerUsuario();
				break;
			case 0:
				sair = true;
				break;
			default:
				System.out.println("Opcao invalida\n");
				break;
			}
			if (sair) {
				break;
			}
			exibeMenuGerenciarUsuario();
		}
	}
	
	public void exibeMenuGerenciarUsuario() {
		System.out.println("Menu de Gerenciador de Usuario\n");
		
		System.out.println("1 - Cadastrar usuarios do sistema antigo");
		System.out.println("2 - Exportar usuarios do sistema antigo");
		System.out.println("3 - Cadastrar novo usuario");
		System.out.println("4 - Cadastrar lista de usuarios");
		System.out.println("5 - Visualizar informacoes de um usuario");
		System.out.println("6 - Visualizar todos os usuarios");
		System.out.println("7 - Editar informacoes de um usuario");
		System.out.println("8 - Remover um usuario do sistema");
		System.out.println("0 - Voltar ao menu principal\n");
		
		System.out.println("Selecione uma opcao:");
	}
	
	/**
	 * Cria uma instância do tipo GerenciaAtividadeInterface e exibe as opçőes de
	 * gerenciamento.
	 * As opçőes săo escolhidas por meio de entrada do usuário.	 * 
	 */
	public void gerenciaAtividade() {
		GerenciaAtividadeInterface gerenciador = new GerenciaAtividadeInterface();
		
		Scanner scanner = new Scanner(System.in);
		
		exibeMenuGerenciarAtividade();
		
		boolean sair = false;
		
		while(scanner.hasNext()) {
			int opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				gerenciador.cadastrarAtividade();
				break;
			case 2:
				gerenciador.cadastrarListaAtividades();
				break;
			case 3:
				gerenciador.visualizarAtividade();
				break;
			case 4:
				gerenciador.visualizarTodasAtividades();
				break;
			case 5:
				gerenciador.editarAtividade();
				break;
			case 6:
				gerenciador.removerAtividade();
				break;
			case 0:
				sair = true;
				break;
			default:
				System.out.println("Opcao invalida\n");
				break;
			}
			if (sair) {
				break;
			}
			exibeMenuGerenciarAtividade();
		}
	}
	
	public void exibeMenuGerenciarAtividade() {
		System.out.println("Menu de Gerenciador de Atividade\n");
		
		System.out.println("1 - Cadastrar atividade");
		System.out.println("2 - Cadastrar lista de atividades");
		System.out.println("3 - Visualizar informacoes de uma atividade");
		System.out.println("4 - Visualizar todas as atividades");
		System.out.println("5 - Editar informacoes de uma atividade");
		System.out.println("6 - Remover uma atividade do sistema");
		System.out.println("0 - Voltar ao menu principal\n");
		
		System.out.println("Selecione uma opcao:");
	}
	
	/**
	 * Cria uma instância do tipo GerenciaLocalInterface e exibe as opçőes de
	 * gerenciamento.
	 * As opçőes săo escolhidas por meio de entrada do usuário.	 * 
	 */
	public void gerenciaLocal() {
		System.out.println("Em Construcao!");
	}
	
	/**
	 * Cria uma instância do tipo GerenciaCalendarioInterface e exibe as opçőes de
	 * gerenciamento.
	 * As opçőes săo escolhidas por meio de entrada do usuário.	 * 
	 */
	public void gerenciaCalendario() {
		GerenciaCalendarioInterface gerenciador = new GerenciaCalendarioInterface();
		
		Scanner scanner = new Scanner(System.in);
		
		exibeMenuGerenciarCalendario();
		
		boolean sair = false;
		
		while(scanner.hasNext()) {
			int opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				gerenciador.adicionaTurma();
				break;
			case 2:
				gerenciador.adicionaTurmasLocal();
				break;
			case 3:
				gerenciador.exibeTurmasLocal();
				break;
			case 4:
				gerenciador.exibeTurmasAtividade();
				break;
			case 5:
				gerenciador.exibeTodasTurmas();
				break;
			case 6:
				gerenciador.reagendaTurma();
				break;
			case 7:
				gerenciador.removeTurma();
				break;
			case 8:
				gerenciador.alteraLocal();
				break;
			case 9:
				gerenciador.adicionaUsuarioTurma();
				break;
			case 10:
				gerenciador.removeUsuarioAtividade();
				break;
			case 0:
				sair = true;
				break;
			default:
				System.out.println("Opcao invalida\n");
				break;
			}
			if (sair) {
				break;
			}
			exibeMenuGerenciarCalendario();
		}
	}
	
	public void exibeMenuGerenciarCalendario() {
		System.out.println("Menu de Gerenciador de Calendario\n");
		
		System.out.println("1 - Adicionar uma turma");
		System.out.println("2 - Adicionar uma lista de turmas em um local");
		System.out.println("3 - Exibir as turmas de um local");
		System.out.println("4 - Exibir as turmas de uma atividade");
		System.out.println("5 - Exibir todas as turmas do clube");
		System.out.println("6 - Reagendar uma turma");
		System.out.println("7 - Remover uma turma");
		System.out.println("8 - Alterar o local de uma turma");
		System.out.println("9 - Adicionar um usuario a uma turma");
		System.out.println("10 - Remover um usuario de uma turma");
		System.out.println("0 - Voltar ao menu principal\n");
		
		System.out.println("Selecione uma opcao:");
	}
	
	/**
	 * Exibe registros de interaçăo com o sistema. Cada registro e composto por data, horário
	 * e interaçăo. As interaçőes săo a descriçăo de alguma operacao do sistema (simbolizada por
	 *  cada método presente nas interfaces) e o resultado daquela operaçăo. As mensagens podem ser 
	 *  configuradas mas devem conter a data e horário, descriçăo e resultado da operaçăo. Deve garantir que 
	 *  exista uma instância única de log no sistema.
	 */
	public void exibeLog() {
		ClubeLogger log = ClubeLogger.getInstance();
		log.registrar("Arquivo de log exibido");
		System.out.println(log.conteudo());
	}
	
	
	/**
	 * Armazena todas as alteraçőes de turmas, calendário, atividades, locais, usuários, etc; assim como o log e 
	 * qualquer interaçăo realizada, para que o sistema possa ser continuado a cada execuçăo. Garante o armazenamento dos 
	 * dados apenas se esta opçăo for escolhida. Esta operaçăo também deve ser registrada no log. 
	 */
	public void salvarEstadoSistema() {
		GerenciaEstadoSistemaInterface gerenciador = new GerenciaEstadoSistemaInterface();
		gerenciador.salvarEstadoSistema();
	}
	
	public void exibeMenuPrincipal() {
		System.out.println("Menu:");
		
		System.out.println("1 - Gerenciar Usuarios");
		System.out.println("2 - Gerenciar Atividades");
		System.out.println("3 - Gerenciar Locais");
		System.out.println("4 - Gerenciar Calendario");
		System.out.println("5 - Exibir Log");
		System.out.println("6 - Salvar Estado do Sistema");
		System.out.println("0 - Sair do sistema\n");
		System.out.println("Selecione uma opcao:");
	}

	
	public static void main(String[] args) {
		
		GerenciamentoClubeJogosInterface clubeInterface = new GerenciamentoClubeJogosInterface();
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Bem vindo ao Clube de Jogos!\n");
		
		clubeInterface.exibeMenuPrincipal();
		
		while(scanner.hasNext()) {
			int opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				clubeInterface.gerenciaUsuario();
				break;
			case 2:
				clubeInterface.gerenciaAtividade();
				break;
			case 3:
				clubeInterface.gerenciaLocal();
				break;
			case 4:
				clubeInterface.gerenciaCalendario();
				break;
			case 5:
				clubeInterface.exibeLog();
				break;
			case 6:
				clubeInterface.salvarEstadoSistema();
				break;
			case 0:
				System.out.println("Ate logo!");
				System.exit(0);
				break;
			}
			clubeInterface.exibeMenuPrincipal();
		}
		
	}
		
		
}

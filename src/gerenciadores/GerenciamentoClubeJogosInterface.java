package gerenciadores;

import java.util.Scanner;

import interfaces.GerenciaUsuarioInterface;

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
			case 6:
				gerenciador.visualizarTodosUsuarios();
				break;
			case 0:
				sair = true;
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
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
	
	/**
	 * Cria uma instância do tipo GerenciaLocalInterface e exibe as opçőes de
	 * gerenciamento.
	 * As opçőes săo escolhidas por meio de entrada do usuário.	 * 
	 */
	public void gerenciaLocal() {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
	
	/**
	 * Cria uma instância do tipo GerenciaCalendarioInterface e exibe as opçőes de
	 * gerenciamento.
	 * As opçőes săo escolhidas por meio de entrada do usuário.	 * 
	 */
	public void gerenciaCalendario() {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
	
	/**
	 * Exibe registros de interaçăo com o sistema. Cada registro e composto por data, horário
	 * e interaçăo. As interaçőes săo a descriçăo de alguma operacao do sistema (simbolizada por
	 *  cada método presente nas interfaces) e o resultado daquela operaçăo. As mensagens podem ser 
	 *  configuradas mas devem conter a data e horário, descriçăo e resultado da operaçăo. Deve garantir que 
	 *  exista uma instância única de log no sistema.
	 */
	public void exibeLog() {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
	
	
	/**
	 * Armazena todas as alteraçőes de turmas, calendário, atividades, locais, usuários, etc; assim como o log e 
	 * qualquer interaçăo realizada, para que o sistema possa ser continuado a cada execuçăo. Garante o armazenamento dos 
	 * dados apenas se esta opçăo for escolhida. Esta operaçăo também deve ser registrada no log. 
	 */
	public void salvarEstadoSistema() {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
	
	public void exibeMenuPrincipal() {
		System.out.println("Menu:");
		
		System.out.println("1 - Gerenciar Usuarios");
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
				System.out.println();
				clubeInterface.gerenciaUsuario();
				break;
			case 0:
				System.out.println("FLW!");
				System.exit(0);
				break;
			}
			clubeInterface.exibeMenuPrincipal();
		}
		
	}
		
		
}

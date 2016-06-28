package interfaces;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import gerenciadores.GerenciadorUsuario;
import usuario.Usuario;

public class GerenciaUsuarioInterface {
	
	private GerenciadorUsuario<Usuario> gerenciador;
	
	public GerenciaUsuarioInterface() {
		this.gerenciador = new GerenciadorUsuario<>();
	}
	
	public void cadastrarUsuariosAntigos() {
		Scanner scanner = new Scanner(System.in);
		String caminho = "";
		while (caminho.equals("")) {
			System.out.println("Digite o caminho do arquivo que contem os usuarios do sistema antigo");
			caminho = scanner.nextLine();
		}
		Path arquivo = Paths.get(caminho);
		boolean importou = gerenciador.cadastraUsuariosAntigos(arquivo);
		if (importou) {
			gerenciador.visualizarTodosUsuarios();
			System.out.println("Usuarios cadastrados com sucesso!\n");
		} else {
			System.out.println("Houve algum erro na importacao\n");
		}
	}
	
	public void exportarUsuariosSistemaAntigo() {
		gerenciador.exportaUsuariosSistemaAntigo();
		System.out.println("Usuarios exportados com sucesso!\n");
	}
	
	public void cadastrarNovoUsuario() {
		boolean cadastrou = gerenciador.cadastrarNovoUsuario();
		if (cadastrou) {
			System.out.println("Usuario cadastrado com sucesso!\n");
		} else {
			System.out.println("Nao foi possivel cadastrar o usuario!\n");
		}
	}
	
	private void menuCadastrarListaUsuario() {
		System.out.println("Deseja adicionar um usuario na lista:\n");
		System.out.println("1 - Sim");
		System.out.println("2 - Nao");
	}
	
	public void cadastrarListaUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		int opcao = 2;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Menu de cadastrar Lista de Usuarios:\n");
		
		menuCadastrarListaUsuario();
		
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
				System.out.println("Digite o nome:");
				String nome = scanner.nextLine();
				
				System.out.println("Digite a idade:");
				int idade = Integer.parseInt(scanner.nextLine());
				
				System.out.println("Digite o endereco:");
				String endereco = scanner.nextLine();
				
				System.out.println("Digite o telefone:");
				long telefone = Long.parseLong(scanner.nextLine());
				
				Usuario novo = new Usuario(nome, idade, endereco, telefone);
				usuarios.add(novo);
				menuCadastrarListaUsuario();
				break;
			case 2:
				finalizou = true;
				break;
			}
			if (finalizou) {
				break;
			}
		}
		if (usuarios.size() > 0) {
			boolean sucesso = gerenciador.cadastrarNovosUsuarios(usuarios);
			if (sucesso) {
				System.out.println("Lista de usuarios cadastrada com sucesso!\n");
			}
		}
	}
	
	private void menuVisualizarUsuario() {
		System.out.println("Menu de Visualizacao de Usuario\n");
		
		System.out.println("1 - Visualizar usuario por nome");
		System.out.println("2 - Visualizar usuario por matricula");
		System.out.println("0 - Voltar para o Menu de Gerenciador de Usuario");
		
		System.out.println("Selecione uma opcao:");
	}
	
	public void visualizarUsuario() {
		menuVisualizarUsuario();
		
		Scanner scanner = new Scanner(System.in);
		
		boolean sair = false;
		
		while(scanner.hasNext()) {
			int opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				visualizarUsuarioNome();
				break;
			case 2:
				visualizarUsuarioMatricula();
				break;
			case 0:
				sair = true;
				break;
			}
			if (sair) {
				break;
			}
			menuVisualizarUsuario();
		}
		
	}
	
	public void visualizarUsuarioNome() {
		Scanner scanner = new Scanner(System.in);
		String nomeUsuario = "";
		while (nomeUsuario.equals("")) {
			System.out.println("Digite o nome do usuario:");
			nomeUsuario = scanner.nextLine();
			if (nomeUsuario.equals("")) {
				System.out.println("Nome de usuario invalido\n");
			}
		}
		gerenciador.visualizarUsuario(nomeUsuario);
	}
	
	public void visualizarUsuarioMatricula() {
		Scanner scanner = new Scanner(System.in);
		int matricula = 0;
		while (matricula == 0) {
			System.out.println("Digite a matricula do usuario:");
			try {
				matricula = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Matricula de usuario invalida\n");
				matricula = 0;
			}
		}
		gerenciador.visualizarUsuario(matricula);
	}
	
	public void visualizarTodosUsuarios() {
		System.out.format("Quantidade de usuarios: %s%n%n", gerenciador.visualizarTodosUsuarios());
	}
	
	private void menuEditarUsuario() {
		System.out.println("Menu de Edicao de Usuario\n");
		
		System.out.println("1 - Selecionar usuario por nome");
		System.out.println("2 - Selecionar usuario por matricula");
		System.out.println("0 - Voltar para o Menu de Gerenciador de Usuario");
		
		System.out.println("Selecione uma opcao:");
	}
	
	public void editarUsuario() {
		menuEditarUsuario();
		
		Scanner scanner = new Scanner(System.in);
		
		boolean sair = false;
		
		while(scanner.hasNext()) {
			int opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				editarUsuarioNome();
				break;
			case 2:
				editarUsuarioMatricula();
				break;
			case 0:
				sair = true;
				break;
			}
			if (sair) {
				break;
			}
			menuVisualizarUsuario();
		}
		
	}
	
	public void editarUsuarioNome() {
		Scanner scanner = new Scanner(System.in);
		String nomeUsuario = "";
		while (nomeUsuario.equals("")) {
			System.out.println("Digite o nome do usuario:");
			nomeUsuario = scanner.nextLine();
			if (nomeUsuario.equals("")) {
				System.out.println("Nome de usuario invalido\n");
			}
		}
		gerenciador.editarUsuario(nomeUsuario);
	}
	
	public void editarUsuarioMatricula() {
		Scanner scanner = new Scanner(System.in);
		int matricula = 0;
		while (matricula == 0) {
			System.out.println("Digite a matricula do usuario:");
			try {
				matricula = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Matricula de usuario invalida\n");
				matricula = 0;
			}
		}
		gerenciador.editarUsuario(matricula);
	}
	
	private void menuRemoverUsuario() {
		System.out.println("Menu de Remocao de Usuario\n");
		
		System.out.println("1 - Selecionar usuario por nome");
		System.out.println("2 - Selecionar usuario por matricula");
		System.out.println("0 - Voltar para o Menu de Gerenciador de Usuario");
		
		System.out.println("Selecione uma opcao:");
	}
	
	public void removerUsuario() {
		menuRemoverUsuario();
		
		boolean sair = false;
		
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			int opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				removerUsuarioNome();
				break;
			case 2:
				removerUsuarioMatricula();
				break;
			case 0:
				sair = true;
				break;
			}
			if (sair) {
				break;
			}
			menuVisualizarUsuario();
		}
		
	}
	
	public void removerUsuarioNome() {
		Scanner scanner = new Scanner(System.in);
		String nomeUsuario = "";
		while (nomeUsuario.equals("")) {
			System.out.println("Digite o nome do usuario:");
			nomeUsuario = scanner.nextLine();
			if (nomeUsuario.equals("")) {
				System.out.println("Nome de usuario invalido\n");
			}
		}
		boolean removido = gerenciador.removerUsuario(nomeUsuario);
		if (removido) {
			System.out.format("Usuario %s removido com sucesso!%n%n", nomeUsuario);
		} else {
			System.out.format("Usuario %s nao foi removido%n%n", nomeUsuario);
		}
	}
	
	public void removerUsuarioMatricula() {
		Scanner scanner = new Scanner(System.in);
		int matricula = 0;
		while (matricula == 0) {
			System.out.println("Digite a matricula do usuario:");
			try {
				matricula = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Matricula de usuario invalida\n");
				matricula = 0;
			}
		}
		boolean removido = gerenciador.removerUsuario(matricula);
		if (removido) {
			System.out.format("Usuario %s removido com sucesso!%n%n", matricula);
		} else {
			System.out.format("Usuario %s nao foi removido%n%n", matricula);
		}
	}
}

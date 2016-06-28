package gerenciadores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import clube.Clube;
import usuario.Usuario;

public class GerenciadorUsuario <U>{

	private Clube clube;
	
	public GerenciadorUsuario() {
		this.clube = Clube.getInstance();
	}
	
	/**
	 * Cadastra usuários baseado no formato do sistema antigo do clube de jogos.
	 * Devem ser incorporados e adaptados os campos de acordo com sua implementaçăo 
	 * de usuário. Se os usuários já estiverem no sistema năo devem ser cadastrados
	 * novamente e uma mensagem deve ser emitida. 
	 * @param arquivo - caminho para o aquivo contendo os registros antigos
	 * @return boolean - true se todos os usuários forem cadastrados ; false - caso
	 * um ou mais usuários năo tenham sido cadastrados
	 */
	public boolean cadastraUsuariosAntigos(Path arquivo) {
		String conteudoArquivo;
		boolean ok = true;
		try {
			conteudoArquivo = new String(Files.readAllBytes(arquivo));
			String[] usuariosArquivo = conteudoArquivo.split("\\|");
			for (String usuarioArquivo : usuariosArquivo) {
				String[] atributos = usuarioArquivo.split(",");
				String nome = atributos[0];
				int idade = Integer.parseInt(atributos[1]);
				String endereco = atributos[2];
				long telefone = Long.parseLong(atributos[3]);
				boolean cadastrou = clube.adicionarUsuario(nome, idade, endereco, telefone);
				if (!cadastrou) {
					System.out.format("O usuario %s ja esta cadastrado no sistema\n", nome);
					ok = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ok;
	}
	
	
	/**
	 * Cria um arquivo contendo todos os usuários cadastrados no sistema seguindo
	 * a modelagem e os campos descritos para o arquivo do sistema antigo. Utiliza a mesma formataçăo
	 * do arquivo do sistema antigo para escrever o novo arquivo com os dados exportados.
	 * Vocę deve incrementar o arquivo a cada vez que este método for executado.
	 */
	public void exportaUsuariosSistemaAntigo() {
		for (Usuario usuario : clube.getUsuarios()) {
			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("usuarios.dat", true)))) {
				out.format("%s|", usuario.formatoSistemaAntigo());
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Cadastra novo usuário, informando primeiro os campos que devem ser preenchidos.
	 * Os campos săo preenchidos por meio de entrada do usuário. Se já houver usuário com 
	 * o mesmo nome năo realiza o cadastro e uma mensagem deve ser exibida.
	 * @return boolean - true se o usuário foi cadastrado ; false - caso contrário
	 */
	public boolean cadastrarNovoUsuario() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o nome:");
		String nome = scanner.nextLine();
		
		System.out.println("Digite a idade:");
		int idade = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Digite o endereco:");
		String endereco = scanner.nextLine();
		
		System.out.println("Digite o telefone:");
		long telefone = Long.parseLong(scanner.nextLine());

		boolean cadastrou = clube.adicionarUsuario(nome, idade, endereco, telefone);
		if (cadastrou) {
			visualizarUsuario(nome);
		} else {
			System.out.format("O usuario %s ja esta cadastrado no sistema\n", nome);
		}
		return cadastrou;
	}
	
	/**
	 * Cadastra uma lista de usuários.
	 * @param usuarios - lista de usuários a serem cadastrados segundo sua implementaçăo.
	 * Se já houver um usuário com o mesmo nome năo realiza o cadastro daquele, os outros săo
	 * cadastrados se năo houver conflito.
	 * @return boolean - true se todos os usuários forem cadastrados ; false - caso
	 * um ou mais usuários năo tiverem sido cadastrados
	 */
	public boolean cadastrarNovosUsuarios(List<U> usuarios) {
		boolean ok = true;
		for (U u : usuarios) {
			Usuario usuario = (Usuario)u;
			boolean cadastrou = clube.adicionarUsuario(usuario.getNome(), usuario.getIdade(), usuario.getEndereco(), usuario.getTelefone());
			if (cadastrou) {
				visualizarUsuario(usuario.getNome());
			} else {
				System.out.format("O usuario %s ja esta cadastrado no sistema\n", usuario.getNome());
				ok = false;
			}
		}
		return ok;
	}
	
	/***
	 * Exibe todos os campos de usuário e as turmas que participa. Também exibe a atividade
	 * da turma, local que ocorre e horário
	 * @param nomeUsuario - nome do usuário a ser visualizado
	 * 
	 */
	public void visualizarUsuario(String nomeUsuario) {
		Usuario u = clube.getUsuario(nomeUsuario);
		if (u == null) {
			System.out.format("O usuario %s nao foi encontrado%n%n", nomeUsuario);
			return;
		}
		
		System.out.format("Matricula: %d%n", u.getId());
		System.out.format("Nome: %s\n", u.getNome());
		System.out.format("Idade: %d\n", u.getIdade());
		System.out.format("Endereco: %s\n", u.getEndereco());
		System.out.format("Telefone: %d\n", u.getTelefone());
		
		u.imprimirTurmas();
		System.out.println();
	}
	
	/**
	 * Exibe todos os campos de usuários e as turmas que participa. Também exibe a atividade
	 * da turma, local que ocorre e horário
	 * @param nMatricula - número de matrícula do usuário a ser visualizado
	 */
	public void visualizarUsuario(int nMatricula) {
		Usuario u = clube.getUsuario(nMatricula);
		if (u == null) {
			System.out.format("O usuario com a matricula %d nao foi encontrado", nMatricula);
			return;
		}
		
		visualizarUsuario(u.getNome());
	}
	
	/**
	 * Exibe o número total de usuários cadastrados e, para cada usuário, o nome, as turmas que participa,
	 * os locais que ocorrem as turmas.
	 * @return int - contendo o número de usuários cadastrados
	 */
	public int visualizarTodosUsuarios() {
		List<Usuario> usuarios = clube.getUsuarios();
		for (Usuario u : usuarios) {
			visualizarUsuario(u.getNome());
		}
		return clube.quantidadeUsuarios();
	}
	
	/**
	 * Altera um ou mais campos do usuário passado como parâmetro.
	 * @param nomeUsuario - nome do usuário a ser editado
	 *  
	 */
	public void editarUsuario(String nomeUsuario) {
		Usuario usuario = clube.getUsuario(nomeUsuario);
		if (usuario == null) {
			System.out.format("Usuario %s nao encontrado%n%n", nomeUsuario);
			return;
		}
		visualizarUsuario(nomeUsuario);
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o nome:");
		String novoNome = scanner.nextLine();
		
		System.out.println("Digite a idade:");
		int novaIdade = Integer.parseInt(scanner.nextLine());

		System.out.println("Digite o endereco:");
		String novoEndereco = scanner.nextLine();
		
		System.out.println("Digite o telefone:");
		long novoTelefone = Long.parseLong(scanner.nextLine());
		
		clube.editarUsuario(nomeUsuario, novoNome, novaIdade, novoEndereco, novoTelefone);
		visualizarUsuario(novoNome);
		System.out.println("Usuario editado com sucesso!\n");
	}
	
	/**
	 * Exibe todos os campos do usuário passado como parâmetro e altera um ou mais campos. 
	 * Os campos săo preenchidos por meio de entrada do usuário. 
	 * @param nMatricula - número de matricula a ser editado
	 * 
	 */
	public void editarUsuario(int nMatricula) {
		Usuario usuario = clube.getUsuario(nMatricula);
		if (usuario == null) {
			System.out.format("Usuario %s nao encontrado%n%n", nMatricula);
			return;
		}
		
		visualizarUsuario(nMatricula);
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o nome:");
		String novoNome = scanner.nextLine();
		
		System.out.println("Digite a idade:");
		int novaIdade = Integer.parseInt(scanner.nextLine());

		System.out.println("Digite o endereco:");
		String novoEndereco = scanner.nextLine();
		
		System.out.println("Digite o telefone:");
		long novoTelefone = Long.parseLong(scanner.nextLine());
		
		clube.editarUsuario(nMatricula, novoNome, novaIdade, novoEndereco, novoTelefone);
	
		visualizarUsuario(nMatricula);
		System.out.println("Usuario editado com sucesso!\n");
	}
	
	/**
	 * Exibe todos os campos do usuário e exige confirmaçăo para realizar a remoçăo.
	 * confirmaçăo é realizada por entrada do usuário.
	 * @param nomeUsuario - nome usuário a ser removido
	 * @return true - se confirma remoçăo; false - se cancela remoçăo
	 */
	public boolean removerUsuario(String nomeUsuario) {
		Usuario usuario = clube.getUsuario(nomeUsuario);
		if (usuario == null) {
			System.out.format("Usuario %s nao encontrado%n%n", nomeUsuario);
			return false;
		}
		
		visualizarUsuario(nomeUsuario);
		
		System.out.println("Deseja remover o usuario:\n");
		System.out.println("1 - Sim");
		System.out.println("2 - Nao");
		
		Scanner scanner = new Scanner(System.in);
		int opcao = 2;
		try {
			opcao = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			opcao = 2;
		}
		if (opcao == 1) {
			return clube.removerUsuario(nomeUsuario);
		}
		return false;
	}
	
	/**
	 * Exibe todos os campos do usuário e exige confirmaçăo para realizar a remoçăo.
	 * confirmaçăo é realizada por entrada do usuário.
	 * @param nMatricula - número de matrícula do usuário a ser removido
	 * @return true - se confirma remoçăo; false - se cancela remoçăo
	 */
	public boolean removerUsuario(int nMatricula) {
		Usuario usuario = clube.getUsuario(nMatricula);
		if (usuario == null) {
			System.out.format("Usuario %s nao encontrado%n%n", nMatricula);
			return false;
		}
		visualizarUsuario(nMatricula);
		
		System.out.println("Deseja remover o usuario:\n");
		System.out.println("1 - Sim");
		System.out.println("2 - Nao");
		
		Scanner scanner = new Scanner(System.in);
		int opcao = 2;
		try {
			opcao = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			opcao = 2;
		}
		if (opcao == 1) {
			return clube.removerUsuario(nMatricula);
		}
		return false;
	}
	
}

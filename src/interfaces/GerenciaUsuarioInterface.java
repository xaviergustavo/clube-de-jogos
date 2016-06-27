package interfaces;

import java.nio.file.Path;
import java.nio.file.Paths;
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
		while (caminho == "") {
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
	
	public void visualizarTodosUsuarios() {
		System.out.format("Quantidade de usuarios: %s%n%n", gerenciador.visualizarTodosUsuarios());
	}
	
}

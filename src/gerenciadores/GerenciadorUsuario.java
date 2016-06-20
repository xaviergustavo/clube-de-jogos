package gerenciadores;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import clube.ClubeSingleton;
import usuario.Usuario;

public class GerenciadorUsuario <U>{

	
	// metodos de usuários
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
					
					ClubeSingleton clube = ClubeSingleton.getInstance();
					
					Usuario novo = new Usuario(nome, idade, endereco, telefone);
					if (clube.usuarioExiste(novo)) {
						System.out.format("O usuario %s ja esta cadastrado no sistema\n", novo.getNome());
						ok = false;
					} else {
						clube.adicionarUsuario(novo);
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
			ClubeSingleton clube = ClubeSingleton.getInstance();	
			
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
			ClubeSingleton clube = ClubeSingleton.getInstance();
			
			// Deve vir como entrada do usuario na interface
			String nome = "Gustavo";
			int idade = 66;
			String endereco = "Rua do Teste";
			long telefone = 912345678;
			
			Usuario usuario = new Usuario(nome, idade, endereco, telefone);
			
			boolean ok = true;
			
			if (clube.usuarioExiste(usuario)) {
				System.out.format("O usuario %s ja esta cadastrado no sistema\n", usuario.getNome());
				ok = false;
			} else {
				clube.adicionarUsuario(usuario);
			}
			
			return ok;
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
			ClubeSingleton clube = ClubeSingleton.getInstance();	
			
			boolean ok = true;
			
			for (U u : usuarios) {
				
				Usuario usuario = (Usuario)u;
				
				if (clube.usuarioExiste(usuario)) {
					System.out.format("O usuario %s ja esta cadastrado no sistema\n", usuario.getNome());
					ok = false;
				} else {
					clube.adicionarUsuario(usuario);
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
			ClubeSingleton clube = ClubeSingleton.getInstance();
			Usuario u = clube.getUsuario(nomeUsuario);
			
			if (u == null) {
				System.out.format("O usuario %s nao foi encontrado", nomeUsuario);
				return;
			}
			
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
			ClubeSingleton clube = ClubeSingleton.getInstance();
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
			ClubeSingleton clube = ClubeSingleton.getInstance();
			
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
			ClubeSingleton clube = ClubeSingleton.getInstance();
			
			// Deve vir como entrada do usuario na interface
			String novoNome = "Sadrac";
			int novaIdade = 66;
			String novoEndereco = "Rua das Orquideas";
			long novoTelefone = 912345678;
			
			clube.editarUsuario(nomeUsuario, novoNome, novaIdade, novoEndereco, novoTelefone);
		}
		
		/**
		 * Exibe todos os campos do usuário passado como parâmetro e altera um ou mais campos. 
		 * Os campos săo preenchidos por meio de entrada do usuário. 
		 * @param nMatricula - número de matricula a ser editado
		 * 
		 */
		public void editarUsuario(int nMatricula) {
			ClubeSingleton clube = ClubeSingleton.getInstance();
			
			// Deve vir como entrada do usuario na interface
			String novoNome = "Barak";
			int novaIdade = 54;
			String novoEndereco = "Rua das Tulipas";
			long novoTelefone = 912345678;
			
			clube.editarUsuario(nMatricula, novoNome, novaIdade, novoEndereco, novoTelefone);
		}
		
		/**
		 * Exibe todos os campos do usuário e exige confirmaçăo para realizar a remoçăo.
		 * confirmaçăo é realizada por entrada do usuário.
		 * @param nomeUsuario - nome usuário a ser removido
		 * @return true - se confirma remoçăo; false - se cancela remoçăo
		 */
		public boolean  removerUsuario(String nomeUsuario) {
			ClubeSingleton clube = ClubeSingleton.getInstance();
			return clube.removerUsuario(nomeUsuario);
		}
		
		/**
		 * Exibe todos os campos do usuário e exige confirmaçăo para realizar a remoçăo.
		 * confirmaçăo é realizada por entrada do usuário.
		 * @param nMatricula - número de matrícula do usuário a ser removido
		 * @return true - se confirma remoçăo; false - se cancela remoçăo
		 */
		public boolean removerUsuario(int nMatricula) {
			ClubeSingleton clube = ClubeSingleton.getInstance();
			return clube.removerUsuario(nMatricula);
		}
	
}

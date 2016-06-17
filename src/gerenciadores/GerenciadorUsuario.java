package gerenciadores;


import java.nio.file.Path;
import java.util.List;

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
			/**
			 * TODO implementar este método seguindo a descriçăo acima
			 */	
			return false;
	}
		
		
		/**
		 * Cria um arquivo contendo todos os usuários cadastrados no sistema seguindo
		 * a modelagem e os campos descritos para o arquivo do sistema antigo. Utiliza a mesma formataçăo
		 * do arquivo do sistema antigo para escrever o novo arquivo com os dados exportados.
		 * Vocę deve incrementar o arquivo a cada vez que este método for executado.
		 */
		public void exportaUsuariosSistemaAntigo() {
			/**
			 * TODO implementar este método seguindo a descriçăo acima
			 */	
		}
		
		/**
		 * Cadastra novo usuário, informando primeiro os campos que devem ser preenchidos.
		 * Os campos săo preenchidos por meio de entrada do usuário. Se já houver usuário com 
		 * o mesmo nome năo realiza o cadastro e uma mensagem deve ser exibida.
		 * @return boolean - true se o usuário foi cadastrado ; false - caso contrário
		 */
		public boolean cadastrarNovoUsuario() {
			/**
			 * TODO implementar este método seguindo a descriçăo acima
			 */	
			return false;
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
			/**
			 * TODO implementar este método seguindo a descriçăo acima
			 */	
			return false;
		}
		
		/***
		 * Exibe todos os campos de usuário e as turmas que participa. Também exibe a atividade
		 * da turma, local que ocorre e horário
		 * @param nomeUsuario - nome do usuário a ser visualizado
		 * 
		 */
		public void visualizarUsuario(String nomeUsuario) {
			/**
			 * TODO implementar este método seguindo a descriçăo acima
			 */	
		}
		
		/**
		 * Exibe todos os campos de usuários e as turmas que participa. Também exibe a atividade
		 * da turma, local que ocorre e horário
		 * @param nMatricula - número de matrícula do usuário a ser visualizado
		 */
		public void visualizarUsuario(int nMatricula) {
			/**
			 * TODO implementar este método seguindo a descriçăo acima
			 */	
		}
		
		/**
		 * Exibe o número total de usuários cadastrados e, para cada usuário, o nome, as turmas que participa,
		 * os locais que ocorrem as turmas.
		 * @return int - contendo o número de usuários cadastrados
		 */
		public int visualizarTodosUsuarios() {
			/**
			 * TODO implementar este método seguindo a descriçăo acima
			 */	
			return 0;
		}
		
		/**
		 * Altera um ou mais campos do usuário passado como parâmetro.
		 * @param nomeUsuario - nome do usuário a ser editado
		 *  
		 */
		public void editarUsuario(String nomeUsuario) {
			/**
			 * TODO implementar este método seguindo a descriçăo acima
			 */	
		}
		
		/**
		 * Exibe todos os campos do usuário passado como parâmetro e altera um ou mais campos. 
		 * Os campos săo preenchidos por meio de entrada do usuário. 
		 * @param nMatricula - número de matricula a ser editado
		 * 
		 */
		public void editarUsuario(int nMatricula) {
			/**
			 * TODO implementar este método seguindo a descriçăo acima
			 */	
		}
		
		/**
		 * Exibe todos os campos do usuário e exige confirmaçăo para realizar a remoçăo.
		 * confirmaçăo é realizada por entrada do usuário.
		 * @param nomeUsuario - nome usuário a ser removido
		 * @return true - se confirma remoçăo; false - se cancela remoçăo
		 */
		public boolean  removerUsuario(String nomeUsuario) {
			/**
			 * TODO implementar este método seguindo a descriçăo acima
			 */	
			return false;
		}
		
		/**
		 * Exibe todos os campos do usuário e exige confirmaçăo para realizar a remoçăo.
		 * confirmaçăo é realizada por entrada do usuário.
		 * @param nMatricula - número de matrícula do usuário a ser removido
		 * @return true - se confirma remoçăo; false - se cancela remoçăo
		 */
		public boolean removerUsuario(int nMatricula) {
			/**
			 * TODO implementar este método seguindo a descriçăo acima
			 */	
			return false;
		}
	
}

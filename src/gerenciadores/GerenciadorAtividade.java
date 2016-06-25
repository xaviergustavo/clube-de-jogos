package gerenciadores;

import java.util.List;

import atividade.Atividade;
import clube.Clube;

public class GerenciadorAtividade <A>{
	
	private Clube clube;
	
	public GerenciadorAtividade() {
		this.clube = Clube.getInstance();
	}
	
	// metodos de Atividades
						
			
	/**
	 * Cadastra nova atividade, informando primeiro os campos que devem ser preenchidos.
	 * Deve especificar o tipo de local que ocorre.
	 * Os campos săo preenchidos por meio de entrada do usuário. Se já houver atividade com 
	 * o mesmo nome năo realiza o cadastro e uma mensagem deve ser exibida.
	 * @return boolean - true se a atividade foi cadastrada ; false - caso contrário
	 */
	public boolean cadastrarAtividade() {
		// Valores virao da interface do usuario
		String nome = "Skateboard";
		
		return clube.adicionarAtividade(nome, clube.getModalidade(1));
	}
	
	/**
	 * Cadastra uma lista de atividades. Se já houver uma atividade com o mesmo nome năo realiza o cadastro daquela, as outras săo
	 * cadastradas se năo houver conflito.
	 * @param atividades - lista de atividades a serem cadastradas segundo sua implementaçăo
	 * @return boolean - true se todas as atividades forem cadastradas ; false - caso
	 * uma ou mais atividades năo tiverem sido cadastradas
	 */
	public boolean cadastrarNovasAtividade(List<A> atividades) {
		boolean ok = true;
		for (A atividade : atividades) {
			Atividade nova = (Atividade)atividade;
			ok = clube.adicionarAtividade(nova.getNome(), nova.getModalidade());
		}
		return ok;
	}
			
	/***
	 * Exibe todos os campos da atividade.
	 * @param nomeAtividade - nome da atividade a ser visualizada
	 * 
	 */		
	public void visualizarAtividade(String nomeAtividade) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
	
	
	/***
	 * Exibe todos os campos da atividade.
	 * @param id - identificador da atividade a ser visualizada
	 * 
	 */
	public void visualizarAtividade(int id) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
	
	/**
	 * Exibe o numero total de atividades cadastradas e todos os campos de cada uma.
	 * @return int - contendo o numero de atividades cadastradas 
	 */
	public int visualizarTodasAtividades() {
		return 0;
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
	
	
	/**
	 * Altera um ou mais campos da atividade passada como parâmetro.
	 * @param nomeAtividade - nome da atividade a ser editada
	 *  
	 */
	public void editarAtividade(String nomeAtividade) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
			
	/**
	 * Exibe todos os campos da atividade passado como parâmetro e altera um ou mais campos. 
	 * Os campos săo preenchidos por meio de entrada do usuário. 
	 * @param id - identificador da atividade a ser editada
	 * 
	 */		
	public void editarAtividade(int id) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
	
	/**
	 * Exibe todos os campos da atividade e exige confirmaçăo para realizar a remoçăo.
	 * confirmaçăo é realizada por entrada do usuário.
	 * @param nomeAtividade - nome atividade a ser removida
	 * @return true - se confirma remoçăo; false - se cancela remoçăo
	 */
	public void removerAtividade(String nomeAtividade) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
	
	/**
	 * Exibe todos os campos da atividade e exige confirmaçăo para realizar a remoçăo.
	 * confirmaçăo é realizada por entrada do usuário.
	 * @param id - identificador da atividade a ser removida
	 * @return true - se confirma remoçăo; false - se cancela remoçăo
	 */
	public void removerAtividade(int id) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}

}

package gerenciadores;

import java.util.List;

public class GerenciadorLocal<L> {
	
	//metodos de Locais
	
	/**
	 * Cadastra novo Local, informando primeiro os campos que devem ser preenchidos.
	 * Os campos săo preenchidos por meio de entrada do usuário. Se já houver Local com 
	 * o mesmo nome năo realiza o cadastro e uma mensagem deve ser exibida.
	 * @return boolean - true se o local foi cadastrado ; false - caso contrário
	 */
	public void cadastrarNovoLocal() {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
	
	
	/**
	 * Cadastra uma lista de locais.
	 * @param locais - lista de locais a serem cadastrados segundo sua implementaçăo.
	 * Se já houver um local com o mesmo nome năo realiza o cadastro daquele, os outros săo
	 * cadastrados se năo houver conflito.
	 * @return boolean - true se todos os locais forem cadastrados ; false - caso
	 * um ou mais locais năo tiverem sido cadastrados
	 */
	public void cadastrarNovosLocais(List<L> locais) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
	
	/***
	 * Exibe todos os campos do local.
	 * @param nomeLocal - nome do local a ser visualizado
	 * 
	 */
	public void visualizarLocal(String nomeLocal) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
	
	/***
	 * Exibe todos os campos do local.
	 * @param id - identificador do local a ser visualizado
	 * 
	 */
	public void visualizarLocal(int id) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
			
	/**
	 * Exibe o número total de locais cadastradas e todos os campos de cada um
	 * @return int - contendo o número de locais cadastrados. 
	 */
	public int visualizarTodosLocais() {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
		return 0;
	}
			
	/**
	 * Altera um ou mais campos do local passado como parâmetro.
	 * @param nomeLocal - nome do local a ser editado
	 *  
	 */		
	public void editarLocal(String nomeLocal) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
	
	/**
	 * Exibe todos os campos do local passado como parâmetro e altera um ou mais campos. 
	 * Os campos săo preenchidos por meio de entrada do usuário. 
	 * @param id - identificador do local a ser editado
	 * 
	 */
	public void editarLocal(int id) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
	}
			
	/**
	 * Exibe todos os campos do local e exige confirmaçăo para realizar a remoçăo.
	 * confirmaçăo é realizada por entrada do usuário.
	 * @param nomeLocal - nome local a ser removido
	 * @return true - se confirma remoçăo; false - se cancela remoçăo
	 */
	public boolean removerLocal(String nomeLocal) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
		return false;
	}
			
	/**
	 * Exibe todos os campos do local e exige confirmaçăo para realizar a remoçăo.
	 * confirmaçăo é realizada por entrada do usuário.
	 * @param id - identificador do local a ser removido
	 * @return true - se confirma remoçăo; false - se cancela remoçăo
	 */
	public boolean removerLocal(int id) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
		return false;
	}

}

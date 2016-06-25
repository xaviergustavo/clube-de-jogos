package gerenciadores;

import java.util.List;

import clube.ClubeSingleton;
import local.Local;

public class GerenciadorLocal<L> {
	
	private ClubeSingleton clube;
	
	public GerenciadorLocal() {
		clube = ClubeSingleton.getInstance();
	}
	
	//metodos de Locais
	
	/**
	 * Cadastra novo Local, informando primeiro os campos que devem ser preenchidos.
	 * Os campos săo preenchidos por meio de entrada do usuário. Se já houver Local com 
	 * o mesmo nome năo realiza o cadastro e uma mensagem deve ser exibida.
	 * @return boolean - true se o local foi cadastrado ; false - caso contrário
	 */
	public boolean cadastrarNovoLocal() {
		
		// Deve vir como entrada do usuario na interface
		Local local = new Local("Quadra 1", clube.getCategoria(1));
		
		return clube.adicionarLocal(local);
	}
	
	/**
	 * Cadastra uma lista de locais.
	 * @param locais - lista de locais a serem cadastrados segundo sua implementaçăo.
	 * Se já houver um local com o mesmo nome năo realiza o cadastro daquele, os outros săo
	 * cadastrados se năo houver conflito.
	 * @return boolean - true se todos os locais forem cadastrados ; false - caso
	 * um ou mais locais năo tiverem sido cadastrados
	 */
	public boolean cadastrarNovosLocais(List<L> locais) {
		boolean ok = true;
		for (L l : locais) {
			Local local = (Local)l;
			ok = clube.adicionarLocal(local);
		}
		return ok;
	}
	
	/***
	 * Exibe todos os campos do local.
	 * @param nomeLocal - nome do local a ser visualizado
	 * 
	 */
	public void visualizarLocal(String nomeLocal) {
		Local local = clube.getLocal(nomeLocal);
		if (local == null) {
			System.out.format("O local %s nao foi encontrado", nomeLocal);
		} else {
			local.exibir();
		}
	}
	
	/***
	 * Exibe todos os campos do local.
	 * @param id - identificador do local a ser visualizado
	 * 
	 */
	public void visualizarLocal(int id) {
		Local local = clube.getLocal(id);
		if (local == null) {
			System.out.format("O local %d nao foi encontrado", id);
		} else {
			local.exibir();
		}
	}
			
	/**
	 * Exibe o número total de locais cadastradas e todos os campos de cada um
	 * @return int - contendo o número de locais cadastrados. 
	 */
	public int visualizarTodosLocais() {
		List<Local> locais = clube.getLocais();
		
		System.out.format("Quantidade de locais: %d\n", locais.size());
		
		for (Local local : locais) {
			visualizarLocal(local.getId());
		}
		
		return locais.size();
	}
			
	/**
	 * Altera um ou mais campos do local passado como parâmetro.
	 * @param nomeLocal - nome do local a ser editado
	 *  
	 */		
	public void editarLocal(String nomeLocal) {
		// Deve vir como entrada do usuario na interface
		String nomeNovo = "Quadra 99";
		
		clube.editarLocal(nomeLocal, nomeNovo);
	}
	
	/**
	 * Exibe todos os campos do local passado como parâmetro e altera um ou mais campos. 
	 * Os campos săo preenchidos por meio de entrada do usuário. 
	 * @param id - identificador do local a ser editado
	 * 
	 */
	public void editarLocal(int id) {
		// Deve vir como entrada do usuario na interface
		String nomeNovo = "Quadra 99";
		
		clube.editarLocal(id, nomeNovo);
	}
			
	/**
	 * Exibe todos os campos do local e exige confirmaçăo para realizar a remoçăo.
	 * confirmaçăo é realizada por entrada do usuário.
	 * @param nomeLocal - nome local a ser removido
	 * @return true - se confirma remoçăo; false - se cancela remoçăo
	 */
	public boolean removerLocal(String nomeLocal) {
		return clube.removerLocal(nomeLocal);
	}
			
	/**
	 * Exibe todos os campos do local e exige confirmaçăo para realizar a remoçăo.
	 * confirmaçăo é realizada por entrada do usuário.
	 * @param id - identificador do local a ser removido
	 * @return true - se confirma remoçăo; false - se cancela remoçăo
	 */
	public boolean removerLocal(int id) {
		return clube.removerLocal(id);
	}

}

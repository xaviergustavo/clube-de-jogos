package gerenciadores;

import java.util.List;
import java.util.Scanner;

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
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite a atividade:");
		String nome = scanner.nextLine();
		
		System.out.println("Digite a modalidade da atividade:");
		System.out.println("1 - Digital");
		System.out.println("2 - Analogico");
		System.out.println("3 - Fisico");
		int id = Integer.parseInt(scanner.nextLine());
		
		return clube.adicionarAtividade(nome, clube.getModalidade(id));
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
		Atividade atividade = clube.getAtividade(nomeAtividade);
		if (atividade == null) {
			System.out.format("Atividade %s nao encontrada", nomeAtividade);
		} else {
			System.out.println(atividade.info());
		}
	}
	
	
	/***
	 * Exibe todos os campos da atividade.
	 * @param id - identificador da atividade a ser visualizada
	 * 
	 */
	public void visualizarAtividade(int id) {
		Atividade atividade = clube.getAtividade(id);
		if (atividade == null) {
			System.out.format("Atividade %s nao encontrada", id);
		} else {
			System.out.println(atividade.info());
		}
	}
	
	/**
	 * Exibe o numero total de atividades cadastradas e todos os campos de cada uma.
	 * @return int - contendo o numero de atividades cadastradas 
	 */
	public int visualizarTodasAtividades() {
		List<Atividade> atividades = clube.atividades();
		for (Atividade atividade : atividades) {
			System.out.println(atividade.info());
		}
		return atividades.size();
	}
	
	
	/**
	 * Altera um ou mais campos da atividade passada como parâmetro.
	 * @param nomeAtividade - nome da atividade a ser editada
	 *  
	 */
	public void editarAtividade(String nomeAtividade) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome da nova atividade");
		String novoNome = scanner.nextLine();
		
		clube.editarAtividade(nomeAtividade, novoNome);
	}
			
	/**
	 * Exibe todos os campos da atividade passado como parâmetro e altera um ou mais campos. 
	 * Os campos săo preenchidos por meio de entrada do usuário. 
	 * @param id - identificador da atividade a ser editada
	 * 
	 */		
	public void editarAtividade(int id) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome da nova atividade");
		String novoNome = scanner.nextLine();
		
		clube.editarAtividade(id, novoNome);
	}
	
	/**
	 * Exibe todos os campos da atividade e exige confirmaçăo para realizar a remoçăo.
	 * confirmaçăo é realizada por entrada do usuário.
	 * @param nomeAtividade - nome atividade a ser removida
	 * @return true - se confirma remoçăo; false - se cancela remoçăo
	 */
	public boolean removerAtividade(String nomeAtividade) {
		return clube.removerAtividade(nomeAtividade);
	}
	
	/**
	 * Exibe todos os campos da atividade e exige confirmaçăo para realizar a remoçăo.
	 * confirmaçăo é realizada por entrada do usuário.
	 * @param id - identificador da atividade a ser removida
	 * @return true - se confirma remoçăo; false - se cancela remoçăo
	 */
	public boolean removerAtividade(int id) {
		return clube.removerAtividade(id);
	}

}

package gerenciadores;

import java.util.List;
import java.util.Scanner;

import clube.Clube;
import local.Local;
import logger.ClubeLogger;

public class GerenciadorLocal<L> {
	
	private ClubeLogger log;
	private Clube clube;
	
	public GerenciadorLocal() {
		clube = Clube.getInstance();
		log = ClubeLogger.getInstance();
	}
	
	//metodos de Locais
	
	/**
	 * Cadastra novo Local, informando primeiro os campos que devem ser preenchidos.
	 * Os campos săo preenchidos por meio de entrada do usuário. Se já houver Local com 
	 * o mesmo nome năo realiza o cadastro e uma mensagem deve ser exibida.
	 * @return boolean - true se o local foi cadastrado ; false - caso contrário
	 */
	public boolean cadastrarNovoLocal() {
		Scanner scanner = new Scanner (System.in);
		
		System.out.println ("Cadastro de novo local.");
		System.out.println ();
		System.out.format ("Os campos que devem preenchidos sao:\n- Nome do Local\n- Categoria\n");
		
		System.out.println ("Digite o nome do local:");
		String nomeLocal = scanner.nextLine();
		
		System.out.println ("Digite a categoria do local:");
		System.out.println ("1 - Quadras");
		System.out.println ("2 - Salas de atividades");
		int id = Integer.parseInt(scanner.nextLine()); 
		
		Local local = new Local(nomeLocal, clube.getCategoria(id));
		
		if (clube.adicionarLocal(local) == false){
			System.out.println("Ja existe um local com este nome! Cadastro nao realizado.");
			return false;
		}
		else
			return true;
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
		Local local = clube.getLocal(nomeLocal);
		if (local == null) {
			log.registrarComSaida("Local " + nomeLocal + " nao encontrado");
			return;
		}
		visualizarLocal(nomeLocal);
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o novo nome do local:");
		String novoNomeLocal = scanner.nextLine();
		
		clube.editarLocal(nomeLocal, novoNomeLocal);
		visualizarLocal(novoNomeLocal);
		log.registrarComSaida("Usuario editado com sucesso!");		
	}
	
	/**
	 * Exibe todos os campos do local passado como parâmetro e altera um ou mais campos. 
	 * Os campos săo preenchidos por meio de entrada do usuário. 
	 * @param id - identificador do local a ser editado
	 * 
	 */
	public void editarLocal(int id) {
		
		Local local = clube.getLocal(id);
		if (local == null) {
			log.registrarComSaida("Local " + id + " nao encontrado");
			return;
		}
		visualizarLocal(id);
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o novo nome do local:");
		String novoNomeLocal = scanner.nextLine();
		
		
		clube.editarLocal(id, novoNomeLocal);
		visualizarLocal(id);
		log.registrarComSaida("Usuario editado com sucesso!");
	}
			
	/**
	 * Exibe todos os campos do local e exige confirmaçăo para realizar a remoçăo.
	 * confirmaçăo é realizada por entrada do usuário.
	 * @param nomeLocal - nome local a ser removido
	 * @return true - se confirma remoçăo; false - se cancela remoçăo
	 */
	public boolean removerLocal(String nomeLocal) {
		Local local = clube.getLocal(nomeLocal);
		if (local == null) {
			log.registrarComSaida("Local " + nomeLocal + " nao encontrado");
			return false;
		}
		
		visualizarLocal(nomeLocal);
		
		System.out.println("Deseja remover o local:\n");
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
			return clube.removerLocal(nomeLocal);
		}
		return false;
		
	}
			
	/**
	 * Exibe todos os campos do local e exige confirmaçăo para realizar a remoçăo.
	 * confirmaçăo é realizada por entrada do usuário.
	 * @param id - identificador do local a ser removido
	 * @return true - se confirma remoçăo; false - se cancela remoçăo
	 */
	public boolean removerLocal(int id) {
		Local local = clube.getLocal(id);
		if (local == null) {
			log.registrarComSaida("Local " + id + " nao encontrado");
			return false;
		}
		
		visualizarLocal(id);
		
		System.out.println("Deseja remover o local:\n");
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
			return clube.removerLocal(id);
		}
		return false;
		
	}

}

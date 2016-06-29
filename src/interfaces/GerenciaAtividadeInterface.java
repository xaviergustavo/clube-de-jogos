package interfaces;

import atividade.Atividade;
import gerenciadores.GerenciadorAtividade;
import logger.ClubeLogger;

public class GerenciaAtividadeInterface {

	private GerenciadorAtividade<Atividade> gerenciador;
	
	private ClubeLogger log;
	
	public GerenciaAtividadeInterface() {
		this.gerenciador = new GerenciadorAtividade<>();
		this.log = ClubeLogger.getInstance();
	}
	
}

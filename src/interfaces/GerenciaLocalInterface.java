package interfaces;

import gerenciadores.GerenciadorLocal;
import local.Local;
import logger.ClubeLogger;

public class GerenciaLocalInterface {

	private GerenciadorLocal<Local> gerenciador;
	
	private ClubeLogger log;
	
	public GerenciaLocalInterface() {
		this.gerenciador = new GerenciadorLocal<>();
		this.log = ClubeLogger.getInstance();
	}
}

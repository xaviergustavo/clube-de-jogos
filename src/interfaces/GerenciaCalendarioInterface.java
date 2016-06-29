package interfaces;

import atividade.Atividade;
import gerenciadores.GerenciadorCalendario;
import local.Local;
import logger.ClubeLogger;
import turma.Turma;
import usuario.Usuario;

public class GerenciaCalendarioInterface {

private GerenciadorCalendario<Atividade, Turma, Local, Usuario> gerenciador;
	
	private ClubeLogger log;
	
	public GerenciaCalendarioInterface() {
		this.gerenciador = new GerenciadorCalendario<>();
		this.log = ClubeLogger.getInstance();
	}
	
}

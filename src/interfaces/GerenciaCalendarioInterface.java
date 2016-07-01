package interfaces;

import java.util.Scanner;

import atividade.Atividade;
import clube.Clube;
import gerenciadores.GerenciadorCalendario;
import local.Local;
import logger.ClubeLogger;
import turma.Turma;
import usuario.Usuario;

public class GerenciaCalendarioInterface {

private GerenciadorCalendario<Atividade, Turma, Local, Usuario> gerenciador;
	
	private ClubeLogger log;
	
	private Clube clube;
	
	public GerenciaCalendarioInterface() {
		this.gerenciador = new GerenciadorCalendario<>();
		this.log = ClubeLogger.getInstance();
		this.clube = Clube.getInstance();
	}
	
	public void adicionaTurma() {
		System.out.println("Em construcao");
	}
	
	public void adicionaTurmasLocal() {
		System.out.println("Em construcao");
	}
	
	public void exibeTurmasLocal() {
		log.registrar("Exibindo as turmas de um local...");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o id do local:");
		System.out.println("1 - Quadra 1");
		System.out.println("2 - Quadra 2");
		System.out.println("3 - Sala 1");
		System.out.println("4 - Sala 2");
		int id = Integer.parseInt(scanner.nextLine());
		Local l = clube.getLocal(id);
		gerenciador.exibeTurmasLocal(l);
	}
	
	public void exibeTurmasAtividade() {
		log.registrar("Exibindo as turmas de uma atividade...");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome da atividade:");
		String nome = scanner.nextLine();
		Atividade atividade = clube.getAtividade(nome);
		gerenciador.exibeTurmasAtividade(atividade);
	}
	
	public void exibeTodasTurmas() {
		log.registrar("Exibindo todas as turmas do clube...");
		gerenciador.exibeTodasTurmas();
	}
	
	public void reagendaTurma() {
		System.out.println("Em construcao");
	}
	
	public void removeTurma() {
		log.registrar("Removendo uma turma");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o id da turma:");
		int id = Integer.parseInt(scanner.nextLine());
		Turma turma = clube.getTurma(id);
		if(turma == null) {
			System.out.println("A turma nao existe");
		} else {
			boolean ok = gerenciador.removeTurma(turma);
			if(ok) log.registrarComSaida("Turma removida com sucesso!");
			else log.registrarComSaida("Nao foi possivel remover a turma");	
		}
	}
	
	public void alteraLocal() {
		System.out.println("Em construcao");
	}
	
	public void adicionaUsuarioTurma() {
		System.out.println("Em construcao");
	}
	
	public void removeUsuarioAtividade() {
		System.out.println("Em construcao");
	}
	
}

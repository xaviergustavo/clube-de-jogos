package calendario;
import java.util.ArrayList;
import java.util.List;

import turma.Turma;
import usuario.Usuario;

public class Cronograma {
	
	// Horario inicial do cronograma (inclusivo)
	private final int horarioInicial = 8;
	
	// Horario final do cronograma (exclusivo)
	private final int horarioFinal = 22;

	// Representa todos os horarios possiveis dentro
	// de um cronograma, baseado em seu horario inicial e final
	public Turma[] agendamentos;

	// Construtor que obriga a implementacao de um array de agendamentos
	// de tamanho fixo (horarioFinal - horarioInicial).
	// Se um cronograma comeca as 8h e vai ate as 22h, logo teremos
	// 22 - 8 = 14 posicoes (horarios) no array
	public Cronograma() {
		this.agendamentos = new Turma[horarioFinal - horarioInicial];
	}
	
	// Dado um horario, retorna a posicao correspondente no array de agendamentos
	private int posicaoPorHorario(int horario) {
		return horario - horarioInicial;
	}
	
	// Dado uma posicao do array, retorna o horario correspondente
	private int horarioPorPosicao(int posicao) {
		return posicao + horarioInicial;
	}
	
	public boolean turmaExiste(Turma turma) {
		for (Turma t : agendamentos) {
			if (t != null && t.equals(turma)) {
				return true;
			}
		}
		return false;
	}
	
	public int agendamentosNoDia(Usuario usuario) {
		int contador = 0;
		Turma atual = null;
		for (Turma t : agendamentos) {
			if (t != null && !t.equals(atual)) {
				atual = t;
				if (t.usuarioExiste(usuario)) contador++;
			}
		}
		return contador;
	}
	
	public boolean usuarioAgendado(Usuario usuario, int horario) {
		Turma turma = getTurma(horario);
		if (turma == null) return false;
		return turma.usuarioExiste(usuario);
	}
	
	// Verifica se todas os horarios desejados estao vagos. Se houver pelo
	// menos um horario ocupado, nao eh possivel agendar no periodo determinado
	// (horario + duracao)
	public boolean podeAgendarNoPeriodo(int inicio, int duracao) {
		for (int horario = inicio; horario <= inicio + duracao; horario++) {
			if (getTurma(horario) != null) {
				return false;
			}
		}
		return true;
	}
	
	public boolean agendar(Turma turma, int inicio, int duracao) {
		if (inicio < horarioInicial || inicio >= horarioFinal) {
			return false;
		}
		if (!podeAgendarNoPeriodo(inicio, duracao) || turmaExiste(turma)) {
			return false;
		}
		for (int horario = inicio; horario < inicio + duracao; horario++) {
			setTurma(turma, horario);
		}
		return true;
	}
	
	public void imprimir() {
		System.out.print("[ ");
		for(int i = 0; i < agendamentos.length; i++) {
			if(agendamentos[i] != null) {
				System.out.printf(" %dh:[Turma %d] ", horarioPorPosicao(i), agendamentos[i].getId());
			} else {
				System.out.printf(" %dh:[] ", horarioPorPosicao(i));
			}
		}
		System.out.println(" ]");
	}
	
	public List<Integer> horariosTurma(Turma t) {
		List<Integer> horarios = new ArrayList<>();
		for (int i = 0; i < agendamentos.length; i++) {
			try {
				if (agendamentos[i].equals(t)) {
					horarios.add(horarioPorPosicao(i));
				}
			} catch (NullPointerException e) {
				
			}	
		}
		return horarios;
	}
	
	// Retorna a turma (ou nulo, caso o horario esteja vago) agendada no horario informado
	public Turma getTurma(int horario) {
		Turma turma = null;
		try{
			turma = agendamentos[posicaoPorHorario(horario)];
		} catch (ArrayIndexOutOfBoundsException e) {
			
		}
		return turma;
	}
	
	public void removerTurma(Turma turma) {
		for (int i = 0; i < agendamentos.length; i++) {
			if (agendamentos[i] != null && agendamentos[i].equals(turma)) {
				agendamentos[i] = null;
			}
		}
	}
	
	private void setTurma(Turma turma, int horario) {
		agendamentos[posicaoPorHorario(horario)] = turma;
	}
	
	public int getHorarioInicial() {
		return horarioInicial;
	}

	public int getHorarioFinal() {
		return horarioFinal;
	}

	public Turma[] getAgendamentos() {
		return agendamentos;
	}
	
}

package calendario;
import java.util.*;

import turma.Turma;
import usuario.Usuario;

import java.io.Serializable;
import java.time.DayOfWeek;

public class Calendario implements Serializable {
	
	private Map<DayOfWeek, Cronograma> calendario;

	public Calendario() {
		this.calendario = new TreeMap<>();
		for (DayOfWeek dia : DayOfWeek.values()) {
			calendario.put(dia, new Cronograma());
		}
	}

	public Map<DayOfWeek, Cronograma> getCalendario() {
		return calendario;
	}
	
	public int agendamentosNoDia(Usuario usuario, DayOfWeek dia) {
		if (calendario.get(dia) == null) return 0;
		return calendario.get(dia).agendamentosNoDia(usuario);
	}
	
	public boolean usuarioAgendado(Usuario usuario, DayOfWeek dia, int horario) {
		if (calendario.get(dia) == null) return false;
		return calendario.get(dia).usuarioAgendado(usuario, horario);
	}
	
	public boolean agendarTurma(Turma turma, DayOfWeek dia, int inicio, int duracao) {
		return calendario.get(dia).agendar(turma, inicio, duracao);
	}
	
	public void imprimeCalendario(DayOfWeek dia) {
		Cronograma cronograma = calendario.get(dia);
		System.out.print(dia + " -> ");
		if (cronograma == null) {
			System.out.println("[ ]");
		} else {
			cronograma.imprimir();
		}
	}
	
	public void horariosTurmaNadia(DayOfWeek dia, Turma t) {
		calendario.get(dia).horariosTurma(t);
	}
	
	public void removerTurma(Turma t) {
		for (Cronograma cronograma : calendario.values()) {
			cronograma.removerTurma(t);
		}
	}
	
}

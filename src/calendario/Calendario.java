package calendario;
import java.util.*;

import turma.Turma;
import usuario.Usuario;

import java.time.LocalDate;

public class Calendario {
	
	private Map<LocalDate, Cronograma> calendario;

	public Calendario() {
		this.calendario = new TreeMap<>();
	}

	public Map<LocalDate, Cronograma> getCalendario() {
		return calendario;
	}
	
	public int agendamentosNoDia(Usuario usuario, LocalDate data) {
		if (calendario.get(data) == null) return 0;
		return calendario.get(data).agendamentosNoDia(usuario);
	}
	
	public boolean usuarioAgendado(Usuario usuario, LocalDate data, int horario) {
		if (calendario.get(data) == null) return false;
		return calendario.get(data).usuarioAgendado(usuario, horario);
	}
	
	public boolean agendarTurma(Turma turma, LocalDate data, int inicio, int duracao) {
		if (!calendario.containsKey(data)) {
			Cronograma cronograma = new Cronograma(8, 22);
			calendario.put(data, cronograma);
		}
		return calendario.get(data).agendar(turma, inicio, duracao);
	}
	
	public void imprimeCalendario(LocalDate data) {
		Cronograma cronograma = calendario.get(data);
		System.out.print(data + " -> ");
		if (cronograma == null) {
			System.out.println("[ ]");
		} else {
			cronograma.imprimir();
		}
	}
	
}

package local;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import calendario.Calendario;
import calendario.Cronograma;
import turma.Turma;
import usuario.Usuario;

public class Local {
	
	private int id;
	
	private String nome;
	
	// Categoria do local
	private CategoriaLocal categoria;
	
	// Calendario especifico de um local, contendo
	// os agendamentos das turmas.
	public Calendario calendario;
	
	public Local(String nome, CategoriaLocal categoria) {
		this.nome = nome;
		this.categoria = categoria;
		this.calendario = new Calendario();
	}
	
	public int agendamentosNoDia(Usuario usuario, DayOfWeek dia) {
		return calendario.agendamentosNoDia(usuario, dia);
	}
	
	public boolean usuarioAgendado(Usuario usuario, DayOfWeek dia, int horario) {
		return calendario.usuarioAgendado(usuario, dia, horario);
	}
	
	public boolean agendarTurma(Turma turma, DayOfWeek dia, int horarioInicio, int duracao) {
		if (!turma.getLocal().equals(this)) {
			return false;
		}
		if (!turma.getAtividade().getModalidade().getCategoria().equals(this.categoria)) {
			return false;
		}
		return calendario.agendarTurma(turma, dia, horarioInicio, duracao);
	}
	
	public void imprimeCalendario(DayOfWeek dia) {
		calendario.imprimeCalendario(dia);
	}
	
	public Map<DayOfWeek, List<Integer>> horariosTurma(Turma t) {
		Map<DayOfWeek, List<Integer>> horariosTurma = new HashMap<>();
		for (Entry<DayOfWeek, Cronograma> entry : calendario.getCalendario().entrySet()) {
			List<Integer> horariosData = entry.getValue().horariosTurma(t);
			if (horariosData.size() > 0) {
				horariosTurma.put(entry.getKey(), horariosData);
			}
		}
		return horariosTurma;
	}
	
	public void imprimirHorariosTurma(Turma t) {
		System.out.format("%s%nHorarios:%n", t);
		
		Map<DayOfWeek, List<Integer>> horarios = horariosTurma(t);
		for (Entry<DayOfWeek, List<Integer>> entry : horarios.entrySet()) {
			System.out.format("%s -> %s%n", entry.getKey(), entry.getValue());
		}
		System.out.println();
	}
	
	public void removerTurma(Turma t) {
		calendario.removerTurma(t);
	}
	
	public String toString() { 
		return this.nome;
	}
	
	public void exibir() {
		System.out.format("ID: %d - Nome: %s - Categoria: %s\n", id, nome, categoria.getNome());
	}

	
	public void editar(String nome) {
		this.nome = nome;
	}
	
	// Getters
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public CategoriaLocal getCategoria() {
		return categoria;
	}

	public Calendario getCalendario() {
		return calendario;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Local other = (Local) obj;
		return this.id == other.id;
	}
	
	
}

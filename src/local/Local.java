package local;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	
	public int agendamentosNoDia(Usuario usuario, LocalDate data) {
		return calendario.agendamentosNoDia(usuario, data);
	}
	
	public boolean usuarioAgendado(Usuario usuario, LocalDate data, int horario) {
		return calendario.usuarioAgendado(usuario, data, horario);
	}
	
	public boolean agendarTurma(Turma turma, LocalDate data, int horarioInicio, int duracao) {
		if (!turma.getLocal().equals(this)) {
			return false;
		}
		if (!turma.getAtividade().getModalidade().getCategoria().equals(this.categoria)) {
			return false;
		}
		return calendario.agendarTurma(turma, data, horarioInicio, duracao);
	}
	
	public void imprimeCalendario(LocalDate data) {
		calendario.imprimeCalendario(data);
	}
	
	public Map<LocalDate, List<Integer>> horariosTurma(Turma t) {
		Map<LocalDate, List<Integer>> horariosTurma = new HashMap<>();
		for (Entry<LocalDate, Cronograma> entry : calendario.getCalendario().entrySet()) {
			List<Integer> horariosData = entry.getValue().horariosTurma(t);
			if (horariosData.size() > 0) {
				horariosTurma.put(entry.getKey(), horariosData);
			}
		}
		return horariosTurma;
	}
	
	public void imprimirHorariosTurma(Turma t) {
		System.out.println("Horarios:");
		
		Map<LocalDate, List<Integer>> horarios = horariosTurma(t);
		for (Entry<LocalDate, List<Integer>> entry : horarios.entrySet()) {
			System.out.format("%s -> %s\n", entry.getKey(), entry.getValue());
		}
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

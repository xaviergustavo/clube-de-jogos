package local;
import java.time.LocalDate;

import calendario.Calendario;
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
	
	public Local(int id, String nome, CategoriaLocal categoria) {
		this.id = id;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Local other = (Local) obj;
		return this.id == other.id;
	}
	
	
}

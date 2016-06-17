package turma;
import java.time.LocalDate;
import java.util.Map;

import atividade.Atividade;
import local.Local;
import usuario.Usuario;

public class Turma {
	
	// Codigo identificador da turma
	private int id;
	
	// Duracao em semanas
	private int duracao;
	
	// Data de inicio da turma
	private LocalDate inicio;
	
	// Atividade pertencente a turma
	private Atividade atividade;
	
	// Local em que a turma costuma ocupar em suas atividades
	private Local local;
	
	// Colecao de usuarios de uma turma
	private Map<Integer, Usuario> usuarios;
	
	public Turma(int id, int duracao, LocalDate inicio, Atividade atividade,
			Local local, Map<Integer, Usuario> usuarios) {
		this.id = id;
		this.duracao = duracao;
		this.inicio = inicio;
		this.atividade = atividade;
		this.local = local;
		this.usuarios = usuarios;
	}
	
	public int quantidadeUsuarios() {
		return usuarios.size();
	}
	
	public LocalDate getFim() {
		return inicio.plusWeeks(duracao);
	}
	
	public boolean usuarioExiste(Usuario usuario) {
		return usuarios.containsValue(usuario);
	}
	
	// Getters

	public int getId() {
		return id;
	}

	public int getDuracao() {
		return duracao;
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public Atividade getAtividade() {
		return atividade;
	}
	
	public Local getLocal() {
		return local;
	}

	public Map<Integer, Usuario> getUsuarios() {
		return usuarios;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Turma other = (Turma) obj;
		return id == other.id;
	}
	
	
	
}

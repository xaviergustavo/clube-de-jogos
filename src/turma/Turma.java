package turma;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atividade.Atividade;
import local.Local;
import usuario.Usuario;

public class Turma implements Serializable {
	
	// Codigo identificador da turma
	private int id;
	
	// Atividade pertencente a turma
	private Atividade atividade;
	
	// Local em que a turma costuma ocupar em suas atividades
	private Local local;
	
	// Colecao de usuarios de uma turma
	private Map<Integer, Usuario> usuarios;
	
	public Turma(Atividade atividade, Local local) {
		this.atividade = atividade;
		this.local = local;
		this.usuarios = new HashMap<>();
	}
	
	public int quantidadeUsuarios() {
		return usuarios.size();
	}
	
	public boolean usuarioExiste(Usuario usuario) {
		return usuarios.containsValue(usuario);
	}
	
	public String toString() {
		return String.format("Turma %d - Atividade: %s | Local: %s", id, atividade, local);
	}
	
	// Getters

	public int getId() {
		return id;
	}
	
	public Atividade getAtividade() {
		return atividade;
	}
	
	public Local getLocal() {
		return local;
	}

	public List<Usuario> getUsuarios() {
		return new ArrayList<>(usuarios.values());
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean podeAlterarLocal(Local novo) {
		return !novo.getCategoria().equals(this.atividade.getModalidade().getCategoria());
	}
	
	public void setLocal(Local local) {
		if (podeAlterarLocal(local)) {
			this.local = local;
		}
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

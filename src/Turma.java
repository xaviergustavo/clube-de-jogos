import java.time.LocalDate;
import java.util.Map;

public class Turma {
	
	// Codigo identificador da turma
	private int id;
	
	// Duracao em semanas
	private int duracao;
	
	// Data de inicio da turma
	private LocalDate inicio;
	
	// Data de finalizacao da turma
	private LocalDate fim;
	
	// Atividade pertencente a turma
	private Atividade atividade;
	
	// Colecao de usuarios de uma turma
	private Map<Integer, Usuario> usuarios;
	
	public Turma(int id, int duracao, LocalDate inicio, LocalDate fim, Atividade atividade,
			Map<Integer, Usuario> usuarios) {
		this.id = id;
		this.duracao = duracao;
		this.inicio = inicio;
		this.fim = fim;
		this.atividade = atividade;
		this.usuarios = usuarios;
	}
	
	public int quantidadeUsuarios() {
		return usuarios.size();
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

	public LocalDate getFim() {
		return fim;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public Map<Integer, Usuario> getUsuarios() {
		return usuarios;
	}
	
}

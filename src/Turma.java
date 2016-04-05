import java.time.LocalDate;
import java.util.Set;

public class Turma {
	
	private int id;
	
	// Duracao em semanas
	private int duracao;
	
	private LocalDate inicio;
	
	private LocalDate fim;
	
	private Atividade atividade;
	
	private int quantidadeUsuarios;
	
	private Local local;
	
	// Collection que contem a lista de usuarios de uma turma.
	// Foi escolhido o HashSet para garantir que nao exista
	// usuarios duplicados
	private Set<Usuario> usuarios;
	
	public Turma(int id, int duracao, LocalDate inicio, LocalDate fim, Atividade atividade, int quantidadeUsuarios,
			Local local, Set<Usuario> usuarios) {
		this.id = id;
		this.duracao = duracao;
		this.inicio = inicio;
		this.fim = fim;
		this.atividade = atividade;
		this.quantidadeUsuarios = quantidadeUsuarios;
		this.local = local;
		this.usuarios = usuarios;
	}

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

	public int getQuantidadeUsuarios() {
		return quantidadeUsuarios;
	}

	public Local getLocal() {
		return local;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}
	
}

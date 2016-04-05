import java.util.Map;
import java.util.Set;

public class Clube {
	
	private final int inicioFuncionamento = 8;
	
	private final int fimFuncionamento = 22;
	
	// Inteiro entre 0 e 6 que representa o dia da semana
	// em que o clube nao abre
	private int fechado;
	
	private Set<Usuario> usuarios;
	
	private Calendario calendario;
	
	private Map<Integer, Local> locais;
	
	private Map<Integer, Atividade> atividades;

	public Clube(int fechado, Map<Integer, Local> locais, Map<Integer, Atividade> atividades) {
		this.fechado = fechado;
		this.locais = locais;
		this.atividades = atividades;
	}
	
	// Getters

	public int getInicioFuncionamento() {
		return inicioFuncionamento;
	}

	public int getFimFuncionamento() {
		return fimFuncionamento;
	}

	public int getFolga() {
		return folga;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public Calendario getCalendario() {
		return calendario;
	}

	public Map<Integer, Local> getLocais() {
		return locais;
	}

	public Map<Integer, Atividade> getAtividades() {
		return atividades;
	}
	
}

import java.util.Map;

public class Clube {
	
	// Horario de inicio do funcionamento
	private final int inicioFuncionamento = 8;
	
	// Horario de fim do funcionamento
	private final int fimFuncionamento = 22;
	
	// Inteiro entre 0 e 6 que representa o dia da semana
	// em que o clube nao abre
	private final int fechado;
	
	// Usuarios cadastrados no clube
	private Map<Integer, Usuario> usuarios;
	
	// Map de todos as atividades existentes no clube
	private Map<Integer, Atividade> atividades;
	
	// Locais disponiveis no clube
	private Map<Integer, Local> locais;

	public Clube(int fechado, Map<Integer, Atividade> atividades) {
		this.fechado = fechado;
		this.atividades = atividades;
	}
	
	// Getters

	public int getInicioFuncionamento() {
		return inicioFuncionamento;
	}

	public int getFimFuncionamento() {
		return fimFuncionamento;
	}

	public int getFechado() {
		return fechado;
	}

	public Map<Integer, Usuario> getUsuarios() {
		return usuarios;
	}

	public Map<Integer, Atividade> getAtividades() {
		return atividades;
	}

	public Map<Integer, Local> getLocais() {
		return locais;
	}
	
}

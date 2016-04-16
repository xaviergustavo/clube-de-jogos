import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Clube {
	
	// Horario de inicio do funcionamento
	private final int inicioFuncionamento = 8;
	
	// Horario de fim do funcionamento
	private final int fimFuncionamento = 22;
	
	// Duracao minima de uma atividade
	private final int duracaoMinima = 2;
	
	// Inteiro entre 0 e 6 que representa o dia da semana
	// em que o clube nao abre
	private final DayOfWeek fechado;
	
	// Usuarios cadastrados no clube
	private Map<Integer, Usuario> usuarios;
	
	// Map de todos as atividades existentes no clube
	private Map<Integer, Atividade> atividades;
	
	// Locais disponiveis no clube
	private Map<Integer, Local> locais;
	
	// Categoria de locais disponiveis no clube
	private Map<Integer, CategoriaLocal> categorias;
	
	// Modalidades disponiveis no clube
	private Map<Integer, Modalidade> modalidades;

	public Clube(DayOfWeek fechado) {
		this.fechado = fechado;
		this.usuarios = new HashMap<>();
		this.atividades = new HashMap<>();
		inicializarCategorias();
		inicializarLocais();
		inicializarModalidades();
	}
	
	private void inicializarCategorias() {
		categorias = new HashMap<>();
		categorias.put(1, new CategoriaLocal(1, "Quadra"));
		categorias.put(2, new CategoriaLocal(1, "Sala"));
	}
	
	private void inicializarLocais() {
		locais = new HashMap<>();
		locais.put(1, new Local(1, "Quadra 1", categorias.get(1)));
		locais.put(2, new Local(2, "Quadra 2", categorias.get(1)));
		locais.put(3, new Local(3, "Sala 1", categorias.get(2)));
		locais.put(4, new Local(4, "Sala 2", categorias.get(2)));
	}
	
	private void inicializarModalidades() {
		this.modalidades = new HashMap<>();
		modalidades.put(1, new Modalidade(1, "Digital", categorias.get(2)));
		modalidades.put(2, new Modalidade(2, "Analogico", categorias.get(2)));
		modalidades.put(3, new Modalidade(3, "Fisico", categorias.get(1)));
	}
	
	public boolean usuarioAgendado(Usuario usuario, LocalDate data, int horario) {
		for (Entry<Integer, Local> entry : locais.entrySet()) {
			Local local = entry.getValue();
			if (local.usuarioAgendado(usuario, data, horario)) {
				return true;
			}
		}
		return false;
	}
	
	public int agendamentosNoDia(Usuario usuario, LocalDate data) {
		int agendamentos = 0;
		for (Entry<Integer, Local> entry : locais.entrySet()) {
			Local local = entry.getValue();
			agendamentos += local.agendamentosNodDia(usuario, data);
		}
		return agendamentos;
	}
	
	public boolean agendarTurma(Turma turma, LocalDate data, int inicio, int duracao) {
		if (data.isBefore(turma.getInicio()) && data.isAfter(turma.getFim())) {
			return false;
		}
		for (Entry<Integer, Usuario> entry : turma.getUsuarios().entrySet()) {
			Usuario usuario = entry.getValue();
			int agendamentos = agendamentosNoDia(usuario, data);
			if (agendamentos >= 2) {
				return false;
			}
			for (int horario = inicio; horario < inicio + duracao; horario++) {
				if (usuarioAgendado(usuario, data, horario)) {
					return false;
				}
			}
		}
		if (data.getDayOfWeek().equals(fechado) || duracao < duracaoMinima) {
			return false;
		}
		return turma.getLocal().agendarTurma(turma, data, inicio, duracao);
	}
	
	public void imprimeCalendario(Local local, LocalDate data) {
		local.imprimeCalendario(data);
	}
	
	// Getters e Setters
	
	public Local getLocal(int id) {
		return locais.get(id);
	}
	
	public Modalidade getModalidade(int id) {
		return modalidades.get(id);
	}
	
	public Atividade getAtividade(int id) {
		return atividades.get(id);
	}
	
	public void adicionarAtividade(Atividade atividade) {
		atividades.put(atividade.getId(), atividade);
	}
	
	public Usuario getUsuario(int id) {
		return usuarios.get(id);
	}
	
	public void adicionarUsuario(Usuario usuario) {
		usuarios.put(usuario.getId(), usuario);
	}

	public int getInicioFuncionamento() {
		return inicioFuncionamento;
	}

	public int getFimFuncionamento() {
		return fimFuncionamento;
	}

	public DayOfWeek getFechado() {
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

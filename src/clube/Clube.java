package clube;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import atividade.Atividade;
import local.CategoriaLocal;
import local.Local;
import modalidade.Modalidade;
import turma.Turma;
import usuario.Usuario;

public class Clube {
	
	private static Clube instance;
	
	// Horario de inicio do funcionamento
	private final int inicioFuncionamento = 8;
	
	// Horario de fim do funcionamento
	private final int fimFuncionamento = 22;
	
	// Duracao minima de uma atividade
	private final int duracaoMinima = 2;
	
	// Inteiro entre 0 e 6 que representa o dia da semana
	// em que o clube nao abre
	private DayOfWeek fechado;
	
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
	
	// Turmas existentes no clube
	private Map<Integer, Turma> turmas;
	
	public static Clube getInstance() {
		if (instance == null) {
			synchronized (Clube.class) {
				if (instance == null) {
					instance = new Clube();
					instance.inicializarClube();
				}
			}
		}
		return instance;
	}
	
	private void inicializarClube() {
		this.fechado = DayOfWeek.SUNDAY;
		this.usuarios = new HashMap<>();
		this.atividades = new HashMap<>();
		this.turmas = new HashMap<>();
		this.inicializarCategorias();
		this.inicializarLocais();
		this.inicializarModalidades();
	}
	
	private void inicializarCategorias() {
		this.categorias = new HashMap<>();
		this.categorias.put(1, new CategoriaLocal(1, "Quadra"));
		this.categorias.put(2, new CategoriaLocal(2, "Sala"));
	}
	
	private void inicializarLocais() {
		this.locais = new HashMap<>();
		this.locais.put(1, new Local(1, "Quadra 1", this.categorias.get(1)));
		this.locais.put(2, new Local(2, "Quadra 2", this.categorias.get(1)));
		this.locais.put(3, new Local(3, "Sala 1", this.categorias.get(2)));
		this.locais.put(4, new Local(4, "Sala 2", this.categorias.get(2)));
	}
	
	private void inicializarModalidades() {
		this.modalidades = new HashMap<>();
		this.modalidades.put(1, new Modalidade(1, "Digital", this.categorias.get(2)));
		this.modalidades.put(2, new Modalidade(2, "Analogico", this.categorias.get(2)));
		this.modalidades.put(3, new Modalidade(3, "Fisico", this.categorias.get(1)));
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
			agendamentos += local.agendamentosNoDia(usuario, data);
		}
		return agendamentos;
	}
	
	public boolean agendarTurma(Turma turma, LocalDate data, int horarioInicial, int duracao) {
		if (data.isBefore(turma.getInicio()) || data.isAfter(turma.getFim())) {
			return false;
		}
		for (Entry<Integer, Usuario> entry : turma.getUsuarios().entrySet()) {
			Usuario usuario = entry.getValue();
			int agendamentos = agendamentosNoDia(usuario, data);
			if (agendamentos >= 2) {		
				return false;
			}
			for (int horario = horarioInicial; horario < horarioInicial + duracao; horario++) {
				if (usuarioAgendado(usuario, data, horario)) {
					return false;
				}
			}
		}
		if (data.getDayOfWeek().equals(fechado) || duracao < duracaoMinima) {
			return false;
		}
		return turma.getLocal().agendarTurma(turma, data, horarioInicial, duracao);
	}
	
	public void imprimeCalendario(Local local, LocalDate data) {
		local.imprimeCalendario(data);
	}
	
	public void imprimeUsuarios() {
		for (Entry<Integer, Usuario> entry : usuarios.entrySet()) {
			System.out.format("%d - %s\n", entry.getValue().getId(), entry.getValue().getNome());
		}
	}
	
	public boolean usuarioExiste(Usuario usuario) {
		for (Entry<Integer, Usuario> entry : usuarios.entrySet()) {
			if (entry.getValue().equals(usuario)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean removerUsuario(String nomeUsuario) {
		Usuario u = getUsuario(nomeUsuario);
		
		if (u == null) return false;
		
		Usuario removido = usuarios.remove(u.getId());
		return removido != null;
	}
	
	public boolean removerUsuario(int id) {
		Usuario removido = usuarios.remove(id);
		return removido != null;
	}
	
	public int quantidadeUsuarios() {
		return usuarios.size();
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
	
	public Usuario getUsuario(int id) {
		return usuarios.get(id);
	}
	
	public Usuario getUsuario(String nome) {
		List<Usuario> usuarios = getUsuarios();
		for (Usuario u : usuarios) {
			if (u.getNome().equals(nome)) {
				return u;
			}
		}
		return null;
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

	public List<Usuario> getUsuarios() {
		List<Usuario> lista = new ArrayList<>();
		for (Entry<Integer, Usuario> entry : usuarios.entrySet()) {
			lista.add(entry.getValue());
		}
		return lista;
	}

	public Map<Integer, Atividade> getAtividades() {
		return atividades;
	}

	public Map<Integer, Local> getLocais() {
		return locais;
	}
	
	public Map<Integer, Turma> getTurmas() {
		return turmas;
	}
	
	public Turma getTurma(int id) {
		return turmas.get(id);
	}
	
	public void adicionarAtividade(Atividade atividade) {
		atividades.put(atividade.getId(), atividade);
	}
	
	public void adicionarUsuario(Usuario usuario) {
		usuario.setId(usuarios.size() + 1);
		usuarios.put(usuario.getId(), usuario);
	}
	
	public void adicionarTurma(Turma turma) {
		turmas.put(turma.getId(), turma);
	}
}

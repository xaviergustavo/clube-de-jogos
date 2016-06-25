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
		locais = new HashMap<>();
		adicionarLocal(new Local("Quadra 1", this.categorias.get(1)));
		adicionarLocal(new Local("Quadra 2", this.categorias.get(1)));
		adicionarLocal(new Local("Sala 1", this.categorias.get(2)));
		adicionarLocal(new Local("Sala 2", this.categorias.get(2)));
	}
	
	private void inicializarModalidades() {
		this.modalidades = new HashMap<>();
		this.modalidades.put(1, new Modalidade(1, "Digital", this.categorias.get(2)));
		this.modalidades.put(2, new Modalidade(2, "Analogico", this.categorias.get(2)));
		this.modalidades.put(3, new Modalidade(3, "Fisico", this.categorias.get(1)));
	}
	
	// Agendamentos
	
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
	
	// Usuarios
	
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
	
	public boolean editarUsuario(String nomeUsuario, String novoNome, int novaIdade, String novoEndereco, long novoTelefone) {
		// Verificar se local com o novo nome ja existe
		Usuario novoUsuario = getUsuario(novoNome);
		if (novoUsuario != null) {
			return false;
		}
		
		Usuario usuario = getUsuario(nomeUsuario);
		if (usuario == null) {
			return false;
		}
		
		usuario.editar(novoNome, novaIdade, novoEndereco, novoTelefone);
		
		return true;
	}
	
	public boolean editarUsuario(int id, String novoNome, int novaIdade, String novoEndereco, long novoTelefone) {
		// Verificar se local com o novo nome ja existe
		Usuario novoUsuario = getUsuario(novoNome);
		if (novoUsuario != null) {
			return false;
		}
		
		Usuario usuario = getUsuario(id);
		if (usuario == null) {
			return false;
		}
		
		usuario.editar(novoNome, novaIdade, novoEndereco, novoTelefone);
		
		return true;
	}
	
	public int quantidadeUsuarios() {
		return usuarios.size();
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
	
	public List<Usuario> getUsuarios() {
		List<Usuario> lista = new ArrayList<>();
		for (Entry<Integer, Usuario> entry : usuarios.entrySet()) {
			lista.add(entry.getValue());
		}
		return lista;
	}
	
	public void adicionarUsuario(Usuario usuario) {
		usuario.setId(usuarios.size() + 1);
		usuarios.put(usuario.getId(), usuario);
	}
	
	// Local
	
	public boolean localExiste(String nomeLocal) {
		for (Entry<Integer, Local> entry : locais.entrySet()) {
			Local local = entry.getValue();
			if (local.getNome().equals(nomeLocal)) {
				return true;
			}
		}
		return false;
	}
	
	public Local getLocal(int id) {
		return locais.get(id);
	}
	
	public List<Local> getLocais() {
		List<Local> lista = new ArrayList<>();
		for (int id : locais.keySet()) {
			lista.add(locais.get(id));
		}
		return lista;
	}
	
	public Local getLocal(String nomeLocal) {
		for (Entry<Integer, Local> entry : locais.entrySet()) {
			Local local = entry.getValue();
			if (local.getNome().equals(nomeLocal)) {
				return local;
			}
		}
		return null;
	}
	
	public boolean adicionarLocal(Local local) {
		if (localExiste(local.getNome())) {
			return false;
		}
		local.setId(locais.size() + 1);
		locais.put(local.getId(), local);
		return true;
	}
	
	public boolean editarLocal(String nomeLocal, String novoNome) {
		// Verificar se local com o novo nome ja existe
		Local novoLocal = getLocal(novoNome);
		if (novoLocal != null) {
			return false;
		}
		
		Local local = getLocal(nomeLocal);
		if (local == null) {
			return false;
		}
		
		local.editar(novoNome);
		
		return true;
	}
	
	public boolean editarLocal(int id, String novoNome) {
		// Verificar se local com o novo nome ja existe
		Local novoLocal = getLocal(novoNome);
		
		if (novoLocal != null) {
			return false;
		}
		
		Local local = getLocal(id);
		if (local == null) {
			return false;
		}
		
		local.editar(novoNome);
		
		return true;
	}
	
	public boolean removerLocal(String nomeLocal) {
		Local local = getLocal(nomeLocal);
		
		if (local == null) return false;
		
		Local removido = locais.remove(local.getId());
		return removido != null;
	}
	
	public boolean removerLocal(int id) {
		Local removido = locais.remove(id);
		return removido != null;
	}
	
	// Categoria
	
	public CategoriaLocal getCategoria(int id) {
		return categorias.get(id);
	}
	
	public CategoriaLocal getCategoria(String nomeCategoria) {
		for (Entry<Integer, CategoriaLocal> entry : categorias.entrySet()) {
			CategoriaLocal categoria = entry.getValue();
			if (categoria.getNome().equals(nomeCategoria)) {
				return categoria;
			}
		}
		return null;
	}
	
	// Modalidade
	
	public Modalidade getModalidade(int id) {
		return modalidades.get(id);
	}
	
	// Atividade
	
	public Atividade getAtividade(int id) {
		return atividades.get(id);
	}
	
	public Atividade getAtividade(String nome) {
		for (Atividade atividade : atividades()) {
			if (atividade.getNome().equals(nome)) {
				return atividade;
			}
		}
		return null;
	}
	
	public List<Atividade> atividades() {
		return new ArrayList<>(atividades.values());
	}
	
	public boolean atividadeExiste(String nome) {
		return getAtividade(nome) != null;
	}
	
	public boolean adicionarAtividade(String nomeAtividade, Modalidade modalidade) {
		if (atividadeExiste(nomeAtividade) || nomeAtividade.length() < 2) {
			return false;
		}
		Atividade nova = new Atividade(nomeAtividade, modalidade);
		nova.setId(atividades.size()+1);
		atividades.put(nova.getId(), nova);
		return true;
	}
	
	public boolean editarAtividade(String nomeAtividade, String novoNome) {
		Atividade atividade = getAtividade(nomeAtividade);
		if (atividade == null) {
			return false;
		}
		if (atividadeExiste(novoNome)) {
			return false;
		}
		atividade.editar(novoNome);
		return true;
	}
	
	public boolean editarAtividade(int id, String novoNome) {
		Atividade atividade = getAtividade(id);
		if (atividade == null) {
			return false;
		}
		if (atividadeExiste(novoNome)) {
			return false;
		}
		atividade.editar(novoNome);
		return true;
	}
	
	// Turma
	
	public Map<Integer, Turma> getTurmas() {
		return turmas;
	}
	
	public Turma getTurma(int id) {
		return turmas.get(id);
	}
	
	public void adicionarTurma(Turma turma) {
		turmas.put(turma.getId(), turma);
	}
	
	// Getters	
	
	public int getInicioFuncionamento() {
		return inicioFuncionamento;
	}

	public int getFimFuncionamento() {
		return fimFuncionamento;
	}

	public DayOfWeek getFechado() {
		return fechado;
	}
}

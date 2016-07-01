package clube;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.DayOfWeek;
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

public class Clube implements Serializable {
	
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
		fechado = DayOfWeek.SUNDAY;
		usuarios = new HashMap<>();
		atividades = new HashMap<>();
		turmas = new HashMap<>();
		locais = new HashMap<>();
		this.inicializarCategorias();
		this.inicializarModalidades();
		
		// Verifica se o arquivo com o estado do sistema existe.
		// Caso nao exista, carrega os valores padrao do sistema.
		if (new File("clube-de-jogos.data").isFile()) {
			recuperarEstadoSistema();
		} else {
			this.inicializarLocais();
		}
	}
	
	private void inicializarCategorias() {
		categorias = new HashMap<>();
		categorias.put(1, new CategoriaLocal(1, "Quadra"));
		categorias.put(2, new CategoriaLocal(2, "Sala"));
	}
	
	private void inicializarLocais() {
		adicionarLocal(new Local("Quadra 1", this.categorias.get(1)));
		adicionarLocal(new Local("Quadra 2", this.categorias.get(1)));
		adicionarLocal(new Local("Sala 1", this.categorias.get(2)));
		adicionarLocal(new Local("Sala 2", this.categorias.get(2)));
	}
	
	private void inicializarModalidades() {
		modalidades = new HashMap<>();
		modalidades.put(1, new Modalidade(1, "Digital", this.categorias.get(2)));
		modalidades.put(2, new Modalidade(2, "Analogico", this.categorias.get(2)));
		modalidades.put(3, new Modalidade(3, "Fisico", this.categorias.get(1)));
	}
	
	public void salvarEstadoSistema() {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("clube-de-jogos.data"))) {
			out.writeObject(instance);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void recuperarEstadoSistema() {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("clube-de-jogos.data"))) {
			instance = (Clube) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Agendamentos
	
	public boolean usuarioAgendado(Usuario usuario, DayOfWeek dia, int horario) {
		for (Local local : locais.values()) {
			if (local.usuarioAgendado(usuario, dia, horario)) {
				return true;
			}
		}
		return false;
	}
	
	public int agendamentosNoDia(Usuario usuario, DayOfWeek dia) {
		int agendamentos = 0;
		for (Entry<Integer, Local> entry : locais.entrySet()) {
			Local local = entry.getValue();
			agendamentos += local.agendamentosNoDia(usuario, dia);
		}
		return agendamentos;
	}
	
	public boolean agendarTurma(Turma turma, DayOfWeek dia, int horarioInicial, int duracao) {
//		for (Usuario usuario : usuarios.values()) {
//			int agendamentos = agendamentosNoDia(usuario, dia);
//			if (agendamentos >= 2) {		
//				return false;
//			}
//			for (int horario = horarioInicial; horario < horarioInicial + duracao; horario++) {
//				if (usuarioAgendado(usuario, dia, horario)) {
//					return false;
//				}
//			}
//		}
		if (turma.getUsuarios().size() > 0) {
			return false;
		}
		if (dia.equals(fechado) || duracao < duracaoMinima) {
			return false;
		}
		boolean ok =  turma.getLocal().agendarTurma(turma, dia, horarioInicial, duracao);
		if (ok) {
			adicionarTurma(turma);
		}
		return ok;
	}
	
	public void imprimeCalendario(Local local, DayOfWeek dia) {
		local.imprimeCalendario(dia);
	}
	
	// Usuarios
	
	public void imprimeUsuarios() {
		for (Entry<Integer, Usuario> entry : usuarios.entrySet()) {
			System.out.format("%d - %s\n", entry.getValue().getId(), entry.getValue().getNome());
		}
	}
	
	public boolean usuarioExiste(String nomeUsuario) {
		for (Usuario usuario : usuarios.values()) {
			if (usuario.getNome().equals(nomeUsuario)) {
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
	
	public boolean editarUsuario(String nomeUsuario, String novoNome, int novaIdade, String novoSexo, String novoEndereco, long novoTelefone) {
		Usuario novoUsuario = getUsuario(novoNome);
		if (novoUsuario != null) {
			return false;
		}
		
		Usuario usuario = getUsuario(nomeUsuario);
		if (usuario == null) {
			return false;
		}
		
		usuario.editar(novoNome, novaIdade, novoSexo, novoEndereco, novoTelefone);
		
		return true;
	}
	
	public boolean editarUsuario(int id, String novoNome, int novaIdade, String novoSexo, String novoEndereco, long novoTelefone) {
		// Verificar se local com o novo nome ja existe
		Usuario novoUsuario = getUsuario(novoNome);
		if (novoUsuario != null) {
			return false;
		}
		
		Usuario usuario = getUsuario(id);
		if (usuario == null) {
			return false;
		}
		
		usuario.editar(novoNome, novaIdade, novoSexo, novoEndereco, novoTelefone);
		
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
		return new ArrayList<Usuario>(usuarios.values());
	}
	
	public boolean adicionarUsuario(String nome, int idade, String sexo, String endereco, long telefone) {
		if (usuarioExiste(nome))  {
			return false;
		}
		Usuario novo = new Usuario(nome, idade, sexo, endereco, telefone);
		novo.setId(usuarios.size() + 1);
		usuarios.put(novo.getId(), novo);
		return true;
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
	
	public void exibirTurmas(Local local) {
		for (Turma t : getTurmas()) {
			if (t.getLocal().equals(local)) {
				local.imprimirHorariosTurma(t);				
			}
		}
	}
	
	// Categoria
	
	public CategoriaLocal getCategoria(int id) {
		return categorias.get(id);
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
	
	public boolean removerAtividade(String nomeAtividade) {
		Atividade atividade = getAtividade(nomeAtividade);
		if (atividade == null) {
			return false;
		}
		return atividades.remove(atividade.getId()) != null;
	}
	
	public boolean removerAtividade(int id) {
		return atividades.remove(id) != null;
	}
	
	public List<Turma> turmasPorAtividade(Atividade atividade) {
		List<Turma> turmas = new ArrayList<>();
		for (Turma turma : this.turmas.values()) {
			if (turma.getAtividade().equals(atividade)) {
				turmas.add(turma);
			}
		}
		return turmas;
	}
	
	// Turma
	
	public List<Turma> getTurmas() {
		return new ArrayList<>(turmas.values());
	}
	
	public Turma getTurma(int id) {
		return turmas.get(id);
	}
	
	private void adicionarTurma(Turma turma) {
		turma.setId(turmas.size() + 1);
		turmas.put(turma.getId(), turma);
	}
	
	public boolean adicionarUsuarioTurma(Usuario usuario, Turma turma) {
		return true;
	}
	
	public boolean removerTurma(Turma turma) {
		for (Usuario usuario : turma.getUsuarios()) {
			usuario.removerTurma(turma);
		}
		
		turma.getLocal().removerTurma(turma);
		
		turmas.remove(turma.getId());
		return true;
	}
	
	public void exibirTurmas() {
		for (Turma t : turmas.values()) {
			System.out.println(t);
		}
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

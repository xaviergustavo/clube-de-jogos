package usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import turma.Turma;

public class Usuario {
	
	private int id;
	
	private String nome;
	
	private int idade;
	
	private String endereco;
	
	private long telefone;
	
	private Map<Integer, Turma> turmas;
		
	public Usuario(String nome, int idade, String endereco, long telefone) {
		this.nome = nome;
		this.idade = idade;
		this.endereco = endereco;
		this.telefone = telefone;
		this.turmas = new HashMap<>();
	}
	
	public String formatoSistemaAntigo() {
		return String.format("%s,%d,%s,%d", nome, idade, endereco, telefone);
	}
	
	// O metodo editar nao altera o atributo turmas pois vai ficar a cargo do
	// clube e seus gerenciadores
	public void editar(String nome, int idade, String endereco, long telefone) {
		this.nome = nome;
		this.idade = idade;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	// Getters

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Usuario other = (Usuario) obj;
		
		return this.nome.equals(other.nome);
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public long getTelefone() {
		return telefone;
	}
	
	public void imprimirTurmas() {
		List<Turma> turmas = getTurmas();
		System.out.println("Quantidade de turmas: " + turmas.size());
		
		for (Turma t : turmas) {
			System.out.println(t);
			t.getLocal().imprimirHorariosTurma(t);
		}
	}
	
	public List<Turma> getTurmas() {
		List<Turma> turmas = new ArrayList<>();
		for (Entry<Integer, Turma> entry : this.turmas.entrySet()) {
			turmas.add(entry.getValue());
		}
		return turmas;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setTurma(Turma t) {
		this.turmas.put(t.getId(), t);
	}
}

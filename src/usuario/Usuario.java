package usuario;

public class Usuario {
	
	private int id;
	
	private String nome;
	
	public Usuario(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	// Getters

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
}


public class Local {

	private int id;
	
	private String nome;
	
	private CategoriaLocal categoria;
	
	public Local(int id, String nome, CategoriaLocal categoria) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
	}
	
	// Getters

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public CategoriaLocal getCategoria() {
		return categoria;
	}
	
}

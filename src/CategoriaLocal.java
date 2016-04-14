// O clube possui duas categorias de locais para atividades: quadras e salas de atividades.
// Lembre-se que ha um numero finito de locais.
public class CategoriaLocal {
	
	private int id;
	
	private String nome;
	
	CategoriaLocal(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

}

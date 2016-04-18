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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		CategoriaLocal other = (CategoriaLocal) obj;
		if (id != other.id) return false;
		return true;
	}

}

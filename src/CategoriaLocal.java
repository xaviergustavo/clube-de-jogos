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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
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

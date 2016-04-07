// O clube possui duas categorias de locais para atividades: quadras e salas de atividades.
// Lembre-se que ha um numero finito de locais.
public enum CategoriaLocal {
	
	QUADRA(1),
	SALA(2);
	
	private final int id;
	
	CategoriaLocal(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}

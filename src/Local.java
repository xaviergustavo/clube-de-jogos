
public enum Local {
	
	SALA1(CategoriaLocal.SALA),
	SALA2(CategoriaLocal.SALA),
	QUADRA1(CategoriaLocal.QUADRA),
	QUADRA2(CategoriaLocal.QUADRA);
	
	private final CategoriaLocal categoria;
	
	Local(CategoriaLocal categoria) {
		this.categoria = categoria;
	}
	
	public CategoriaLocal getCategoria() {
		return categoria;
	}
	
}

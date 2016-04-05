// Existem tres modalidades diferentes de jogos: digitais, analogicos e fisicos.
// Todas as atividades serao categorizadas por um desses tipos.
// Podemos citar como exemplo de cada modalidade as atividades: videogame, xadrez e volei.
public enum Modalidade {
	
	DIGITAL(CategoriaLocal.SALA),
	ANALOGICO(CategoriaLocal.SALA),
	FISICO(CategoriaLocal.QUADRA);
	
	// Atividades de jogos digitais e analogicos acontecer nas salas de
	// atividades. Atividades de jogos fisicos so podem acontecer nas quadras.
	// Toda modalidade tera uma instancia do objeto CategoriaLocal para que
	// essa condicao seja validada futuramente
	private final CategoriaLocal categoria;

	Modalidade(CategoriaLocal categoria) {
		this.categoria = categoria;
	}

	public CategoriaLocal getCategoria() {
		return categoria;
	}
	
}

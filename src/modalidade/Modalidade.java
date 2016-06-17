package modalidade;

import local.CategoriaLocal;

// Existem tres modalidades diferentes de jogos: digitais, analogicos e fisicos.
// Todas as atividades serao categorizadas por um desses tipos.
// Podemos citar como exemplo de cada modalidade as atividades: videogame, xadrez e volei.
public class Modalidade {
	
	private int id;
	
	private String nome;
	
	// Atividades de jogos digitais e analogicos so podem acontecer nas salas de
	// atividades. Atividades de jogos fisicos so podem acontecer nas quadras.
	// Toda modalidade tera uma instancia do objeto CategoriaLocal para que
	// essa condicao seja validada futuramente
	private CategoriaLocal categoria;

	public Modalidade(int id, String nome, CategoriaLocal categoria) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
	}

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

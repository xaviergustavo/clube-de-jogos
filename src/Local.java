
public class Local {
	
	private int id;
	
	private String nome;
	
	// Categoria do local
	private CategoriaLocal categoria;
	
	// Calendario especifico de um local, contendo
	// os agendamentos das turmas.
	private Calendario calendario;
	
	public Local(CategoriaLocal categoria) {
		this.categoria = categoria;
		this.calendario = new Calendario();
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

	public Calendario getCalendario() {
		return calendario;
	}
	
}

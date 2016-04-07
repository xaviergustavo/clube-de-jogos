
public class Cronograma {
	
	// Horario inicial do cronograma (inclusivo)
	private final int horarioInicial;
	
	// Horario final do cronograma (exclusivo)
	private final int horarioFinal;

	// agendamentos representa todos os horarios possiveis dentro
	// de um cronograma, baseado em seu horario inicial e final
	private Turma[] agendamentos;

	// Construtor que obriga a implementacao de um array de agendamentos
	// de tamanho fixo (horarioFinal - horarioInicial).
	// Se um cronograma comeca as 8h e vai ate as 22h, logo teremos
	// 22 - 8 = 14 posicoes (horarios) no array
	public Cronograma(int horarioInicial, int horarioFinal) {
		this.horarioInicial = horarioInicial;
		this.horarioFinal = horarioFinal;
		this.agendamentos = new Turma[horarioFinal - horarioInicial];
	}
	
	// Dado um horario, retorna a posicao correspondente no array de agendamentos
	private int posicaoPorHorario(int horario) {
		return horario - horarioInicial;
	}
	
	// Retorna a turma (ou nulo, caso o horario esteja vago) agendada no horario informado
	public Turma getTurma(int horario) {
		return agendamentos[posicaoPorHorario(horario)];
	}
	
	// Getters
	
	public int getHorarioInicial() {
		return horarioInicial;
	}

	public int getHorarioFinal() {
		return horarioFinal;
	}

	public Turma[] getAgendamentos() {
		return agendamentos;
	}
	
}

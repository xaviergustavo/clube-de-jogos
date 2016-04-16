import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Teste {
	public static void main(String[] args) {
		
		Clube clubinho = new Clube(DayOfWeek.SUNDAY);
		
		clubinho.adicionarUsuario(new Usuario(1, "Lucas"));
		clubinho.adicionarUsuario(new Usuario(2, "Gustavo"));
		clubinho.adicionarUsuario(new Usuario(3, "Felipe"));
		clubinho.adicionarUsuario(new Usuario(4, "Jose"));
		clubinho.adicionarUsuario(new Usuario(5, "Joao"));
		clubinho.adicionarUsuario(new Usuario(6, "Amanda"));
		clubinho.adicionarUsuario(new Usuario(7, "Sergio"));
		clubinho.adicionarUsuario(new Usuario(8, "Astrogildo"));
		clubinho.adicionarUsuario(new Usuario(9, "Marta"));
		
		clubinho.adicionarAtividade(new Atividade(1, "Futebol", clubinho.getModalidade(1)));
		clubinho.adicionarAtividade(new Atividade(2, "Basquete", clubinho.getModalidade(1)));
		
		LocalDate inicio = LocalDate.of(2016, 4, 15);
		
		Map<Integer, Usuario> usuarios1 = new HashMap<>();
		for (int i = 1; i < 4; i++) {
			Usuario u = clubinho.getUsuario(i);
			usuarios1.put(u.getId(), u);
		}
		
		Map<Integer, Usuario> usuarios2 = new HashMap<>();
		for (int i = 4; i < 7; i++) {
			Usuario u = clubinho.getUsuario(i);
			usuarios1.put(u.getId(), u);
		}
		
		Map<Integer, Usuario> usuarios3 = new HashMap<>();
		for (int i = 7; i < 10; i++) {
			Usuario u = clubinho.getUsuario(i);
			usuarios1.put(u.getId(), u);
		}
		
		Turma turma1 = new Turma(1, 4, inicio, clubinho.getAtividade(1), clubinho.getLocal(1), usuarios1);
		Turma turma2 = new Turma(2, 4, inicio, clubinho.getAtividade(1), clubinho.getLocal(1), usuarios2);
		Turma turma3 = new Turma(3, 4, inicio, clubinho.getAtividade(1), clubinho.getLocal(1), usuarios3);
		Turma turma4 = new Turma(4, 4, inicio, clubinho.getAtividade(1), clubinho.getLocal(1), clubinho.getUsuarios());
		
		LocalDate data = LocalDate.of(2016, 4, 23);
		boolean ok = clubinho.agendarTurma(turma1, data, 10, 1);
		if (!ok) {
			System.out.println("deu merda");
		}
		ok = clubinho.agendarTurma(turma2, data, 14, 3);
		if (!ok) {
			System.out.println("deu merda");
		}
		ok = clubinho.agendarTurma(turma3, data, 19, 2);
		if (!ok) {
			System.out.println("deu merda");
		}
		ok = clubinho.agendarTurma(turma4, data, 8, 2);
		if (!ok) {
			System.out.println("deu merda");
		}
		ok = clubinho.agendarTurma(turma2, data, 8, 2);
		if (!ok) {
			System.out.println("deu merda");
		}
		ok = clubinho.agendarTurma(turma4, data, 13, 4);
		if (!ok) {
			System.out.println("deu merda");
		}
		LocalDate domingo = LocalDate.of(2016, 4, 24);
		ok = clubinho.agendarTurma(turma1, domingo, 10, 2);
		if (!ok) {
			System.out.println("deu merda");
		}
		clubinho.imprimeCalendario(clubinho.getLocal(1), data);
		clubinho.imprimeCalendario(clubinho.getLocal(2), data.plusDays(1));
	}
}
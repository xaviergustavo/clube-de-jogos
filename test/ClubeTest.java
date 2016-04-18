import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ClubeTest {
	
	private Clube clube;
	
	@Before
	public void inicializarClube() {
		clube = new Clube(DayOfWeek.SUNDAY);
		
		clube.adicionarUsuario(new Usuario(1, "Lucas"));
		clube.adicionarUsuario(new Usuario(2, "Gustavo"));
		clube.adicionarUsuario(new Usuario(3, "Felipe"));
		clube.adicionarUsuario(new Usuario(4, "Jose"));
		clube.adicionarUsuario(new Usuario(5, "Jorge"));
		clube.adicionarUsuario(new Usuario(6, "Amanda"));
		clube.adicionarUsuario(new Usuario(7, "Sergio"));
		clube.adicionarUsuario(new Usuario(8, "Astrogildo"));
		clube.adicionarUsuario(new Usuario(9, "Marta"));
		
		clube.adicionarAtividade(new Atividade(1, "Futebol", clube.getModalidade(3)));
		clube.adicionarAtividade(new Atividade(2, "Basquete", clube.getModalidade(3)));
		clube.adicionarAtividade(new Atividade(3, "Xadrez", clube.getModalidade(2)));
		clube.adicionarAtividade(new Atividade(4, "Video Game", clube.getModalidade(1)));
		
		LocalDate inicioDaTurma = LocalDate.of(2016, 4, 10);
		Map<Integer, Usuario> usuarios1 = new HashMap<>();
		for (int i = 1; i < 4; i++) {
			Usuario u = clube.getUsuario(i);
			usuarios1.put(u.getId(), u);
		}
		Turma turma1 = new Turma(1, 20, inicioDaTurma, clube.getAtividade(1), clube.getLocal(1), usuarios1);
		clube.adicionarTurma(turma1);
		
		Map<Integer, Usuario> usuarios2 = new HashMap<>();
		for (int i = 3; i < 7; i++) {
			Usuario u = clube.getUsuario(i);
			usuarios2.put(u.getId(), u);
		}
		Turma turma2 = new Turma(2, 20, inicioDaTurma, clube.getAtividade(1), clube.getLocal(1), usuarios2);
		clube.adicionarTurma(turma2);
		
		Turma turma3 = new Turma(3, 20, inicioDaTurma, clube.getAtividade(1), clube.getLocal(2), clube.getUsuarios());
		clube.adicionarTurma(turma3);
	}

	@Test
	public void agendarAntesDas8h() {
		LocalDate data = LocalDate.of(2016, 4, 15);
		
		int horarioInicial = 7;
		int duracao = 3;
		
		assertFalse(clube.agendarTurma(clube.getTurma(1), data, horarioInicial, duracao));
	}
	
	@Test
	public void agendarAs22h() {
		LocalDate data = LocalDate.of(2016, 4, 15);
		
		int horarioInicial = 22;
		int duracao = 3;
		
		assertFalse(clube.agendarTurma(clube.getTurma(1), data, horarioInicial, duracao));
	}
	
	@Test
	public void agendarDepoisDas22() {
		LocalDate data = LocalDate.of(2016, 4, 15);
		
		int horarioInicial = 23;
		int duracao = 3;
		
		assertFalse(clube.agendarTurma(clube.getTurma(1), data, horarioInicial, duracao));
	}
	
	@Test
	public void agendarNoDiaDeFolga() {
		LocalDate data = LocalDate.of(2016, 4, 17); // Domingo
		
		int horarioInicial = 8;
		int duracao = 2;
		
		assertFalse(clube.agendarTurma(clube.getTurma(1), data, horarioInicial, duracao));
	}
	
	@Test
	public void agendarAtividadeComMenosDe2Horas() {		
		LocalDate data = LocalDate.of(2016, 4, 15);
		
		int horarioInicial = 8;
		int duracao = 1;
		
		assertFalse(clube.agendarTurma(clube.getTurma(1), data, horarioInicial, duracao));
	}
	
	@Test
	public void agendarTurmaEmDoisHorariosNoMesmoDia() {		
		LocalDate data = LocalDate.of(2016, 4, 13);
		
		int horarioInicial = 14;
		int duracao = 2;
				
		// Primeiro agendamento. Deve conseguir agendar
		assertTrue(clube.agendarTurma(clube.getTurma(1), data, horarioInicial, duracao));
		
		horarioInicial = 18;
		duracao = 3;
		
		// Segundo agendamento no mesmo dia. Nao deve conseguir agendar
		assertFalse(clube.agendarTurma(clube.getTurma(1), data, horarioInicial, duracao));
	}
	
	@Test
	public void agendarDuasTurmasNoMesmoHorario() {		
		LocalDate data = LocalDate.of(2016, 4, 15);
		
		int horarioInicial = 8;
		int duracao = 3;
		
		// Agendar a turma 1 as 8h. Deve conseguir agendar.
		assertTrue(clube.agendarTurma(clube.getTurma(1), data, horarioInicial, duracao));
		
		// Agendar a turma 2 tambem as 8h. Nao deve conseguir agendar
		assertFalse(clube.agendarTurma(clube.getTurma(2), data, horarioInicial, duracao));
	}
	
	@Test
	public void usuarioEmLocaisDiferentesNoMesmoDiaEHorario() {		
		LocalDate data = LocalDate.of(2016, 4, 15);
		
		int horarioInicial = 8;
		int duracao = 3;
		
		// Turma da Quadra 1
		assertTrue(clube.agendarTurma(clube.getTurma(1), data, horarioInicial, duracao));
		
		// Turma da Quadra 2. Como a turma 3 possui todos os usuarios do clube, os usuarios
		// da turma 1 tambem estao na turma 3. Logo, nao deve conseguir agendar.
		assertFalse(clube.agendarTurma(clube.getTurma(3), data, horarioInicial, duracao));
	}
	
	@Test
	public void usuarioEmMaisDeDuasTurmasNoMesmoDia() {
		// O usuario de id = 3 esta em todas as turmas inicializadas no clube.
		// Logo, os dois primeiros agendamentos devem passar e o terceiro deve
		// falhar
		LocalDate data = LocalDate.of(2016, 4, 15);
		
		int horarioInicial = 8;
		int duracao = 3;
		assertTrue(clube.agendarTurma(clube.getTurma(1), data, horarioInicial, duracao));
		
		horarioInicial = 13;
		duracao = 2;
		assertTrue(clube.agendarTurma(clube.getTurma(2), data, horarioInicial, duracao));
		
		horarioInicial = 17;
		duracao = 3;
		assertFalse(clube.agendarTurma(clube.getTurma(3), data, horarioInicial, duracao));
	}

}

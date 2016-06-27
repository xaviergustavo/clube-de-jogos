import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;

import org.junit.BeforeClass;
import org.junit.Test;

import atividade.Atividade;
import clube.Clube;
import gerenciadores.GerenciadorCalendario;
import local.Local;
import turma.Turma;
import usuario.Usuario;

public class ClubeTest {
	
	private static Clube clube;
	
	@BeforeClass
	public static void inicializarClube() {
		clube = Clube.getInstance();
		
		clube.adicionarUsuario("Lucas", 21, "Rua Teste", 912367532);
		clube.adicionarUsuario("Gustavo", 21, "Rua Teste", 912367532);
		clube.adicionarUsuario("Felipe", 21, "Rua Teste", 912367532);
		clube.adicionarUsuario("Jose", 21, "Rua Teste", 912367532);
		clube.adicionarUsuario("Jorge", 21, "Rua Teste", 912367532);
		clube.adicionarUsuario("Sergio", 21, "Rua Teste", 912367532);
		clube.adicionarUsuario("Astrogildo", 21, "Rua Teste", 912367532);
		clube.adicionarUsuario("Marta", 21, "Rua Teste", 912367532);
		
		clube.adicionarAtividade("Futebol", clube.getModalidade(3));
		clube.adicionarAtividade("Basquete", clube.getModalidade(3));
		clube.adicionarAtividade("Xadrez", clube.getModalidade(2));
		clube.adicionarAtividade("Video Game", clube.getModalidade(1));
		
//		Turma turma3 = new Turma(clube.getAtividade(1), clube.getLocal(2));
	}

	@Test
	public void agendarAntesDas8h() {
		DayOfWeek dia = DayOfWeek.MONDAY;
		
		int horarioInicial = 7;
		int duracao = 3;
		
		Turma turma1 = new Turma(clube.getAtividade(1), clube.getLocal(1));
		assertFalse(clube.agendarTurma(turma1, dia, horarioInicial, duracao));
	}
	
	@Test
	public void agendarAs22h() {
		DayOfWeek dia = DayOfWeek.MONDAY;
		
		int horarioInicial = 22;
		int duracao = 3;
		
		Turma turma1 = new Turma(clube.getAtividade(1), clube.getLocal(1));
		assertFalse(clube.agendarTurma(turma1, dia, horarioInicial, duracao));
	}
	
	@Test
	public void agendarDepoisDas22() {
		DayOfWeek dia = DayOfWeek.MONDAY;
		
		int horarioInicial = 23;
		int duracao = 3;
		
		Turma turma1 = new Turma(clube.getAtividade(1), clube.getLocal(1));
		assertFalse(clube.agendarTurma(turma1, dia, horarioInicial, duracao));
	}
	
	@Test
	public void agendarNoDiaDeFolga() {
		DayOfWeek dia = DayOfWeek.SUNDAY;
		
		int horarioInicial = 8;
		int duracao = 2;
		
		Turma turma1 = new Turma(clube.getAtividade(1), clube.getLocal(1));
		assertFalse(clube.agendarTurma(turma1, dia, horarioInicial, duracao));
	}
	
	@Test
	public void agendarAtividadeComMenosDe2Horas() {		
		DayOfWeek dia = DayOfWeek.MONDAY;
		
		int horarioInicial = 8;
		int duracao = 1;
		
		Turma turma1 = new Turma(clube.getAtividade(1), clube.getLocal(1));
		assertFalse(clube.agendarTurma(turma1, dia, horarioInicial, duracao));
	}
	
	@Test
	public void agendarTurmaEmDoisHorariosNoMesmoDia() {		
		DayOfWeek dia = DayOfWeek.MONDAY;
		
		int horarioInicial = 14;
		int duracao = 2;
		
		Turma turma1 = new Turma(clube.getAtividade(1), clube.getLocal(1));
		
		// Primeiro agendamento. Deve conseguir agendar
		assertTrue(clube.agendarTurma(turma1, dia, horarioInicial, duracao));
		
		horarioInicial = 18;
		duracao = 3;
		
		// Segundo agendamento no mesmo dia. Nao deve conseguir agendar
		assertFalse(clube.agendarTurma(turma1, dia, horarioInicial, duracao));
	}
	
	@Test
	public void agendarDuasTurmasNoMesmoHorario() {		
		DayOfWeek dia = DayOfWeek.TUESDAY;
		
		int horarioInicial = 8;
		int duracao = 3;
		
		Turma turma1 = new Turma(clube.getAtividade(1), clube.getLocal(1));
		
		// Agendar a turma 1 as 8h. Deve conseguir agendar.
		assertTrue(clube.agendarTurma(turma1, dia, horarioInicial, duracao));
		
		Turma turma2 = new Turma(clube.getAtividade(1), clube.getLocal(1));
		
		// Agendar a turma 2 tambem as 8h. Nao deve conseguir agendar
		assertFalse(clube.agendarTurma(turma2, dia, horarioInicial, duracao));
	}
	
//	@Test
//	public void usuarioEmLocaisDiferentesNoMesmoDiaEHorario() {		
//		DayOfWeek dia = DayOfWeek.WEDNESDAY;
//		
//		int horarioInicial = 8;
//		int duracao = 3;
//		
//		// Turma da Quadra 1
//		assertTrue(clube.agendarTurma(turma1, dia, horarioInicial, duracao));
//		
//		// Turma da Quadra 2. Como a turma 3 possui todos os usuarios do clube, os usuarios
//		// da turma 1 tambem estao na turma 3. Logo, nao deve conseguir agendar.
//		assertFalse(clube.agendarTurma(turma3, dia, horarioInicial, duracao));
//	}
//	
//	@Test
//	public void usuarioEmMaisDeDuasTurmasNoMesmoDia() {
//		// O usuario de id = 3 esta em todas as turmas inicializadas no clube.
//		// Logo, os dois primeiros agendamentos devem passar e o terceiro deve
//		// falhar
//		DayOfWeek dia = DayOfWeek.THURSDAY;
//		
//		int horarioInicial = 8;
//		int duracao = 3;
//		assertTrue(clube.agendarTurma(turma1, dia, horarioInicial, duracao));
//		
//		horarioInicial = 13;
//		duracao = 2;
//		assertTrue(clube.agendarTurma(turma2, dia, horarioInicial, duracao));
//		
//		clube.imprimeCalendario(clube.getLocal(1), DayOfWeek.THURSDAY);
//		
//		horarioInicial = 17;
//		duracao = 3;
//		assertFalse(clube.agendarTurma(turma3, dia, horarioInicial, duracao));
//	}
	
	@Test
	public void teste() {
		GerenciadorCalendario<Atividade, Turma, Local, Usuario> gerenciador = new GerenciadorCalendario<>();
		
		clube.exibirTurmas();
		
		gerenciador.removeTurma(clube.getTurma(1));
		
		clube.exibirTurmas();
	}

}

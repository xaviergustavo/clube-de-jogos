import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import atividade.Atividade;
import clube.Clube;
import gerenciadores.GerenciadorAtividade;
import gerenciadores.GerenciadorUsuario;
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
		
		LocalDate inicioDaTurma = LocalDate.of(2016, 4, 10);
		List<Usuario> usuarios1 = new ArrayList<>();
		for (int i = 1; i < 4; i++) {
			usuarios1.add(clube.getUsuario(i));
		}
		Turma turma1 = new Turma(1, 20, inicioDaTurma, clube.getAtividade(1), clube.getLocal(1), usuarios1);
		clube.adicionarTurma(turma1);
		
		List<Usuario> usuarios2 = new ArrayList<>();
		for (int i = 3; i < 7; i++) {
			usuarios2.add(clube.getUsuario(i));
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
		LocalDate data = LocalDate.of(2016, 4, 16);
		
		int horarioInicial = 22;
		int duracao = 3;
		
		assertFalse(clube.agendarTurma(clube.getTurma(1), data, horarioInicial, duracao));
	}
	
	@Test
	public void agendarDepoisDas22() {
		LocalDate data = LocalDate.of(2016, 4, 16);
		
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
		LocalDate data = LocalDate.of(2016, 4, 18);
		
		int horarioInicial = 8;
		int duracao = 1;
		
		assertFalse(clube.agendarTurma(clube.getTurma(1), data, horarioInicial, duracao));
	}
	
	@Test
	public void agendarTurmaEmDoisHorariosNoMesmoDia() {		
		LocalDate data = LocalDate.of(2016, 4, 19);
		
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
		LocalDate data = LocalDate.of(2016, 4, 20);
		
		int horarioInicial = 8;
		int duracao = 3;
		
		// Agendar a turma 1 as 8h. Deve conseguir agendar.
		assertTrue(clube.agendarTurma(clube.getTurma(1), data, horarioInicial, duracao));
		
		// Agendar a turma 2 tambem as 8h. Nao deve conseguir agendar
		assertFalse(clube.agendarTurma(clube.getTurma(2), data, horarioInicial, duracao));
	}
	
	@Test
	public void usuarioEmLocaisDiferentesNoMesmoDiaEHorario() {		
		LocalDate data = LocalDate.of(2016, 4, 21);
		
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
		LocalDate data = LocalDate.of(2016, 4, 22);
		
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
	
	@Test
	public void teste() {
		GerenciadorUsuario<Usuario> gerenciador = new GerenciadorUsuario<>();
		
		gerenciador.cadastrarNovoUsuario();
	}

}

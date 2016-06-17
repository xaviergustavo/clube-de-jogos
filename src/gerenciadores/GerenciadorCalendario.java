package gerenciadores;

import java.sql.Date;
import java.util.List;

public class GerenciadorCalendario <A,T,L,U>{
	
	/**
	 * Cria uma turma no calendário em determinado horário, com uma atividade,
	 * capacidade e um local. A turma no momento de sua criaçăo tem de estar com capacidade vazia (nenhum usuário).
	 * Os usuários săo adicionados posteriormente. Verifica se a atividade pode 
	 * pertencer a sala, e se o horário é compatível (conflito com outras atividades e horário
	 * de funcionamento).
	 * @return true - se a turma pode ser alocada com determinada atividade
	 * no local e horário desejados  
	 * false - se a turma năo puder ser alocada 
	 */
	public boolean adicionaTurma(T turma) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */
		return false;
	}
	
	/**
	 * Cria uma turma para cada item em uma lista de turmas no calendário para um mesmo local. Cada qual com seu determinado horário, com uma atividade,
	 * capacidade. As turmas no momento de sua criaçăo tem de estar com capacidade vazias (nenhum usuário).
	 * Os usuários săo adicionados posteriormente. Verifica se as atividades podem 
	 * pertencer ŕs salas, e se os horários săo compativeis (conflito com outras atividades e horário
	 * de funcionamento).
	 * @return true - se todas as turmas podem ser alocadas com determinada atividade,
	 * no local e horário desejados  
	 * false - se as turmas năo puderem ser alocadas 
	 */
	public boolean adicionaTurmasLocal(List<T> turmas, L local) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */
		return false;
	}
	
	/**
	 * Exibe as informaçőes de todas as turmas pertencentes a um local. Exibe também
	 * os horários e ocorręncias no calendário, como duraçăo, ocorręncia, quando termina, etc.
	 */
	public void exibeTurmasLocal(L local) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */
	}
	
	/**
	 * Exibe todas as informaçőes de todas turmas de determinada atividade. 
	 * @param atividade - atividade para exibiçăo de turmas
	 */
	public void exibeTurmasAtividade(A atividade) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */
	}
	
	/**
	 * Exibe todas as informaçőes de todas as turmas criadas, incluindo os locais,
	 *  atividades, número de participantes, horários, etc.
	 */
	public void exibeTodasTurmas() {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */
	}
	
	
		
	/**
	 * Altera o dia, a hora de início e a hora de fim que uma turma ocorre 
	 * e verifica conflitos entre os usuários que participam de uma outra turma no
	 * novo dia e horário. Os usuários em conflito săo removidos. Năo é obrigatório
	 * que dia, hora inicio e hora fim sejam alterados ao mesmo tempo.
	 * @param turma - turma a ser reagendada
	 * @param dia - novo dia da semana para a atividade ocorrer
	 * @param inicio - nova hora de inicio
	 * @param fim - nova hora de fim 
	 * @return List<U> - contendo os usuários removidos por conflito de horários. Uma 
	 * mensagem indicando a operaçăo pode ser configurada no log, mas deve conter o número
	 * de usuários removidos
	 *  
	 */
	public List<U> reagendaTurma(T turma, Date dia, Date inicio, Date fim) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
		return null;
	}
	
	
	/**
	 * Remove a turma do calendario deixando a sala, os horários e os usuários
	 * livres. Primeiro exibe todos os campos da turma e exige confirmaçăo por entrada
	 * do usuário para completar a operaçăo.
	 * @param turma - turma a ser removida
	 * @return true - se confirma remoçăo; false - se cancela remoçăo
	 * 
	 */
	public boolean removeTurma(T turma) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
		return false;
	}
	
	
	/**
	 * Altera o local (sala) que a turma ocorre verificando se há disponibilidade
	 * no mesmo horário no local novo, e se o local novo pode receber atividades do
	 * tipo da atividade da turma. Se houver qualquer conflito năo altera o local.
	 * @param turma - turma que terá local alterado
	 * @param local - novo local
	 * @return true - se alterou local, false - caso contrário
	 */
	public boolean alteraLocal(T turma, L local){
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
		return false;
	};
	
	
	/**
	 * Adiciona um usuário a uma turma verificando se a turma possui capacidade livre
	 *  (năo está cheia), se o usuário năo participa de uma outra turma no mesmo horário, e
	 *  se o usuário ja participa de duas turmas em um mesmo dia. Em caso de conflitos o usuário năo é adicionado.  
	 * @param usuario - usuário a ser adicionado
	 * @param turma - turma a incluir o usuário
	 * @return true - se usuário foi incluido; false - caso o contrário
	 */
	public boolean adicionaUsuarioTurma(U usuario, T turma) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
		return false;
	}
	
	/**
	 * Remove o usuário da turma permitindo que ele possa estar eleito para ingressar em outra 
	 * turma naquele horário e libera a capacidade da turma para um novo usuário. Primeiro exibe todas as informaçőes
	 * principais de usuário, incluindo outras turmas, atividades, horários e locais que ele frequenta e exige confirmaçăo
	 * por entrada do usuário para completar operaçăo.
	 * @param usuario - usuário a ser removido
	 * @param turma - turma da qual o usuário será removido
	 * @return true - se confirma remoçăo; false - se cancela remoçăo
	 */
	public boolean removeUsuarioAtividade(U usuario, T turma) {
		/**
		 * TODO implementar este método seguindo a descriçăo acima
		 */	
		return false;
	}
	
	
	
	
	
	
	
	

}

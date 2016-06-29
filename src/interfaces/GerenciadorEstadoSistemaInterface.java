package interfaces;

import java.util.InputMismatchException;
import java.util.Scanner;

import clube.Clube;
import logger.ClubeLogger;

public class GerenciadorEstadoSistemaInterface {

	private ClubeLogger log;
	
	public GerenciadorEstadoSistemaInterface() {
		this.log = ClubeLogger.getInstance();
	}
	
	public void salvarEstadoSistema() {
		log.registrar("Salvando o estado do sistema...");
		
		System.out.println("Deseja salvar o estado do sistema?\n");
		
		System.out.println("1 - Sim");
		System.out.println("2 - Nao\n");
		
		System.out.println("Escolha uma opcao:");
		
		Scanner scanner = new Scanner(System.in);
		
		int opcao = 2;
		
		try{
			opcao = scanner.nextInt();
		} catch (InputMismatchException e) {
			opcao = 2;
		}
		
		if (opcao == 1) {
			Clube.getInstance().salvarEstadoSistema();
			log.registrarComSaida("Estado do Sistema salvo com sucesso!");
		}
		
	}
	
}

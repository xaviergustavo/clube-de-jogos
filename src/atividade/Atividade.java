package atividade;

import modalidade.Modalidade;

public class Atividade {

	private int id;
	
	private String nome;
	
	private Modalidade modalidade;
	
	public Atividade(int id, String nome, Modalidade modalidade) {
		this.id = id;
		this.nome = nome;
		this.modalidade = modalidade;
	}
	
	public String toString() {
		return this.nome;
	}
	
	// Getters

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}
	
}

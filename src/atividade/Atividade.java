package atividade;

import modalidade.Modalidade;

public class Atividade {

	private int id;
	
	private String nome;
	
	private Modalidade modalidade;
	
	public Atividade(String nome, Modalidade modalidade) {
		this.nome = nome;
		this.modalidade = modalidade;
	}
	
	public String toString() {
		return this.nome;
	}
	
	public String info() {
		return String.format("Nome: %s - Modalidade: %s", nome, modalidade.getNome());
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
	
	public void setId(int id) {
		this.id = id;
	}
}

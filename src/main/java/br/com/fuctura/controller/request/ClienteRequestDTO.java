package br.com.fuctura.controller.request;

public class ClienteRequestDTO {
	private String nome;
	private String cpf;

	public ClienteRequestDTO(String nome, String cpf) {
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



}

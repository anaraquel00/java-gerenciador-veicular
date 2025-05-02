package br.com.fuctura.entidade;

public class Cliente {
    private int id_cliente;
    private String nome;
    private String cpf;

    
    public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getNome() { 
    	return nome; }
    public void setNome(String nome) { 
    	this.nome = nome; }
    public String getCpf() { 
    	return cpf; }
    public void setCpf(String cpf) { 
    	this.cpf = cpf; }
}
package br.com.fuctura.entidade;

public class Vendedor {
private String nome;
private String cpf;

public Vendedor() {}
public Vendedor(String nome, String cpf) {
	super();
	this.nome = nome;
	this.cpf = cpf;
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
public void setIdLoja(Integer codigo) {
	// TODO Auto-generated method stub
	
}
public int getCodigo() {
	// TODO Auto-generated method stub
	return 0;
}
public int getIdLoja() {
	
	return 0;
}
public void setCodigo(int int1) {
	// TODO Auto-generated method stub
	
}



}

package br.com.fuctura.entidade;

public class Loja {
private Integer codigo;
private String nomeLoja;
private String vendedor;
private int veiculosVendidos;

public Loja() {}


public Loja(Integer codigo, String nomeLoja, String vendedor, int veiculosVendidos) {
	super();
	this.codigo = codigo;
	this.nomeLoja = nomeLoja;
	this.vendedor = vendedor;
	this.veiculosVendidos = veiculosVendidos;
}


public Integer getCodigo() {
	return codigo;
}


public void setCodigo(Integer codigo) {
	this.codigo = codigo;
}


public String getNomeLoja() {
	return nomeLoja;
}

public void setNomeLoja(String nomeLoja) {
	this.nomeLoja = nomeLoja;
}

public String getVendedor() {
	return vendedor;
}

public void setVendedor(String vendedor) {
	this.vendedor = vendedor;
}

public int getVeiculosVendidos() {
	return veiculosVendidos;
}


public void setVeiculosVendidos(int veiculosVendidos) {
	this.veiculosVendidos = veiculosVendidos;
}


}

	
	

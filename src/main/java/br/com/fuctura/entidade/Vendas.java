package br.com.fuctura.entidade;

import java.math.BigDecimal;
import java.time.LocalDate;



public class Vendas {
	    private Integer codigo;
	    private Veiculo veiculo;
	    private Vendedor vendedor;
	    private Loja loja;
	    private BigDecimal valor;
	    private LocalDate dataVenda;


public Vendas() {}


public Integer getCodigo() {
	return codigo;
}


public void setCodigo(Integer codigo) {
	this.codigo = codigo;
}


public Veiculo getVeiculo() {
	return veiculo;
}


public void setVeiculo(Veiculo veiculo) {
	this.veiculo = veiculo;
}


public Vendedor getVendedor() {
	return vendedor;
}


public void setVendedor(Vendedor vendedor) {
	this.vendedor = vendedor;
}


public Loja getLoja() {
	return loja;
}


public void setLoja(Loja loja) {
	this.loja = loja;
}


public BigDecimal getValor() {
	return valor;
}


public void setValor(BigDecimal valor) {
	this.valor = valor;
}


public LocalDate getDataVenda() {
	return dataVenda;
}


public void setDataVenda(LocalDate dataVenda) {
	this.dataVenda = dataVenda;
}





}















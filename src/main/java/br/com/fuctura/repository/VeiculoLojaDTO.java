package br.com.fuctura.repository;

public class VeiculoLojaDTO {
	 private String placa;
	    private String modelo;
	    private int ano;
	    private String loja;
	    private String vendedor;
	       
	    
		public VeiculoLojaDTO() {}
		
				
		public VeiculoLojaDTO(String placa, String modelo, int ano, String loja, String vendedor) {
			super();
			this.placa = placa;
			this.modelo = modelo;
			this.ano = ano;
			this.loja = loja;
			this.vendedor = vendedor;
		}


		public String getPlaca() {
			return placa;
		}
		public void setPlaca(String placa) {
			this.placa = placa;
		}
		public String getModelo() {
			return modelo;
		}
		public void setModelo(String modelo) {
			this.modelo = modelo;
		}
		public int getAno() {
			return ano;
		}
		public void setAno(int ano) {
			this.ano = ano;
		}
		public String getLoja() {
			return loja;
		}
		public void setLoja(String loja) {
			this.loja = loja;
		}
		public String getVendedor() {
			return vendedor;
		}
		public void setVendedor(String vendedor) {
			this.vendedor = vendedor;
		}
	    
	    
}

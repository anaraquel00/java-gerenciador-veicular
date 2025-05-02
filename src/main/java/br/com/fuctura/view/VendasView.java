package br.com.fuctura.view;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.fuctura.dto.VendaRelatorioDTO;


public class VendasView {
	


		public void exibirRelatorio(List<VendaRelatorioDTO> vendas) {
			
		        if (vendas.isEmpty()) {
		            System.out.println("Nenhuma venda registrada!");
		            return;
		        }

		        String format = "| %-6s | %-10s | %-15s | %-6s | %-12s | %-20s | %-15s |%n";
		        System.out.format(format, "Código", "Placa", "Modelo", "Ano", "Valor", "Vendedor", "Loja");
		        System.out.println("+--------+------------+-----------------+--------+--------------+----------------------+-----------------+");

		        NumberFormat moedaFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		        
		        for (VendaRelatorioDTO venda : vendas) {
		            System.out.format(format,
		                venda.getCodigo(),
		                venda.getPlaca(),
		                venda.getModelo(),
		                venda.getAno(),
		                moedaFormat.format(venda.getValor()),
		                venda.getVendedor(),
		                venda.getLoja()
		            );
		        }
		    }
			
		}


package br.com.fuctura.view;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TabelaUtils {
	 public static void exibirTabelaVendas(List<Map<String, Object>> vendas) {
	        // Definir cabeçalho e larguras das colunas
	        String[] colunas = {"codigo", "placa", "modelo", "ano", "valor", "vendedor", "loja"};
	        String[] cabecalhos = {"Código", "Placa", "Modelo", "Ano", "Valor", "Vendedor", "Loja"};
	        int[] larguras = new int[colunas.length];
	        
	        // Calcular largura máxima para cada coluna
	        for (int i = 0; i < colunas.length; i++) {
	            larguras[i] = cabecalhos[i].length();
	            for (Map<String, Object> venda : vendas) {
	                Object valor = venda.get(colunas[i]);
	                int tam = valor != null ? valor.toString().length() : 0;
	                larguras[i] = Math.max(larguras[i], tam);
	            }
	            larguras[i] += 2; // Margem
	        }
	        
	        // Imprimir cabeçalho
	        System.out.printf("| %-" + larguras[0] + "s ", cabecalhos[0]);
	        for (int i = 1; i < colunas.length; i++) {
	            System.out.printf("| %-" + larguras[i] + "s ", cabecalhos[i]);
	        }
	        System.out.println("|");
	        
	        // Imprimir linha separadora
	        for (int largura : larguras) {
	            System.out.print("+" + "-".repeat(largura + 1));
	        }
	        System.out.println("+");
	        
	        // Imprimir dados
	        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	        
	        for (Map<String, Object> venda : vendas) {
	            System.out.printf("| %-" + larguras[0] + "d ", venda.get("codigo"));
	            System.out.printf("| %-" + larguras[1] + "s ", venda.get("placa"));
	            System.out.printf("| %-" + larguras[2] + "s ", venda.get("modelo"));
	            System.out.printf("| %-" + larguras[3] + "d ", venda.get("ano"));
	            System.out.printf("| %" + larguras[4] + "s ", 
	                formatoMoeda.format(venda.get("valor")));
	            System.out.printf("| %-" + larguras[5] + "s ", venda.get("vendedor"));
	            System.out.printf("| %-" + larguras[6] + "s |\n", venda.get("loja"));
	        }
	    }
}

package com.scc4.scc4.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class IndexController {

	private boolean isVogel(char c) {
		switch (c) {
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u':
			return true;
		default:
			return false;
		}
	}

	private boolean isConsonant(char c) {
		return !isVogel(c);
	}

	@GetMapping("/listaReversa")
	public String listaReversa(@RequestParam String lista) {
		StringBuilder value = new StringBuilder(lista.replace("{", "").replace("}", ""));
		return "{" + value.reverse() + "}";
	}

	@GetMapping("/imprimirImpares")
	public String imprimirImpares(@RequestParam String lista) {
		String value = lista.replace("{", "").replace("}", "");
		StringBuilder result = new StringBuilder();
		for (String item : value.split(",")) {
			boolean flag = Integer.parseInt(item) % 2 != 0;
			if (flag)
				result.append(item + ',');
		}
		int index = result.lastIndexOf(",");
		return "{" + result.deleteCharAt(index) + "}";
	}

	@GetMapping("/imprimirPares")
	public String imprimirPares(@RequestParam String lista) {
		String value = lista.replace("{", "").replace("}", "");
		StringBuilder result = new StringBuilder();
		for (String item : value.split(",")) {
			boolean flag = Integer.parseInt(item) % 2 == 0;
			if (flag)
				result.append(item + ',');
		}
		int index = result.lastIndexOf(",");
		return "{" + result.deleteCharAt(index) + "}";
	}

	@GetMapping("/tamanho")
	public String tamanho(@RequestParam String palavra) {
		return "tamanho=" + palavra.length();
	}

	@GetMapping("/maisculas")
	public String maisculas(@RequestParam String palavra) {
		return palavra.toUpperCase();
	}

	@GetMapping("/vogais")
	public String vogais(@RequestParam String palavra) {
		StringBuilder result = new StringBuilder();
		for (char c : palavra.toCharArray()) {
			boolean flag = isVogel(c);
			if (flag)
				result.append(c);
		}
		return result.toString();
	}

	@GetMapping("/consoantes")
	public String consoantes(@RequestParam String palavra) {
		StringBuilder result = new StringBuilder();
		for (char c : palavra.toCharArray()) {
			boolean flag = isConsonant(c);
			if (flag)
				result.append(c);
		}
		return result.toString();
	}

	@GetMapping("/nomeBibliografico")
	public String nomeBibliografico(@RequestParam String nome) {
		String[] nomes = nome.split("%");
		StringBuilder result = new StringBuilder(nomes[nomes.length - 1].toUpperCase());
		result.append(", ");
		for (int index = 0; index < nomes.length - 1; index++) {
			String addNome = nomes[index];
			result.append(addNome.substring(0, 1).toUpperCase() + addNome.substring(1));
			result.append(' ');
		}
		return result.toString().trim();
	}

	@GetMapping("/sistemaMonetario")
	public String sistemaMonetario(@RequestParam("saque") int valor) {
		if (valor < 8)
			return "Não foi possível fazer a conversão!";
		int total5 = 0, total3 = 0;
		for (int aux = valor; aux > 0;) {
			boolean flag = aux % 5 == 0;
			if (flag) {
				total5 = aux / 5;
				aux = 0;
			} else {
				aux -= 3;
				total3++;
			}
		}
		StringBuilder result = new StringBuilder("Saque R$" + valor + ": ");
		if (total3 == 1)
			result.append(total3 + " nota de R$3");
		else if (total3 > 1)
			result.append(total3 + " notas de R$3");

		if (total3 > 0 && total5 > 0)
			result.append(" e ");

		if (total5 == 1)
			result.append(total5 + " nota de R$5");
		else if (total5 > 1)
			result.append(total5 + " notas de R$5");

		return result.toString();
	}

}

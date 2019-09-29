package com.scc4.scc4.utils;

public class Calculadora {

	public Fracao soma(Fracao f1, Fracao f2) {
		int newNumerator = (f1.getNumerador() * f2.getDenominador()) + (f2.getNumerador() * f1.getDenominador());
		int newDenominator = f1.getDenominador() * f2.getDenominador();
		return new Fracao(newNumerator, newDenominator);
	}

	public Fracao subtracao(Fracao f1, Fracao f2) {
		int newNumerator = (f1.getNumerador() * f2.getDenominador()) - (f2.getNumerador() * f1.getDenominador());
		int newDenominator = f1.getDenominador() * f2.getDenominador();
		return new Fracao(newNumerator, newDenominator);
	}

	public Fracao multiplicacao(Fracao f1, Fracao f2) {
		int newNumerator = f1.getNumerador() * f2.getNumerador();
		int newDenominator = f1.getDenominador() * f2.getDenominador();
		return new Fracao(newNumerator, newDenominator);
	}

	public Fracao divisao(Fracao f1, Fracao f2) {
		int newNumerator = f1.getNumerador() * f2.getDenominador();
		int newDenominator = f1.getDenominador() * f2.getNumerador();
		return new Fracao(newNumerator, newDenominator);
	}
}

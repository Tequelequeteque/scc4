package com.scc4.scc4.utils;

public class Fracao {
	
	public int numerador;
	 
	public int denominador;
	
	public Fracao(int numerador, int denominador) {
		this.numerador = numerador;
		this.denominador = denominador;
		reduce();
	}

	public int getNumerador() {
		return numerador;
	}

	public void setNumerador(int numerator) {
		this.numerador = numerator;
	}

	public int getDenominador() {
		return denominador;
	}

	public void setDenominador(int denominator) {
		this.denominador = denominator;
	}

	private int calculateGCD(int numerator, int denominator) {
		if (numerator % denominator == 0) {
			return denominator;
		}
		return calculateGCD(denominator, numerator % denominator);
	}

	private void reduce() {
		int gcd = calculateGCD(numerador, denominador);
		numerador /= gcd;
		denominador /= gcd;
		
	}
	
	public String toString() {
		return this.numerador + "/" + this.denominador;
	}
}

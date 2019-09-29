package com.scc4.scc4.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scc4.scc4.models.CalculadoraRequestModel;
import com.scc4.scc4.utils.*;


@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

	private Calculadora _calculadora = new Calculadora();

	@PutMapping("/soma")
	public Fracao soma(@Valid @RequestBody CalculadoraRequestModel model) {
	//public Fracao soma(@Valid @RequestBody CalculadoraRequestModel model) {
		return _calculadora.soma(model.f1,model.f2);
		//return _calculadora.soma(model.f1,model.f2).toString();
	}
	
	@PutMapping("/subtracao")
	public Fracao subtracao(@Valid @RequestBody CalculadoraRequestModel model) {
	//public String subtracao(@Valid @RequestBody CalculadoraRequestModel model) {
		return _calculadora.subtracao(model.f1, model.f2);
		//return _calculadora.subtracao(model.f1, model.f2).toString();
	}
	
	@PutMapping("/multiplicacao")
	public Fracao multiplicacao(@Valid @RequestBody CalculadoraRequestModel model) {
	//public String multiplicacao(@Valid @RequestBody CalculadoraRequestModel model) {
		return _calculadora.multiplicacao(model.f1, model.f2);
		//return _calculadora.multiplicacao(model.f1, model.f2).toString();
	}
	
	@PutMapping("/divisao")
	public Fracao divisao(@Valid @RequestBody CalculadoraRequestModel model) {
	//public String divisao(@Valid @RequestBody CalculadoraRequestModel model) {
		return _calculadora.divisao(model.f1, model.f2);
		//return _calculadora.divisao(model.f1, model.f2).toString();
	}
}

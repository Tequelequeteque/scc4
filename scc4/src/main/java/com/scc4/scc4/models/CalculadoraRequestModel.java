package com.scc4.scc4.models;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scc4.scc4.utils.Fracao;

public class CalculadoraRequestModel {

	@NotNull
	@JsonProperty("f1")
	public Fracao f1;
	
	@NotNull
	@JsonProperty("f2")
	public Fracao f2;
}

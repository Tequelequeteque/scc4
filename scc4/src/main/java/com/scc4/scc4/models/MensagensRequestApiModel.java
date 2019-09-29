package com.scc4.scc4.models;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MensagensRequestApiModel {
	
	@NotNull
	@JsonProperty("destinatarios")
	public List<Long> destinatarios;
	
	@NotNull
	@JsonProperty("mensagem")
	public String mensagem;

	public List<Long> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(List<Long> destinatarios) {
		this.destinatarios = destinatarios;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}

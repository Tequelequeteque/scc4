package com.scc4.scc4.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scc4.scc4.models.Destinatario;
import com.scc4.scc4.models.MensagensRequestApiModel;
import com.scc4.scc4.models.Remetente;
import com.scc4.scc4.repositories.IDestinatarioRepository;
import com.scc4.scc4.repositories.IRemetenteRepository;

@RestController
@RequestMapping("/remetentes")
public class MensagemController {
	
	@Autowired
	private IRemetenteRepository _remetentesRepository;
	@Autowired
	private IDestinatarioRepository _destinatarioRespository;

	@PutMapping("{remetenteId}/mensagens")
	public ResponseEntity<?> mensagem(@Valid @PathVariable("remetenteId") long remetenteId,
			@RequestBody MensagensRequestApiModel model) {
		Optional<Remetente> remetente = _remetentesRepository.findById(remetenteId);
		if(!remetente.isPresent())
			return new ResponseEntity<String>("Remetente não encontrado.",HttpStatus.BAD_REQUEST);
		
		List<Destinatario> destinatarios = _destinatarioRespository.findAllById(model.destinatarios);
		if(destinatarios.size() == 0)
			return new ResponseEntity<String>("Não foi encontrado nenhum destinatário.",HttpStatus.BAD_REQUEST);
		
		System.out.println(String.format("%s enviou um mensagem para %d destinatarios",remetente.get().getName(),destinatarios.size()));
		System.out.println(String.format("O conteúdo da mensagem é:\n%s\n", model.mensagem));
		for (Destinatario destinatario : destinatarios) {
			System.out.println(String.format("%s enviou uma mensagem para %s",remetente.get().getName(),destinatario.getName()));
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

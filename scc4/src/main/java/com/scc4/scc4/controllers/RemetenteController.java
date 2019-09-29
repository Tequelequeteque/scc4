package com.scc4.scc4.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.scc4.scc4.models.Remetente;
import com.scc4.scc4.repositories.IRemetenteRepository;

@RestController
@RequestMapping("/remetentes")
public class RemetenteController {

	@Autowired
	private IRemetenteRepository _remetentesRepository;

	@PostMapping
	public ResponseEntity<?> store(@Valid @RequestBody Remetente model) {
		Optional<Remetente> remetenteExist = _remetentesRepository.findByEmail(model.getEmail());
		if (remetenteExist.isPresent())
			return new ResponseEntity<String>("Existe outro remetente com este email.",HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Remetente>(_remetentesRepository.save(model), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<?> index() {
		return new ResponseEntity<List<Remetente>>(_remetentesRepository.findAll(), HttpStatus.OK);
	}

	@DeleteMapping("/{remetenteId}")
	public ResponseEntity<?> delete(@Valid @PathVariable("remetenteId") long remetenteId) {
		Boolean remetenteDelete = _remetentesRepository.existsById(remetenteId);
		if (!remetenteDelete)
			return new ResponseEntity<String>("Remetente não encontrado.", HttpStatus.ACCEPTED);

		_remetentesRepository.deleteById(remetenteId);
		return new ResponseEntity<String>("Remetente deletado com sucesso.", HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Remetente model) {
		Optional<Remetente> remetenteUpdate = _remetentesRepository.findById(model.getId());
		if (!remetenteUpdate.isPresent())
			return new ResponseEntity<String>("Remetente não encontrado.",HttpStatus.BAD_REQUEST);

		Optional<Remetente> hasRemetente = _remetentesRepository.findByEmail(model.getEmail());
		if (hasRemetente.isPresent() && hasRemetente.get().getId() != model.getId())
			return new ResponseEntity<String>("Existe outro remetente com este email.",HttpStatus.BAD_REQUEST);

		remetenteUpdate.get().setEmail(model.getEmail());
		remetenteUpdate.get().setCpf(model.getCpf());
		remetenteUpdate.get().setAddress(model.getAddress());
		remetenteUpdate.get().setName(model.getName());
		remetenteUpdate.get().setPhone(model.getPhone());

		return new ResponseEntity<Remetente>(_remetentesRepository.save(remetenteUpdate.get()), HttpStatus.OK);
	}
	
}

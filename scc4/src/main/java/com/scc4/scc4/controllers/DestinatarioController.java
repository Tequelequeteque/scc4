package com.scc4.scc4.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.scc4.scc4.models.Destinatario;
import com.scc4.scc4.models.Remetente;
import com.scc4.scc4.repositories.IDestinatarioRepository;

@RestController
@RequestMapping("/destinatarios")
public class DestinatarioController {

	@Autowired
	private IDestinatarioRepository _destinatarioRespository;

	@PostMapping
	public ResponseEntity<?> store(@Valid @RequestBody Destinatario model) {
		Optional<Destinatario> destinatarioExists = _destinatarioRespository.findByEmail(model.getEmail());
		if (destinatarioExists.isPresent())
			return new ResponseEntity<String>("Existe um destinatário com este email.",HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Destinatario>(_destinatarioRespository.save(model), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<?> index() {
		return new ResponseEntity<List<Destinatario>>(_destinatarioRespository.findAll(), HttpStatus.OK);
	}

	@DeleteMapping("/{destinatarioId}")
	public ResponseEntity<?> delete(@Valid @PathVariable("destinatarioId") long remetenteId) {
		Boolean destinatarioDelete = _destinatarioRespository.existsById(remetenteId);
		if (!destinatarioDelete)
			return new ResponseEntity<String>("Destinatario não existe.", HttpStatus.ACCEPTED);

		_destinatarioRespository.deleteById(remetenteId);
		return new ResponseEntity<String>("Destinatario deletado com sucesso.", HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Remetente model) {
		Optional<Destinatario> destinatarioUpdate = _destinatarioRespository.findById(model.getId());
		if (!destinatarioUpdate.isPresent())
			return new ResponseEntity<String>("Destinatário não encontrado",HttpStatus.BAD_REQUEST);

		Optional<Destinatario> hasRemetente = _destinatarioRespository.findByEmail(model.getEmail());
		if (hasRemetente.isPresent() && hasRemetente.get().getId() != model.getId())
			return new ResponseEntity<String>("Existe um destinatario com este email.",HttpStatus.BAD_REQUEST);

		destinatarioUpdate.get().setEmail(model.getEmail());
		destinatarioUpdate.get().setCpf(model.getCpf());
		destinatarioUpdate.get().setAddress(model.getAddress());
		destinatarioUpdate.get().setName(model.getName());
		destinatarioUpdate.get().setPhone(model.getPhone());

		return new ResponseEntity<Destinatario>(_destinatarioRespository.save(destinatarioUpdate.get()), HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity<List<Destinatario>> upload(@RequestParam MultipartFile file) throws IOException {
		String fileString = new String(file.getBytes());
		String[] lines = fileString.replace("\r", "").split("\n");
		String[] columns = lines[0].split(",");
		List<Destinatario> destinatarios = new ArrayList<Destinatario>();
		
		for (int index = 1; index < lines.length; index++) {
			String[] values = lines[index].split(",");
			destinatarios.add(setDestinatario(columns, values));
		}

		destinatarios = _destinatarioRespository.saveAll(destinatarios);
		return new ResponseEntity<List<Destinatario>>(destinatarios, HttpStatus.CREATED);
	}

	private Destinatario setDestinatario(String[] columns, String[] values) {
		Destinatario result = new Destinatario();
		for (int index = 0; index < columns.length; index++) {
			String key = columns[index].toLowerCase();
			String value = values[index];
			switch (key) {
			case "name":
				result.setName(value);
				break;
			case "email":
				result.setEmail(value);
				break;
			case "address":
				result.setAddress(value);
				break;
			case "cpf":
				result.setCpf(value);
				break;
			case "phone":
				result.setPhone(value);
				break;
			default:
				break;
			}
		}
		return result;
	}

}

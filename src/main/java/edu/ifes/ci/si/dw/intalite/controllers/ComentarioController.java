package edu.ifes.ci.si.dw.intalite.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.ifes.ci.si.dw.intalite.model.Comentario;
import edu.ifes.ci.si.dw.intalite.model.Postagem;
import edu.ifes.ci.si.dw.intalite.services.ComentarioService;
import edu.ifes.ci.si.dw.intalite.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/api/comentario")
public class ComentarioController {

	@Autowired
	private ComentarioService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Comentario>> findAll() {
		Collection<Comentario> collection = service.findAll();
		return ResponseEntity.ok().body(collection);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Comentario> find(@PathVariable Integer id) {
		Comentario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Comentario> insert(@Valid @RequestBody Comentario obj, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Comentario> update(@Valid @RequestBody Comentario obj, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		obj = service.update(obj);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/findByPostagem/{idPostagem}", method = RequestMethod.GET)
	public ResponseEntity<Collection<Comentario>> findByPostagem(@PathVariable Integer idPostagem) {
		Postagem obj = new Postagem();
		obj.setId(idPostagem);
		Collection<Comentario> collection = service.findByPostagem(obj);
		return ResponseEntity.ok().body(collection);
	}

}

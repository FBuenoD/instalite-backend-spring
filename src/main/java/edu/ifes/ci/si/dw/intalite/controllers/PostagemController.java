package edu.ifes.ci.si.dw.intalite.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ifes.ci.si.dw.intalite.model.Postagem;
import edu.ifes.ci.si.dw.intalite.model.Usuario;
import edu.ifes.ci.si.dw.intalite.services.PostagemService;
import edu.ifes.ci.si.dw.intalite.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/api/postagem")
public class PostagemController {

	@Autowired
	private PostagemService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Postagem>> findAll() {
		Collection<Postagem> collection = service.findAll();
		return ResponseEntity.ok().body(collection);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Postagem> find(@PathVariable Integer id) {
		Postagem obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	/*
	 * Este método insere um postagem esperando receber dois parâmetros no Request:
	 * artista: objeto postagem no formato de String JSON artistaImagem: File
	 */
	@RequestMapping(value = "/insertfile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Postagem> insertfile(@RequestParam("postagem") String postagem,
			@RequestParam("postagemImagem") MultipartFile postagemImagem) {
		Postagem obj;
		try {
			obj = new ObjectMapper().readValue(postagem, Postagem.class);
			obj.setFotoUrl(postagemImagem.getBytes());
			obj = service.insert(obj);
			return ResponseEntity.ok().body(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Este método insere um postagem esperando receber dois parâmetros no Request:
	 * artista: objeto postagem no formato de String JSON artistaImagem: File
	 */
	@RequestMapping(value = "/updatefile/{id}", method = RequestMethod.PATCH, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Postagem> updatefile(String postagem,
			@RequestParam("postagemImagem") MultipartFile postagemImagem) {
		Postagem obj;
		try {
			obj = new ObjectMapper().readValue(postagem, Postagem.class);
			obj.setFotoUrl(postagemImagem.getBytes());
			obj = service.update(obj);
			return ResponseEntity.ok().body(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Este método altera um postagem esperando receber dois parâmetros no Request:
	 * artista: objeto postagem no formato de String JSON artistaImagem: File
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Postagem> insert(@Valid @RequestBody Postagem obj, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Postagem> update(@Valid @RequestBody Postagem obj, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		obj = service.update(obj);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Postagem> update(String postagem,
			@RequestParam("postagemImagem") MultipartFile postagemImagem) {
		Postagem obj;
		try {
			obj = new ObjectMapper().readValue(postagem, Postagem.class);
			obj.setFotoUrl(postagemImagem.getBytes());
			obj = service.update(obj);
			return ResponseEntity.ok().body(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/findByUsuario/{idUsuario}", method = RequestMethod.GET)
	public ResponseEntity<Collection<Postagem>> findByUsuario(@PathVariable Integer idUsuario) {
		Usuario obj = new Usuario();
		obj.setId(idUsuario);
		Collection<Postagem> collection = service.findByUsuario(obj);
		return ResponseEntity.ok().body(collection);
	}

}

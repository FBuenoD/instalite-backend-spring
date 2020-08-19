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

import edu.ifes.ci.si.dw.intalite.model.Usuario;
import edu.ifes.ci.si.dw.intalite.services.UsuarioService;
import edu.ifes.ci.si.dw.intalite.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Usuario>> findAll() {
		Collection<Usuario> collection = service.findAll();
		return ResponseEntity.ok().body(collection);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable Integer id) {
		Usuario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	/*
	 * Este método insere um usuario esperando receber dois parâmetros no Request:
	 * artista: objeto usuario no formato de String JSON artistaImagem: File
	 */
	@RequestMapping(value = "/insertfile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Usuario> insertfile(@RequestParam("usuario") String usuario,
			@RequestParam("usuarioImagem") MultipartFile usuarioImagem) {
		Usuario obj;
		try {
			obj = new ObjectMapper().readValue(usuario, Usuario.class);
			obj.setFotoPerfil(usuarioImagem.getBytes());
			obj = service.insert(obj);
			return ResponseEntity.ok().body(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Este método insere um usuario esperando receber dois parâmetros no Request:
	 * artista: objeto usuario no formato de String JSON artistaImagem: File
	 */
	@RequestMapping(value = "/updatefile/{id}", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Usuario> updatefile(String usuario,
			@RequestParam("usuarioImagem") MultipartFile usuarioImagem) {
		Usuario obj;
		try {
			obj = new ObjectMapper().readValue(usuario, Usuario.class);
			obj.setFotoPerfil(usuarioImagem.getBytes());
			obj = service.update(obj);
			return ResponseEntity.ok().body(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Este método altera um usuario esperando receber dois parâmetros no Request:
	 * artista: objeto usuario no formato de String JSON artistaImagem: File
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> insert(@Valid @RequestBody Usuario obj, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		obj = service.insert(obj);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> update(@Valid @RequestBody Usuario obj, BindingResult br) {
		if (br.hasErrors())
			throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		obj = service.update(obj);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Usuario> update(String usuario, @RequestParam("usuarioImagem") MultipartFile usuarioImagem) {
		Usuario obj;
		try {
			obj = new ObjectMapper().readValue(usuario, Usuario.class);
			obj.setFotoPerfil(usuarioImagem.getBytes());
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

}

package edu.ifes.ci.si.dw.intalite.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.dw.intalite.model.Comentario;
import edu.ifes.ci.si.dw.intalite.model.Postagem;
import edu.ifes.ci.si.dw.intalite.repositories.ComentarioRepository;
import edu.ifes.ci.si.dw.intalite.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.dw.intalite.services.exceptions.ObjectNotFoundException;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository repository;

	public Comentario findById(Integer id) {
		try {
			Comentario obj = repository.findById(id).get();
			return obj;
		} catch (NoSuchElementException e) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Comentario.class.getName());
		}
	}

	public Collection<Comentario> findAll() {
		return repository.findAll();
	}

	public Comentario insert(Comentario obj) {
		obj.setId(null);
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Comentario não foi(foram) preenchido(s): Cidade");
		}
	}

	public Comentario update(Comentario obj) {
		findById(obj.getId());
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Comentario não foi(foram) preenchido(s): Cidade");
		}
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir um Comentario associado a uma Postagem ou Usuario!");
		}
	}

	public Collection<Comentario> findByPostagem(Postagem postagem) {
		return repository.findByPostagem(postagem);
	}

}

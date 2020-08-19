package edu.ifes.ci.si.dw.intalite.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.dw.intalite.model.Postagem;
import edu.ifes.ci.si.dw.intalite.model.Usuario;
import edu.ifes.ci.si.dw.intalite.repositories.PostagemRepository;
import edu.ifes.ci.si.dw.intalite.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.dw.intalite.services.exceptions.ObjectNotFoundException;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository repository;

	public Postagem findById(final Integer id) {
		try {
			Postagem obj = repository.findById(id).get();
			return obj;
		} catch (NoSuchElementException e) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Postagem.class.getName());
		}
	}

	public Collection<Postagem> findAll() {
		return repository.findAll();
	}

	public Postagem insert(final Postagem obj) {
		obj.setId(null);
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Postagem não foi(foram) preenchido(s)");
		}
	}

	public Postagem update(final Postagem obj) {
		findById(obj.getId());
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Postagem não foi(foram) preenchido(s)");
		}
	}

	public void delete(final Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (final DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Postagem!");
		}
	}

	public Collection<Postagem> findByUsuario(Usuario usuario) {
		return repository.findByUsuario(usuario);
	}

}

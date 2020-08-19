package edu.ifes.ci.si.dw.intalite.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.dw.intalite.model.Usuario;
import edu.ifes.ci.si.dw.intalite.repositories.UsuarioRepository;
import edu.ifes.ci.si.dw.intalite.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.dw.intalite.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Usuario findById(final Integer id) {
		try {
			Usuario obj = repository.findById(id).get();
			return obj;
		} catch (NoSuchElementException e) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
		}
	}

	public Collection<Usuario> findAll() {
		return repository.findAll();
	}

	public Usuario insert(final Usuario obj) {
		obj.setId(null);
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Usuario não foi(foram) preenchido(s)");
		}
	}

	public Usuario update(final Usuario obj) {
		findById(obj.getId());
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Usuario não foi(foram) preenchido(s)");
		}
	}

	public void delete(final Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (final DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Usuario!");
		}
	}

}

package edu.ifes.ci.si.dw.intalite.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.dw.intalite.model.Postagem;
import edu.ifes.ci.si.dw.intalite.model.Usuario;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Integer> {

	@Transactional(readOnly = true)
	public Collection<Postagem> findByUsuario(Usuario usuario);

}

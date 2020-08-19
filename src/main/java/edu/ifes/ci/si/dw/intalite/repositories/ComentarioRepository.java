package edu.ifes.ci.si.dw.intalite.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.dw.intalite.model.Comentario;
import edu.ifes.ci.si.dw.intalite.model.Postagem;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

	@Transactional(readOnly = true)
	public Collection<Comentario> findByPostagem(Postagem postagem);

}

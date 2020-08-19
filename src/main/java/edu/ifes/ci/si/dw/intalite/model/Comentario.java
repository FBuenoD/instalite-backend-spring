package edu.ifes.ci.si.dw.intalite.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Entity
public class Comentario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 200)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 3, max = 200, message = "O campo deve ter pelo menos 3 e no maximo 200 letras")
	private String comentario;

	@NotNull(message = "Postagem deve ser preenchida")
	@ManyToOne
	@JoinColumn(name = "postagem_id")
	private Postagem postagem;

	@NotNull(message = "Usuario deve ser preenchida")
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@Column(length = 200)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 3, max = 200, message = "O campo deve ter pelo menos 3 e no maximo 200 letras")
	private String dataComentario;

}

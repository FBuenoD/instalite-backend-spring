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
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Entity
public class Postagem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 200)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 3, max = 200, message = "O campo deve ter pelo menos 3 e no maximo 200 letras")
	private String descricao;

	@NotNull(message = "Usuario deve ser preenchida")
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@Column(length = 100)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 1, max = 100, message = "O campo deve ter pelo menos 1 e no maximo 100 digitos")
	private float curtidas;

	@Column(length = 200)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 3, max = 200, message = "O campo deve ter pelo menos 3 e no maximo 200 letras")
	private String dataPost;

	@Size(min = 1, message = "Imagem do Perfil deve ser preenchida")
	@Lob
	@Type(type = "org.hibernate.type.ImageType") // Esta notação é necessário para funcionar no Postgresql
	private byte[] fotoUrl;

}

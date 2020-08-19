package edu.ifes.ci.si.dw.intalite.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 200)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 3, max = 200, message = "O campo deve ter pelo menos 3 e no maximo 200 letras")
	private String nome;

	@Column(length = 200)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 3, max = 200, message = "O campo deve ter pelo menos 3 e no maximo 200 letras")
	private String descricao;
	
	@Column(length = 200)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 3, max = 200, message = "O campo deve ter pelo menos 3 e no maximo 200 letras")
	private String email;
	
	@Column(length = 200)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 3, max = 200, message = "O campo deve ter pelo menos 3 e no maximo 200 letras")
	private String login;
	
	@Column(length = 200)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 3, max = 200, message = "O campo deve ter pelo menos 3 e no maximo 200 letras")
	private String senha;
		
    @Size(min=1, message = "Imagem do Perfil deve ser preenchida")
    @Lob
    @Type(type = "org.hibernate.type.ImageType") //Esta notação é necessário para funcionar no Postgresql
    private byte[] fotoPerfil;

}

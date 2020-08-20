package edu.ifes.ci.si.dw.intalite.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import edu.ifes.ci.si.dw.intalite.model.Comentario;
import edu.ifes.ci.si.dw.intalite.model.Postagem;
import edu.ifes.ci.si.dw.intalite.model.Usuario;
import edu.ifes.ci.si.dw.intalite.repositories.ComentarioRepository;
import edu.ifes.ci.si.dw.intalite.repositories.PostagemRepository;
import edu.ifes.ci.si.dw.intalite.repositories.UsuarioRepository;

@Service
public class _DBService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PostagemRepository postagemRepository;
	@Autowired
	private ComentarioRepository comentarioRepository;

	public void instantiateTestDatabase() throws ParseException, IOException {

		Usuario usuario1 = new Usuario(null, "Fernando Bueno", "Vivendo a vida!", "fernandobuenodansi@gmail.com",
				"fbuenod", "123456789",
				StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/perfil/perfil01.png")));
		Usuario usuario2 = new Usuario(null, "Jose Bueno", "Curtindo a vida!", "josebueno@gmail.com", "joseb",
				"123456789",
				StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/perfil/perfil02.png")));
		Usuario usuario3 = new Usuario(null, "Fabiana Bueno", "Correndo a vida!", "fabianabueno@gmail.com", "fabianab",
				"123456789",
				StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/perfil/perfil03.png")));
		Usuario usuario4 = new Usuario(null, "Gerson Dansi", "Trabalhando a vida!", "gersondansi@gmail.com", "gersond",
				"123456789",
				StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/perfil/perfil04.png")));

		Postagem postagem1 = new Postagem(null, "Que mundo Belo!", usuario1, 3, "2020-08-10",
				StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/postagem/img01.jpg")));
		Postagem postagem2 = new Postagem(null, "Que mundo Lindo!", usuario2, 5, "2020-08-12",
				StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/postagem/img02.jpg")));
		Postagem postagem3 = new Postagem(null, "Que mundo Grande!", usuario3, 6, "2020-08-13",
				StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/postagem/img03.jpg")));
		Postagem postagem4 = new Postagem(null, "Que mundo Escuro!", usuario4, 8, "2020-08-14",
				StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/postagem/img04.jpg")));
		Postagem postagem5 = new Postagem(null, "Que mundo Claro!", usuario1, 10, "2020-08-15",
				StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/postagem/img05.jpg")));
		Postagem postagem6 = new Postagem(null, "Que mundo Legal!", usuario4, 6, "2020-08-16",
				StreamUtils.copyToByteArray(getClass().getResourceAsStream("/static/images/postagem/img03.jpg")));

		Comentario comentario1 = new Comentario(null, "Que Foto Maneira", postagem1, usuario1, "2020-08-10");
		Comentario comentario2 = new Comentario(null, "Que Foto Legal", postagem1, usuario2, "2020-08-11");
		Comentario comentario3 = new Comentario(null, "Que Foto Chique", postagem2, usuario3, "2020-08-12");
		Comentario comentario4 = new Comentario(null, "Que Foto Louca", postagem2, usuario4, "2020-08-13");
		Comentario comentario5 = new Comentario(null, "Que Foto Doida", postagem3, usuario1, "2020-08-13");
		Comentario comentario6 = new Comentario(null, "Que Foto Maluca", postagem3, usuario2, "2020-08-14");
		Comentario comentario7 = new Comentario(null, "Que Foto Clara", postagem4, usuario3, "2020-08-14");
		Comentario comentario8 = new Comentario(null, "Que Foto Escura", postagem4, usuario4, "2020-08-15");
		Comentario comentario9 = new Comentario(null, "Que Foto Top", postagem5, usuario1, "2020-08-15");
		Comentario comentario10 = new Comentario(null, "Que Foto Zica", postagem5, usuario2, "2020-08-16");
		Comentario comentario11 = new Comentario(null, "Que Foto Louca", postagem6, usuario3, "2020-08-16");
		Comentario comentario12 = new Comentario(null, "Que Foto Legal", postagem6, usuario4, "2020-08-17");

		usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2, usuario3, usuario4));
		postagemRepository.saveAll(Arrays.asList(postagem1, postagem2, postagem3, postagem4, postagem5, postagem6));
		comentarioRepository.saveAll(Arrays.asList(comentario1, comentario2, comentario3, comentario4, comentario5,
				comentario6, comentario7, comentario8, comentario9, comentario10, comentario11, comentario12));

	}
}

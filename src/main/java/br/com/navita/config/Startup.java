package br.com.navita.config;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import br.com.navita.commons.utils.TokenUtils;
import br.com.navita.marca.dao.MarcaDAO;
import br.com.navita.marca.model.MarcaPatrimonio;
import br.com.navita.patrimonio.dao.PatrimonioDAO;
import br.com.navita.patrimonio.model.Patrimonio;
import br.com.navita.usuario.dao.UsuarioDAO;
import io.quarkus.runtime.StartupEvent;

/**
 * Para carregar dados iniciais do Banco
 */
@Singleton
public class Startup {

	@Inject
	UsuarioDAO usuarioDAO;

	@Inject
	MarcaDAO marcaDAO;

	@Inject
	PatrimonioDAO patrimonioDAO;

	public boolean isRodarStartUp = true;

	@Transactional
	public void carregarAdmin(@Observes StartupEvent evt) {
		if(!isRodarStartUp) {
			return;
		}
		if (usuarioDAO.encontrarUsuarioEmail("admin@email.org.br") == null) {
			usuarioDAO.novoUsuario("admin", "admin@email.org.br", TokenUtils.hash("admin"), "admin,user");
		}
	}

	@Transactional
	public void carregarTemplateMarcasEPatrimonios(@Observes StartupEvent evt) {
		if(!isRodarStartUp) {
			return;
		}
		List<MarcaPatrimonio> todos = marcaDAO.listAll();
		if (todos == null || todos.isEmpty()) {
			marcaDAO.persist(
				Arrays.asList(
					new MarcaPatrimonio(null, "Microsoft"), 
					new MarcaPatrimonio(null, "Dell"),
					new MarcaPatrimonio(null, "Apple"), 
					new MarcaPatrimonio(null, "AMD"),
					new MarcaPatrimonio(null, "Intel")));
		}
		List<Patrimonio> todosPat = patrimonioDAO.listAll();
		if (todosPat == null || todosPat.isEmpty()) {
			MarcaPatrimonio marca1 = marcaDAO.recuperarPorNome("Microsoft");
			MarcaPatrimonio marca2 = marcaDAO.recuperarPorNome("Apple");
			todosPat = Arrays.asList( 
				new Patrimonio("Windows 10 PRO", "Sistema Operacional", marca1),
				new Patrimonio("Windows 10 PRO", "Sistema Operacional", marca1),
				new Patrimonio("Office 360", "Licença Microsoft Office 360", marca1), 
				new Patrimonio("Office 360", "Licença Microsoft Office 360", marca1),
				new Patrimonio("Macbook Pro 10", "Macbook Pro 10", marca2),
				new Patrimonio("AppleWatch", "AppleWatch", marca2)
				);
			Integer nroTombo = 0;
			for(Patrimonio pat : todosPat) {
				pat.gerarNumeroTombo(++nroTombo);
			}

			patrimonioDAO.persist(todosPat);
		}

	}
}

package br.com.navita.usuario.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.navita.commons.excecoes.ExcecaoGeral;
import br.com.navita.commons.utils.TokenUtils;
import br.com.navita.usuario.dao.UsuarioDAO;
import br.com.navita.usuario.model.Usuario;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;

@RequestScoped
public class UsuarioService {

	@Inject
	UsuarioValidacaoService validacao;

	@Inject
	UsuarioDAO usuarioDAO;

	public String login(String email, String senha) throws ExcecaoGeral {
		validacao.validarLogin(email, senha);

		Usuario usu = usuarioDAO.encontrarUsuarioEmail(email);
		if(usu == null) {
			throw new ExcecaoGeral("Usuário ainda não cadastrado");
		}
		if (TokenUtils.verifyBCryptPassword(usu.getSenha(), senha)) {
			return TokenUtils.gerarToken(usu);
		}
		throw new ExcecaoGeral(2, "Senha inválida!");
	}

	@Transactional
	public String cadastrarNovoUsuario(Usuario novo) throws ExcecaoGeral {
		return cadastrarNovoUsuario(novo, "user");
	}

	@Transactional
	public String cadastrarNovoAdmin(Usuario novo) throws ExcecaoGeral {
		return cadastrarNovoUsuario(novo, "admin,user");
	}

	@Transactional
	private String cadastrarNovoUsuario(Usuario novo, String papeis) throws ExcecaoGeral {
		validacao.validarNovoUsuario(novo);
		
		Usuario usu = usuarioDAO.encontrarUsuarioEmail(novo.getEmail());
		if(usu != null) {
			throw new ExcecaoGeral(2, "Usuário com e-mail já cadastrado - utilize outro e-mail para cadastro");
		}

		usu = new Usuario(novo.getNome(), novo.getEmail(), TokenUtils.hash(novo.getSenha()), papeis);
		usuarioDAO.persist(usu);

		return TokenUtils.gerarToken(usu);
	}

	public List<Usuario> consultarUsuarioPorEmail(String email) throws ExcecaoGeral {
		validacao.validarEmailSimplesParaOperacao(email);

		return usuarioDAO.list("email LIKE :termoEmail", Parameters.with("termoEmail", "%" + email + "%"));
	}

	public List<Usuario> consultarUsuarioPorNome(String nome) throws ExcecaoGeral {
		validacao.validarNomeSimplesParaOperacao(nome);

		return usuarioDAO.list("nome LIKE :termoNome", Parameters.with("termoNome", "%" + nome + "%"));
	}

	public List<Usuario> consultarTodos() {
		return usuarioDAO.listAll(Sort.ascending("email"));
	}

}

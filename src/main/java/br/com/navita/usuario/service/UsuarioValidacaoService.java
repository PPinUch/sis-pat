package br.com.navita.usuario.service;

import static br.com.navita.commons.utils.GeralUtils.isNullOrBlank;

import java.util.regex.Pattern;

import javax.enterprise.context.RequestScoped;

import br.com.navita.commons.excecoes.ExcecaoGeral;
import br.com.navita.usuario.model.Usuario;
import br.com.navita.usuario.vo.requisicao.UsuarioCadastroVO;
import br.com.navita.usuario.vo.requisicao.UsuarioLoginVO;
import br.com.navita.usuario.vo.requisicao.UsuarioParametroPesquisaVO;

@RequestScoped
public class UsuarioValidacaoService {

	public static final int TAMANHO_MIN_SENHA = 3;

	// -------------- VALIDACOES DE LOGIN -------------- \\
	public void validarUsuarioLoginVO(UsuarioLoginVO vo) throws ExcecaoGeral {
		if(vo == null) {
			throw new ExcecaoGeral("Ocorreu um erro na transmissão dos dados de login");
		}
	}

	public void validarLogin(String email, String senha) throws ExcecaoGeral {
		if( isNullOrBlank(email) ) {
			throw new ExcecaoGeral(1, "Email não pode ser vazio");
		}
		if( isNullOrBlank(senha) ) {
			throw new ExcecaoGeral(1, "Senha não pode ser vazia");
		}
	}

	// -------------- VALIDACOES DE CADASTRO -------------- \\
	public void validarUsuarioCadastroVO(UsuarioCadastroVO vo) throws ExcecaoGeral {
		if(vo == null) {
			throw new ExcecaoGeral("Ocorreu um erro na transmissão do novo registro de usuário");
		}
	}

	public void validarNovoUsuario(Usuario novo) throws ExcecaoGeral {
		if(novo == null) {
			throw new ExcecaoGeral("Houve um problema no recebimento das informações de novo usuário");
		}
		if( isNullOrBlank(novo.getNome()) ) {
			throw new ExcecaoGeral(2, "Nome do novo usuário não pode ser vazio");
		}
		if( isNullOrBlank(novo.getEmail()) ) {
			throw new ExcecaoGeral(2, "Informação de e-mail é obrigatória");
		}
		if( !Pattern.matches("\\b(\\w[\\._-]*)+@(\\w[\\._-]*)+\\w+\\b", novo.getEmail()) ) {
			throw new ExcecaoGeral(2, "Formato de e-mail não é aceito: "+novo.getEmail());
		}
		if( isNullOrBlank(novo.getSenha()) ) {
			throw new ExcecaoGeral(1, "Senha não pode ser vazia");
		}
		if(novo.getSenha().length() < TAMANHO_MIN_SENHA) {
			throw new ExcecaoGeral(2, "Senha curta demais. Tamanho mínimo aceito: "+TAMANHO_MIN_SENHA);
		}
	}

	// -------------- VALIDACOES DE CONSULTA -------------- \\
	private void validarUsuarioParametroPesquisaVO(UsuarioParametroPesquisaVO param) throws ExcecaoGeral {
		if(param == null) {
			throw new ExcecaoGeral("Ocorreu um erro na transmissão do parâmetro de pesquisa");
		}
	}

	public void validarUsuarioParametroPesquisaVOEmail(UsuarioParametroPesquisaVO param) throws ExcecaoGeral {
		validarUsuarioParametroPesquisaVO(param);
		validarEmailSimplesParaOperacao(param.getEmail());
	}

	public void validarUsuarioParametroPesquisaVONome(UsuarioParametroPesquisaVO param) throws ExcecaoGeral {
		validarUsuarioParametroPesquisaVO(param);
		validarNomeSimplesParaOperacao(param.getNome());
	}

	// -------------- VALIDACOES GERAIS -------------- \\
	public void validarEmailSimplesParaOperacao(String email) throws ExcecaoGeral {
		if( isNullOrBlank(email) ) {
			throw new ExcecaoGeral(1, "Não é possível executar esta operação sem ao menos uma fração do e-mail");
		}
	}

	public void validarNomeSimplesParaOperacao(String nome) throws ExcecaoGeral {
		if( isNullOrBlank(nome) ) {
			throw new ExcecaoGeral(1, "Não é possível executar esta operação sem ao menos uma fração do nome");
		}
	}
}

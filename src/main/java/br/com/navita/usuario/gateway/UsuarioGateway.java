package br.com.navita.usuario.gateway;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import br.com.navita.commons.excecoes.ExcecaoGeral;
import br.com.navita.usuario.service.UsuarioService;
import br.com.navita.usuario.service.UsuarioValidacaoService;
import br.com.navita.usuario.vo.requisicao.UsuarioCadastroVO;
import br.com.navita.usuario.vo.requisicao.UsuarioLoginVO;
import br.com.navita.usuario.vo.requisicao.UsuarioParametroPesquisaVO;
import br.com.navita.usuario.vo.resposta.UsuarioMsgTokenVO;
import br.com.navita.usuario.vo.resposta.UsuarioMultiRespostaVO;

@RequestScoped
public class UsuarioGateway {
	
	@Inject
	private UsuarioService servico;

	@Inject
	private UsuarioValidacaoService validacao;

	@Inject
	Logger log;

	private void gravarLog(ExcecaoGeral e) {
		if(e.isGrave()){
			log.error(e.getMessage(), e);
		}
	}

	public UsuarioMsgTokenVO login(UsuarioLoginVO login) {
		try {
			validacao.validarUsuarioLoginVO(login);
			return new UsuarioMsgTokenVO("Login efetuado com sucesso", 
				servico.login(login.getEmail(), login.getSenha()));
		} catch (ExcecaoGeral e) {
			gravarLog(e);
			return new UsuarioMsgTokenVO("ERRO DE LOGIN: "+e.getMessage(), "-");
		}
	}

	public UsuarioMsgTokenVO cadastrarNovoUsuario(UsuarioCadastroVO novo) {
		try {
			validacao.validarUsuarioCadastroVO(novo);
			return new UsuarioMsgTokenVO("Usuario cadastrado com sucesso!", 
				servico.cadastrarNovoUsuario(novo.toModel()) );
		} catch (ExcecaoGeral e) {
			gravarLog(e);
			return new UsuarioMsgTokenVO("ERRO DURANTE O CADASTRO DE NOVO USUÁRIO: "+e.getMessage(), "-");
		}
	}

	public UsuarioMsgTokenVO cadastrarNovoAdmin(UsuarioCadastroVO novo) {
        try {
            validacao.validarUsuarioCadastroVO(novo);
		    return new UsuarioMsgTokenVO("Administrador cadastrado com sucesso!", 
                servico.cadastrarNovoAdmin(novo.toModel()) );
        } catch (ExcecaoGeral e) {
            gravarLog(e);
            return new UsuarioMsgTokenVO("ERRO DURANTE O CADASTRO DE NOVO USUÁRIO: "+e.getMessage(), "-");
        }
    }

	public UsuarioMultiRespostaVO consultarPorEmail(UsuarioParametroPesquisaVO param) {
		try {
			validacao.validarUsuarioParametroPesquisaVOEmail(param);
			return new UsuarioMultiRespostaVO()
				.loadDadosAndReturn(servico.consultarUsuarioPorEmail(param.getEmail()) );
		} catch (ExcecaoGeral e) {
			gravarLog(e);
			return new UsuarioMultiRespostaVO(e.getMessage());
		}
	}

	public UsuarioMultiRespostaVO consultarPorNome(UsuarioParametroPesquisaVO param) {
		try {
			validacao.validarUsuarioParametroPesquisaVONome(param);
			return new UsuarioMultiRespostaVO()
				.loadDadosAndReturn( servico.consultarUsuarioPorNome(param.getNome()) );
		} catch (ExcecaoGeral e) {
			gravarLog(e);
			return new UsuarioMultiRespostaVO(e.getMessage());
		}
	}

	public UsuarioMultiRespostaVO consultarTodos() {
		return new UsuarioMultiRespostaVO()
				.loadDadosAndReturn( servico.consultarTodos() );
	}
}

package br.com.navita.marca.gateway;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import br.com.navita.commons.Mensagem;
import br.com.navita.commons.excecoes.ExcecaoGeral;
import br.com.navita.marca.service.MarcaService;
import br.com.navita.marca.service.MarcaValidacaoService;
import br.com.navita.marca.vo.requisicao.MarcaAlteracaoVO;
import br.com.navita.marca.vo.requisicao.MarcaCadastroVO;
import br.com.navita.marca.vo.requisicao.MarcaConsultaVO;
import br.com.navita.marca.vo.requisicao.MarcaExclusaoVO;
import br.com.navita.marca.vo.resposta.MarcaMultiRespostaVO;
import br.com.navita.marca.vo.resposta.MarcaRespostaVO;

@RequestScoped
public class MarcaGateway {

	@Inject
	Logger log;
    
    @Inject
    MarcaService servico;

	@Inject
	MarcaValidacaoService validacao;

	private void gravarLog(ExcecaoGeral e) {
		if(e.isGrave()){
			log.error(e.getMessage(), e);
		}
	}

    public Mensagem cadastrarMarca(MarcaCadastroVO marca) {
        try {
			validacao.validarMarcaCadastroVO(marca);
            servico.cadastrarMarca(marca.toModel());
        } catch (ExcecaoGeral e) {
			gravarLog(e);
            return new Mensagem("Erro durante o cadastro: "+e.getMessage());
        }

        return new Mensagem("Marca de patrimônio registrado com sucesso");
    }

    public Mensagem alterarMarcaPatrimonio(MarcaAlteracaoVO alt) {
		try {
			validacao.validarMarcaAlteracaoVO(alt);
			servico.alterarMarcaPatrimonio(alt.getId(), alt.getNovoNome());
		} catch (ExcecaoGeral e) {
			gravarLog(e);
			return new Mensagem("Erro durante a alteração: "+e.getMessage());
		}

		return new Mensagem("Atualização de marca de patrimônio realizada com sucesso");
    }

	public Mensagem excluirMarca(MarcaExclusaoVO paramExclVO) {
		try {
			validacao.validarParamExclusaoMarca(paramExclVO);
			servico.excluirMarca(paramExclVO.getIdMarca());
		} catch (ExcecaoGeral e) {
			gravarLog(e);
			return new Mensagem("Erro durante a exclusão: "+e.getMessage());
		}

		return new Mensagem("Marca de patrimônio com o ID "+paramExclVO.getIdMarca()+" excluído com sucesso");
	}

	public MarcaRespostaVO recuperarPorId(MarcaConsultaVO param) {
		try {
			validacao.validarParametroConsulta(param);
			return new MarcaRespostaVO()
				.loadWithMsg(servico.recuperarPorId(param.getId()),
				"Consulta por ID realizada com sucesso") ;
		} catch (ExcecaoGeral e) {
			gravarLog(e);
			return new MarcaRespostaVO(e.getMessage());
		}
	}

	public MarcaRespostaVO recuperarPorNomeExato(MarcaCadastroVO paramNome) {
		try {
			return new MarcaRespostaVO()
				.loadWithMsg(servico.recuperarPorNomeExato(paramNome.getNome()), 
					"Consulta por NOME realizada com sucesso");
		} catch (ExcecaoGeral e) {
			gravarLog(e);
			return new MarcaRespostaVO(e.getMessage());
		}
	}

	public MarcaMultiRespostaVO recuperarTodasMarcas() {
		return new MarcaMultiRespostaVO().loadDadosAndReturn( servico.recuperarTodasMarcas() );
	}
}

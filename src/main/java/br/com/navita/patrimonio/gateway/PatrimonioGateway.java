package br.com.navita.patrimonio.gateway;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import br.com.navita.commons.Mensagem;
import br.com.navita.commons.excecoes.ExcecaoGeral;
import br.com.navita.patrimonio.service.PatrimonioService;
import br.com.navita.patrimonio.service.PatrimonioValidacaoService;
import br.com.navita.patrimonio.vo.requisicao.PatrimonioAlteracaoVO;
import br.com.navita.patrimonio.vo.requisicao.PatrimonioCadastroVO;
import br.com.navita.patrimonio.vo.requisicao.PatrimonioConsultaVO;
import br.com.navita.patrimonio.vo.requisicao.PatrimonioExclusaoVO;
import br.com.navita.patrimonio.vo.resposta.PatrimonioMultiRespostaVO;
import br.com.navita.patrimonio.vo.resposta.PatrimonioRespostaVO;

@RequestScoped
public class PatrimonioGateway {

	@Inject
	private PatrimonioService servico;

	@Inject
	private PatrimonioValidacaoService validacao;

	@Inject
	Logger log;

	private void gravarLog(ExcecaoGeral e) {
		if(e.isGrave()){
			log.error(e.getMessage(), e);
		}
	}

	public Mensagem cadastrarPatrimonio(PatrimonioCadastroVO patrimonio) {
		try {
			validacao.validarPatrimonioCadastroVO(patrimonio);
			servico.cadastrarPatrimonio(patrimonio.toModel());
			return new Mensagem("Patrimônio cadastrado com sucesso");
		} catch (ExcecaoGeral e) {
			gravarLog(e);
			return new Mensagem("ERRO DURANTE O CADASTRO DE PATRIMÔNIO: " + e.getMessage());
		}
	}

	public Mensagem alterarPatrimonio(PatrimonioAlteracaoVO patrimonio) {
		try {
			validacao.validarPatrimonioAlteracaoVO(patrimonio);
			servico.alterarPatrimonio(patrimonio.getNroTombo(), patrimonio.toModel());
			return new Mensagem("Patrimônio ["+patrimonio.getNroTombo()+"] alterado com sucesso");
		} catch (ExcecaoGeral e) {
			gravarLog(e);
			return new Mensagem("ERRO DURANTE ALTERAÇÃO DE PATRIMÔNIO: " + e.getMessage());
		}
	}

	public Mensagem excluirPatrimonio(PatrimonioExclusaoVO paramExclusao) {
		try {
			validacao.validarPatrimonioExclusaoVO(paramExclusao);
			servico.excluirPatrimonio(paramExclusao.getNroTombo());
			return new Mensagem("Patrimônio " + paramExclusao.getNroTombo() + " excluído com sucesso");
		} catch (ExcecaoGeral e) {
			gravarLog(e);
			return new Mensagem("ERRO DURANTE EXCLUSÃO DE PATRIMÔNIO: " + e.getMessage());
		}
	}

	public PatrimonioRespostaVO consultarPorNroTombo(PatrimonioConsultaVO paramConsulta) {
		try {
			validacao.validarPatrimonioConsultaVO(paramConsulta);
			return new PatrimonioRespostaVO().loadWithMsg(servico.consultarPorNroTombo(paramConsulta.getNroTombo()),
				"Consulta realizada com sucesso");
		} catch (ExcecaoGeral e) {
			gravarLog(e);
			return new PatrimonioRespostaVO(e.getMessage());
		}
	}

	public PatrimonioMultiRespostaVO consultarPorIdMarca(PatrimonioConsultaVO paramConsulta) {
		try {
			validacao.validarPatrimonioConsultaVO(paramConsulta);
			return new PatrimonioMultiRespostaVO().loadDadosAndReturn(servico.consultarPorMarca(paramConsulta.getIdMarca()),
				"Consulta realizada com sucesso");
		} catch (ExcecaoGeral e) {
			gravarLog(e);
			return new PatrimonioMultiRespostaVO(e.getMessage());
		}
	}

	public PatrimonioMultiRespostaVO consultarTodos() {
		return new PatrimonioMultiRespostaVO().loadDadosAndReturn(servico.consultarTodos(),
			"Consulta realizada com sucesso");
	}
}

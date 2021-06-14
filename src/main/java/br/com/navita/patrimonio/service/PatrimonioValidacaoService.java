package br.com.navita.patrimonio.service;

import static br.com.navita.commons.utils.GeralUtils.isNullOrBlank;

import javax.enterprise.context.RequestScoped;

import br.com.navita.commons.excecoes.ExcecaoGeral;
import br.com.navita.marca.model.MarcaPatrimonio;
import br.com.navita.patrimonio.model.Patrimonio;
import br.com.navita.patrimonio.vo.requisicao.PatrimonioAlteracaoVO;
import br.com.navita.patrimonio.vo.requisicao.PatrimonioCadastroVO;
import br.com.navita.patrimonio.vo.requisicao.PatrimonioConsultaVO;
import br.com.navita.patrimonio.vo.requisicao.PatrimonioExclusaoVO;

@RequestScoped
public class PatrimonioValidacaoService {
	
	// ------------- VALIDACOES DE CADASTRO ------------- \\
	public void validarPatrimonioCadastroVO(PatrimonioCadastroVO cadastroVO) throws ExcecaoGeral {
		if(cadastroVO == null) {
			throw new ExcecaoGeral("Ocorreu um erro na comunicação dos dados do novo patrimônio");
		}
	}

	public void validarPatrimonioInsercao(Patrimonio patrimonio) throws ExcecaoGeral {
		if(patrimonio == null) {
			throw new ExcecaoGeral("Ocorreu um erro na comunicação dos dados do novo patrimônio");
		}
		validarNomeParaOperacao(patrimonio.getNome());
		validarMarcaPatrimonioParaOperacao(patrimonio.getMarca());
	}

	// ------------- VALIDACOES DE ALTERACAO ------------- \\
	public void validarPatrimonioAlteracaoVO(PatrimonioAlteracaoVO alteracao) throws ExcecaoGeral {
		if(alteracao == null) {
			throw new ExcecaoGeral("Ocorreu um erro na comunicação dos dados para alteração patrimônio");
		}
	}

	public void validarPatrimonioAlteracao(Patrimonio alteracao) throws ExcecaoGeral {
		if(alteracao == null) {
			throw new ExcecaoGeral("Ocorreu um erro na comunicação dos dados para alteração patrimônio");
		}
		validarNomeParaOperacao(alteracao.getNome());
		validarMarcaPatrimonioParaOperacao(alteracao.getMarca());
	}

	// ------------- VALIDACOES DE EXCLUSAO ------------- \\
	public void validarPatrimonioExclusaoVO(PatrimonioExclusaoVO exclusaoVO) throws ExcecaoGeral {
		if(exclusaoVO == null) {
			throw new ExcecaoGeral("Ocorreu um erro na comunicação dos dados para exclusão do patrimônio");
		}
	}

	// ------------- VALIDACOES DE CONSULTA ------------- \\
	public void validarPatrimonioConsultaVO(PatrimonioConsultaVO vo) throws ExcecaoGeral {
		if(vo == null) {
			throw new ExcecaoGeral("O parâmetro para realização de consultas não pode ser vazio");
		}
	}

	// ------------- VALIDACOES GERAIS ------------- \\
	public void validarNroTomboParaOperacao(String nroTombo) throws ExcecaoGeral {
		if( isNullOrBlank(nroTombo) ) {
			throw new ExcecaoGeral("Número do tombo não pode ser vazio para realizar esta operação");
		}
	}

	public void validarNomeParaOperacao(String nome) throws ExcecaoGeral {
		if( isNullOrBlank(nome) ) {
			throw new ExcecaoGeral("Nome não pode ser vazio para realizar esta operação");
		}
	}

	public void validarMarcaPatrimonioParaOperacao(MarcaPatrimonio marca) throws ExcecaoGeral {
		if(marca == null) {
			throw new ExcecaoGeral("Referência à marca do patrimônio é obrigatória para esta operação");
		}
		if(marca.getId() == null) {
			throw new ExcecaoGeral("Referência ao identificador da marca do patrimônio é obrigatória para esta operação");
		}
	}

}

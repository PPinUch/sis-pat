package br.com.navita.marca.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.navita.commons.excecoes.ExcecaoGeral;
import br.com.navita.marca.dao.MarcaDAO;
import br.com.navita.marca.model.MarcaPatrimonio;
import br.com.navita.marca.vo.requisicao.MarcaAlteracaoVO;
import br.com.navita.marca.vo.requisicao.MarcaCadastroVO;
import br.com.navita.marca.vo.requisicao.MarcaConsultaVO;
import br.com.navita.marca.vo.requisicao.MarcaExclusaoVO;

import static br.com.navita.commons.utils.GeralUtils.*;

@RequestScoped
public class MarcaValidacaoService {
	
	@Inject
	private MarcaDAO marcaDAO;

	// ------------- VALIDACOES CADASTRO ------------- \\
	public void validarMarcaCadastroVO(MarcaCadastroVO marca) throws ExcecaoGeral {
		if(marca == null) {
			throw new ExcecaoGeral("Houve um erro de comunicação ao transmitir dados da marca do patrimônio");
		}
		/* if( isNullOrBlank( marca.getNome() ) ) {
			throw new ExcecaoGeral(1, "O nome da marca do patrimônio não pode ser vazio");
		} */
		validarNomeMarcaPatrimonio(marca.getNome());
	}

	public void validarMarcaPatrimonio(MarcaPatrimonio marca) throws ExcecaoGeral {
		if(marca == null) {
			throw new ExcecaoGeral("Houve um erro de comunicação ao transmitir dados da marca do patrimônio");
		}
		validarNomeMarcaPatrimonio(marca.getNome());
	}

	public void validarMarcaParaInclusao(MarcaPatrimonio marca) throws ExcecaoGeral {
		validarMarcaPatrimonio(marca);
		MarcaPatrimonio marc = marcaDAO.recuperarPorNome(marca.getNome());
		if(marc != null) {
			throw new ExcecaoGeral(2, "Duas marcas não podem ser cadastradas com o mesmo nome");
		}
	}

	// ------------- VALIDACOES ALTERACAO ------------- \\
	public void validarMarcaAlteracaoVO(MarcaAlteracaoVO alt) throws ExcecaoGeral {
		if(alt == null) {
			throw new ExcecaoGeral("Houve um erro durante comunicação dos dados da marca do patrimônio");
		}
	}

	public void validarInfoAlteracaoMarcaPatrimonio(Long id, String nome) throws ExcecaoGeral {
		if(id == null || id < 1) {
			throw new ExcecaoGeral(1, "É necessário um ID de referência para realizar a alteração");
		}
		if( isNullOrBlank(nome) ) {
			throw new ExcecaoGeral(1, "O nome da marca de patrimônio não pode ser vazio");
		}
		MarcaPatrimonio marca = marcaDAO.recuperarPorId(id);
		if(marca == null) {
			throw new ExcecaoGeral(1, 
				"Não há marca de patrimônio para alteração: ["+id+"] ");
		}
		MarcaPatrimonio verNome = marcaDAO.recuperarPorNome(nome);
		if(verNome != null && !id.equals(verNome.getId()) ) {
			throw new ExcecaoGeral(2, 
				"Não é possível utilizar este valor de nome para alteração - nome já existente em ["+verNome.getId()+"]");
		}
	}
	/*
	public void validacaoPrimariaDeAlteracao(MarcaAlteracaoVO alt) throws ExcecaoGeral {
		if(alt == null) {
			throw new ExcecaoGeral("Houve um erro durante comunicação dos dados da marca do patrimônio");
		}
		if(alt.getMarca() == null) {
			throw new ExcecaoGeral(1, "Não foi passado nenhum dado de marca de patrimônio para ser alterado");
		}
		if(alt.getMarca().getId() == null && (alt.getMarca().getNome() == null || alt.getMarca().getNome().isBlank()) ){
			throw new ExcecaoGeral(1, "É necessário ao menos um ID ou um Nome de referência para realizar a alteração");
		}
		if(alt.getNovoNome() == null || alt.getNovoNome().isBlank()) {
			throw new ExcecaoGeral(1, "O nome da marca de patrimônio não pode ser vazio");
		}
		if( alt.getNovoNome().equals(alt.getMarca().getNome()) ) {
			throw new ExcecaoGeral(1, "O novo nome da marca é igual ao existente - operação cancelada");
		}
	} //*/

	// ------------- VALIDACOES EXCLUSAO ------------- \\
	public void validarParamExclusaoMarca(MarcaExclusaoVO marcaExclVO) throws ExcecaoGeral {
		if(marcaExclVO == null) {
			throw new ExcecaoGeral("Houve um erro de comunicação ao transmitir dados para exclusão da marca do patrimônio");
		}
		validarIdMarcaPatrimonio(marcaExclVO.getIdMarca());
	}

	// ------------- VALIDACOES CONSULTA ------------- \\
	public void validarParametroConsulta(MarcaConsultaVO param) throws ExcecaoGeral {
		if(param == null) {
			throw new ExcecaoGeral("Houve um erro de comunicação ao transmitir dados do identificador da marca do patrimônio");
		}
	}

	// ------------- VALIDACOES GERAIS ------------- \\
	public void validarIdMarcaPatrimonio(Long idMarca) throws ExcecaoGeral {
		if(idMarca == null) {
			throw new ExcecaoGeral("Houve um erro de comunicação ao transmitir dados do identificador da marca do patrimônio");
		}
		if(idMarca < 1L) {
			throw new ExcecaoGeral(1, "O identificador da marca do patrimônio está inválido: "+idMarca);
		}
	}

	public void validarNomeMarcaPatrimonio(String nome) throws ExcecaoGeral {
		if( isNullOrBlank( nome ) ) {
			throw new ExcecaoGeral(1, "O nome da marca do patrimônio não pode ser vazio");
		}
	}
}

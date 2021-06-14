package br.com.navita.marca.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.navita.commons.excecoes.ExcecaoGeral;
import br.com.navita.marca.dao.MarcaDAO;
import br.com.navita.marca.model.MarcaPatrimonio;
import io.quarkus.panache.common.Parameters;

@RequestScoped
public class MarcaService {
	
	@Inject
	private MarcaDAO marcaDAO;

	@Inject
	private MarcaValidacaoService validacao;

	@Transactional
	public void cadastrarMarca(MarcaPatrimonio marca) throws ExcecaoGeral {
		validacao.validarMarcaParaInclusao(marca);
		marcaDAO.persist(marca);
	}

	@Transactional
	public void alterarMarcaPatrimonio(Long id, String novoNome) throws ExcecaoGeral {
		validacao.validarInfoAlteracaoMarcaPatrimonio(id, novoNome);
		marcaDAO.update("nome = :nome WHERE id = :id", 
			Parameters.with("nome", novoNome).and("id", id));
	}

	/**
	 * Alteração segura, levando em consideração duplicações
	 * @param alt
	 * @return
	 * @throws ExcecaoGeral
	 */
	/*@Transactional
	public void alterarMarcaPatrimonio(MarcaAlteracaoVO alt) throws ExcecaoGeral {
		validacao.validacaoPrimariaDeAlteracao(alt);
		MarcaPatrimonio marca;
		String updateQuery;
		Parameters parameters;
		if(alt.getMarca().getId() != null) {
			marca = marcaDAO.recuperarPorId(alt.getMarca().getId());
			updateQuery = "nome = :nome WHERE id = :id";
			parameters = Parameters.with("nome", alt.getNovoNome())
				.and("id", alt.getMarca().getId());
		}
		else {
			marca = marcaDAO.recuperarPorNome(alt.getMarca().getNome());
			updateQuery = "nome = :nome WHERE nome = :par_nome";
			parameters = Parameters.with("nome", alt.getNovoNome())
				.and("par_nome", alt.getMarca().getNome());
		}

		if(marca == null) {
			throw new ExcecaoGeral(1, 
				"Não há marca de patrimônio com identificadores para alteração: ["+alt.getMarca().getId()+"] "
					+alt.getMarca().getNome());
		}
		if(marcaDAO.recuperarPorNome(alt.getNovoNome()) != null) {
			throw new ExcecaoGeral(2, 
				"Não é possível utilizar este valor de nome para alteraçõa - nome já existe em outro lugar: "+alt.getNovoNome());
		}
		marcaDAO.update(updateQuery, parameters);
	}//*/

	@Transactional
	public void excluirMarca(Long idExclusao) throws ExcecaoGeral {
		validacao.validarIdMarcaPatrimonio(idExclusao);
		MarcaPatrimonio alvo = recuperarPorId(idExclusao);

		if(alvo != null) {
			marcaDAO.delete(alvo);
		} else {
			throw new ExcecaoGeral(1, "Não existe marca de patrimônio para exclusão com o ID "+idExclusao);
		}
	}

	public MarcaPatrimonio recuperarPorId(Long id) throws ExcecaoGeral {
		validacao.validarIdMarcaPatrimonio(id);
		return marcaDAO.recuperarPorId(id.longValue());
	}

	public MarcaPatrimonio recuperarPorNomeExato(String nome) throws ExcecaoGeral {
		validacao.validarNomeMarcaPatrimonio(nome);
		return marcaDAO.recuperarPorNome(nome);
	}

	public List<MarcaPatrimonio> recuperarTodasMarcas() {
		return marcaDAO.findAll().list();
	}

	/* private void validarMarcaVO(MarcaVO marca) throws ExcecaoGeral {
		if(marca == null) {
			throw new ExcecaoGeral("Houve um erro de comunicação ao transmitir dados da marca do patrimônio");
		}
		if(marca.getNome() == null || marca.getNome().isBlank()) {
			throw new ExcecaoGeral("O nome da marca do patrimônio não pode ser vazio");
		}
	}

	private void validarMarcaParaInclusao(MarcaVO marca) throws ExcecaoGeral {
		validarMarcaVO(marca);
		MarcaPatrimonio marc = marcaDAO.recuperarPorNome(marca.getNome());
		if(marc != null) {
			throw new ExcecaoGeral("Duas marcas não podem ser cadastradas com o mesmo nome");
		}
	}

	private void validacaoPrimariaDeAlteracao(MarcaAlteracaoVO alt) throws ExcecaoGeral {
		if(alt == null) {
			throw new ExcecaoGeral("Houve um erro durante comunicação dos dados da marca do patrimônio");
		}
		if(alt.getMarca() == null) {
			throw new ExcecaoGeral("Não foi passado nenhum dado de marca de patrimônio para ser alterado");
		}
		if(alt.getMarca().getId() == null && (alt.getMarca().getNome() == null || alt.getMarca().getNome().isBlank()) ){
			throw new ExcecaoGeral("É necessário ao menos um ID ou um Nome de referência para realizar a alteração");
		}
		if(alt.getNovoNome() == null || alt.getNovoNome().isBlank()) {
			throw new ExcecaoGeral("O nome da marca de patrimônio não pode ser vazio");
		}
		if( alt.getNovoNome().equals(alt.getMarca().getNome()) ) {
			throw new ExcecaoGeral("O novo nome da marca é igual ao existente - operação cancelada");
		}
	}

	private void validarIdMarcaPatrimonio(Long idMarca) throws ExcecaoGeral {
		if(idMarca == null) {
			throw new ExcecaoGeral("Houve um erro de comunicação ao transmitir dados do identificador da marca do patrimônio");
		}
		if(idMarca < 1L) {
			throw new ExcecaoGeral("O identificador da marca do patrimônio está inválido: "+idMarca);
		}
	}  */
}

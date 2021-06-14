package br.com.navita.patrimonio.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.navita.commons.excecoes.ExcecaoGeral;
import br.com.navita.patrimonio.dao.PatrimonioDAO;
import br.com.navita.patrimonio.model.Patrimonio;
import io.quarkus.panache.common.Sort;

@RequestScoped
public class PatrimonioService {

	@Inject
	PatrimonioDAO patrimonioDAO;
	@Inject
	PatrimonioValidacaoService validacao;

	@Transactional
	public void cadastrarPatrimonio(Patrimonio patrimonio) throws ExcecaoGeral {
		validacao.validarPatrimonioInsercao(patrimonio);

		Integer ultimoNumTombo = recuperarUltimoRegistroNumericoNroTombo();

		patrimonio.gerarNumeroTombo(++ultimoNumTombo);
		patrimonioDAO.persist(patrimonio);
	}

	public Integer recuperarUltimoRegistroNumericoNroTombo() {
		Integer ultimoNumTombo = 0;
		Patrimonio ultimoRegistro = patrimonioDAO.recuperarUltimoRegistro();
		if (ultimoRegistro != null) {
			int tamNroTombo = ultimoRegistro.getNroTombo().length();
			String strNumTombo = ultimoRegistro.getNroTombo().substring(
					tamNroTombo - Patrimonio.QNT_DIGITOS_NUM_TOMBO,
					tamNroTombo);
			ultimoNumTombo = Integer.parseInt(strNumTombo);
		}
		return ultimoNumTombo;
	}

	@Transactional
	public void alterarPatrimonio(String nroTombo, Patrimonio patrimonio) throws ExcecaoGeral {
		validacao.validarPatrimonioAlteracao(patrimonio);
		validacao.validarNroTomboParaOperacao(nroTombo);

		Patrimonio existente = patrimonioDAO.recuperarPorNroTombo(nroTombo);
		if(existente == null) {
			throw new ExcecaoGeral("Não há patrimônio registrado com esse número de tombo para alteração");
		}

		existente.setDescricao(patrimonio.getDescricao());
		existente.setNome(patrimonio.getNome());
		existente.setMarca(patrimonio.getMarca());

		patrimonioDAO.persist(existente);
		/* patrimonioDAO.update("nome = :nome, descricao = :descricao, marca.id = :idMarca WHERE nroTombo = :nroTombo", 
			Parameters.with("nome", patrimonio.getNome())
				.and("descricao", patrimonio.getDescricao())
				.and("idMarca", patrimonio.getMarca().getId())
				.and("nroTombo", patrimonio.getNroTombo())
			); */
	}

	@Transactional
	public void excluirPatrimonio(String nroTombo) throws ExcecaoGeral {
		validacao.validarNroTomboParaOperacao(nroTombo);

		patrimonioDAO.delete("nroTombo", nroTombo);
	}

	public Patrimonio consultarPorNroTombo(String nroTombo) throws ExcecaoGeral {
		validacao.validarNroTomboParaOperacao(nroTombo);

		return patrimonioDAO.recuperarPorNroTombo(nroTombo);
	}

	public List<Patrimonio> consultarPorMarca(Long idMarca) throws ExcecaoGeral {
		if(idMarca == null) {
			throw new ExcecaoGeral("Identificador da marca é obrigatório para esta consulta");
		}

		return patrimonioDAO.recuperarPorMarca(idMarca);
	}

	public List<Patrimonio> consultarTodos() {
		return patrimonioDAO.listAll(Sort.descending("nroTombo"));
	}
}

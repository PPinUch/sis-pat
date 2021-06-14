package br.com.navita.patrimonio.vo.requisicao;

import javax.json.bind.annotation.JsonbTransient;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import br.com.navita.marca.model.MarcaPatrimonio;
import br.com.navita.patrimonio.model.Patrimonio;

@Schema(name = "Patrimônio - REQ - ALTERAÇÃO")
public class PatrimonioAlteracaoVO {

	private String nroTombo;
	private String nome;
	private String descricao;
	private Long idMarca;

	@JsonbTransient
	public Patrimonio toModel() {
		Patrimonio model = new Patrimonio(nome, descricao, new MarcaPatrimonio());
		model.getMarca().setId(idMarca);
		return model;
	}

	public PatrimonioAlteracaoVO() {
	}

	public PatrimonioAlteracaoVO(String nroTombo, String nome, String descricao, Long idMarca) {
		this.nroTombo = nroTombo;
		this.nome = nome;
		this.descricao = descricao;
		this.idMarca = idMarca;
	}

	public String getNroTombo() {
		return nroTombo;
	}

	public void setNroTombo(String nroTombo) {
		this.nroTombo = nroTombo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

}

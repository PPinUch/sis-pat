package br.com.navita.patrimonio.vo.resposta;

import javax.json.bind.annotation.JsonbTransient;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import br.com.navita.commons.Mensagem;
import br.com.navita.commons.utils.GeralUtils;
import br.com.navita.patrimonio.model.Patrimonio;

@Schema(name = "Patrimônio - RESP")
public class PatrimonioRespostaVO {
	
	@JsonbTransient
	private Patrimonio patrimonio = new Patrimonio();

	@JsonbTransient
	private Mensagem mensagem = new Mensagem("");

	@JsonbTransient
    public PatrimonioRespostaVO load(Patrimonio patrimonio) {
        setPatrimonio(patrimonio);
		return this;
    }

    @JsonbTransient
    public PatrimonioRespostaVO loadWithMsg(Patrimonio patrimonio, String msg) {
        this.mensagem = new Mensagem(msg);
        return load(patrimonio);
    }

	public PatrimonioRespostaVO() { }

	/* public PatrimonioRespostaVO(Patrimonio patrimonio) {
		super();
		setPatrimonio(patrimonio);
	} */

	public PatrimonioRespostaVO(String mensagem) {
		super();
		setMsg(mensagem);
	}

	@JsonbTransient
	public Patrimonio getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(Patrimonio patrimonio) {
		if(patrimonio == null) {
			this.mensagem = new Mensagem("Nenhum patrimônio encontrado");
			return;
		}
		this.patrimonio = patrimonio;
	}

	public String getNroTombo() {
		return this.patrimonio.getNroTombo();
	}

	public String getNome() {
		return this.patrimonio.getNome();
	}

	public String getDescricao() {
		return this.patrimonio.getDescricao();
	}

	public String getNomeMarca() {
		return this.patrimonio.getMarca() != null ? 
			this.patrimonio.getMarca().getNome() : null;
	}

	public Long getIdMarca() {
		return this.patrimonio.getMarca() != null ? 
			this.patrimonio.getMarca().getId() : null;
	}

	public String getMsg() {
		return mensagem.getMsg();
	}

	public void setMsg(String msg) {
		if(GeralUtils.isNullOrBlank(msg)) {
			return;
		}
		this.mensagem = new Mensagem(msg);
	}
}

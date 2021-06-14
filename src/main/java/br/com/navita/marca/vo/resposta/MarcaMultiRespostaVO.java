package br.com.navita.marca.vo.resposta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.bind.annotation.JsonbTransient;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import br.com.navita.commons.Mensagem;
import br.com.navita.marca.model.MarcaPatrimonio;

@Schema(name = "Marca Patrimônio - RESP - ARRAY")
public class MarcaMultiRespostaVO {
    
    private List<MarcaRespostaVO> dados = new ArrayList<>();
	private Integer qntItens = 0;
	private Mensagem msg = new Mensagem("");

    public MarcaMultiRespostaVO() {
    }

    public MarcaMultiRespostaVO(String msg) {
        this.msg = new Mensagem(msg);
    }

    public void loadDados(List<MarcaPatrimonio> rawData) {
		this.dados = rawData.stream()
			.map(marca -> new MarcaRespostaVO().load(marca))
			.collect(Collectors.toList());
		this.qntItens = this.dados.size();
		this.msg = new Mensagem("Consulta realizada com sucesso");
	}

    @JsonbTransient
	public MarcaMultiRespostaVO loadDadosAndReturn(List<MarcaPatrimonio> rawData) {
		loadDados(rawData);
		return this;
	}

    public List<MarcaRespostaVO> getDados() {
        return dados;
    }

    public void setDados(List<MarcaRespostaVO> dados) {
        this.dados = dados;
    }

    public Integer getQntItens() {
        return qntItens;
    }

    public void setQntItens(Integer qntItens) {
        this.qntItens = qntItens;
    }

    public Mensagem getMsg() {
        return msg;
    }

    public void setMsg(Mensagem msg) {
        this.msg = msg;
    }
}

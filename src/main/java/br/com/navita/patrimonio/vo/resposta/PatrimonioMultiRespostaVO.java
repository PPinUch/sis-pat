package br.com.navita.patrimonio.vo.resposta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.bind.annotation.JsonbTransient;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import br.com.navita.commons.Mensagem;
import br.com.navita.patrimonio.model.Patrimonio;

@Schema(name = "Patrimônio - RESP - ARRAY")
public class PatrimonioMultiRespostaVO {
    
    private List<PatrimonioRespostaVO> dados = new ArrayList<>();
	private Integer qntItens = 0;
	private Mensagem msg = new Mensagem("");

    public PatrimonioMultiRespostaVO() {
    }

    public PatrimonioMultiRespostaVO(String msg) {
        this.msg = new Mensagem(msg);
    }

    public void loadDados(List<Patrimonio> rawData) {
		this.dados = rawData.stream()
			.map(usu -> new PatrimonioRespostaVO().load(usu))
			.collect(Collectors.toList());
		this.qntItens = this.dados.size();
		this.msg = new Mensagem("Consulta realizada com sucesso");
	}

    @JsonbTransient
	public PatrimonioMultiRespostaVO loadDadosAndReturn(List<Patrimonio> rawData) {
		loadDados(rawData);
		return this;
	}
    @JsonbTransient
	public PatrimonioMultiRespostaVO loadDadosAndReturn(List<Patrimonio> rawData, String msg) {
        this.msg = new Mensagem(msg);
		return loadDadosAndReturn(rawData);
	}

    public List<PatrimonioRespostaVO> getDados() {
        return dados;
    }

    public void setDados(List<PatrimonioRespostaVO> dados) {
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

package br.com.navita.usuario.vo.resposta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.bind.annotation.JsonbTransient;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import br.com.navita.commons.Mensagem;
import br.com.navita.usuario.model.Usuario;

@Schema(name = "Usuário - RESP - ARRAY")
public class UsuarioMultiRespostaVO {
	
	private List<UsuarioRespostaVO> dados = new ArrayList<>();
	private Integer qntItens = 0;
	private Mensagem msg = new Mensagem("");

	public UsuarioMultiRespostaVO() {}

	public UsuarioMultiRespostaVO(String mensagem) {
		this.msg = new Mensagem(mensagem);
	}

	public void loadDados(List<Usuario> rawData) {
		this.dados = rawData.stream()
			.map(usu -> new UsuarioRespostaVO(usu))
			.collect(Collectors.toList());
		this.qntItens = this.dados.size();
		this.msg = new Mensagem(this.qntItens > 0 ? "Consulta realizada com sucesso" : "Sem retorno para os termos pesquisados");
	}

	@JsonbTransient
	public UsuarioMultiRespostaVO loadDadosAndReturn(List<Usuario> rawData) {
		loadDados(rawData);
		return this;
	}
 
	public List<UsuarioRespostaVO> getDados() {
		return dados;
	}
	public void setDados(List<UsuarioRespostaVO> dados) {
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

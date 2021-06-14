package br.com.navita.usuario.vo.resposta;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import br.com.navita.commons.Mensagem;

@Schema(name = "Usuário - RESP - TOKEN")
public class UsuarioMsgTokenVO {
    private Mensagem msg;

    private String token;

    public UsuarioMsgTokenVO() {    }

    public UsuarioMsgTokenVO(Mensagem msg, String token) {
        this.msg = msg;
        this.token = token;
    }

    public UsuarioMsgTokenVO(String msg, String token) {
        this.msg = new Mensagem(msg);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg != null ? msg.getMsg() : null;
    }

    public String getTimestamp() {
        return msg != null ? msg.getTimestampFormat() : null;
    }

    public void setMsg(Mensagem msg) {
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = new Mensagem(msg);
    }
}

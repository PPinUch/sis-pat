package br.com.navita.usuario.vo.resposta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import br.com.navita.commons.Mensagem;
import br.com.navita.commons.utils.GeralUtils;
import br.com.navita.usuario.model.Usuario;

@Schema(name = "Usuário - RESP")
public class UsuarioRespostaVO {
    
    @JsonbTransient
    private Usuario usuario = new Usuario();

    private Mensagem mensagem = new Mensagem("");

    public UsuarioRespostaVO(Usuario usuario) {
        super();
        setUsuario(usuario);
    }

    public UsuarioRespostaVO(Usuario usuario, String msg) {
        super();
        setUsuario(usuario);
        mensagem = new Mensagem(msg);
    }

    @JsonbTransient
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        if(usuario == null) {
            return;
        }
        this.usuario = usuario;
    }

    @JsonbTransient
    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public Long getId() {
        return this.usuario.getId();
    }

    public String getNome() {
        return this.usuario.getNome();
    }

    public String getEmail() {
        return this.usuario.getEmail();
    }

    public List<String> getPapeis() {
        if( GeralUtils.isNullOrBlank(this.usuario.getPapeis()) ) {
            return new ArrayList<>();
        }

        return Arrays.asList(this.usuario.getPapeis().split(","));
    }

    public String getMsg() {
        return mensagem.getMsg();
    }
}

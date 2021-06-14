package br.com.navita.usuario.vo.requisicao;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "Usuário - REQ - LOGIN")
public class UsuarioLoginVO {
    
    private String email;
    private String senha;

    public UsuarioLoginVO(){}
    
    public UsuarioLoginVO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

}

package br.com.navita.usuario.vo.requisicao;

import javax.json.bind.annotation.JsonbTransient;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import br.com.navita.usuario.model.Usuario;

@Schema(name = "Usuário - REQ - CADASTRO")
public class UsuarioCadastroVO {
    
    private String nome;
    private String email;
    private String senha;

    @JsonbTransient
    public Usuario toModel() {
        Usuario usu = new Usuario();
        usu.setEmail(email);
        usu.setNome(nome);
        usu.setSenha(senha);
        return usu;
    }
    
    public UsuarioCadastroVO() {}
    
    public UsuarioCadastroVO(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
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

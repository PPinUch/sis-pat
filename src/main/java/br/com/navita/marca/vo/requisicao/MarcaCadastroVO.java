package br.com.navita.marca.vo.requisicao;

import javax.json.bind.annotation.JsonbTransient;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import br.com.navita.marca.model.MarcaPatrimonio;

@Schema(name = "Marca Patrimônio - REQ - CADASTRO")
public class MarcaCadastroVO {
    
    private String nome;

    public MarcaCadastroVO() {
    }

    public MarcaCadastroVO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonbTransient
    public MarcaPatrimonio toModel() {
        return new MarcaPatrimonio(null, nome);
    }

}

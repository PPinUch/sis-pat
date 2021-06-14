package br.com.navita.marca.vo.requisicao;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "Marca Patrimônio - REQ - ALTERAÇÃO")
public class MarcaAlteracaoVO {
    
    private Long id;
    private String novoNome;

    public MarcaAlteracaoVO() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNovoNome() {
        return novoNome;
    }

    public void setNovoNome(String novoNome) {
        this.novoNome = novoNome;
    }

}

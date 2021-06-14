package br.com.navita.marca.vo.requisicao;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "Marca Patrimônio - REQ - CONSULTA")
public class MarcaConsultaVO {
    
    private Long id;

    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

package br.com.navita.marca.vo.requisicao;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "Marca Patrimônio - REQ - EXCLUSÃO")
public class MarcaExclusaoVO {
    
    private Long idMarca;

    public MarcaExclusaoVO() {
    }

    public MarcaExclusaoVO(Long idMarca) {
        this.idMarca = idMarca;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }

}

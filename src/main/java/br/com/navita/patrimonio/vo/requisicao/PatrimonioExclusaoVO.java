package br.com.navita.patrimonio.vo.requisicao;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "Patrimônio - REQ - EXCLUSÃO")
public class PatrimonioExclusaoVO {
    
    private String nroTombo;

    public String getNroTombo() {
        return nroTombo;
    }

    public void setNroTombo(String nroTombo) {
        this.nroTombo = nroTombo;
    }

    
}

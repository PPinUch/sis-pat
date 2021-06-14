package br.com.navita.patrimonio.vo.requisicao;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "Patrimônio - REQ - CONSULTA")
public class PatrimonioConsultaVO {
    
    private String nroTombo;
    private Long idMarca;
 
    public String getNroTombo() {
        return nroTombo;
    }
    public void setNroTombo(String nroTombo) {
        this.nroTombo = nroTombo;
    }
    public Long getIdMarca() {
        return idMarca;
    }
    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }

}

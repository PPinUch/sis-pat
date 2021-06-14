package br.com.navita.marca.vo.resposta;

import javax.json.bind.annotation.JsonbTransient;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import br.com.navita.commons.Mensagem;
import br.com.navita.marca.model.MarcaPatrimonio;

@Schema(name = "Marca Patrimônio - RESP")
public class MarcaRespostaVO {
    
    @JsonbTransient
    private MarcaPatrimonio marca = new MarcaPatrimonio();

    @JsonbTransient
    private Mensagem msg = new Mensagem("");

    @JsonbTransient
    public MarcaRespostaVO load(MarcaPatrimonio marca) {
        setMarca(marca);
        return this;
    }

    /**
     * Carrega um objeto do tipo MarcaPatrimonio com uma mensagem
     * @param marca
     * @param msg
     * @return
     */
    @JsonbTransient
    public MarcaRespostaVO loadWithMsg(MarcaPatrimonio marca, String msg) {
        this.msg = new Mensagem(msg);
        return load(marca);
    }

    public MarcaRespostaVO() { }

    public MarcaRespostaVO(String msg) {
        this.msg = new Mensagem(msg);
    }

    @JsonbTransient
    public MarcaPatrimonio getMarca() {
        return marca;
    }

    public String getNome() {
        return this.marca.getNome();
    }

    public Long getId() {
        return this.marca.getId();
    }

    public void setMarca(MarcaPatrimonio marca) {
        if(marca == null) {
            this.msg = new Mensagem("Nenhuma marca de patrimônio encontrada");
            return;
        }
        this.marca = marca;
    }

    public String getMsg() {
        return msg.getMsg();
    }

    public void setMsg(Mensagem msg) {
        this.msg = msg;
    }

}

package br.com.navita.patrimonio.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.navita.marca.model.MarcaPatrimonio;

@Entity
@Table(name = "tb_patrimonio")
public class Patrimonio {
    
    /* @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_patrimonio")
    private Long id; */

    @Id
	@Column(name = "nro_tombo", nullable = false, unique = true, length = 14)
    private String nroTombo;

    @Column(name = "nom_patrimonio", nullable = false, length = 150)
    private String nome;

    @Column(name = "dsc_patrimonio", columnDefinition = "TEXT NULL")
    private String descricao;

    @ManyToOne
    @JoinColumn(
        name = "id_marca", 
        foreignKey = @ForeignKey(
            name = "fk_id_marca"))
    private MarcaPatrimonio marca = new MarcaPatrimonio();

    @Transient
    @JsonbTransient
    private LocalDate refGeracaoNumeroPatrimonio = LocalDate.now();
    public static final String FORMATO_DATA_NUM_TOMBO = "yyyyMMdd";
    public static final int QNT_DIGITOS_NUM_TOMBO = 6;

    public Patrimonio() {
    }

    public Patrimonio(String nome, String descricao, MarcaPatrimonio marca) {
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
    }

    public void gerarNumeroTombo(Integer valor) {
        if(valor == null) {
            return;
        }
        refGeracaoNumeroPatrimonio = LocalDate.now();
        this.nroTombo = refGeracaoNumeroPatrimonio.format(DateTimeFormatter.ofPattern(FORMATO_DATA_NUM_TOMBO))
            + String.format("%0"+QNT_DIGITOS_NUM_TOMBO+"d", valor);
    }

    public String getNroTombo() {
        return nroTombo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public MarcaPatrimonio getMarca() {
        return marca;
    }

    public void setMarca(MarcaPatrimonio marca) {
        this.marca = marca;
    }

    
}

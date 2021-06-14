package br.com.navita.marca.model;

import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.navita.patrimonio.model.Patrimonio;

@Entity
@Table(name = "tb_marca_patrimonio")
public class MarcaPatrimonio {
    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_marca")
    private Long id;

    @Column(name = "nm_marca", nullable = false, length = 200, unique = true)
	private String nome;

    @JsonbTransient
    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
    private List<Patrimonio> patrimonios = new ArrayList<>();

    public MarcaPatrimonio() {}

    public MarcaPatrimonio(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

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

    public List<Patrimonio> getPatrimonios() {
        return patrimonios;
    }

    public void setPatrimonios(List<Patrimonio> patrimonios) {
        this.patrimonios = patrimonios;
    }

    
}

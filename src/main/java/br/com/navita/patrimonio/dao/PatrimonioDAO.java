package br.com.navita.patrimonio.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.navita.patrimonio.model.Patrimonio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

@ApplicationScoped
public class PatrimonioDAO implements PanacheRepository<Patrimonio> {
    
    public Patrimonio recuperarPorNroTombo(String nroTombo) {
        return find("nroTombo", nroTombo).firstResultOptional().orElse(null);
    }

    public List<Patrimonio> recuperarPorMarca(Long idMarca) {
        return list("marca.id", idMarca);
    }

    public Patrimonio recuperarUltimoRegistro() {
        return find("", Sort.descending("nroTombo")).firstResultOptional().orElse(null);
    }
}

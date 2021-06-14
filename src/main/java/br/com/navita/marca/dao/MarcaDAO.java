package br.com.navita.marca.dao;

import javax.enterprise.context.ApplicationScoped;

import br.com.navita.marca.model.MarcaPatrimonio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MarcaDAO implements PanacheRepository<MarcaPatrimonio> {
	
	public MarcaPatrimonio recuperarPorNome(String nome) {
		/* try {
			return find("nome", nome).singleResult();
		} catch (NoResultException nre) {
			return null;
		} */
		return find("nome", nome).singleResultOptional().orElse(null);
	}

	public MarcaPatrimonio recuperarPorId(Long id) {
		/* try {
			return findById(id);
		} catch (NoResultException nre) {
			return null;
		} */
		return findByIdOptional(id).orElse(null);
	}
}

package br.com.navita.usuario.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintViolationException;

import br.com.navita.usuario.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UsuarioDAO implements PanacheRepository<Usuario> {

    public void novoUsuario(Usuario usuario) throws ConstraintViolationException {
        persist(usuario);
    }

    public void novoUsuario(String nome, String email, String senha, String papeis)
            throws ConstraintViolationException {
        Usuario usuario = new Usuario(nome, email, senha, papeis);
        persist(usuario);
    }

    public Usuario encontrarUsuarioEmail(String email) {
        return find("email", email).singleResultOptional().orElse(null);
    }

    public List<Usuario> listarTodos() {
        return findAll().list();
    }

}

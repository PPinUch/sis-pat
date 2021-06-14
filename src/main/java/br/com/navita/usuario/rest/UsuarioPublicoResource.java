package br.com.navita.usuario.rest;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.navita.usuario.gateway.UsuarioGateway;
import br.com.navita.usuario.vo.requisicao.UsuarioCadastroVO;
import br.com.navita.usuario.vo.requisicao.UsuarioLoginVO;
import br.com.navita.usuario.vo.resposta.UsuarioMsgTokenVO;

@Path("/publico")
@PermitAll
@Tag(name = "2 - Usuário - Público")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioPublicoResource {

    @Inject
    UsuarioGateway gateway;

    @POST
    @Path("/login")
    @Tag(name = "1 - Login")
    @Consumes(MediaType.APPLICATION_JSON)
    public UsuarioMsgTokenVO logar(UsuarioLoginVO login) {
        return gateway.login(login);
    }

    @POST
    @Path("/novoUsuario")
    //@PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public UsuarioMsgTokenVO criarUsuario(UsuarioCadastroVO novo) {
        return gateway.cadastrarNovoUsuario(novo);
    }
}

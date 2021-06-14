package br.com.navita.usuario.rest;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.navita.usuario.gateway.UsuarioGateway;
import br.com.navita.usuario.vo.requisicao.UsuarioCadastroVO;
import br.com.navita.usuario.vo.requisicao.UsuarioParametroPesquisaVO;
import br.com.navita.usuario.vo.resposta.UsuarioMsgTokenVO;
import br.com.navita.usuario.vo.resposta.UsuarioMultiRespostaVO;

@Path("/admin")
@Tag(name = "3 - Usuário - Administrador", description = "Área do administrador")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("admin")
@SecurityRequirement(name = "validacao-token")
public class UsuarioAdminResource {
	
	@Inject
	UsuarioGateway gateway;

	@GET
	@Path("/consultar_todos_usuarios")
	public UsuarioMultiRespostaVO consultarTodosUsuarios() {
		return gateway.consultarTodos();
	}

	@POST
	@Path("/consultar_usuarios_por_email")
	public UsuarioMultiRespostaVO consultarUsuariosPorEmail(UsuarioParametroPesquisaVO param) {
		return gateway.consultarPorEmail(param);
	}

	@POST
	@Path("/consultar_usuarios_por_nome")
	public UsuarioMultiRespostaVO consultarUsuariosPorNome(UsuarioParametroPesquisaVO param) {
		return gateway.consultarPorNome(param);
	}

	@POST
	@Path("/criar_usuario_admin")
	public UsuarioMsgTokenVO criarUsuarioAdmin(UsuarioCadastroVO usuarioCadastroVO) {
		return gateway.cadastrarNovoAdmin(usuarioCadastroVO);
	}
}

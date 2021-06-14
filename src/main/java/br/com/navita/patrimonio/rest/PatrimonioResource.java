package br.com.navita.patrimonio.rest;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.navita.commons.Mensagem;
import br.com.navita.patrimonio.gateway.PatrimonioGateway;
import br.com.navita.patrimonio.vo.requisicao.PatrimonioAlteracaoVO;
import br.com.navita.patrimonio.vo.requisicao.PatrimonioCadastroVO;
import br.com.navita.patrimonio.vo.requisicao.PatrimonioConsultaVO;
import br.com.navita.patrimonio.vo.requisicao.PatrimonioExclusaoVO;
import br.com.navita.patrimonio.vo.resposta.PatrimonioMultiRespostaVO;
import br.com.navita.patrimonio.vo.resposta.PatrimonioRespostaVO;

@Path("/patrimonio")
@Tag(name = "Patrimonio", description = "Endpoints de Patrimônio")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({ "user", "admin" })
@SecurityRequirement(name = "validacao-token")
public class PatrimonioResource {

	@Inject
	PatrimonioGateway gateway;

	@POST
	@Path("/cadastrar")
	public Mensagem salvarNovoPatrimonio(PatrimonioCadastroVO patrimonio) {
		return gateway.cadastrarPatrimonio(patrimonio);
	}

	@POST
	@Path("/alterar")
	public Mensagem alterarPatrimonio(PatrimonioAlteracaoVO patrimonio) {
		return gateway.alterarPatrimonio(patrimonio);
	}

	@POST
	@Path("/excluir")
	public Mensagem excluirPatrimonio(PatrimonioExclusaoVO paramExclusao) {
		return gateway.excluirPatrimonio(paramExclusao);
	}

	@POST
	@Path("/consultar/nroTombo")
	public PatrimonioRespostaVO consultarPatrimonioPorNroTombo(PatrimonioConsultaVO param) {
		return gateway.consultarPorNroTombo(param);
	}

	@POST
	@Path("/consultar/idMarca")
	public PatrimonioMultiRespostaVO consultarPorIdMarca(PatrimonioConsultaVO param) {
		return gateway.consultarPorIdMarca(param);
	}

	@GET
	public PatrimonioMultiRespostaVO consultarTodos() {
		return gateway.consultarTodos();
	}
}

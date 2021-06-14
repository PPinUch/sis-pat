package br.com.navita.marca.rest;

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
import br.com.navita.marca.gateway.MarcaGateway;
import br.com.navita.marca.vo.requisicao.MarcaAlteracaoVO;
import br.com.navita.marca.vo.requisicao.MarcaCadastroVO;
import br.com.navita.marca.vo.requisicao.MarcaConsultaVO;
import br.com.navita.marca.vo.requisicao.MarcaExclusaoVO;
import br.com.navita.marca.vo.resposta.MarcaMultiRespostaVO;
import br.com.navita.marca.vo.resposta.MarcaRespostaVO;

@Path("/marca_patrimonio")
@Tag(name = "Marca Patrimonio", description = "Endpoints de Marca de Patrimônio")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({ "user", "admin" })
@SecurityRequirement(name = "validacao-token")
public class MarcaResource {
    
    @Inject
    MarcaGateway gateway;

    @POST
    @Path("/cadastrar")
    public Mensagem cadastrar(MarcaCadastroVO marcaVO) {
        return gateway.cadastrarMarca(marcaVO);
    }

    @POST
    @Path("/alterar")
    public Mensagem alterar(MarcaAlteracaoVO altVO) {
        return gateway.alterarMarcaPatrimonio(altVO);
    }

    @POST
    @RolesAllowed("admin")
    @Path("/excluir")
    public Mensagem excluir(MarcaExclusaoVO paramExclVO) {
        return gateway.excluirMarca(paramExclVO);
    }

    @POST
    @Path("/consultar/id")
    public MarcaRespostaVO consultarPorID(MarcaConsultaVO altVO) {
        return gateway.recuperarPorId(altVO);
    }

    @GET
    public MarcaMultiRespostaVO consultarTodos() {
        return gateway.recuperarTodasMarcas();
    }
}

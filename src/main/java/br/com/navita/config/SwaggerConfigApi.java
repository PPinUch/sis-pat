package br.com.navita.config;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;


@OpenAPIDefinition(
    info = @Info(
        title = "SIS-PAT - Gerenciador de Patrimônios",
        version = "1.0.0",
        contact = @Contact(
            name = "Pedro Pinheiro Uchoa"
        )
    )
)
public class SwaggerConfigApi extends Application {
}

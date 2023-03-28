package ms.spring.crudbasic.api.infra.configurations.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "CRUD BASIC",
                version = "1.0",
                description = "API to perform basic registrations",
                contact = @Contact(url = "https://sites.google.com/view/brunocfigueira", name = "Bruno Figueira", email = "brunocfigueirati@gmail.com")
        )
        /*
        tags = {
                @Tag(name = "Tag 1", description = "desc 1", externalDocs = @ExternalDocumentation(description = "docs desc"))
        },
        externalDocs = @ExternalDocumentation(description = "definition docs desc"),
        security = {
                @SecurityRequirement(name = "req 1", scopes = {"a", "b"})
        },
        servers = {
                @Server(
                        description = "server 1",
                        url = "http://foo",
                        variables = {
                                @ServerVariable(name = "var1", description = "var 1", defaultValue = "1", allowableValues = {"1", "2"})
                        })
        }*/
)
public class OpenApiConfiguration {
    @Bean
    public OpenAPI customOpenApi() {

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key", // this parameter is equals defined in Controller
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}

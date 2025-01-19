package oracle.alura.challenge.forohub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info().title("Documentation API Alquileres")
                         .version("1.0")
                         .description("Esta es una API para el manejo de alquileres de bicicletas")
                         .termsOfService("https://swagger.io/terms/")
                         .license(new License().name("Apache 2.0")
                                               .url("https://springdoc.org"));
    }
}

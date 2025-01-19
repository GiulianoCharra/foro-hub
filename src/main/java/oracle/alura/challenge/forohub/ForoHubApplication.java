package oracle.alura.challenge.forohub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class ForoHubApplication implements CommandLineRunner {
    private final Environment environment;

    public ForoHubApplication(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {
        SpringApplication.run(ForoHubApplication.class,
                              args
                             );
    }
    public void run(String... args) throws UnknownHostException {
        String port        = environment.getProperty("server.port",
                                                     "8080"
                                                    );
        String contextPath = environment.getProperty("server.servlet.context-path",
                                                     ""
                                                    );
        String hostname    = environment.getProperty("server.address",
                                                     InetAddress.getLocalHost()
                                                                .getHostName()
                                                    );
        String protocol    = environment.getProperty("server.ssl.enabled",
                                                     "false"
                                                    )
                                        .equals("true") ? "https" : "http";

        // Construcción dinámica del URL base
        String baseUrl = protocol + "://" + hostname + ":" + port + contextPath;

        System.out.println("\n========================");
        System.out.println("¡Bienvenido a ForoHub!");
        System.out.println("Accede a Swagger en: " + baseUrl + "/swagger-ui/index.html");
        System.out.println("La API está lista en: " + baseUrl);
        System.out.println("========================");
    }
}
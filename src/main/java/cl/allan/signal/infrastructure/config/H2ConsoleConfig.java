package cl.allan.signal.infrastructure.config;

import org.h2.server.web.JakartaWebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for the H2 Database console.
 * Enables the web interface to manage and inspect the in-memory database during development.
 */
@Configuration
public class H2ConsoleConfig {

    @Bean
    public ServletRegistrationBean<JakartaWebServlet> h2ConsoleServletRegistration() {
        ServletRegistrationBean<JakartaWebServlet> registrationBean =
                new ServletRegistrationBean<>(new JakartaWebServlet());
        registrationBean.addUrlMappings("/h2-console/*");
        return registrationBean;
    }
}

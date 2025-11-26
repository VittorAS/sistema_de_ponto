package com.system.electronic_point_app.config;

import org.h2.server.web.JakartaWebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2Config {

    @Bean
    public ServletRegistrationBean<JakartaWebServlet> h2ServletRegistration() {
        // Registra o Servlet do H2 manualmente (Em caso de Erro com o application.proprieties
        ServletRegistrationBean<JakartaWebServlet> registration = new ServletRegistrationBean<>(new JakartaWebServlet());
        // Define a URL de acesso
        registration.addUrlMappings("/h2-console/*");
        return registration;
    }
}
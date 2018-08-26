package com.peterholub.atm;


import com.peterholub.atm.httpsession.tomcatsessionmanager.CacheSessionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AtmApplication {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> sessionManagerCustomizer() {
        return server -> server.addContextCustomizers(context -> context.setManager(new CacheSessionManager()));
    }


    public static void main(String[] args) {
        SpringApplication.run(AtmApplication.class, args);
    }
}

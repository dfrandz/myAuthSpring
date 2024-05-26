package com.frandz.produit;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig{
    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
                .directory("./")  // Spécifiez le répertoire contenant le fichier .env
                .load();
    }
}
